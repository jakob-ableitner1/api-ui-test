package com.solvd.weatherapitest.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.utils.config.Configuration;

import java.util.Properties;

public class GetWeatherByCoordinates extends AbstractApiMethodV2 {
    public GetWeatherByCoordinates(Double lat, Double lon) {
        super(null, "api/data/2.5/get_weather_rs.json");
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
        replaceUrlPlaceholder("app_id", Configuration.getRequired("app_id"));
        replaceUrlPlaceholder("lat", lat.toString());
        replaceUrlPlaceholder("lon", lon.toString());

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
