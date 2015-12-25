package com.example.tanx.hiweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tanx.hiweather.model.City;
import com.example.tanx.hiweather.model.Province;
import com.example.tanx.hiweather.model.County;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanx on 2015-12-24.
 */
public class HiweatherDB{
    //database name
    public static final String DB_NAME = "Hi_weather";

    //version of database
    public static final int VERSION = 1;
    private static HiweatherDB hiweatherDB;
    private SQLiteDatabase db;

    private HiweatherDB(Context context){
        HiweatherOpenHelper dbHelper = new HiweatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    //get the Hiweather
    public synchronized static HiweatherDB getInstance(Context context){
        if(hiweatherDB == null){
            hiweatherDB = new HiweatherDB(context);
        }
        return  hiweatherDB;
    }

    //将Province实例存储到数据库中
    public void saveProvince(Province province){
        if(province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }

    //从数据库中读取全国所有的省份信息
    public List<Province>loadProvince() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //将city实例存储到数据库中
    public void saveCity(City city){
        if(city != null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City",null,values);
        }
    }

    //从数据库中读取某省下所有的城市信息
    public List<City>loadCities (int provinceId){
        List<City>list = new ArrayList<City>();
        Cursor cursor = db.query("City",null,"province_id=?",new String []{String.valueOf(provinceId)},null,null,null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //将country实例存储到数据库中
    public void saveCountry(County country){
        if(country != null){
            ContentValues values = new ContentValues();
            values.put("country_name",country.getCountyName());
            values.put("country_code",country.getCountyCode());
            values.put("city_id",country.getCityId());
            db.insert("country",null,values);
        }
    }

    //
    public List<County>loadCounties (int cityId){
        List<County>list = new ArrayList<County>();
        Cursor cursor = db.query("County",null,"city_id=?",new String []{String.valueOf(cityId)},null,null,null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
