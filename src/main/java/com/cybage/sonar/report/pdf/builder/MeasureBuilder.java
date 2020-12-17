package com.cybage.sonar.report.pdf.builder;

import com.cybage.sonar.report.pdf.entity.Measure;
import com.cybage.sonar.report.pdf.entity.Period;
import com.cybage.sonar.report.pdf.entity.Period_;
import org.apache.commons.lang3.Validate;
import org.sonarqube.ws.Common.Metric;
import org.sonarqube.ws.Measures;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MeasureBuilder {

    /**
     * Init measure from XML node. The root node must be "msr".
     *
     * @param measureNode the measure node
     * @param periods_    the periods
     * @param metric      the metric
     * @return measure
     */
    public static Measure initFromNode(final Measures.Measure measureNode, List<Period_> periods_,
                                       Metric metric) {
        Validate.isTrue(!periods_.isEmpty(), "Periods should be available.");
        Measures.PeriodsValue      periodsValue     = measureNode.getPeriods();
        List<Measures.PeriodValue> periodsValueList = periodsValue.getPeriodsValueList();
        List<Period> periods = periodsValueList.stream()
                                               .map(MeasureBuilder::newPeriod)
                                               .collect(Collectors.toList());

        return newMeasure(measureNode, periods, metric);

    }

    private static Period newPeriod(final Measures.PeriodValue p) {
        return new Period(p.getIndex(), p.getValue());
    }

    private static Measure newMeasure(final Measures.Measure measureNode, final List<Period> periods, final Metric metric) {
        return new Measure(measureNode.getMetric(),
                measureNode.getValue(),
                metric.getName(),
                metric.getType(),
                metric.getDomain(),
                metric.getHigherValuesAreBetter(), periods);
    }
}
