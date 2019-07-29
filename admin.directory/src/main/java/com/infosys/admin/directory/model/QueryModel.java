package com.infosys.admin.directory.model;


public class QueryModel {

	
	String queryMetric;
	String queryValue;
	String changeMetric;
	String changeValue;
	
	
	public String getQueryMetric() {
		return queryMetric;
	}
	public void setQueryMetric(String queryMetric) {
		this.queryMetric = queryMetric;
	}
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public String getChangeMetric() {
		return changeMetric;
	}
	public void setChangeMetric(String changeMetric) {
		this.changeMetric = changeMetric;
	}
	public String getChangeValue() {
		return changeValue;
	}
	public void setChangeValue(String changeValue) {
		this.changeValue = changeValue;
	}
	public QueryModel(String queryMetric, String queryValue, String changeMetric, String changeValue) {
		super();
		this.queryMetric = queryMetric;
		this.queryValue = queryValue;
		this.changeMetric = changeMetric;
		this.changeValue = changeValue;
	}
	public QueryModel() {
	
	}
	
	
}
