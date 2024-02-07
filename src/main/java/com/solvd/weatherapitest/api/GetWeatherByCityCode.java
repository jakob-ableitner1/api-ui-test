package com.solvd.weatherapitest.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.utils.config.Configuration;

public class GetWeatherByCityCode extends AbstractApiMethodV2 {
    public GetWeatherByCityCode(String cityId) {
        super(null, "api/data/2.5/get_weather_rs.json");
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
        replaceUrlPlaceholder("app_id", Configuration.getRequired("app_id"));
        replaceUrlPlaceholder("city_id", cityId);

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
