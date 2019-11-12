package Servlet;

import DAO.DiaryDAO;
import DAO.userDAO;
import Enity.User;
import Tool.Rand;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@WebServlet(name = "uphead")
public class uphead extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
            List<FileItem> list= upload.parseRequest(request);
            for (FileItem item:list) {
                if(item.isFormField()){
                    request.getRequestDispatcher("./error.html").forward(request, response);
                }else{

                    String path ="/Users/zhangxiang/Desktop/head";
                    String UUID = Rand.generateShortUUID();
                    user.setHead("/Users/zhangxiang/Desktop/head/" + UUID + item.getName());
                    InputStream is = item.getInputStream();
                    File file = new File(path, UUID+item.getName());
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    OutputStream os = new FileOutputStream(file);
                    IOUtils.copy(is,os);
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(os);
                    DiaryDAO diaryDAO = new DiaryDAO();
                    diaryDAO.changheadpath(user.getUsername(),user.getHead());
                    userDAO userDAO = new userDAO();
                    userDAO.uphead(user.getUsername(),user.getHead());
                }

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("./information.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
