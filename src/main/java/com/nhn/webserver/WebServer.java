package com.nhn.webserver;

import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.nhn.controller.Controller;

public class WebServer {

	//log trace
	private static final Logger log = LoggerFactory.getLogger(WebServer.class);
	
    public static void main(String argv[]) throws Exception {
        // 서버소켓을 생성한다.설정파일에 지정한 포트를 사용한다. 
    	ServerConfig.initConfig(new File("./src/main/resources/serverConfig.json"));
    	
	//	Gson gson = new Gson();
	//	ServerConfig serverConfig = gson.fromJson(new FileReader("./src/main/resources/serverConfig.json"), ServerConfig.class);


    	try (ServerSocket listenSocket = new ServerSocket(ServerConfig.getPort())) {
    		log.info("Web Application Server started {} port.", ServerConfig.getPort());
 
            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
            	RequestHandler requestHandler = new RequestHandler(connection);
                requestHandler.start();
            }
    	}
    }
}

