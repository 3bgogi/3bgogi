package com.gogi.proj.configurations.vo;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

//판매처 구분
public class StoreSectionVO {

	private int ssPk; //판매처 고유 번호
	private String ssName; //판매처명
	private String ssStoreId; //판매처 아이디
	private String ssStorePassword; //판매처 비밀번호
	private String ssAuthKey; //판매처 인증키
	private String ssStoreUrl; //판매처 관리페이지
	private String ssStoreNickname; // 판매처 별칭
	private int ssCommission; // 스토어 수수료
	private int ssSpecialNumberCount; //판매처 묶음번호값
	private Timestamp ssUpdate; // 판매처 수정일
	private Timestamp ssRegdate; //판매처 등록일
	private String ssMerge; //판매처 묶음정리
	private String ssSendingHeadForm; // 엑셀 파일열 제목
	private String ssSendingBodyForm; // DB에서 가져올 목록  
	private String ssSendingGroupForm; //DB 그룹화 
	
	private String ssSendingDay;
	
	private String [] ssSendingHeadFormList;
	
	public StoreSectionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreSectionVO(int ssPk, String ssName, String ssStoreId, String ssStorePassword, String ssAuthKey,
			String ssStoreUrl, String ssStoreNickname, int ssCommission, int ssSpecialNumberCount, Timestamp ssUpdate,
			Timestamp ssRegdate, String ssMerge, String ssSendingHeadForm, String ssSendingBodyForm,
			String ssSendingGroupForm, String ssSendingDay, String[] ssSendingHeadFormList) {
		super();
		this.ssPk = ssPk;
		this.ssName = ssName;
		this.ssStoreId = ssStoreId;
		this.ssStorePassword = ssStorePassword;
		this.ssAuthKey = ssAuthKey;
		this.ssStoreUrl = ssStoreUrl;
		this.ssStoreNickname = ssStoreNickname;
		this.ssCommission = ssCommission;
		this.ssSpecialNumberCount = ssSpecialNumberCount;
		this.ssUpdate = ssUpdate;
		this.ssRegdate = ssRegdate;
		this.ssMerge = ssMerge;
		this.ssSendingHeadForm = ssSendingHeadForm;
		this.ssSendingBodyForm = ssSendingBodyForm;
		this.ssSendingGroupForm = ssSendingGroupForm;
		this.ssSendingDay = ssSendingDay;
		this.ssSendingHeadFormList = ssSendingHeadFormList;
	}

	public String getSsSendingDay() {
		return ssSendingDay;
	}

	public void setSsSendingDay(String ssSendingDay) {
		this.ssSendingDay = ssSendingDay;
	}

	public int getSsPk() {
		return ssPk;
	}

	public void setSsPk(int ssPk) {
		this.ssPk = ssPk;
	}

	public String getSsName() {
		return ssName;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
	}

	public String getSsStoreId() {
		return ssStoreId;
	}

	public void setSsStoreId(String ssStoreId) {
		this.ssStoreId = ssStoreId;
	}

	public String getSsStorePassword() {
		return ssStorePassword;
	}

	public void setSsStorePassword(String ssStorePassword) {
		this.ssStorePassword = ssStorePassword;
	}

	public String getSsAuthKey() {
		return ssAuthKey;
	}

	public void setSsAuthKey(String ssAuthKey) {
		this.ssAuthKey = ssAuthKey;
	}

	public String getSsStoreUrl() {
		return ssStoreUrl;
	}

	public void setSsStoreUrl(String ssStoreUrl) {
		this.ssStoreUrl = ssStoreUrl;
	}

	public String getSsStoreNickname() {
		return ssStoreNickname;
	}

	public void setSsStoreNickname(String ssStoreNickname) {
		this.ssStoreNickname = ssStoreNickname;
	}

	public int getSsCommission() {
		return ssCommission;
	}

	public void setSsCommission(int ssCommission) {
		this.ssCommission = ssCommission;
	}

	public int getSsSpecialNumberCount() {
		return ssSpecialNumberCount;
	}

	public void setSsSpecialNumberCount(int ssSpecialNumberCount) {
		this.ssSpecialNumberCount = ssSpecialNumberCount;
	}

	public Timestamp getSsUpdate() {
		return ssUpdate;
	}

	public void setSsUpdate(Timestamp ssUpdate) {
		this.ssUpdate = ssUpdate;
	}

	public Timestamp getSsRegdate() {
		return ssRegdate;
	}

	public void setSsRegdate(Timestamp ssRegdate) {
		this.ssRegdate = ssRegdate;
	}

	public String getSsMerge() {
		return ssMerge;
	}

	public void setSsMerge(String ssMerge) {
		this.ssMerge = ssMerge;
	}

	public String getSsSendingHeadForm() {
		return ssSendingHeadForm;
	}

	public void setSsSendingHeadForm(String ssSendingHeadForm) {
		this.ssSendingHeadForm = ssSendingHeadForm;
	}

	public String getSsSendingBodyForm() {
		return ssSendingBodyForm;
	}

	public void setSsSendingBodyForm(String ssSendingBodyForm) {
		this.ssSendingBodyForm = ssSendingBodyForm;
	}

	public String getSsSendingGroupForm() {
		return ssSendingGroupForm;
	}

	public void setSsSendingGroupForm(String ssSendingGroupForm) {
		this.ssSendingGroupForm = ssSendingGroupForm;
	}

	public String[] getSsSendingHeadFormList() {
		return ssSendingHeadFormList;
	}

	public void setSsSendingHeadFormList(String[] ssSendingHeadFormList) {
		this.ssSendingHeadFormList = ssSendingHeadFormList;
	}

	@Override
	public String toString() {
		return "StoreSectionVO [ssPk=" + ssPk + ", ssName=" + ssName + ", ssStoreId=" + ssStoreId + ", ssStorePassword="
				+ ssStorePassword + ", ssAuthKey=" + ssAuthKey + ", ssStoreUrl=" + ssStoreUrl + ", ssStoreNickname="
				+ ssStoreNickname + ", ssCommission=" + ssCommission + ", ssSpecialNumberCount=" + ssSpecialNumberCount
				+ ", ssUpdate=" + ssUpdate + ", ssRegdate=" + ssRegdate + ", ssMerge=" + ssMerge
				+ ", ssSendingHeadForm=" + ssSendingHeadForm + ", ssSendingBodyForm=" + ssSendingBodyForm
				+ ", ssSendingGroupForm=" + ssSendingGroupForm + ", ssSendingHeadFormList="
				+ Arrays.toString(ssSendingHeadFormList) + "]";
	}
}
