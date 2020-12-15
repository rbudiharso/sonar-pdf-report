package com.cybage.sonar.report.pdf.entity;

import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * This class encapsulates the measures info.
 */
public class Measures {

	private Hashtable<String, Measure> measuresTable = new Hashtable<String, Measure>();
	private List<Period_> periods;

	public Measures() {

	}

	public int getMeasuresCount() {
		return measuresTable.size();
	}

	public Set<String> getMeasuresKeys() {
		return measuresTable.keySet();
	}

	public Measure getMeasure(final String key) {
		return measuresTable.get(key);
	}

	public void addMeasure(final String name, final Measure value) {
		measuresTable.put(name, value);
	}

	public boolean containsMeasure(final String measureKey) {
		return measuresTable.containsKey(measureKey);
	}

	public Optional<Period_> getPeriod_(Integer index) {
		return periods.stream().filter(p -> p.getIndex() == index).findFirst();
	}

	public Optional<Period_> getPeriod_(String mode) {
		return periods.stream().filter(p -> p.getMode().equals(mode)).findFirst();
	}

	public void setPeriods(List<Period_> periods) {
		this.periods = periods;
	}
	
	public List<Period_> getPeriods() {
		return this.periods;
	}

	@Override
	public String toString() {
		return "Measures [measuresTable=" + measuresTable + ", periods=" + periods + "]";
	}

}
