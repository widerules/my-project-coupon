package com.alywhere.client.data;

public class CouponItem extends Item{
	private String couponName;
	private String couponType;
	private String couponImagePath;
	public CouponItem(String couponName, String couponType,
			String couponImagePath) {
		super();
		this.couponName = couponName;
		this.couponType = couponType;
		this.couponImagePath = couponImagePath;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public String getCouponImagePath() {
		return couponImagePath;
	}
	public void setCouponImagePath(String couponImagePath) {
		this.couponImagePath = couponImagePath;
	}
	
}
