package com.cybage.sonar.report.pdf.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.sonarqube.ws.Common.Metric;

import com.cybage.sonar.report.pdf.entity.Measure;
import com.cybage.sonar.report.pdf.entity.Period;
import com.cybage.sonar.report.pdf.entity.Period_;
import org.sonarqube.ws.Measures;

public class MeasureBuilder {

    /**
     * Init measure from XML node. The root node must be "msr".
     *
     * @param measureNode    the measure node
     * @param periods_       the periods
     * @param optionalMetric the optionalMetric
     * @return measure
     */
    public static Measure initFromNode(final Measures.Measure measureNode, List<Period_> periods_,
                                       Optional<Metric> optionalMetric) {
        Validate.isTrue(optionalMetric.isPresent(), "Metric should be present");
        Validate.isTrue(!periods_.isEmpty(), "Periods should be available.");
        List<Period> periods = new ArrayList<>();

        for (int i = 0; i < periods_.size(); i++) {
            Measures.PeriodsValue      periodsValue     = measureNode.getPeriods();
            List<Measures.PeriodValue> periodsValueList = periodsValue.getPeriodsValueList();
            periodsValueList.forEach(p -> {
                periods.add(new Period(p.getIndex(), p.getValue()));
            });
            //Measures.PeriodValue  periodValue = periodsValue.getPeriodsValue(i);
        }

        Metric metric = optionalMetric.get();
        return new Measure(measureNode.getMetric(),
                measureNode.getValue(),
                metric.getName(),
                metric.getType(),
                metric.getDomain(),
                metric.getHigherValuesAreBetter(), periods);

    }
}
