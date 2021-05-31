package com.gogi.proj.websocket;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.security.AdminVO;
import com.gogi.proj.util.FileuploadUtil;

@ServerEndpoint("/broadcasting.do")
public class BroadSocket {

	private FileuploadUtil fileUploadUtil;

	private static final List<Session> sessionList = new ArrayList<Session>();;
	private static final Logger logger = LoggerFactory.getLogger(BroadSocket.class);

	final static String filePath = "C:\\Users\\3bgogi\\Desktop\\opera_repository\\3bgogiOpera\\src\\main\\webapp\\resources\\pds_upload\\web_socket_upload\\";
	static File uploadedFile = null;
	static String fileName = null;
	static FileOutputStream fos = null;

	public BroadSocket() {
		// TODO Auto-generated constructor stub

	}

	@RequestMapping(value = "/chat.do")
	public ModelAndView getChatViewPage(ModelAndView mav) {
		mav.setViewName("chat");
		return mav;
	}

	@OnOpen
	public void onOpen(Session session) {
		session.setMaxIdleTimeout(0);
		try {
			/*
			 * final Basic basic = session.getBasicRemote();
			 * basic.sendText("Connection Established");
			 */
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		sessionList.add(session);
	}

	/*
	 * 모든 사용자에게 메시지를 전달한다.
	 * 
	 * @param self
	 * 
	 * @param message
	 */
	private void sendAllSessionToMessage(Session self, String message) {
		try {
			for (Session session : BroadSocket.sessionList) {
				if (!self.getId().equals(session.getId())) {
					if(message.contains("filename:")) {
						session.getBasicRemote().sendText(message);
					}else {						
						session.getBasicRemote().sendText(message.split(",")[1] + " : " + message.split(",")[0]);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	@OnMessage
	public void processUpload(ByteBuffer msg, boolean last, Session session) {
		// BufferedOutputStream bos = new BufferedOutputStream();
		while(msg.hasRemaining()) {         
            try {
                fos.write(msg.get());
            } catch (IOException e) {               
                e.printStackTrace();
            }
        }
		
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		
		
		if (message.contains("filenameEnded*")) {
			message = message.substring(message.indexOf("filenameEnded*") + 14);
			try {
				final Basic basic = session.getBasicRemote();
				basic.sendText("나 : " + message.split(",")[0]);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			sendAllSessionToMessage(session, message);
			
		}else {			
			if (!message.equals("end*")) {
				fileName = message.substring(message.indexOf(':') + 1);
				uploadedFile = new File(filePath + fileName);
				
				try {
					fos = new FileOutputStream(uploadedFile);
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			} else {
				
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {					
					sendAllSessionToMessage(session, "filename:"+fileName);
				}
				
			}
			
		}
	}

	@OnError
	public void onError(Throwable e, Session session) {

	}

	@OnClose
	public void onClose(Session session) {
		sessionList.remove(session);
	}

}
