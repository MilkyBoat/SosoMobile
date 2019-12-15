<%--
  Created by IntelliJ IDEA.
  User: surface
  Date: 2019/10/19
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>sosoMobile移动业务大厅</title>
	<meta charset="utf-8">
	<meta name="author" content="小辣稽">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/sosomobile.css"
	      charset="UTF-8">

	<script>
        function userSoso() {
            var number = document.getElementById("userUseSoso").value;
            if (number < 13900000000 || number > 17822011172)
                return false;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    document.getElementById("userSoso").innerHTML = xmlhttp.responseText;
                }
            }
            xmlhttp.open("POST", "user/showUser.do", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("number=" + number);
        }

        function charge() {
            var number = document.getElementById("userCharge").value;
            var amount = document.getElementById("chargeAmount").value;
            if (number < 13900000000 || number > 17822011172)
                return false;
            var xmlhttp = new XMLHttpRequest();
            var form = new FormData();
            form.append("number", number.toString());
            form.append("amount", amount.toString());
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    document.getElementById("charge").innerHTML = xmlhttp.responseText;
                }
            }
            xmlhttp.open("POST", "user/charge.do", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send(form);
        }

        function showDescription() {
            location.href = "${pageContext.request.contextPath }/resource/charges descreption.txt";
        }
	</script>

</head>
<body>

<div id="header">
	<h6 style="font-size: 12px"><del>java</del>( html+css+javascript )<del>平时</del>( 大 )作业</h6>
	<h1>sosoMobile 移动业务大厅</h1>
</div>

<div class="login">
	<form id="login" action="mySosoMobile.jsp" method="post">
		用户名：<input name="number" type="number" min="13900000000" max="17822011172" step="1">
		密码：<input name="password" type="password">
		<input class="submit" type="submit" value="登录">
	</form>
</div>

<div class="fun">
	<form id="singnUp" action="signUp.jsp">
		<input class="submit" type="submit" value="没有账号？去注册">
	</form>
</div>

<div class="fun">
	<form id="userSoso">
		用户名：<input id="userUseSoso" name="number" type="number" min="13900000000" max="17822011172" step="1">
		<input class="submit" type="submit" onclick="userSoso()" value="使用嗖嗖">
	</form>
	<p id="userSosoOutput"></p>
</div>

<div class="fun">
	<form id="charge">
		用户名：<input name="number" id="userCharge" type="number" min="13900000000" max="17822011172" step="1">
		充值金额：<input name="amount" id="chargeAmount" type="number" min="50" step="1">
		<input class="submit" type="submit" onclick="charge()" value="话费充值">
		<p id="chargeOutput"></p>
	</form>
</div>

<div class="fun">
	<form>
		<input class="submit" type="submit" onclick="showDescription()" value="查看资费详情">
	</form>
</div>

</body>
</html>