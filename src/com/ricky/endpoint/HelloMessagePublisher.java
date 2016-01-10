package com.ricky.endpoint;

import javax.xml.ws.Endpoint;

import com.ricky.service.HelloMessageImpl;


//Publish the web service
public class HelloMessagePublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/hello", new HelloMessageImpl());
	}

}
