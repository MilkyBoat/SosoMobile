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
	<title>我的嗖嗖移动</title>
	<meta charset="utf-8">
	<meta name="author" content="小辣稽">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/sosomobile.css"
	      charset="UTF-8">
	<script>
        function bill() {

        }

        function remain() {

        }

        function consumList() {

        }

        function changePack() {

        }

        function del() {

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

<div class="fun">
	<div id="billQuery" class="form">
		<input class="submit" type="submit" onclick="bill()" value="本月账单查询">
		<p id="billOutput"></p>
	</div>
</div>

<div class="fun">
	<div id="packageRemain" class="form">
		<input class="submit" type="submit" onclick="remain()" value="套餐余量查询">
		<p id="remainOutput"></p>
	</div>
</div>

<div class="fun">
	<div id="consumptionList" class="form">
		<input class="submit" type="submit" onclick="consumList()" value="打印消费详单">
		<p id="listOutput"></p>
	</div>
</div>

<div class="fun">
	<div id="changePack" class="form">
		选择要变更的套餐：<input id="package" name="servicePackage" type="text" list="packages"><br>
		<datalist id="packages"></datalist>
		<input class="submit" type="submit" onclick="changePack()" value="变更套餐">
		<p id="packOutput"></p>
	</div>
</div>

<div class="fun">
	<div id="delCard" class="form">
		<input class="submit" type="submit" onclick="del()" value="办理退网">
		<p id="delOutput"></p>
	</div>
</div>
<script>
    var xmlhttp1 = new XMLHttpRequest();
    xmlhttp1.onreadystatechange = function func1() {
        if (xmlhttp1.readyState === 4 && xmlhttp1.status === 200) {
            var textSize = xmlhttp1.responseText.length - 2;
            document.getElementById("packages").innerHTML = xmlhttp1.responseText.substr(1, textSize);
        }
    }
    xmlhttp1.open("POST", "getChanPacks.do", true);
    xmlhttp1.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp1.send();
</script>
</body>
</html>
