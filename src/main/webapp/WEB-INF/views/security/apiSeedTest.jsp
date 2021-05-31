<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<style>
* {font-family:"Arial"}
P.desc {font-size:9pt}
P.tit {font-size:10pt; color:#3366CC; font-weight:bold}
INPUT.txt {vertical-align:middle;font-size:11pt;font-family:"Arial";border-color:#99aadd; border-style:solid;border-width: 1px; height:24px; background-color:#f3f3f3}
INPUT.btn {vertical-align:middle; height:24px}
</style>

<script language="JavaScript">
function jsSubmit() {
	var frm = document.frm;
	if (frm.skey.value=="") {
		alert("보안키를 입력해주세요");
		frm.skey.focus();
		return;
	}
	if (frm.plainStr.value=="" && frm.encryptStr.value=="") {
		alert("평문 또는 암호문을 입력해주세요");
		frm.plainStr.focus();
		return;
	}

	
}
</script>

<body>

<h2>TEST PAGE for JAVA</h2>

<p class="desc">
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
</p>
<p class="desc">
* <b>보안키</b> : 접수데이터 파라미터 값(regData) 암호화를 위해 계약고객전용시스템 오픈API 신청결과 화면에서 생성한 접수용 보안키
</p>
<p class="desc">
* <b>평문</b> : 접수데이터 파라미터 값(regData)을 평문으로 입력 후 [암호화/복호화] 버튼을 클릭하면 보안키를 사용하여 암호화 된 값이 생성됩니다.<br>
<span style="color:#4497DB;padding-left:30px">ex) custNo=0001234567&reqType=1&officeSer=01&weight=5&volume=60&ordCompNm=고객주문처&ordNm=김주문...</span>
</p>
<p class="desc">
* <b>암호문</b> : 보안키를 사용하여 암호화 된 접수데이터 파라미터 값(regData)을 입력 후 [암호화/복호화] 버튼을 클릭하면 평문에 복호화 된 값이 생성됩니다.<br>
<span style="color:#4497DB;padding-left:30px">ex) 63921ec6ac397d1bbd1aefb27058029e9bf1078cef0bccd8a9f8c3d75dbcd506dd1d0dde123e6d4aaa54c9066c8364e9...</span>
</p>
<p class="desc">
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
</p>

<form method="post" name="frm" onSubmit="return jsSubmit()" action="<c:url value='/security/epost.do'/>">


<p class="tit">보안키(seedKey) : <input type="text" class="txt" size="40" name="skey" value="${skey }">&nbsp;&nbsp;
<input type="submit" class="btn" value="암호화/복호화"></p>

<p class="tit">평문<br>
<textarea name="plainStr" cols="80" rows="12">${plainStr }${eposts }</textarea>
</p>

<p class="tit">암호문<br>
<textarea name="encryptStr" cols="80" rows="17">${encryptStr }</textarea>
</p>
</form>
</body>
</html>

