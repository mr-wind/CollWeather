package com.quhaofeng.collweather.uitl;

import android.text.TextUtils;

import com.quhaofeng.collweather.model.City;
import com.quhaofeng.collweather.model.CoolWeatherDB;
import com.quhaofeng.collweather.model.County;
import com.quhaofeng.collweather.model.Province;

/**
 * Created by Quhaofeng on 2016-4-3-0003.
 */
public class Utility {
    /**
    * 解析和处理服务器返回的省级数据<p/>
    * */
    public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB, String response){
        if (!TextUtils.isEmpty(response)){
            String[] allPrivinces = response.split(",");
            if (allPrivinces != null && allPrivinces.length > 0 ){
                for (String p : allPrivinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
    * 解析和处理服务器返回的市级数据<p/>
    * */
    public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0 ){
                for (String c : allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
    * 解析和返回处理器返回的县级数据<p/>
    * */
    public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0 ){
                for (String c : allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
