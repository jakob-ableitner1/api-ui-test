package com.solvd.weatherapitest;

import com.solvd.weatherapitest.api.GetWeatherByCityCode;
import com.solvd.weatherapitest.api.GetWeatherByCityNameAndCountryCode;
import com.solvd.weatherapitest.api.GetWeatherByCoordinates;
import com.solvd.weatherapitest.domain.City;
import com.solvd.weatherapitest.domain.Country;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.crypto.CryptoConsole;
import javassist.tools.reflect.Reflection;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class WeatherTest {

    @Test
    public void verifyGetWeatherByCoordinates(){

        Country country = new Country();
        country.setId("2001797");
        country.setName("DE");

        City location = new City();
        location.setName("Lower Franconia");
        location.setId("2819564");
        location.setLatitude(50.0);
        location.setLongitude(10.0);
        location.setTimezone(3600);
        location.setCountry(country);

        GetWeatherByCoordinates getWeatherByCoordinates = new GetWeatherByCoordinates(50.0, 10.0 );
        getWeatherByCoordinates.addProperty("location", location);

        getWeatherByCoordinates.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByCoordinates.callAPI();

        getWeatherByCoordinates.validateResponse();
    }

    @Test
    public void verifyGetWeatherByCoordinatesWithoutVerification() {
        Country country = new Country();
        country.setId("2001797");
        country.setName("DE");

        City location = new City();
        location.setName("Lower Franconia");
        location.setId("2819564");
        location.setLatitude(50.0);
        location.setLongitude(10.0);
        location.setTimezone(3600);
        location.setCountry(country);

        GetWeatherByCoordinates getWeatherByCoordinates = new GetWeatherByCoordinates(50.0, 10.0, false);
        getWeatherByCoordinates.addProperty("location", location);

        getWeatherByCoordinates.expectResponseStatus(HttpResponseStatusType.UNAUTHORIZED_401);
        getWeatherByCoordinates.callAPI();

        getWeatherByCoordinates.validateResponse();
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

        GetWeatherByCityCode getWeatherByCityName = new GetWeatherByCityCode(city.getId(), "api/data/2.5/get_weather_rs.json");
        getWeatherByCityName.addProperty("location", city);

        getWeatherByCityName.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByCityName.callAPI();

        getWeatherByCityName.validateResponse();
    }

    @Test
    public void verifyGetWeatherByInvalidCityCode(){

        GetWeatherByCityCode getWeatherByCityName = new GetWeatherByCityCode("1", "api/data/2.5/get_weather_rs_city_not_found.json");

        getWeatherByCityName.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        getWeatherByCityName.callAPI();

        getWeatherByCityName.validateResponse();
    }
}
