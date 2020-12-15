package com.cybage.sonar.report.pdf.builder;

import com.cybage.sonar.report.pdf.entity.Condition;
import com.cybage.sonar.report.pdf.entity.ProjectStatus;
import com.cybage.sonar.report.pdf.entity.StatusPeriod;
import org.sonarqube.ws.Qualitygates;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.qualitygates.ProjectStatusRequest;

import java.util.ArrayList;
import java.util.List;

public class ProjectStatusBuilder {

    // private static final Logger LOGGER = LoggerFactory.getLogger(ProjectStatusBuilder.class);

    private static ProjectStatusBuilder builder;

    private final WsClient wsClient;

    public ProjectStatusBuilder(final WsClient wsClient) {
        this.wsClient = wsClient;
    }

    public static ProjectStatusBuilder getInstance(final WsClient wsClient) {
        if (builder == null) {
            return new ProjectStatusBuilder(wsClient);
        }

        return builder;
    }

    public ProjectStatus initProjectStatusByProjectKey(final String key) {

        // LOGGER.info("Retrieving project status info for " + key);

        ProjectStatusRequest projectStatusWsReq = new ProjectStatusRequest();
        projectStatusWsReq.setProjectKey(key);
        Qualitygates.ProjectStatusResponse projectStatusWsRes = wsClient.qualitygates().projectStatus(projectStatusWsReq);

        List<Condition> conditions = new ArrayList<>();
        for (Qualitygates.ProjectStatusResponse.Condition condition : projectStatusWsRes.getProjectStatus().getConditionsList()) {
            Condition cond = new Condition(condition.getStatus().toString(), condition.getMetricKey(),
                    condition.getComparator().toString(), condition.getPeriodIndex(), condition.getErrorThreshold(),
                    condition.getActualValue(), condition.getWarningThreshold());
            conditions.add(cond);
        }

        List<StatusPeriod> statusPeriods = new ArrayList<>();
        for (Qualitygates.ProjectStatusResponse.Period period : projectStatusWsRes
                .getProjectStatus().getPeriodsList()) {
            StatusPeriod statusPeriod = new StatusPeriod();
            statusPeriod.setIndex(period.getIndex());
            statusPeriod.setMode(period.getMode());
            if (period.getDate() != null) {
                statusPeriod.setDate(period.getDate());
            }
            if (period.getParameter() != null) {
                statusPeriod.setParameter(period.getParameter());
            }
            statusPeriods.add(statusPeriod);
        }
        return new ProjectStatus(projectStatusWsRes.getProjectStatus().getStatus().toString(), conditions,
                statusPeriods);

    }
}
