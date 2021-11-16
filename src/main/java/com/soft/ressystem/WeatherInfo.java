package com.soft.ressystem;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Service
public class WeatherInfo {

	@Async
	public String getWeather() throws InterruptedException {

		String url = "https://api.openweathermap.org/data/2.5/weather?q=Espoo&appid=ac364fe5af282fb33a91949db1969019&units=metric";

		RestTemplate restTemplate = new RestTemplate();

		String result = restTemplate.getForObject(url, String.class);

		String str = "";

		Double doub = 1.0;

		Integer inte = 1;

		try {
			JSONObject jsonObjectTemp = new JSONObject(result);

			JSONArray jsonArrayDesc = (JSONArray) jsonObjectTemp.get("weather");

			jsonArrayDesc.put(jsonObjectTemp.get("weather"));

			jsonObjectTemp = (JSONObject) jsonObjectTemp.get("main");

			Thread.sleep(1000);

			if (jsonObjectTemp.get("temp").toString().contains(".")) {

				doub = (Double) (jsonObjectTemp.get("temp"));
				str = (String) "Should I go play outside? It is "
						+ ((JSONObject) jsonArrayDesc.get(0)).get("description") + " and " + doub
						+ " degrees out there.";

			} else {

				inte = (Integer) (jsonObjectTemp.get("temp"));
				str = (String) "Should I go play outside? It is "
						+ ((JSONObject) jsonArrayDesc.get(0)).get("description") + " and " + inte
						+ " degrees out there.";

			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return str;
	}
}
