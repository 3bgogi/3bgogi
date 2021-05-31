<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function addValue() {
		var f = document.form;

		with (document.form) {
			var sVal = sel.options[sel.selectedIndex].value;
			var flag = true;

			if (ShowList.length > 0) {
				for (i = 0; i < ShowList.length; i++) {
					if (ShowList.options[i].value == sVal) {
						flag = false;
					}
				}
			}

			if (flag) {
				ShowList.options[ShowList.length] = new Option(sVal, sVal);
			}
		}
	}

	function DeleteList() {
		with (document.form) {
			ShowList.options[ShowList.selectedIndex] = null;
		}
	}

	function combineValue() {

		var ShowListCombineValue = "";
		var sForm = document.getElementById("ShowList");
		
			
			if (sForm.length < 1) {
				event.preventDefault();

				return false;

			} else {

				for (var i = 0; i < sForm.length; i++) {
					if (i == sForm.length - 1) {
						ShowListCombineValue += sForm.options[i].value;
						
					} else {
						ShowListCombineValue += sForm.options[i].value + ",";
						
					}

				}

			}

		document.getElementById("ShowListCombineValue").value = ShowListCombineValue;

	}
</script>
<BODY>
	<form name='form' action="TEST2.jsp" method="post">

		<select id="ShowList" name='ShowList' size='10' style='width: 280' multiple></select>
		<br> <select name="sel">
			<option value="1">안녕1</option>
			<option value="2">안녕2</option>
			<option value="3">안녕3</option>
			<option value="4">안녕4</option>

		</select> <input type='button' value='추가' onclick='javascript_:addValue();'>
		<input type='button' value='삭제' onclick='javascript_:DeleteList();'>

		<input type="submit" value="전송" onclick="javascript_:combineValue();">
		<input type="hidden" id="ShowListCombineValue" name="ShowListCombineValue">
	</form>
</BODY>
</HTML>