package com.springboot.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	@Id
	@NotNull(message = "ID is required")
	private Integer id;
	@Column
	@NotEmpty(message = "Employee name is required")
	private String ename;
	@Column
	@NotNull(message = "Employee exp is required")
	private Integer exp;

	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(Integer id, String ename, Integer exp) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.ename=ename;
		this.exp=exp;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", ename=" + ename + ", exp=" + exp + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

}
