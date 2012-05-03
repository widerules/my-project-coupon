package com.alywhere.client.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	private static final String DATABASE_NAME = "STORE_AND_COUPON_INFO";
	private static final String STROE_TABLE = "store";
	private static final String COUPON_TABLE = "coupon";
	private static final int DATABASE_VERSION = 1;
	private static final String STORE_TABLE_CREATE = "CREATE TABLE store(storeName TEXT, storeType TEXT, storeAddress TEXT, longitude REAL, latitude REAL, storeImagePath TEXT)";
	private static final String COUPON_TABLE_CREATE = "CREATE TABLE coupon(couponName TEXT, couponType TEXT, couponImagePath TEXT)";
	public static final String KEY_STORE_NAME = "storeName";
	public static final String KEY_STORE_TYPE = "storeType";
	public static final String KEY_STORE_ADDRESS = "storeAddress";
	public static final String KEY_STORE_LONGITUDE = "Longitude";
	public static final String KEY_STORE_LATITUDE = "latitude";
	public static final String KEY_STORE_IMAGE_PATH = "storeImagePath";
	public static final String KEY_COUPON_NAME = "couponName";
	public static final String KEY_COUPON_TYPE = "couponType";
	public static final String KEY_COUPON_IMAGE_PATH = "couponImagePath";
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	// open the database
	public DBAdapter open() {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	// close the database
	public void close() {
		dbHelper.close();
	}

	// insert a raw into the coupon table
	public long insertToCouponTable(String couponName, String couponType,
			String couponImagePath) {
		ContentValues values = new ContentValues();
		values.put(KEY_COUPON_NAME, couponName);
		values.put(KEY_COUPON_TYPE, couponType);
		values.put(KEY_COUPON_IMAGE_PATH, couponImagePath);
		return db.insert(COUPON_TABLE, null, values);
	}

	// insert a raw into the store table
	public long insertToStoreTable(String storeName, String storeType,
			String storeAddress, double longitude, double latitude, String storeImagePath) {
		ContentValues values = new ContentValues();
		values.put(KEY_STORE_NAME, storeName);
		values.put(KEY_STORE_TYPE, storeType);
		values.put(KEY_STORE_ADDRESS, storeAddress);
		values.put(KEY_STORE_LONGITUDE, longitude);
		values.put(KEY_STORE_LATITUDE, latitude);
		values.put(KEY_STORE_IMAGE_PATH, storeImagePath);
		return db.insert(STROE_TABLE, null, values);
	}

	// query store from table store by name
	public Cursor queryStoreByName(String storeName) {
		System.out.println(storeName);
		String sql = "select * from store where storeName like ?";
		String[] selectionArgs = new String[] { "%" + storeName + "%" };
		return db.rawQuery(sql, selectionArgs);
	}

	/*
	public List<StoreItem> queryStoreByDistance(double CurLongitude, double CurLatitude, double distance) {
		String sql = "select * from store where city like ?";
		String[] selectionArgs = new String[]{"%" + LocationOperations.getCityName() + "%"};
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		List<StoreItem> storeItemList = new ArrayList<StoreItem>();
		while (cursor.moveToNext()) {
			double longitude = cursor.getDouble(cursor.getColumnIndex(KEY_STORE_LONGITUDE));
			double latitude = cursor.getDouble(cursor.getColumnIndex(KEY_STORE_LATITUDE));
			if (LocationOperations.getDistance(CurLongitude, CurLatitude, longitude, latitude) <= distance) {
				String storeName = cursor.getString(cursor.getColumnIndex(KEY_STORE_NAME));
				String storeType = cursor.getString(cursor.getColumnIndex(KEY_STORE_TYPE));
				String storeAddress = cursor.getString(cursor.getColumnIndex(KEY_STORE_ADDRESS));
				String storeImagePath = cursor.getString(cursor.getColumnIndex(KEY_STORE_IMAGE_PATH));
				storeItemList.add(new StoreItem(storeName, storeType, storeImagePath, storeAddress, longitude, latitude));
			}
		}
		return storeItemList;
	}
	*/

	// get all rows from a table
	public Cursor getAll(String tableName) {
		String sql = "select * from ?";
		String[] selectionArgs = new String[] { tableName };
		return db.rawQuery(sql, selectionArgs);
	}

	// inner class used to operate on the database
	private static class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) { // init database
			db.execSQL(STORE_TABLE_CREATE);
			db.execSQL(COUPON_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}
}
