package com.ricky.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

//similar to: http://www.mkyong.com/webservices/jax-ws/application-authentication-with-jax-ws/

@WebService(endpointInterface="com.ricky.service.HelloMessage")
public class HelloMessageImpl implements HelloMessage {

	@Resource
	WebServiceContext webServiceContext;
	
	@Override
	public String getMessage() {
		MessageContext messageContext = webServiceContext.getMessageContext();
		//get detail from the request header
		Map<?,?> httpHeaders = (Map<?,?>)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
		List<?> userNameList = (List<?>) httpHeaders.get("username");
		List<?> passwordList = (List<?>) httpHeaders.get("password");
		
		String userName="";
		String password="";
		
		if(userNameList != null){
			userName = userNameList.get(0).toString();
		}
		
		if(passwordList != null){
			password = passwordList.get(0).toString();
		}
		
		//Should validate username and password with value from database.
		
		if(userName.equals("Ricky1") && password.equals("Password1")){
			return "Hello from JAX-WS - Valid user";
		}else {
			return "Invalid user name or password ["+userName+"/"+password+"]";
		}
	}

}
