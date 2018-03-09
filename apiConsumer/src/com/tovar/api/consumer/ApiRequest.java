package com.tovar.api.consumer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * @author tovar
 *
 */
public class ApiRequest {
	private static  String apiUrl ="";
	private static HttpURLConnection con;
	
	private static HttpsURLConnection getConnection(String url){
		
		HttpsURLConnection conection = null;
		try {
			URL obj = new URL(url);
			conection = (HttpsURLConnection) obj.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conection;
	}
	
	public static void makeCall(String method,String parameters) throws Exception{
		
		if(method.equalsIgnoreCase("GET")){apiUrl="https://jsonplaceholder.typicode.com/posts/4";}
		if(method.equalsIgnoreCase("POST")){apiUrl="https://jsonplaceholder.typicode.com/posts";}
		if(method.equalsIgnoreCase("PUT")){apiUrl="https://jsonplaceholder.typicode.com/posts/1";}
		if(method.equalsIgnoreCase("PATCH")){apiUrl="https://jsonplaceholder.typicode.com/posts/1";}
		if(method.equalsIgnoreCase("DELETE")){apiUrl="https://jsonplaceholder.typicode.com/posts/1";}
		
		con = getConnection(apiUrl);
		
		//override to make the call by POST
		if(method.equalsIgnoreCase("PATCH")){
			con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
			method = "POST";
		}

		// add headers to request
		con.setRequestMethod(method);
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "es-mx,en-US,en;q=0.5");
		
		// For this example I dont't need parameters for the GET call
		if(!method.equalsIgnoreCase("GET")){
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(parameters);
			wr.flush();
			wr.close();
		}
		
		// Send request
		int responseCode = con.getResponseCode();
		System.out.println("\nSending "+ method +" request to URL : " + apiUrl);
		System.out.println("Parameters : " + parameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(response.toString());
		
	}
}
