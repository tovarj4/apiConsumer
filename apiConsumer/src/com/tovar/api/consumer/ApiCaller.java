package com.tovar.api.consumer;


/**
 * @author tovar
 *
 */

public class ApiCaller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	         ApiRequest.makeCall("GET", "title=foo=&body=bar&userId=1");
	         ApiRequest.makeCall("POST", "title=foo=&body=bar&userId=1");
	         ApiRequest.makeCall("PATCH", "title=foo=&body=bar&userId=1");
	         ApiRequest.makeCall("PUT", "title=foo=&body=bar&userId=1");
	         ApiRequest.makeCall("DELETE", "title=foo=&body=bar&userId=1");
	        } catch (Exception e) {
	         e.printStackTrace();
	       }
	}

}
