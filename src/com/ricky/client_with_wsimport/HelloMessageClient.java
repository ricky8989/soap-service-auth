package com.ricky.client_with_wsimport;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

public class HelloMessageClient {

	public static void main(String[] args) {
		HelloMessageImplService service = new HelloMessageImplService();
		HelloMessage message = service.getHelloMessageImplPort();

		/*******************UserName & Password ******************************/
        Map<String, Object> requestContext = ((BindingProvider)message).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, service.getWSDLDocumentLocation().toString());
        
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Username", Collections.singletonList("Ricky1"));
        headers.put("Password", Collections.singletonList("Password1"));
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        /**********************************************************************/
		
		System.out.println(message.getMessage());
	}

}
