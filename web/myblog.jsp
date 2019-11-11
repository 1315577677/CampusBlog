<%@ page import="Enity.Diary" %>
<%@ page import="java.util.List" %>
<%@ page import="Enity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="nav.jsp"></jsp:include>
<link rel="stylesheet" href="css/home.css"  type="text/css" />
<link rel="stylesheet" href="css/diarybox.css"  type="text/css" />
<%
    request.setCharacterEncoding("utf-8");
    if(session.getAttribute("user") == null){
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
    List<Diary> list = (List<Diary>) session.getAttribute("diarylist");
    User user = new User();
    user=(User)session.getAttribute("user");

%>

<html>
<head>
    <title>facebowl | myblog</title>
</head>
<body>
<%

    if(list!=null && list.size() > 0)
    {
        for(int i = list.size() - 1; i >= 0 ; i--)
        {
            Diary diary = list.get(i);
%>
<div id="Diray-box" >
<div id="box">
    <table>
        <tr>
            <td>
                <img src="FileDownlod?path=<%=user.getHead()%>"  style=" border-radius:100%;width: 40px;height: 40px">
            </td>
        </tr>
        <tr>
            <td id="date">
                <p ><%=diary.getDate()%></p>
            </td>
        </tr>
            <tr>
                <td>

                    <a href="Diarys?action=showDiary&UUID=<%=diary.getUUID()%>" methods="post" target="_blank" style="text-decoration : none"><%=diary.getDiary()%></a>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="check">
                        <img src="FileDownlod?path=<%=diary.getPath()%>" onerror="this.style.display='none'">
                    </div>

                </td>
            </tr>

        <tr>
            <td>
                <div>
                    <a href="Diarys?action=deleteDiary&username=<%=diary.getWriter()%>&date=<%=diary.getDate()%>" id="delete"></a>
                </div>

            </td>
        </tr>
    </table>
</div>
<%
        }
    }
%>
</body>
</html>
