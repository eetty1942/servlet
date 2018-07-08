package com.nhn.webserver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.nhn.controller.Controller;
import com.nhn.controller.Controller.Urlmap;
import com.nhn.model.Host;

public class ServerConfig {
	private static final Logger log = LoggerFactory.getLogger(ServerConfig.class);
	private static int port;
	private static Map<String, Host> map = new HashMap<String, Host>();

	static {

	}

	public static Map<String, Host> getMap() {
		return map;
	}

	public static void setMap(Map<String, Host> newMap) {

	}

	// json file mapping
	@SuppressWarnings("unchecked")
	public static void initConfig(File jsonFile){
		try {
			
			
			
	    	JSONParser parser = new JSONParser();
	    	Object obj = parser.parse(new FileReader("./src/main/resources/serverConfig.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray array = (JSONArray) jsonObject.get("hosts");
			
			port = Integer.parseInt(jsonObject.get("port").toString());
			
			for (Object obj1 : array.toArray()) {
				JSONObject hos = (JSONObject) obj1;
				//String json = hos.to
				
				
			}
			
			
			for(int i=0; i<array.size(); i++){ 
				 
				Host host = new  Host();
				JSONObject result = (JSONObject) array.get(i);
				
				host.setDomainName(result.get("domainName").toString());
				host.setDocumentRoot(result.get("documentRoot").toString());		
				host.setWelcomeFile(result.get("welcomeFile").toString());
				
				Map<String, Controller> requserMap = (Map<String, Controller>) result.get("servlet");
				
				
				
				for(int j=0; j<array.size(); i++) {
					
				}
					
				
//				String path = "com.nhn."+result.get("PackagePath").toString()+
//				Class<Controller> controllerClass = (Class<Controller>) Class.forName(path);
//				controller = controllerClass.newInstance();
				
				host.setRequestMap(requserMap);
				
				map.put(result.get("domainName").toString(), host);
				
				
			}
			
		}catch (Exception e) {
			log.debug("error reading JSON file!");
			e.printStackTrace();
		}
	}
	public static int getPort() {
		return port;
	}
	public static Map<String, Host> getHostMap() {
		return map;
	}
	
}