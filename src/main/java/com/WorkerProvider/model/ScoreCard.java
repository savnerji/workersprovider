package com.WorkerProvider.model;

public class ScoreCard {

	
	private String name;
	
	private String totalRun;

	public ScoreCard(String name, String totalRun) {
		super();
		this.name = name;
		this.totalRun = totalRun;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTotalRun() {
		return totalRun;
	}

	public void setTotalRun(String totalRun) {
		this.totalRun = totalRun;
	}

	public ScoreCard() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
}
