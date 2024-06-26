package com.main.project.model;

import javax.persistence.Embeddable;

@Embeddable
public class Rating {

	private float rate;
	
	private int count;

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}