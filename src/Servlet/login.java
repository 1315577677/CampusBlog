package Servlet;

import DAO.DiaryDAO;
import DAO.userDAO;
import Enity.Diary;
import Enity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Servlet")
public class login extends HttpServlet {
    userDAO userDAO = new userDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("login".equals(action)) this.login(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = null;
        String password = null;
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        username = request.getParameter("username");
        password = request.getParameter("password");
        User user = new User();
        user = userDAO.login(username, password);
        if (user != null) {

            if (user.getStatus() == 0) {
                out.write("unpass");
            } else {
                session.setAttribute("user", user);
                DiaryDAO diaryDAO = new DiaryDAO();
                List<Diary> diaryList = diaryDAO.getDiary(user.getUsername());
                session.setAttribute("diarylist",diaryList);
                request.getRequestDispatcher("./home.jsp").forward(request, response);
            }
        }
        else{
                out.write("false");
            }
        }
    }


