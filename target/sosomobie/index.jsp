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
	<title>SosoMobie移动业务大厅</title>
	<meta charset="utf-8">
	<meta name="author" content="小辣稽">

	<style>
		#header{
			width: 100%;
			padding: 12px;
			box-sizing: border-box;
			background-color: #B03167;
			color: white;
			display: block;
			text-align: left;
			align-items: center;
			flex-wrap: nowrap;
		}
		.part{
			background-color: aliceblue;
			padding: 8px;
			margin: 12px 12px 12px 36px;
		}
		h1{
			font-size: 36px;
			text-align: left;
			width: 960px;
			margin: 12px;
		}
		h3{
			margin-left: 12px;
			color: cornflowerblue;
		}
		.select{
			margin-bottom: 8px;
			padding-bottom: 4px;
		}
		.submit{
			margin: 2px;
			padding: 12px;
			display: block;

			text-decoration: none;
			color: #666;
		}

		input{
			padding: 4px;
		}
		output{
			padding-left: 8px;
			padding-right: 8px;
		}
		form{
			margin-left: 24px;
		}
		body{
			font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Helvetica,Arial,sans-serif,Apple Color Emoji,Segoe UI Emoji;
			background-color: white;
			display: block;
			margin: 0;
			padding: 0;
		}
	</style>
</head>
<script>
    function selectUser() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                document.getElementById("test").innerHTML = xmlhttp.responseText;
            }
        }
        xmlhttp.open("POST", "user/showUser.do", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("id=1");
    }
</script>
<body>
<p id="test">Hello World!</p>
<button type="button" onclick="selectUser()">onclick test</button>

<div id="header">
	<h6 style="font-size: 12px"><del>java</del>( html+css+javascript )<del>平时</del>( 大 )作业</h6>
	<h1>SosoMobie 移动业务大厅</h1>
</div>



</body>
</html>