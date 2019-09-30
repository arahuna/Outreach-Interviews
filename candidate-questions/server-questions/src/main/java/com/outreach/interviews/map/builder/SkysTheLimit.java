package com.outreach.interviews.map.builder;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SkysTheLimit {
	
	public static class LongLatFinder{
		//Set variables
		private String AddressNum, Street, City, Country;
		private JsonObject result;
		private final String URL = "https://maps.googleapis.com/maps/api/";
		private CloseableHttpClient httpClient = HttpClients.createDefault();
		
		/* Sets the location
		 * @param Number of the street, Street Name, City Name and Country as strings
		 * @return {@link LongLatFinder}
		 */
		public LongLatFinder setLocation(String Number, String Street, String City, String Country) {
			this.AddressNum = Number;
			this.Street = Street;
			this.City = City;
			this.Country = Country;
			return this;
		}
		/*Performs the HTTP exchange
		 * @param None
		 * @return {@link LongLatFinder}
		 */
		public LongLatFinder build() throws UnsupportedOperationException, IOException, IllegalArgumentException{
			String requestURL = this.getURL() + "address=" + this.createAddress() + "&key=" + this.getAPIKey();
			
			HttpGet httpGet = new HttpGet(requestURL);
			
			CloseableHttpResponse response =  httpClient.execute(httpGet);
			
			try {
				HttpEntity entity = response.getEntity();
				String result = IOUtils.toString(entity.getContent(), "UTF-8");
				this.result = new JsonParser().parse(result).getAsJsonObject();
			}finally {
				response.close();
			}
			return this;
		}
		
		/*Find the longitude for the location
		 * @param None
		 * @return The longitude of the location in string format
		 */
		public String getLong(){
			String Lng = this.result.get("results")
									 .getAsJsonArray().get(0)
									 .getAsJsonObject().get("geometry")
									 .getAsJsonObject().get("location")
									 .getAsJsonObject().get("lng").getAsString();
			return Lng;
		}
		
		/* Finds the latitude for the given location
		 * @param None
		 * @return the latitude of the location in string format
		 */
		public String getLat() {
			String Lat = this.result.get("results")
						.getAsJsonArray().get(0)
					 	.getAsJsonObject().get("geometry")
					 	.getAsJsonObject().get("location")
					 	.getAsJsonObject().get("lat").getAsString();
			return Lat;
		}
		
		/*Creates the base URL needed to access the API
		 * @param None
		 * @return String containing the base of the URL
		 */
		private final String getURL() {
			String requestURL = this.URL + "geocode/json?";
			return requestURL;
		}
		
		/* Gets the API Key for the API
		 * @param none
		 * @return The API key in string format
		 */
		private final String getAPIKey() {
			return System.getenv("OUTREACH_MAPS_KEY");
		}
		/*
		 * Creates an address String that can be used as a URL
		 * @param None
		 * @return String with the address in API format
		 */
		private final String createAddress() {
			String AddressCall = this.AddressNum + " " + this.Street + ", " + this.City + ", " + this.Country;
			AddressCall = AddressCall.replace(" ", "+");
			return AddressCall;
		}
	}
}
