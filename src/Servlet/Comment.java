package Servlet;

import DAO.CommentDAO;
import Tool.Rand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "Comment")
public class Comment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("showwords".equals(action)) this.showwords(request, response);
        if("upwords".equals(action))this.upwords(request,response);
    }

    private void upwords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session =request.getSession();

        Enity.Comment comment = new Enity.Comment();
        Date t = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss");
        comment.setWriter(request.getParameter("writer"));
        comment.setWriterID(request.getParameter("writerID"));
        comment.setUUID(request.getParameter("UUID"));
        comment.setAddress(request.getParameter("address"));
        comment.setWords(request.getParameter("words"));
        comment.setData(df.format(t));
        CommentDAO commentDAO = new CommentDAO();
        commentDAO.addComment(comment);
        ArrayList<Enity.Comment> list = commentDAO.queryComment(comment);
        session.setAttribute("commentlist",list);
        request.getRequestDispatcher("./text.jsp").forward(request, response);
    }
    private void showwords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
