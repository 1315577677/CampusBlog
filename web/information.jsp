<%@ page import="Enity.User" %><%--
  Created by IntelliJ IDEA.
  User: zhangxiang
  Date: 2019-07-23
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    if(session.getAttribute("user") == null){
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    User user = new User();
    user=(User)session.getAttribute("user");

%>
<link rel="stylesheet" href="css/register.css"  type="text/css" />
<jsp:include page="nav.jsp"></jsp:include>
<html>
<head>
    <title>facebowl | 个人信息</title>
</head>




<body>
<div id="box">
    <div style="width: 360px;margin: 30px auto;">

        <form method="post" action="uphead" enctype="multipart/form-data">
            <img src="FileDownlod?path=<%=user.getHead()%>" style=" padding-left: 23px; width: 80px;height: 80px">
            <input type="file" value="浏览" name="head"><br/>
            <input class="button" type="submit" value="确认上传">
        </form>
    </div>

    <form  id="from" action="register?action=changvalue&username=<%=user.getUsername()%>" method="post"onclick="return formcheck()">
        <table  style="margin: 20px auto">

            <tr>
                <td>
                    <input autocomplete="off" value="<%=user.getName()%>" id="name" class="input" type="text" name="name" onkeyup="checkname()" >
                </td>
                <td>
                    <input autocomplete="off" value="<%=user.getPassword()%>" id="pass1" class="input" type="password" name="password" onkeyup="checkpasswordleng()">
                </td>
            </tr>
            <tr>
                <td> <div class="size error"id="namecheck" ></div></td>
                <td><div class="size error"id="checkpass1"></div></td>
            </tr>
            <tr>
                <td><input autocomplete="off" value="<%=user.getPassword()%>" id="pass2" class="input" type="password"onkeyup="check_pass()"></td>
                <td><input class="button"  value="确认"  type="submit"></td>
            </tr>
            <tr>
                <td><div class="size error"id="checkpass2"></div></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
