package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/download/*")
public class FileDownload extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] url = request.getParameter("path").split("FileDownload/");
        String path = url[0];
        BufferedInputStream bis = null;
        OutputStream os = null;


            //你要查看的路
            try (InputStream inputStream = new FileInputStream(path)) {
                response.setContentType("application/octet-stream; charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + path);
                byte[] buff = new byte[1024];


                try {
                    os = response.getOutputStream();
                    bis = new BufferedInputStream(inputStream);
                    int i = bis.read(buff);
                    while (i != -1) {
                        os.write(buff, 0, buff.length);
                        os.flush();
                        i = bis.read(buff);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
