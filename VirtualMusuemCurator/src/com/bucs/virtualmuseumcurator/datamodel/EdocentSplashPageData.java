package com.bucs.virtualmuseumcurator.datamodel;

import java.util.ArrayList;
import java.util.List;

public class EdocentSplashPageData {
	
	List<String> States=new ArrayList<String>();
	List<String> Cities=new ArrayList<String>();
	List<String> Musuems=new ArrayList<String>();
	List<String> prKey=new ArrayList<String>();
	
	public List<String> getPrKey() {
		return prKey;
	}
	public void setPrKey(List<String> prKey) {
		this.prKey = prKey;
	}
	public List<String> getStates() {
		return States;
	}
	public void setStates(List<String> states) {
		States = states;
	}
	public List<String> getCities() {
		return Cities;
	}
	public void setCities(List<String> cities) {
		Cities = cities;
	}
	public List<String> getMusuems() {
		return Musuems;
	}
	public void setMusuems(List<String> musuems) {
		Musuems = musuems;
	}

	
	

}
