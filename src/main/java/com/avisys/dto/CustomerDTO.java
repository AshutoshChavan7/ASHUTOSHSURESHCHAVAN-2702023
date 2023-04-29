package com.avisys.dto;

import java.util.ArrayList;
import java.util.List;

import com.avisys.entities.MobileNumber;

public class CustomerDTO {

	private String firstName;
	private String lastName;
	private List<String> mobileNumbers=new ArrayList<>();
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getMobileNumbers() {
		return mobileNumbers;
	}
	public void setMobileNumbers(List<String> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
	
	
}
