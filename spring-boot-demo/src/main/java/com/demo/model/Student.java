package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table
public class Student {
	@Id
	@Column
	@ApiModelProperty(notes = "The Student ID")
	private int id;
	@Column
	@ApiModelProperty(notes = "The Student Name")
	private String name;
	@Column
	@ApiModelProperty(notes = "The Student Age")
	private int age;
	@Column
	@ApiModelProperty(notes = "The Student Email")
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}