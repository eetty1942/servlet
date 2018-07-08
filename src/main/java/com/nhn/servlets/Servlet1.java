package com.nhn.servlets;

import com.nhn.controller.Controller;
import com.nhn.http.Request;
import com.nhn.http.Response;
import com.nhn.http.ResponseFactory;

public class Servlet1 implements Controller{
	@Override
	public Response service(Request rq){
		System.out.println("test1");
		return ResponseFactory.get200Json(rq.getCookie(), "UTF-8");
	}
}
