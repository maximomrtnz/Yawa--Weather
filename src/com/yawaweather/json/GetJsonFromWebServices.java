package com.yawaweather.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

//This Class get a JSON from WebService 


public class GetJsonFromWebServices {
	
	public String readJSON(String url) {
	    StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    //HttpGet httpGet = new HttpGet("http://query.yahooapis.com/v1/public/yql?q=select*from%20geo.places%20where%20text=%22New%20York%22&format=json");
	    HttpGet httpGet = new HttpGet(url);
	    httpGet.setHeader("Content-type", "application/json");
	    try {
	      HttpResponse response = client.execute(httpGet);
	      StatusLine statusLine = response.getStatusLine();
	      int statusCode = statusLine.getStatusCode();
	      if (statusCode == 200) {
	        HttpEntity entity = response.getEntity();
	        InputStream content = entity.getContent();
	        //json is UTF-8 by default i beleive
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"), 8);
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	      } else {
	        Log.e("Network Error", "Failed to download file");
	      }
	    } catch (ClientProtocolException e) {
	      Log.e("Client Protocol Exception",e.toString());
	    } catch (IOException e) {
	      Log.e("IO Exception", e.toString());
	    }
	    return builder.toString();
	  }
}
