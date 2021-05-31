package com.gogi.proj.aligo.util;

public class AligoVO {

	private String key;						//인증용 API Key
	private String userId;					// 사용자 id
	private String sender;					//발신자 전화번호 (최대 16btyes)
	private String receiver;				//수신자 전화번호 - 컴마(,) 분기 입력으로 최대 1000명
	private String msg;						//메세지 내용 String (1 ~ 2000Byte)
	private String msgType;					//SMS(단문), LMS(장문), MMS(그림문자) 구분
	private String title;					//문자 제목( LMS, MMS만 허용 )
	private String destination;				// %고객명% 치환용 입력
	private String rDate;					//예약일( 현재일이상) YYYYMMDD
	private String rTime;					//예약시간 - 현재시간 기준 10분 이후 HHII
	private String testmodeYn;				//연동테스트시 Y 적용	
	
	/**		Request
	 * 		POST /send/ HTTP/1.1
			Host: apis.aligo.in
			Service Port: 443
	 * curl -X POST "https://apis.aligo.in/send/" 
	--data-urlencode "key=xxxxx" 
	--data-urlencode "user_id=xxxxx" 
	--data-urlencode "sender=025114560" 
	--data-urlencode "receiver=01111111111,01111111112" \
	--data-urlencode "destination=01111111111|홍길동,01111111112|아무개" 
	--data-urlencode "msg=%고객명%님! 안녕하세요. API TEST SEND" 
	--data-urlencode "title=API TEST 입니다" 
	--data-urlencode "rdate=20200706" 
	--data-urlencode "rtime=1056" 
	--data-urlencode "testmode_yn=Y" 
	--form image=@localfilename
	 */
	
	
	/** 응답 바디 - JSON 
	 * 	result_code	결과코드(API 수신유무)	Integer
		message	결과 메세지( result_code 가 0 보다 작은경우 실패사유 표기)	String
		msg_id	메세지 고유 ID	Integer
		success_cnt	요청성공 건수	Integer
		error_cnt	요청실패 건수	Integer
		msg_type	메시지 타입 (1. SMS, 2.LMS, 3. MMS)	String
	 */
	
	
	/**		문자 보내기(대량)
	 * curl -X POST "https://apis.aligo.in/send_mass/" \
		--data-urlencode "key=xxxxx" \
		--data-urlencode "user_id=xxxxx" \
		--data-urlencode "sender=025114560" \
		--data-urlencode "rec_1=01111111111" \
		--data-urlencode "msg_1=안녕하세요. 홍길동님! 반갑습니다." \
		--data-urlencode "rec_2=01111111112" \
		--data-urlencode "msg_2=반갑습니다. 아무개님." \
		--data-urlencode "rec_3=01111111113" \
		--data-urlencode "msg_3=알리고에서 알려드립니다." \
		--data-urlencode "cnt=3" \
		--data-urlencode "msg_type=LMS" \
		--data-urlencode "title=알리고 공지" \
		--data-urlencode "rdate=20200706" \
		--data-urlencode "rtime=1056" \
		--data-urlencode "testmode_yn=Y" \
		--form image=@localfilename
	 * 
	 * 
	 * 
	 * 
	 * -- 응답
	 * result_code	결과코드(API 수신유무)	Integer
		message	결과 메세지( result_code 가 0 보다 작은경우 실패사유 표기)	String
		msg_id	메세지 고유ID	Integer
		success_cnt	요청성공 건수	Integer
		error_cnt	요청실패 건수	Integer
		msg_type	메시지 타입 (1. SMS, 2.LMS, 3. MMS)	String
	*/
	
	public AligoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AligoVO(aligoBuilder aligoBuilder) {
		super();
		this.key = aligoBuilder.aligoForm().key;
		this.userId = aligoBuilder.aligoForm().userId;
		this.sender = aligoBuilder.aligoForm().sender;
	}

	public AligoVO(String key, String userId, String sender, String receiver, String msg, String msgType, String title,
			String destination, String rDate, String rTime, String testmodeYn) {
		super();
		this.key = key;
		this.userId = userId;
		this.sender = sender;
		this.receiver = receiver;
		this.msg = msg;
		this.msgType = msgType;
		this.title = title;
		this.destination = destination;
		this.rDate = rDate;
		this.rTime = rTime;
		this.testmodeYn = testmodeYn;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	public String getrTime() {
		return rTime;
	}

	public void setrTime(String rTime) {
		this.rTime = rTime;
	}

	public String getTestmodeYn() {
		return testmodeYn;
	}

	public void setTestmodeYn(String testmodeYn) {
		this.testmodeYn = testmodeYn;
	}

	@Override
	public String toString() {
		return "AligoVO [key=" + key + ", userId=" + userId + ", sender=" + sender + ", receiver=" + receiver + ", msg="
				+ msg + ", msgType=" + msgType + ", title=" + title + ", destination=" + destination + ", rDate="
				+ rDate + ", rTime=" + rTime + ", testmodeYn=" + testmodeYn + "]";
	}
	
	
	class aligoBuilder {
		private String key = "zfg29xmik8qpil747n40b5iktnynl87b";						//인증용 API Key
		private String userId = "wwaudgns";					// 사용자 id
		private String sender = "07044061631";					//발신자 전화번호 (최대 16btyes)
		private String receiver;				//수신자 전화번호 - 컴마(,) 분기 입력으로 최대 1000명
		private String msg;						//메세지 내용 String (1 ~ 2000Byte)
		private String msgType;					//SMS(단문), LMS(장문), MMS(그림문자) 구분
		private String title;					//문자 제목( LMS, MMS만 허용 )
		private String destination;				// %고객명% 치환용 입력
		private String rDate;					//예약일( 현재일이상) YYYYMMDD
		private String rTime;					//예약시간 - 현재시간 기준 10분 이후 HHII
		private String testmodeYn;				//연동테스트시 Y 적용	
		
		
		public aligoBuilder() {
			super();
			// TODO Auto-generated constructor stub
		}

		public aligoBuilder(String receiver, String msg, String msgType, String title, String destination) {
			super();
			this.receiver = receiver;
			this.msg = msg;
			this.msgType = msgType;
			this.title = title;
			this.destination = destination;
		}

		public String getReceiver() {
			return receiver;
		}

		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getMsgType() {
			return msgType;
		}

		public void setMsgType(String msgType) {
			this.msgType = msgType;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}
		
		public AligoVO aligoForm() {
			return new AligoVO(this);
		}
		
	}
}
