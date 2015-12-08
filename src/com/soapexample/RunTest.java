package com.soapexample;

import java.io.FileNotFoundException;

public class RunTest {
	
	
	public static void main(String args[]) throws FileNotFoundException {

		Weather weather = new Weather();
		String xml = weather.getWeather();
		System.out.println(weather.isSuccess(xml));
	}

}
