package com.alywhere.client.data;

public class StoreItem extends Item{
	private String storeName;
	private String storeType;
	private String storeImagePath;
	private String storeAddress;
	private double Longitude;
	private double Latitude;

	public StoreItem(String storeName, String storeType, String storeImagePath,
			String storeAddress, double longitude, double latitude) {
		super();
		this.storeName = storeName;
		this.storeType = storeType;
		this.storeImagePath = storeImagePath;
		this.storeAddress = storeAddress;
		Longitude = longitude;
		Latitude = latitude;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getStoreImagePath() {
		return storeImagePath;
	}

	public void setStoreImagePath(String sotreImagePath) {
		this.storeImagePath = sotreImagePath;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

}
