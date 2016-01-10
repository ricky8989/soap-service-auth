package com.ricky.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;

import com.ricky.service.HelloMessage;

public class HelloMessageClient {

	private static final String WS_WSDL_LOCATION = "http://localhost:9999/ws/hello?wsdl";
	private static final String WS_NAMESPACE = "http://service.ricky.com/"; //namespace URL..see wsdl content
	private static final String WS_NAME = "HelloMessageImplService";//service name ...see wsdl content 
	
	public static void main(String[] args) {
		try {
			URL wsdlLocation = new URL(WS_WSDL_LOCATION);
			QName qName = new QName(WS_NAMESPACE,WS_NAME);
			Service service = Service.create(wsdlLocation, qName);
			HelloMessage serviceMessage = service.getPort(HelloMessage.class);

			/*******************UserName & Password ******************************/
	        Map<String, Object> requestContext = ((BindingProvider)serviceMessage).getRequestContext();
	        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_WSDL_LOCATION);

	        Map<String, List<String>> headers = new HashMap<String, List<String>>();
	        headers.put("Username", Collections.singletonList("Ricky1"));
	        headers.put("Password", Collections.singletonList("Password1"));
	        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
	        /**********************************************************************/
	        
	        System.out.println("Message : "+serviceMessage.getMessage());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Unable to find wsdl "+WS_WSDL_LOCATION);
		}

	}

}
