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

        List<Condition>    conditions    = initProjectConditions(projectStatusWsRes);
        List<StatusPeriod> statusPeriods = initStatusPeriods(projectStatusWsRes);
        return new ProjectStatus(projectStatusWsRes.getProjectStatus().getStatus().toString(), conditions,
                statusPeriods);

    }

    private List<Condition> initProjectConditions(final Qualitygates.ProjectStatusResponse projectStatusWsRes) {
        List<Condition> conditions = new ArrayList<>();
        for (Qualitygates.ProjectStatusResponse.Condition condition : projectStatusWsRes.getProjectStatus().getConditionsList()) {
            Condition cond = new ConditionBuilder()
                    .setStatus(condition.getStatus().toString())
                    .setMetricKey(condition.getMetricKey())
                    .setComparator(condition.getComparator().toString())
                    .setPeriodIndex(condition.getPeriodIndex())
                    .setErrorThreshold(condition.getErrorThreshold())
                    .setActualValue(condition.getActualValue())
                    .setWarningThreshold(condition.getWarningThreshold())
                    .createCondition();
            conditions.add(cond);
        }
        return conditions;
    }

    private List<StatusPeriod> initStatusPeriods(final Qualitygates.ProjectStatusResponse projectStatusWsRes) {
        List<StatusPeriod> statusPeriods = new ArrayList<>();
        for (Qualitygates.ProjectStatusResponse.Period period : projectStatusWsRes.getProjectStatus().getPeriodsList()) {
            StatusPeriod statusPeriod = new StatusPeriodBuilder()
                    .setIndex(period.getIndex())
                    .setMode(period.getMode())
                    .createStatusPeriod();

            if (period.getDate() != null) {
                statusPeriod.setDate(period.getDate());
            }
            if (period.getParameter() != null) {
                statusPeriod.setParameter(period.getParameter());
            }
            statusPeriods.add(statusPeriod);
        }
        return statusPeriods;
    }
}
