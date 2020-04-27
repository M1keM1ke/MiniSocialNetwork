package com.example.sweater.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeatherParse {
    private static Map<String, String> weatherInfo = new HashMap<>();

    public static Document getPage() {
        String url = "https://world-weather.ru/pogoda/russia/moscow/";
        Document page = null;

        try {
            page = Jsoup.parse(new URL(url), 5000); //время ожидания
        } catch (IOException e) {
            e.printStackTrace();
        }

        return page;
    }

    public static Map<String, String> getParseInfoWeather() {
        Document page = getPage();
        Element columnNames = page.select("table.weather-today").first();

        String weatherTemperatureHead = columnNames.select("#weather-temperature").text();
        String weatherFeelingHead = columnNames.select("#weather-feeling").text();
        String weatherProbabilityHead = columnNames.select("#weather-probability").text();
        String weatherPressureHead = columnNames.select("#weather-pressure").text();
        String weatherWindHead = columnNames.select("#weather-wind").text();
        String weatherHumidityHead = columnNames.select("#weather-humidity").text();

        Element nightRow = page.selectFirst("tr.night");
        String night = nightRow.select("td.weather-day").text();
        String nightAtmosphericPhenomenon = nightRow.select("div").attr("title");
        String nightWeatherTemperature = nightRow.selectFirst("span").text();
        String nightWeatherFeeling = nightRow.select("td.weather-feeling").text();
        String nightWeatherProbability = nightRow.select("td.weather-probability").text();
        String nightWeatherPressure = nightRow.select("td.weather-pressure").text();
        String nightWeatherWind = nightRow.select("td.weather-wind").text();
        String nightWeatherHumidity = nightRow.select("td.weather-humidity").text();

        Element morningRow = page.selectFirst("tr.morning");
        String morning = morningRow.select("td.weather-day").text();
        String morningAtmosphericPhenomenon = morningRow.select("div").attr("title");
        String morningWeatherTemperature = morningRow.selectFirst("span").text();
        String morningWeatherFeeling = morningRow.select("td.weather-feeling").text();
        String morningWeatherProbability = morningRow.select("td.weather-probability").text();
        String morningWeatherPressure = morningRow.select("td.weather-pressure").text();
        String morningWeatherWind = morningRow.select("td.weather-wind").text();
        String morningWeatherHumidity = morningRow.select("td.weather-humidity").text();

        Element dayRow = page.selectFirst("tr.day");
        String day = dayRow.select("td.weather-day").text();
        String dayAtmosphericPhenomenon = dayRow.select("div").attr("title");
        String dayWeatherTemperature = dayRow.selectFirst("span").text();
        String dayWeatherFeeling = dayRow.select("td.weather-feeling").text();
        String dayWeatherProbability = dayRow.select("td.weather-probability").text();
        String dayWeatherPressure = dayRow.select("td.weather-pressure").text();
        String dayWeatherWind = dayRow.select("td.weather-wind").text();
        String dayWeatherHumidity = dayRow.select("td.weather-humidity").text();

        Element eveningRow = page.selectFirst("tr.evening");
        String evening = eveningRow.select("td.weather-day").text();
        String eveningAtmosphericPhenomenon = eveningRow.select("div").attr("title");
        String eveningWeatherTemperature = eveningRow.selectFirst("span").text();
        String eveningWeatherFeeling = eveningRow.select("td.weather-feeling").text();
        String eveningWeatherProbability = eveningRow.select("td.weather-probability").text();
        String eveningWeatherPressure = eveningRow.select("td.weather-pressure").text();
        String eveningWeatherWind = eveningRow.select("td.weather-wind").text();
        String eveningWeatherHumidity = eveningRow.select("td.weather-humidity").text();

        weatherInfo.put("weatherTemperatureHead", weatherTemperatureHead);
        weatherInfo.put("weatherFeelingHead", weatherFeelingHead);
        weatherInfo.put("weatherProbabilityHead", weatherProbabilityHead);
        weatherInfo.put("weatherPressureHead", weatherPressureHead);
        weatherInfo.put("weatherWindHead", weatherWindHead);
        weatherInfo.put("weatherHumidityHead", weatherHumidityHead);

        weatherInfo.put("night", night);
        weatherInfo.put("nightAtmosphericPhenomenon", nightAtmosphericPhenomenon);
        weatherInfo.put("nightWeatherTemperature", nightWeatherTemperature);
        weatherInfo.put("nightWeatherFeeling", nightWeatherFeeling);
        weatherInfo.put("nightWeatherProbability", nightWeatherProbability);
        weatherInfo.put("nightWeatherPressure", nightWeatherPressure);
        weatherInfo.put("nightWeatherWind", nightWeatherWind);
        weatherInfo.put("nightWeatherHumidity", nightWeatherHumidity);

        weatherInfo.put("morning", morning);
        weatherInfo.put("morningAtmosphericPhenomenon", morningAtmosphericPhenomenon);
        weatherInfo.put("morningWeatherTemperature", morningWeatherTemperature);
        weatherInfo.put("morningWeatherFeeling", morningWeatherFeeling);
        weatherInfo.put("morningWeatherProbability", morningWeatherProbability);
        weatherInfo.put("morningWeatherPressure", morningWeatherPressure);
        weatherInfo.put("morningWeatherWind", morningWeatherWind);
        weatherInfo.put("morningWeatherHumidity", morningWeatherHumidity);

        weatherInfo.put("day", day);
        weatherInfo.put("dayAtmosphericPhenomenon", dayAtmosphericPhenomenon);
        weatherInfo.put("dayWeatherTemperature", dayWeatherTemperature);
        weatherInfo.put("dayWeatherFeeling", dayWeatherFeeling);
        weatherInfo.put("dayWeatherProbability", dayWeatherProbability);
        weatherInfo.put("dayWeatherPressure", dayWeatherPressure);
        weatherInfo.put("dayWeatherWind", dayWeatherWind);
        weatherInfo.put("dayWeatherHumidity", dayWeatherHumidity);

        weatherInfo.put("evening", evening);
        weatherInfo.put("eveningAtmosphericPhenomenon", eveningAtmosphericPhenomenon);
        weatherInfo.put("eveningWeatherTemperature", eveningWeatherTemperature);
        weatherInfo.put("eveningWeatherFeeling", eveningWeatherFeeling);
        weatherInfo.put("eveningWeatherProbability", eveningWeatherProbability);
        weatherInfo.put("eveningWeatherPressure", eveningWeatherPressure);
        weatherInfo.put("eveningWeatherWind", eveningWeatherWind);
        weatherInfo.put("eveningWeatherHumidity", eveningWeatherHumidity);

        return weatherInfo;
    }
}
