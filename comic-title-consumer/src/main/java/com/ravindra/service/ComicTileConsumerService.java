package com.ravindra.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ravindra.exception.ComicServiceNotFoundException;

/**
 * This is the business layer to call the third party Comic Rest API
 * 
 * @author Veera Shankara RavindraReddy Kakarla
 *
 */
@Service
@RefreshScope
public class ComicTileConsumerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${comic.url}")
	private String comicUrl;
	/**
	 * This calls the Comic Rest API and gets the comic key from Comic Json object
	 * 
	 * @return comic title
	 */
	public String getComicTitle() {
		logger.info("Begin ComicTileConsumerService - getComicTitle()");
		RestTemplate template = new RestTemplate();
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpResponse response = client.execute(new HttpGet(comicUrl));
			int statusCode = response.getStatusLine().getStatusCode();
			/*
			 * URL url = new URL(comicUrl); HttpURLConnection connection =
			 * (HttpURLConnection) url.openConnection(); connection.setRequestMethod("GET");
			 * connection.setConnectTimeout(3000); connection.connect(); int statusCode =
			 * connection.getResponseCode();
			 */

			if (statusCode == 404) {
				logger.error("ComicServiceNotFoundException occured in ComicTileConsumerService "+statusCode);
				throw new ComicServiceNotFoundException(
						"Comic Service is not available at this time. Please try again after sometime.");
			}
		} catch (IOException ie) {
			logger.error("IOException occured in ComicTileConsumerService "+ie.getMessage());
		}
		String title = "";
		try {
			String json = template.getForObject(comicUrl, String.class);
			JSONObject jsonObject = new JSONObject(json);
			title = (String) jsonObject.get("safe_title");
			System.out.println("title : " + title);
		} catch (JSONException e) {
			logger.error("JSONException occured in ComicTileConsumerService "+e.getMessage());
		}
		logger.info("End ComicTileConsumerService - getComicTitle()");
		return title;
	}
}