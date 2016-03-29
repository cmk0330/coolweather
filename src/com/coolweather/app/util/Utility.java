package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {
	//解析和处理服务器返回的省数据
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolweatherDB, String response) {
		if(!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length>0) {
				for(String p : allProvinces) {//for(String i; i<allProvinces; i++) {String p = allProvinces[i]}
					String[] array = p.split("//|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//将解析出来的数据保存在Province表
					coolweatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	//解析和处理服务器返回的市级数据
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolweatherDB, String response, int provinceId) {
		if(!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if(allCities != null && allCities.length>0) {
				for(String c : allCities) {//for(String i; i<allProvinces; i++) {String p = allProvinces[i]}
					String[] array = c.split("//|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					//将解析出来的数据保存在City表
					coolweatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	//Jiangxi解析和处理服务器返回的县级数据
	public synchronized static boolean handleCountyResponse(CoolWeatherDB coolweatherDB, String response, int cityId) {
		if(!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if(allCounties != null && allCounties.length>0) {
				for(String c : allCounties) {//for(String i; i<allProvinces; i++) {String p = allProvinces[i]}
					String[] array = c.split("//|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					//将解析出来的数据保存在County表
					coolweatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
