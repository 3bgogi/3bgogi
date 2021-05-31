package com.gogi.proj.epost.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegDataVO {

	private String custNo = "0004610509";		//우체국 고객번호
	private int reqType = 1;					//신청구분
	private String officeSer = "01";			//공급지코드 기 등록된 공급지 코드
	private int weight = 2;						//무게(kg)
	private int volume = 30;					//부피(cm)
	private String ordCompNm;					//고객주문처명
	private String ordNm = "테스트 주문자";			//주문자명
	private String ordZip = "21126";			//주문자우편번호
	private String ordAddr1 = "인천광역시 계양구 효서로 388";//주문자우편번호주소
	private String ordAddr2 = "3층";				//주문자상세주소
	private String ordTel = "0507-1312-1620";	//주문자전화번호
	private String ordMob;						//주문자핸드폰번호
	private String recNm;						//수취인명
	private String recZip;						//수취인 우편번호
	private String recAddr1;					//수취인주소
	private String recAddr2;					//수취인상세주소
	private String recTel;						//수취인전화번호
	private String recMob;						//수취인이동통신
	private String apprNo = "4003181560";		//승인번호
	private int payType =  1;					//요금납부구분 1 : 일반 / 2: 수취인부담 , 우리는 1
	private String microYn = "N";				//초소형택배구분 (Y/N) , 우리는 N
	private String contCd = "022";				//주요내용품코드, 우리는 022
	private String goodsNm;						//상품명
	private String goodsCd;						//상품코드
	private String goosMdl;						//상품모델
	private String goodsSize;					//상품사이즈
	private String goodsColor;					//색상
	private String qty="1";						//수량
	private String orderNo;						//주문번호
	private String delivMsg= "";				//배송메세지
	private String retReason;					//반품사유
	private String retVisitYmd;					//상품희망방문일
	private String testYn = "N";				//테스트 신청 여부
	private String printYn = "N";				//자체출력여부
	
	
	//추가사항
	private String orPk;
	
	//응답메세지
	private String reqNo;						//우체국택배신청번호(건당부여)
	private String resNo;						//예약번호(일자당 부여)
	private String regiNo;						//운송장번호
	private String regiPoNm;					//접수우체국
	private String resDate;						//예약일시
	private int price;							//예상접수요금
	private String vTelNo;						//가상전화번호
	private String arrCnpoNm;					//도착집중국명
	private String delivPoNm;					//배달우체국명
	private String delivAreaCd;					//배달구구분코드
	private int ediPk;							//고유값
	
	private String cancelregino;				//취소(삭제) 운송장번호
	private String cancelDate;					//취소일시
	private String canceledyn;					//취소결과 여부 Y:취소, N:미취소, D:삭제
	private String notcancelReason;				//미 취소 사유
	private String resno;
	private String reqno;
	private String error_code;
	private String message;
	
	private String canceledYn;
	private String cancelRegiNo;
	private String notCancelReason;
	
	public RegDataVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RegDataVO(String custNo, int reqType, String officeSer, int weight, int volume, String ordCompNm,
			String ordNm, String ordZip, String ordAddr1, String ordAddr2, String ordTel, String ordMob, String recNm,
			String recZip, String recAddr1, String recAddr2, String recTel, String recMob, String apprNo, int payType,
			String microYn, String contCd, String goodsNm, String goodsCd, String goosMdl, String goodsSize,
			String goodsColor, String qty, String orderNo, String delivMsg, String retReason, String retVisitYmd,
			String testYn, String printYn, String orPk, String reqNo, String resNo, String regiNo, String regiPoNm,
			String resDate, int price, String vTelNo, String arrCnpoNm, String delivPoNm, String delivAreaCd,
			int ediPk) {
		super();
		this.custNo = custNo;
		this.reqType = reqType;
		this.officeSer = officeSer;
		this.weight = weight;
		this.volume = volume;
		this.ordCompNm = ordCompNm;
		this.ordNm = ordNm;
		this.ordZip = ordZip;
		this.ordAddr1 = ordAddr1;
		this.ordAddr2 = ordAddr2;
		this.ordTel = ordTel;
		this.ordMob = ordMob;
		this.recNm = recNm;
		this.recZip = recZip;
		this.recAddr1 = recAddr1;
		this.recAddr2 = recAddr2;
		this.recTel = recTel;
		this.recMob = recMob;
		this.apprNo = apprNo;
		this.payType = payType;
		this.microYn = microYn;
		this.contCd = contCd;
		this.goodsNm = goodsNm;
		this.goodsCd = goodsCd;
		this.goosMdl = goosMdl;
		this.goodsSize = goodsSize;
		this.goodsColor = goodsColor;
		this.qty = qty;
		this.orderNo = orderNo;
		this.delivMsg = delivMsg;
		this.retReason = retReason;
		this.retVisitYmd = retVisitYmd;
		this.testYn = testYn;
		this.printYn = printYn;
		this.orPk = orPk;
		this.reqNo = reqNo;
		this.resNo = resNo;
		this.regiNo = regiNo;
		this.regiPoNm = regiPoNm;
		this.resDate = resDate;
		this.price = price;
		this.vTelNo = vTelNo;
		this.arrCnpoNm = arrCnpoNm;
		this.delivPoNm = delivPoNm;
		this.delivAreaCd = delivAreaCd;
		this.ediPk = ediPk;
	}

	
	public String getNotCancelReason() {
		return notCancelReason;
	}

	public void setNotCancelReason(String notCancelReason) {
		this.notCancelReason = notCancelReason;
	}

	public String getCanceledYn() {
		return canceledYn;
	}

	public void setCanceledYn(String canceledYn) {
		this.canceledYn = canceledYn;
	}

	public String getCancelRegiNo() {
		return cancelRegiNo;
	}

	public void setCancelRegiNo(String cancelRegiNo) {
		this.cancelRegiNo = cancelRegiNo;
	}

	public String getResno() {
		return resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public String getReqno() {
		return reqno;
	}

	public void setReqno(String reqno) {
		this.reqno = reqno;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCancelregino() {
		return cancelregino;
	}

	public void setCancelregino(String cancelregino) {
		this.cancelregino = cancelregino;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCanceledyn() {
		return canceledyn;
	}

	public void setCanceledyn(String canceledyn) {
		this.canceledyn = canceledyn;
	}

	public String getNotcancelReason() {
		return notcancelReason;
	}

	public void setNotcancelReason(String notcancelReason) {
		this.notcancelReason = notcancelReason;
	}

	public int getEdiPk() {
		return ediPk;
	}

	public void setEdiPk(int ediPk) {
		this.ediPk = ediPk;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public int getReqType() {
		return reqType;
	}

	public void setReqType(int reqType) {
		this.reqType = reqType;
	}

	public String getOfficeSer() {
		return officeSer;
	}

	public void setOfficeSer(String officeSer) {
		this.officeSer = officeSer;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getOrdCompNm() {
		return ordCompNm;
	}

	public void setOrdCompNm(String ordCompNm) {
		this.ordCompNm = ordCompNm;
	}

	public String getOrdNm() {
		return ordNm;
	}

	public void setOrdNm(String ordNm) {
		this.ordNm = ordNm;
	}

	public String getOrdZip() {
		return ordZip;
	}

	public void setOrdZip(String ordZip) {
		this.ordZip = ordZip;
	}

	public String getOrdAddr1() {
		return ordAddr1;
	}

	public void setOrdAddr1(String ordAddr1) {
		this.ordAddr1 = ordAddr1;
	}

	public String getOrdAddr2() {
		return ordAddr2;
	}

	public void setOrdAddr2(String ordAddr2) {
		this.ordAddr2 = ordAddr2;
	}

	public String getOrdTel() {
		return ordTel;
	}

	public void setOrdTel(String ordTel) {
		this.ordTel = ordTel;
	}

	public String getOrdMob() {
		return ordMob;
	}

	public void setOrdMob(String ordMob) {
		this.ordMob = ordMob;
	}

	public String getRecNm() {
		return recNm;
	}

	public void setRecNm(String recNm) {
		this.recNm = recNm;
	}

	public String getRecZip() {
		return recZip;
	}

	public void setRecZip(String recZip) {
		this.recZip = recZip;
	}

	public String getRecAddr1() {
		return recAddr1;
	}

	public void setRecAddr1(String recAddr1) {
		this.recAddr1 = recAddr1;
	}

	public String getRecAddr2() {
		return recAddr2;
	}

	public void setRecAddr2(String recAddr2) {
		this.recAddr2 = recAddr2;
	}

	public String getRecTel() {
		return recTel;
	}

	public void setRecTel(String recTel) {
		this.recTel = recTel;
	}

	public String getRecMob() {
		return recMob;
	}

	public void setRecMob(String recMob) {
		this.recMob = recMob;
	}

	public String getApprNo() {
		return apprNo;
	}

	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getMicroYn() {
		return microYn;
	}

	public void setMicroYn(String microYn) {
		this.microYn = microYn;
	}

	public String getContCd() {
		return contCd;
	}

	public void setContCd(String contCd) {
		this.contCd = contCd;
	}

	public String getGoodsNm() {
		return goodsNm;
	}

	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	public String getGoodsCd() {
		return goodsCd;
	}

	public void setGoodsCd(String goodsCd) {
		this.goodsCd = goodsCd;
	}

	public String getGoosMdl() {
		return goosMdl;
	}

	public void setGoosMdl(String goosMdl) {
		this.goosMdl = goosMdl;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getGoodsColor() {
		return goodsColor;
	}

	public void setGoodsColor(String goodsColor) {
		this.goodsColor = goodsColor;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDelivMsg() {
		return delivMsg;
	}

	public void setDelivMsg(String delivMsg) {
		this.delivMsg = delivMsg;
	}

	public String getRetReason() {
		return retReason;
	}

	public void setRetReason(String retReason) {
		this.retReason = retReason;
	}

	public String getRetVisitYmd() {
		return retVisitYmd;
	}

	public void setRetVisitYmd(String retVisitYmd) {
		this.retVisitYmd = retVisitYmd;
	}

	public String getTestYn() {
		return testYn;
	}

	public void setTestYn(String testYn) {
		this.testYn = testYn;
	}

	public String getPrintYn() {
		return printYn;
	}

	public void setPrintYn(String printYn) {
		this.printYn = printYn;
	}

	public String getOrPk() {
		return orPk;
	}

	public void setOrPk(String orPk) {
		this.orPk = orPk;
	}

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public String getRegiNo() {
		return regiNo;
	}

	public void setRegiNo(String regiNo) {
		this.regiNo = regiNo;
	}

	public String getRegiPoNm() {
		return regiPoNm;
	}

	public void setRegiPoNm(String regiPoNm) {
		this.regiPoNm = regiPoNm;
	}

	public String getResDate() {
		return resDate;
	}

	public void setResDate(String resDate) {
		this.resDate = resDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getvTelNo() {
		return vTelNo;
	}

	public void setvTelNo(String vTelNo) {
		this.vTelNo = vTelNo;
	}

	public String getArrCnpoNm() {
		return arrCnpoNm;
	}

	public void setArrCnpoNm(String arrCnpoNm) {
		this.arrCnpoNm = arrCnpoNm;
	}

	public String getDelivPoNm() {
		return delivPoNm;
	}

	public void setDelivPoNm(String delivPoNm) {
		this.delivPoNm = delivPoNm;
	}

	public String getDelivAreaCd() {
		return delivAreaCd;
	}

	public void setDelivAreaCd(String delivAreaCd) {
		this.delivAreaCd = delivAreaCd;
	}

	public String epostDelivToString() {
		return "custNo=" + custNo + "&reqType=" + reqType + "&officeSer=" + officeSer + "&weight=" + weight
				+ "&volume=" + volume + "&ordCompNm=" + ordCompNm + "&ordNm=" + ordNm + "&ordZip=" + ordZip
				+ "&ordAddr1=" + ordAddr1 + "&ordAddr2=" + ordAddr2 + "&ordTel=" + ordTel + "&ordMob=" + ordMob
				+ "&recNm=" + recNm + "&recZip=" + recZip + "&recAddr1=" + recAddr1 + "&recAddr2=" + recAddr2
				+ "&recTel=" + recMob + "&recMob=" + recMob + "&apprNo=" + apprNo + "&payType=" + payType
				+ "&microYn=" + microYn + "&contCd=" + contCd + "&goodsNm=" + goodsNm + "&goodsCd=" + goodsCd
				+ "&goosMdl=" + goosMdl + "&goodsSize=" + goodsSize + "&goodsColor=" + goodsColor + "&qty=" + qty
				+ "&orderNo=" + orderNo + "&delivMsg=" + delivMsg + "&retReason=" + retReason + "&retVisitYmd="
				+ retVisitYmd + "&testYn=" + testYn + "&printYn=" + printYn;
	}
	
	public String epostDeliteToString() {
		return ("custNo=" + custNo + "&apprNo=" + apprNo + "&reqType=1" + "&reqNo=" + reqno 
				+ "&resNo=" + resNo + "&regiNo=" + regiNo + "&reqYmd=" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "&delYn="+"Y").replace("-", "");
	}
	
	public String errorToString() {
		return "error_code="+error_code+"&message="+message;
	}
	
	public String notcancleReasonToString() {
		return "notcancelReason="+notcancelReason;
	}
}
