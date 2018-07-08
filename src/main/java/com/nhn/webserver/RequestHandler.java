package com.nhn.webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.http.Request;
import com.nhn.http.Response;
import com.nhn.model.Host;

public class RequestHandler extends Thread {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
	
	private Socket connection;

	public RequestHandler(Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	public void run() {

		
		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
			DataOutputStream dos = new DataOutputStream(out);
			Request rq = new Request(in);
			
			System.out.println("url : " + rq.getServerName());
			System.out.println("url : " + rq.getServerName());
			Host host = null;
			for(Entry<String, Host> entry : ServerConfig.getHostMap().entrySet()) {
				if(entry.getKey().equals(rq.getServerName())) {
					host = entry.getValue();
					System.out.println("Docu Root : " + host.getDocumentRoot());
				}
			}
			
			// 방어로직 추
			
			RequestMapper rm = new RequestMapper(host.getDocumentRoot(), RequestMap.getMap(), "UTF-8");
			//rm.getResponse(rq);
			// 로그 표현 수정 
			log.debug("New Client Connect! Connected IP : {}, Port : {} " + "URL : " + rq.getUrl() + ""  , connection.getInetAddress(), connection.getPort());
			response(dos, rm.getResponse(rq)); 
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	//response객체를 받아서 header와 body를 차례로 전송
	private void response(DataOutputStream dos, Response rp) {
		try {
			byte[] header = rp.getHeader();
			byte[] body = rp.getBody();
			dos.write(header, 0, header.length);
			dos.write(body, 0, body.length);
			dos.writeBytes("\r\n");
			dos.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
