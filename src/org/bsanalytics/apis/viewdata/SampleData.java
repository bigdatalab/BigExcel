package org.bsanalytics.apis.viewdata;

public class SampleData {

	private Integer years;
	private String firstname;
	private String lastname;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the id
	 */
	public Integer getYears() {
		return years;
	}
	/**
	 * @param id the id to set
	 */
	public void setYears(Integer years) {
		this.years = years;
	}
	
}