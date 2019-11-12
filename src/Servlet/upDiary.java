package Servlet;


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

@WebServlet(name = "upDiary")
public class upDiary extends HttpServlet {
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String message="图片";
    int num = 0;
    String url=null;
        try {
            HttpSession session = request.getSession();
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
            List<FileItem> list= upload.parseRequest(request);
            for (FileItem item:list) {
                if(item.isFormField()){
                    request.getRequestDispatcher("./error.html").forward(request, response);
                }else{
                    String UUID = Rand.generateShortUUID();
                    String path =request.getServletContext().getRealPath(
                            "/images");

                    url="http://localhost:8080/Facebowl_war_exploded/images/"+ UUID + item.getName();
                    num=1;
                    InputStream is = item.getInputStream();
                    File file = new File(path, UUID+item.getName());
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    OutputStream os = new FileOutputStream(file);
                    IOUtils.copy(is,os);
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(os);
                }


            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }



        PrintWriter out = response.getWriter();
        String str ="{\"success\":1,\"message\":\"susse\",\"url\":"+'\"'+url+'\"'+"}";
        out.println(str);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
