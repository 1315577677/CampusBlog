
 package Servlet;

         import java.io.*;
         import java.text.SimpleDateFormat;
         import java.util.*;

         import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
         import javax.servlet.http.HttpSession;

         import DAO.DiaryDAO;
         import DAO.userDAO;
         import Enity.Diary;
         import Enity.User;
         import Tool.Rand;
         import org.apache.commons.fileupload.FileItem;
 import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
 import org.apache.commons.fileupload.servlet.ServletFileUpload;
         import org.apache.commons.io.IOUtils;


 @WebServlet("/FileUpload")
 public class FileUpload extends HttpServlet {
     private static final long serialVersionUID = 1L;
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
             HttpSession session = request.getSession();
             User user = (User) session.getAttribute("user");
             userDAO userDAO = new userDAO();
             Diary diary = new Diary();
             Date t = new Date();
             SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss");
             diary.setWriter(user.getUsername());
             diary.setDate(df.format(t));
             String UUID =Rand.generateShortUUID();
             diary.setUUID(UUID);
             diary.setDiary(request.getParameter("diaryname"));
             diary.setText(request.getParameter("test-editormd-html-code"));
             diary.setHeadpath(user.getHead());
             diary.setWritername(user.getName());
             diary.setWriter(user.getUsername());
             diary.setHeadpath(user.getHead());
             DiaryDAO diaryDAO = new DiaryDAO();
             diaryDAO.addDiary(diary);
             List<Diary> diaryList = diaryDAO.getDiary(user.getUsername());
             session.setAttribute("diarylist",diaryList);
             request.getRequestDispatcher("./myblog.jsp").forward(request, response);
     }
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
            }
 }