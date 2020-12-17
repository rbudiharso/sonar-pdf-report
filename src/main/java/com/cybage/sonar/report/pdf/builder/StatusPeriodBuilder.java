package com.cybage.sonar.report.pdf.builder;

import com.cybage.sonar.report.pdf.entity.StatusPeriod;

public class StatusPeriodBuilder {
    private Integer index;
    private String mode;
    private String date;
    private String parameter;

    public StatusPeriodBuilder setIndex(final Integer index) {
        this.index = index;
        return this;
    }

    public StatusPeriodBuilder setMode(final String mode) {
        this.mode = mode;
        return this;
    }

    public StatusPeriodBuilder setDate(final String date) {
        this.date = date;
        return this;
    }

    public StatusPeriodBuilder setParameter(final String parameter) {
        this.parameter = parameter;
        return this;
    }

    public StatusPeriod createStatusPeriod() {
        return new StatusPeriod(index, mode, date, parameter);
    }
}