package com.solvd.weatherapitest;

import com.solvd.weatherapitest.api.GetWeatherByCityCode;
import com.solvd.weatherapitest.api.GetWeatherByCityNameAndCountryCode;
import com.solvd.weatherapitest.api.GetWeatherByCoordinates;
import com.solvd.weatherapitest.domain.City;
import com.solvd.weatherapitest.domain.Country;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.Test;

public class WeatherTest {

    @Test
    public void verifyGetWeatherByCoordinates(){
        City location = new City();
        location.setName("Lower Franconia");
        location.setLatitude(50.0);
        location.setLongitude(10.0);
        location.setTimezone(3600);

        GetWeatherByCoordinates getWeatherByCoordinates = new GetWeatherByCoordinates(50.0, 10.0 );
        getWeatherByCoordinates.addProperty("location", location);

        getWeatherByCoordinates.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByCoordinates.callAPI();

        getWeatherByCoordinates.validateResponse();
    }

    @Test
    public void verifyGetWeatherByCoordinatesWithoutVerification(){

    }

    @Test
    public void verifyGetWeatherByCityNameAndCountryCode(){
        Country country = new Country();
        country.setId("2012208");
        country.setName("FR");

        City city = new City();
        city.setId("2988507");
        city.setName("Paris");
        city.setLatitude(48.8534);
        city.setLongitude(2.3488);
        city.setTimezone(3600);
        city.setCountry(country);

        GetWeatherByCityNameAndCountryCode getWeatherByCityName = new GetWeatherByCityNameAndCountryCode(city.getName(), city.getCountry().getId());
        getWeatherByCityName.addProperty("location", city);

        getWeatherByCityName.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByCityName.callAPI();

        getWeatherByCityName.validateResponse();
    }

    @Test
    public void verifyGetWeatherByCityCode(){
        Country country = new Country();
        country.setId("2012208");
        country.setName("FR");

        City city = new City();
        city.setId("2988507");
        city.setName("Paris");
        city.setLatitude(48.8534);
        city.setLongitude(2.3488);
        city.setTimezone(3600);
        city.setCountry(country);

        GetWeatherByCityCode getWeatherByCityName = new GetWeatherByCityCode(city.getId());
        getWeatherByCityName.addProperty("location", city);

        getWeatherByCityName.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByCityName.callAPI();

        getWeatherByCityName.validateResponse();
    }
}
