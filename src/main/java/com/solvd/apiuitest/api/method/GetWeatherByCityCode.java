package com.solvd.apiuitest.api.method;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.utils.config.Configuration;

public class GetWeatherByCityCode extends AbstractApiMethodV2 {
    public GetWeatherByCityCode(String cityId, String compareFile) {
        super(null, compareFile);
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
        replaceUrlPlaceholder("app_id", Configuration.getRequired("app_id"));
        replaceUrlPlaceholder("city_id", cityId);

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
