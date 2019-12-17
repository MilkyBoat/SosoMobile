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
	<title>嗖嗖移动用户注册</title>
	<meta charset="utf-8">
	<meta name="author" content="小辣稽">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/sosomobile.css"
	      charset="UTF-8">
	<style>
		.form {
			background-color: lightgrey;
			height: 400px;
			width: 280px;
			text-align: right;
		}
		.form p {
			text-align: left;
		}
	</style>
	<script>
        function reg() {
            var number = document.getElementById("number").value;
            if (number < 13900000000 || number > 17822011172) {
                document.getElementById("info").innerHTML = "卡号格式不合法";
                return false;
            }
            var userName = document.getElementById("userName").value;
            var pwd = document.getElementById("password").value;
            var rePwd = document.getElementById("rePassword").value;
            if (pwd !== rePwd) {
                document.getElementById("info").innerHTML = "两次输入密码不一致";
                return false;
            }
            var pack = document.getElementById("package").value;
            var money = document.getElementById("money").value;

            var xmlhttp = new XMLHttpRequest();

            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    var infoLen = xmlhttp.responseText.length;
                    document.getElementById("info").innerHTML = xmlhttp.responseText.substring(1, infoLen - 1);
                }
            }
            xmlhttp.open("POST", "reg.do", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("info=" + number + ";" + userName + ";" + pwd + ";" + pack + ";" + money);
        }
	</script>
</head>
<body>
<div id="header">
	<h6 style="font-size: 12px">
		<del>java</del>
		( html+css+javascript )
		<del>平时</del>
		( 大 )作业
	</h6>
	<h1>sosoMobile 移动业务大厅</h1>
</div>

<div id="signUp" class="form">
	<label for="number">选择手机号码：</label><input id="number" name="number" type="number" min="13900000000"
	                                          max="17822011172" step="1" list="numbers"><br>
	<datalist id="numbers"></datalist>
	<label for="userName">用户名：</label><input id="userName" name="userName" type="text"><br>
	<label for="password">密码：</label><input id="password" name="password" type="password"><br>
	<label for="rePassword">确认密码：</label><input id="rePassword" name="repPassword" type="password"><br>
	<label for="package">选择套餐：</label><input id="package" name="servicePackage" type="text" list="packages"><br>
	<datalist id="packages"></datalist>
	<label for="money">预存金额：</label><input id="money" name="preMoney" type="number" min="0" step="0.01"><br>
	<div style="text-align: center">
		<input class="submit" type="submit" onclick="reg()" value="注册">
	</div>
	<p id="info"></p>
</div>

<script>
    var xmlhttp0 = new XMLHttpRequest();
    xmlhttp0.onreadystatechange = function func0() {
        if (xmlhttp0.readyState === 4 && xmlhttp0.status === 200) {
            var textSize = xmlhttp0.responseText.length - 2;
            document.getElementById("numbers").innerHTML = xmlhttp0.responseText.substr(1, textSize);
        }
    }
    xmlhttp0.open("POST", "getNumbers.do", true);
    xmlhttp0.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp0.send("num=9");
    var xmlhttp1 = new XMLHttpRequest();
    xmlhttp1.onreadystatechange = function func1() {
        if (xmlhttp1.readyState === 4 && xmlhttp1.status === 200) {
            var textSize = xmlhttp1.responseText.length - 2;
            document.getElementById("packages").innerHTML = xmlhttp1.responseText.substr(1, textSize);
        }
    }
    xmlhttp1.open("POST", "getPacks.do", true);
    xmlhttp1.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp1.send();
</script>
</body>
</html>
