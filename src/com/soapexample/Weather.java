package com.soapexample;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.path.xml.XmlPath;

public class Weather {
	
	final static String WEATHER_ENVELOPE= "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:weat=\"http://ws.cdyne.com/WeatherWS/\"><soapenv:Header/><soapenv:Body><weat:GetCityForecastByZIP><!--Optional:--><weat:ZIP>YYY</weat:ZIP></weat:GetCityForecastByZIP></soapenv:Body></soapenv:Envelope>";
	
	
	public String getWeather(final String zipCode){
		
		String weather = WEATHER_ENVELOPE.replace("YYY", zipCode);
		return given()
				.request()
				.header("SOAPAction",
						"http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP")
				.contentType("text/xml;charset=UTF-8").body(weather).expect()
				.statusCode(200).when()
				.post("http://wsf.cdyne.com/WeatherWS/Weather.asmx").asString();
		
		
	}
	
	public boolean isSuccess(String xml){
		XmlPath path = new XmlPath(xml);
		return (path.get("Envelope.Body.GetCityForecastByZIPResponse.GetCityForecastByZIPResult.Success").equals("true"));
	}

}
