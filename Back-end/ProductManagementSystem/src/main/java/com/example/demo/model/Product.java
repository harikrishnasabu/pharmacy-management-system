package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pId;
	private String pName;
	private String pDescription;
	private Date pManufactureDate;
	private Date pExpiryDate;
	private int pQuantity;
	private double pSellingPrice;
	private double pCostPrice;
	private int sId;

	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}
	public void setpManufactureDate(Date pManufactureDate) {
		this.pManufactureDate = pManufactureDate;
	}
	public void setpExpiryDate(Date pExpiryDate) {
		this.pExpiryDate = pExpiryDate;
	}
	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}
	public void setpSellingPrice(double pSellingPrice) {
		this.pSellingPrice = pSellingPrice;
	}
	public void setpCostPrice(double pCostPrice) {
		this.pCostPrice = pCostPrice;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getpName() {
		return pName;
	}
	public String getpDescription() {
		return pDescription;
	}
	public Date getpManufactureDate() {
		return pManufactureDate;
	}
	public Date getpExpiryDate() {
		return pExpiryDate;
	}
	public int getpQuantity() {
		return pQuantity;
	}
	public double getpSellingPrice() {
		return pSellingPrice;
	}
	public double getpCostPrice() {
		return pCostPrice;
	}
	public int getsId() {
		return sId;
	}
	
}
