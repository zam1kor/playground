package com.lang.process.framework.impl;

import java.util.ArrayList;
import java.util.List;

public class Word {

	protected Word(Integer ID, String text, Pool pool){
		
		this.ID=ID;
		this.text=text;
		this.pools=new ArrayList<Pool>();
		
		pools.add(pool);
	}
	
	private String text;
	
	private Integer ID;
	
	private List<Pool> pools;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public List<Pool> getPools() {
		return pools;
	}

	public void setPools(List<Pool> pools) {
		this.pools = pools;
	}
	
	/**
	 * It is mandatory to update Word specific info in the pool when the pool is updated
	 */
	
	public void addPool(Pool pool){
		this.pools.add(pool);
		pool.addWord(this);
	}
	
	
}
