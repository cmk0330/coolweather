package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {
	//�����ʹ�����������ص�ʡ����
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolweatherDB, String response) {
		if(!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length>0) {
				for(String p : allProvinces) {//for(String i; i<allProvinces; i++) {String p = allProvinces[i]}
					String[] array = p.split("//|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//���������������ݱ�����Province��
					coolweatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	//�����ʹ�����������ص��м�����
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
					//���������������ݱ�����City��
					coolweatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	//Jiangxi�����ʹ�����������ص��ؼ�����
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
					//���������������ݱ�����County��
					coolweatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
