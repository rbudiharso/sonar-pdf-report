package com.cybage.sonar.report.pdf.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarqube.ws.Issues.SearchWsResponse;
import org.sonarqube.ws.client.WsClient;

import com.cybage.sonar.report.pdf.entity.Issue;
import org.sonarqube.ws.client.issues.SearchRequest;

public class IssueBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueBuilder.class);

    private static IssueBuilder builder;

    private final WsClient wsClient;

    public IssueBuilder(final WsClient wsClient) {
        this.wsClient = wsClient;
    }

    public static IssueBuilder getInstance(final WsClient wsClient) {
        if (builder == null) {
            return new IssueBuilder(wsClient);
        }
        return builder;
    }

    public List<Issue> initIssueDetailsByProjectKey(final String key, final Set<String> typesOfIssue) {

        // LOGGER.info("Retrieving issue details for " + key);

        List<Issue> issues     = new ArrayList<>();
        Integer     pageNumber = 1;
        Integer     pageSize   = 500;

        while (true) {
            SearchRequest searchWsReq = new SearchRequest();
            searchWsReq.setComponentKeys(Arrays.asList(key));
            //searchWsReq.setPage(pageNumber);
            //searchWsReq.setPageSize(pageSize);
            searchWsReq.setStatuses(Arrays.asList("OPEN"));
            searchWsReq.setTypes(typesOfIssue.stream().map(t -> t.toUpperCase()).collect(Collectors.toList()));
            SearchWsResponse searchWsRes = wsClient.issues().search(searchWsReq);

            if (searchWsRes.getTotal() > 0) {
                for (int i = 0; i < searchWsRes.getIssuesCount(); i++) {
                    org.sonarqube.ws.Issues.Issue issue = searchWsRes.getIssues(i);

                    Optional<String> component = searchWsRes.getComponentsList().stream()
                            .filter(c -> c.getKey().equals(issue.getComponent())).map(c -> c.getName()).findFirst();

                    Optional<String> componentPath = searchWsRes.getComponentsList().stream()
                            .filter(c -> c.getKey().equals(issue.getComponent())).map(c -> c.getLongName()).findFirst();

                    issues.add(new Issue(component.get(), componentPath.get(), issue.getSeverity().name(),
                            issue.getLine(), issue.getStatus(), issue.getMessage().replaceAll("\\\"", "\""),
                            issue.getType().name(), issue.getEffort()));
                }
                if (searchWsRes.getTotal() > (pageNumber * pageSize)) {
                    pageNumber++;
                } else {
                    break;
                }
            } else {
                LOGGER.debug("There are no issues in project : " + key);
                break;
            }
        }
        return issues;
    }
}
