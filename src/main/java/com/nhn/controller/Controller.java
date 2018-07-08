package com.nhn.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nhn.http.Request;
import com.nhn.http.Response;


public interface Controller {
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface Urlmap {
		public String url();
	}

	Response service(Request rq);
}
