package com.cybage.sonar.report.pdf.builder;

import com.cybage.sonar.report.pdf.entity.Condition;

public class ConditionBuilder {
    private String status;
    private String  metricKey;
    private String  comparator;
    private Integer periodIndex;
    private String  errorThreshold;
    private String  actualValue;
    private String  warningThreshold;

    public ConditionBuilder setStatus(final String status) {
        this.status = status;
        return this;
    }

    public ConditionBuilder setMetricKey(final String metricKey) {
        this.metricKey = metricKey;
        return this;
    }

    public ConditionBuilder setComparator(final String comparator) {
        this.comparator = comparator;
        return this;
    }

    public ConditionBuilder setPeriodIndex(final Integer periodIndex) {
        this.periodIndex = periodIndex;
        return this;
    }

    public ConditionBuilder setErrorThreshold(final String errorThreshold) {
        this.errorThreshold = errorThreshold;
        return this;
    }

    public ConditionBuilder setActualValue(final String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public ConditionBuilder setWarningThreshold(final String warningThreshold) {
        this.warningThreshold = warningThreshold;
        return this;
    }

    public Condition createCondition() {
        return new Condition(status, metricKey, comparator, periodIndex, errorThreshold, actualValue, warningThreshold);
    }
}