package com.cybage.sonar.report.pdf.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarqube.ws.Common;
import org.sonarqube.ws.Common.FacetValue;
import org.sonarqube.ws.Issues.SearchWsResponse;
import org.sonarqube.ws.client.WsClient;

import com.cybage.sonar.report.pdf.entity.Priority;
import com.cybage.sonar.report.pdf.entity.Rule;
import org.sonarqube.ws.client.issues.SearchRequest;

public class RuleBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleBuilder.class);
    public static final int TOP_MAX_VIOLATED_RULES = 10;

    private static RuleBuilder builder;

    private final WsClient wsClient;

    public RuleBuilder(final WsClient wsClient) {
        this.wsClient = wsClient;
    }

    public static RuleBuilder getInstance(final WsClient wsClient) {
        if (builder == null) {
            return new RuleBuilder(wsClient);
        }

        return builder;
    }

    public List<Rule> initProjectMostViolatedRulesByProjectKey(final String key) {

        String[]   priorities = Priority.getPrioritiesArray();
        List<Rule> rules      = new ArrayList<>();

        // Reverse iteration to get violations with upper level first

        for (int i = priorities.length - 1; i >= 0; i--) {
            SearchRequest searchWsReq = new SearchRequest();
            searchWsReq.setComponentKeys(Arrays.asList(key));
            searchWsReq.setAdditionalFields(Arrays.asList("rules"));
            searchWsReq.setFacets(Arrays.asList("rules"));
            searchWsReq.setSeverities(Arrays.asList(priorities[i]));
            SearchWsResponse searchWsRes = wsClient.issues().search(searchWsReq);

            if (searchWsRes.getFacets().getFacets(0) != null) {
                int limit = TOP_MAX_VIOLATED_RULES;
                limit = searchWsRes.getFacets().getFacets(0).getValuesCount() > limit ? limit
                        : searchWsRes.getFacets().getFacets(0).getValuesCount();
                for (int j = 0; j < limit; j++) {
                    FacetValue facetValue = searchWsRes.getFacets().getFacets(0).getValues(j);
                    Optional<Common.Rule> rule = searchWsRes.getRules().getRulesList().stream()
                            .filter(r -> r.getKey().equals(facetValue.getVal())).findFirst();
                    rules.add(new Rule(facetValue.getVal(), rule.get().getName(), facetValue.getCount(),
                            rule.get().getLangName(), Priority.getPriority(priorities[i])));
                }
            } else {
                LOGGER.debug("There are no violations with level " + priorities[i]);
            }
        }

        return rules;

    }
}
