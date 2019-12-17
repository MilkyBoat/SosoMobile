<%--
  Created by IntelliJ IDEA.
  User: surface
  Date: 2019/12/15
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>sosoMobile移动业务大厅</title>
	<meta charset="utf-8">
	<meta name="author" content="小辣稽">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/sosomobile.css">

	<script>
        function userSoso() {
            var number = document.getElementById("userUseSoso").value;
            if (number < 13900000000 || number > 17822011172)
                return false;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    var infoLen = xmlhttp.responseText.length;
                    document.getElementById("userSosoOutput").innerHTML = xmlhttp.responseText.substring(1, infoLen - 1);
                }
            }
            xmlhttp.open("POST", "user/useSoso.do", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("number=" + number);
        }

        function charge() {
            var number = document.getElementById("userCharge").value;
            var amount = document.getElementById("chargeAmount").value;
            if (number < 13900000000 || number > 17822011172)
                return false;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    var infoLen = xmlhttp.responseText.length;
                    document.getElementById("chargeOutput").innerHTML = xmlhttp.responseText.substring(1, infoLen - 1);
                }
            }
            xmlhttp.open("POST", "user/charge.do", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("infos=" + number + ";" + amount);
        }
	</script>

</head>
<body>
<div id="header">
	<h6 style="font-size: 12px"><del>java</del>( html+css+javascript )<del>平时</del>( 大 )作业</h6>
	<h1>sosoMobile 移动业务大厅</h1>
</div>

<div class="login">
	<form id="login" class="form" action="card/mySosoMobile" method="post">
		<label>用户名： </label><input name="number" type="number" min="13900000000" max="17822011172" step="1">
		<label>密码：</label><input name="password" type="password">
		<input class="submit" type="submit" value="登录">
		<output>${info}</output>
		<input class="submit" type="submit" formaction="user/signUp" value="没有账号？去注册">
	</form>
</div>

<div class="fun">
	<div id="userSoso" class="form">
		<label for="userUseSoso">用户名：</label><input id="userUseSoso" name="number" type="number" min="13900000000"
		                                            max="17822011172" step="1">
		<input class="submit" type="submit" onclick="userSoso()" value="使用嗖嗖">
		<p id="userSosoOutput"></p>
	</div>
</div>

<div class="fun">
	<div id="charge" class="form">
		<label for="userCharge">用户名：</label><input name="number" id="userCharge" type="number" min="13900000000"
		                                           max="17822011172" step="1">
		<label for="chargeAmount">充值金额：</label><input name="amount" id="chargeAmount" type="number" min="50" step="1">
		<input class="submit" type="submit" onclick="charge()" value="话费充值">
		<p id="chargeOutput"></p>
	</div>
</div>

<div class="fun">
	<div class="form">
		<a href="${pageContext.request.contextPath }/resource/FeesDetails.pdf">查看资费详情</a>
	</div>
</div>

</body>
</html>