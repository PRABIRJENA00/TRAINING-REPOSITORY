package com.springboot.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class AuditUser {
	@Id
	private int id;
	@Column
	private String username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String date1) {
		this.startDate = date1;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Column(name ="start_Date")
	private String startDate;
	@Column(name ="end_date")
	private String endDate;

}
