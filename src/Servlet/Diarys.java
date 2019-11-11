package Servlet;

import DAO.CommentDAO;
import DAO.DiaryDAO;

import Enity.Comment;
import Enity.Diary;
import Enity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Diarys")
public class Diarys extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("deleteDiary".equals(action)) this.deleteDiary(request, response);
        if("getAllDiaryList".equals(action))this.getAllDiaryList(request,response);
        if("getMyDiaryList".equals(action))this.getMyDiaryList(request,response);
        if("showDiary".equals(action))this.showDiary(request,response);
    }

    private void showDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        DiaryDAO diaryDAO =new DiaryDAO();
        Diary diary= diaryDAO.showDiary(request.getParameter("UUID"));
        session.setAttribute("showdiary",diary);
        CommentDAO commentDAO = new CommentDAO();
        Comment comment = new Comment();
        comment.setUUID(request.getParameter("UUID"));
        ArrayList<Comment> list = commentDAO.queryComment(comment);
        session.setAttribute("commentlist",list);
        request.getRequestDispatcher("./diary.jsp").forward(request,response);
    }

    private void getMyDiaryList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        HttpSession session = request.getSession();
       User user= (User) session.getAttribute("user");
        DiaryDAO diaryDAO = new DiaryDAO();
        List<Enity.Diary> diaryList = diaryDAO.getDiary(user.getUsername());
        session.setAttribute("diarylist",diaryList);
        request.getRequestDispatcher("myblog.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void deleteDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DiaryDAO diary =new DiaryDAO();
       String username = request.getParameter("username");
       String date =request.getParameter("date");
       diary.deleteDairy(username,date);
       List<Enity.Diary> diaryList = diary.getDiary(username);
        session.setAttribute("diarylist",diaryList);
        request.getRequestDispatcher("./myblog.jsp").forward(request, response);
    }
    private void getAllDiaryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        DiaryDAO diary =new DiaryDAO();
        String username = request.getParameter("username");
        List<Enity.Diary> alldiarylist =  diary.getAllDiray();
        session.setAttribute("alldiarylist",alldiarylist);
        request.getRequestDispatcher("./comment.jsp").forward(request, response);
    }

}