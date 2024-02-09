package com.solvd.apiuitest.api.method;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${config.api_url}/data/2.5/weather?q=${city_name},${country_id}&appid=${app_id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/data/2.5/get_weather_rs.json")
public class GetWeatherByCityNameAndCountryCode extends AbstractApiMethodV2 {
    public GetWeatherByCityNameAndCountryCode(String cityName, String countryId) {
        replaceUrlPlaceholder("app_id", Configuration.getRequired("app_id"));
        replaceUrlPlaceholder("city_name", cityName);
        replaceUrlPlaceholder("country_id", countryId);

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
