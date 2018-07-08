package com.nhn.model;

import java.util.Map;

public class Host {

	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDocumentRoot() {
		return documentRoot;
	}
	public void setDocumentRoot(String documentRoot) {
		this.documentRoot = documentRoot;
	}
	public String getWelcomeFile() {
		return welcomeFile;
	}
	public void setWelcomeFile(String welcomeFile) {
		this.welcomeFile = welcomeFile;
	}
	public Map<?, ?> getRequestMap() {
		return requestMap;
	}
	public void setRequestMap(Map<?, ?> requestMap) {
		this.requestMap = requestMap;
	}
	private String domainName;
	private String documentRoot;
	private String welcomeFile;
	private Map<?, ?> requestMap;
	
	
}
