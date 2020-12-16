package com.cybage.sonar.report.pdf.builder;

import com.cybage.sonar.report.pdf.entity.FileInfo;
import com.cybage.sonar.report.pdf.util.MetricKeys;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarqube.ws.Common.FacetValue;
import org.sonarqube.ws.Issues.Component;
import org.sonarqube.ws.Issues.SearchWsResponse;
import org.sonarqube.ws.Measures;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.issues.SearchRequest;
import org.sonarqube.ws.client.measures.ComponentTreeRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class FileInfoBuilder {

    public static final  String          FACET_FILES                      = "files";
    public static final  int             NUMBER_ISSUES_PER_PAGE           = 500;
    public static final  int             LIMIT                            = 10;
    public static final  String          S_METRIC                         = "metric";
    public static final  String          S_METRIC_PERIOD                  = "metricPeriod";
    public static final  String          S_NAME                           = "name";
    public static final  String          S_PATH                           = "path";
    public static final  String          S_QUALIFIER                      = "qualifier";
    public static final  String          S_METRIC_SORT_WITH_MEASURES_ONLY = "withMeasuresOnly";
    public static final  String          S_QUALIFIER_FIL                  = "FIL";
    private static final Logger          LOGGER                           = LoggerFactory.getLogger(ProjectStatusBuilder.class);
    private static       FileInfoBuilder builder;

    private final WsClient wsClient;

    public FileInfoBuilder(final WsClient wsClient) {
        this.wsClient = wsClient;
    }

    public static FileInfoBuilder getInstance(final WsClient wsClient) {
        if (builder == null) {
            return new FileInfoBuilder(wsClient);
        }

        return builder;
    }

    public List<FileInfo> initProjectMostViolatedFilesByProjectKey(final String key) {

        LOGGER.info("Retrieving most violated files info for " + key);
        List<FileInfo> files = new ArrayList<>();

        // Reverse iteration to get violations with upper level first

        SearchRequest searchWsReq = new SearchRequest();
        searchWsReq.setComponentKeys(singletonList(key));
        searchWsReq.setFacets(singletonList(FACET_FILES));
        searchWsReq.setPs("" + NUMBER_ISSUES_PER_PAGE);
        SearchWsResponse searchWsRes = wsClient.issues().search(searchWsReq);
        //LOGGER.info("Response :{}", new ReflectionToStringBuilder(searchWsRes).toString());
        // Facets is the list of components or resources.
        if (searchWsRes.getFacets().getFacets(0) != null) {
            int                   limit          = getLowerBound(LIMIT, searchWsRes.getFacets().getFacets(0).getValuesCount());
            FacetValue            facetValue     = searchWsRes.getFacets().getFacets(0).getValues(0);
            final List<Component> componentsList = searchWsRes.getComponentsList();
            LOGGER.info("Components to scan {}", componentsList.size());

            final List<FileInfo> fileInfos = componentsList.stream()
                                                         .limit(limit)
                                                         .map(component -> {
                                                             FileInfo fileInfo = new FileInfo();
                                                             fileInfo.setKey(facetValue.getVal());
                                                             fileInfo.setName(component.getName());
                                                             fileInfo.setPath(component.getPath());
                                                             fileInfo.setViolations(String.valueOf(facetValue.getCount()));
                                                             fileInfo.setComplexity("0");
                                                             fileInfo.setDuplicatedLines("0");
                                                             return fileInfo;
                                                         })
                                                         .collect(Collectors.toList());
            files.addAll(fileInfos);
        } else {
            LOGGER.debug("There are no violated files");
        }
        return files;
    }

    private int getLowerBound(final int limit, final int valuesCount) {
        return Math.min(valuesCount, limit);
    }

    /**
    private Predicate<Component> retrievingFileComponent(final FacetValue facetValue) {

        return c -> {
            if (c.getPath().equals(facetValue.getVal()) && c.getQualifier().equals(S_QUALIFIER_FIL)) return c;
            else return null;
        };
    }
     **/

    public List<FileInfo> initProjectMostComplexFilesByProjectKey(final String key) {

        // LOGGER.info("Retrieving most complex files info for " + key);

        List<FileInfo> files = new ArrayList<>();

        int limit = LIMIT;

        ComponentTreeRequest compTreeWsReq = new ComponentTreeRequest();
        compTreeWsReq.setComponent(key);
        compTreeWsReq.setMetricKeys(singletonList(MetricKeys.COMPLEXITY));
        compTreeWsReq.setMetricSort(MetricKeys.COMPLEXITY);
        compTreeWsReq.setS(Lists.newArrayList(S_METRIC));
        //compTreeWsReq.setAsc(S_METRIC);
        compTreeWsReq.setMetricSortFilter(S_METRIC_SORT_WITH_MEASURES_ONLY);
        compTreeWsReq.setQualifiers(singletonList(S_QUALIFIER_FIL));
        Measures.ComponentTreeWsResponse componentTreeWsRes = wsClient.measures().componentTree(compTreeWsReq);

        if (componentTreeWsRes.getComponentsList() != null) {
            final int componentsCount = componentTreeWsRes.getComponentsCount();
            limit = getLowerBound(limit, componentsCount);
            for (int j = componentsCount - 1; j >= componentsCount - limit; j--) {
                Measures.Component component = componentTreeWsRes.getComponents(j);

                /*
                 * LOGGER.info("File Info : Measures Count : " +
                 * String.valueOf(component.getMeasuresCount()));
                 * LOGGER.info("Measure Values List : "); for (Measure measure :
                 * component.getMeasuresList()) {
                 * LOGGER.info(measure.toString()); }
                 */

                FileInfo fileInfo = new FileInfo();
                fileInfo.setKey(component.getId());
                fileInfo.setName(component.getName());
                fileInfo.setPath(component.getPath());
                fileInfo.setViolations("0");
                fileInfo.setComplexity(String.valueOf(component.getMeasures(0).getValue()));
                fileInfo.setDuplicatedLines("0");
                files.add(fileInfo);
            }
        } else {
            LOGGER.debug("There are no complex files");
        }
        return files;
    }

    public List<FileInfo> initProjectMostDuplicatedFilesByProjectKey(final String key) {

        // LOGGER.info("Retrieving most duplicated files info for " + key);

        List<FileInfo> files = new ArrayList<>();

        int limit = LIMIT;

        ComponentTreeRequest compTreeWsReq = new ComponentTreeRequest();
        compTreeWsReq.setComponent(key);
        compTreeWsReq.setMetricKeys(singletonList(MetricKeys.DUPLICATED_LINES));
        compTreeWsReq.setMetricSort(MetricKeys.DUPLICATED_LINES);
        compTreeWsReq.setS(Arrays.asList(S_METRIC));
        compTreeWsReq.setMetricSortFilter(S_METRIC_SORT_WITH_MEASURES_ONLY);
        compTreeWsReq.setQualifiers(singletonList(S_QUALIFIER_FIL));
        Measures.ComponentTreeWsResponse componentTreeWsRes = wsClient.measures().componentTree(compTreeWsReq);

        if (componentTreeWsRes.getComponentsList() != null) {
            limit = getLowerBound(limit, componentTreeWsRes.getComponentsCount());
            LOGGER.info("Found {} components with duplication metrics", componentTreeWsRes.getComponentsList());
            for (int j = componentTreeWsRes.getComponentsCount() - 1; j >= componentTreeWsRes.getComponentsCount() - limit; j--) {
                Measures.Component component = componentTreeWsRes.getComponents(j);

                FileInfo fileInfo = new FileInfo();
                fileInfo.setKey(component.getId());
                fileInfo.setName(component.getName());
                fileInfo.setPath(component.getPath());
                fileInfo.setViolations("0");
                fileInfo.setComplexity("0");
                fileInfo.setDuplicatedLines(String.valueOf(component.getMeasures(0).getValue()));
                files.add(fileInfo);
            }
        } else {
            LOGGER.debug("There are no duplicated files");
        }
        return files;

    }

}
