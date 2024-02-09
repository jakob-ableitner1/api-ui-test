package com.solvd.apiuitest.api;

import com.solvd.apiuitest.api.method.GetWeatherByCityCode;
import com.solvd.apiuitest.api.method.GetWeatherByCityNameAndCountryCode;
import com.solvd.apiuitest.api.method.GetWeatherByCoordinates;
import com.solvd.apiuitest.api.domain.City;
import com.solvd.apiuitest.api.domain.Country;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.Test;

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
