package Servlet;

import DAO.DiaryDAO;
import Enity.Diary;
import Enity.User;
import  DAO.userDAO;
import Mail.Mailtest;
import Tool.Rand;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "register")
public class register extends HttpServlet {
    userDAO userDAO =new userDAO();
    User user = new User();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("register".equals(action)) this.register(request, response);
        else if ("QueryUsernameById".equals(action))this.QueryUsernameById(request, response);
        else if("mailcheck".equals(action))this.mailcheck(request,response);
        else if("sendmail".equals(action))this.sendmail(request,response);
        else if("Querymail".equals(action))this.Querymail(request,response);
        else if("changpassword".equals(action))this.changpassword(request,response);
         else if("changPWbyId".equals(action))this.changPWbyId(request,response);
        else if("QuerymailById".equals(action))this.QuerymailById(request,response);
        else if("changvalue".equals(action))this.changvalue(request,response);
    }

    private void changvalue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User  use =(User)session.getAttribute("user");
        String name =request.getParameter("name");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        userDAO.changValue(name,password,username);
        use.setPassword(password);
        use.setName(name);
        DiaryDAO diary = new DiaryDAO();
        diary.changname(use);
        session.setAttribute("user",use);
        request.getRequestDispatcher("./information.jsp").forward(request,response);
    }

    private void QueryUsernameById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
      if(userDAO.QueryUsernameById(request.getParameter("id"))) {
          out.write("true") ;
      }
         else{
             out.write("false");
      }
    }
    private void QuerymailById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        if(userDAO.QuerymailById(request.getParameter("id"))) {
            out.write("true") ;
        }
        else{
            out.write("false");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    private void mailcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid =request.getParameter("UUID");
        if(null!=uuid&&userDAO.QueryUUID(uuid)){
            userDAO.changStatus(uuid);
            userDAO.deleteUUID(uuid);
            response.sendRedirect("./OP.html");
        }else{
            response.sendRedirect("./error.html");
        }
    }
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i= 0;
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setMail(request.getParameter("mail"));
        user.setUUID(Rand.generateShortUUID());
        try {
            Mailtest smt=new Mailtest();
            smt.Sendcheckmail(1,user);
        } catch (Exception e) {
            request.getRequestDispatcher("./error.html").forward(request,response);
            i++;
        }
        if(i==0) {
            userDAO.regsiter(user);
            userDAO userDAO = new userDAO();
            userDAO.uphead(user.getUsername(),"/Users/Administrator/Desktop/head1/默认头像.jpg");
            request.getRequestDispatcher("./sendmail.html").forward(request,response);
        }
    }
    private void Querymail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mail = request.getParameter("id");
        if(null!=userDAO.Querymail(mail)){
            out.write("in");
        }else{
            out.write("notin");
        }
    }
    private void sendmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i=0;
        String mail = request.getParameter("mail");
        User user = userDAO.Querymail(mail);
        user.setUUID(Rand.generateShortUUID());
        try {
            Mailtest smt=new Mailtest();
            smt.Sendcheckmail(2,user);
        } catch (Exception e) {
            request.getRequestDispatcher("./error.html").forward(request,response);
            i++;
        }
        if(i==0) {

            userDAO.changUUID(user);
        }

        request.getRequestDispatcher("./sendmail.html").forward(request,response);
    }
    private void changpassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid =request.getParameter("UUID");
        HttpSession session = request.getSession();
        session.setAttribute("check",uuid);
        if(null!=uuid&&userDAO.QueryUUID(uuid)){
            userDAO.changStatus(uuid);
            request.getRequestDispatcher("./changPW.jsp").forward(request,response);
        }else{
            response.sendRedirect("./error.html");
        }
    }
    private void changPWbyId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("id");
        String pw =request.getParameter("PW");
        if(userDAO.changPWbyId(username,pw)==1){
            userDAO.deleteUUIDbyusername(username);
           request.getRequestDispatcher("./OP.html").forward(request,response);
        }else{
            request.getRequestDispatcher("./error.html").forward(request,response);
        }
    }
}
