package com.gogi.proj.websocket;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ServerEndpoint("/ccsocket.do")
public class ClassifyCenterSocket {

	private static final List<Session> sessionList = new ArrayList<Session>();;
	private static final Logger logger = LoggerFactory.getLogger(BroadSocket.class);

	public ClassifyCenterSocket() {
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
		logger.info("Open session id:" + session.getId());
		try {
			/*final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Established");*/
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
			for (Session session : ClassifyCenterSocket.sessionList) {
				if (!self.getId().equals(session.getId())) {
					session.getBasicRemote().sendText(message.split(",")[1] + " : " + message.split(",")[0]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("나 : " + message.split(",")[0]);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		sendAllSessionToMessage(session, message);
	}

	@OnError
	public void onError(Throwable e, Session session) {

	}

	@OnClose
	public void onClose(Session session) {
		sessionList.remove(session);
	}
}
