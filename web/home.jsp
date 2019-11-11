<%@ page import="Enity.User" %>
<%@ page import="Enity.Diary" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhangxiang
  Date: 2019-07-19
  Time: 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/home.css"  type="text/css" />
<link rel="stylesheet" href="css/diarybox.css"  type="text/css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/editormd.css" />
<%
    request.setCharacterEncoding("utf-8");
    if(session.getAttribute("user") == null){
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
    List<Diary> list = (List<Diary>) session.getAttribute("diarylist");
    User user = new User();
    user=(User)session.getAttribute("user");

%>

<jsp:include page="nav.jsp"></jsp:include>
<html>
<head>
    <title><%=user.getName()%>  |  脸盆网</title>

</head>
<body style="background-color: #f9f9fa">

</body>

<div id="layout">
    <form action="FileUpload" method="post">
        <div id="write-mess">
            <input type="text" placeholder="标题" name="diaryname" autocomplete="off" style=" outline: none; height: 49px; border: none; width:94%;font-size: 32px;" required ><input  id="homesub" type="submit" value="确认发布">
        </div>

        <div id="test-editormd" >
            <textarea name="content" style="display:none;" ></textarea>
            <textarea id="test-editormd-html-code" name="test-editormd-html-code" style="display:none;"></textarea>
        </div>
    </form>
</div>

    var testEditor;

    $(function() {
        testEditor = editormd("test-editormd", {
            width   : "90%",
            height  : 640,
            syncScrolling : "single",
           // htmlDecode: "style,script,iframe",
            codeFold: true,
            path    : "./lib/",
            saveHTMLToTextarea : true,
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "upDiary"//注意你后端的上传图片服务地址
        });

        /*
        // or
        testEditor = editormd({
            id      : "test-editormd",
            width   : "90%",
            height  : 640,
            path    : "../lib/"
        });
        */
    });

</html>
<%--<form  action="FileUpload" method="post" enctype="multipart/form-data">--%>
<%--    <table id="text-box">--%>
<%--        <tr>--%>
<%--            <td id="td-write">--%>
<%--                <input type="text" placeholder="标题" name="diaryname" autocomplete="off" style=" outline: none;" required id="write-mess">--%>
<%--            </td>--%>
<%--            <td id="td-photo">--%>
<%--                <input type="file"id="up-photo" name="head" style=" font-size:0;line-height:0; text-indent:-9999px;">--%>
<%--            </td>--%>
<%--            <td id="td-mess">--%>
<%--                <input type="submit" id="up-mess" style="font-size:0;line-height:0; text-indent:-9999px;"/>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td colspan="3" >--%>
<%--                <textarea cols="100"   rows="8" placeholder="写下你的故事" type="text" name="diary" id="myArea" onkeyup="keypress2()" ></textarea>--%>
<%--                <label id="pinglun">800</label><span>/</span><label>800</label>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>