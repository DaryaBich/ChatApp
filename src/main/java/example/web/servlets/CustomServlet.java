package example.web.servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomServlet extends HttpServlet implements Servlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("somthing comes");
        System.out.println(req.getQueryString());
        String item = req.getParameter("param");
        ///myServlet/s?param=dsdsd
        String newItem = new String(item.getBytes("ISO-8859-1"), "utf-8");
        System.out.println(newItem + " -<new parameter");
        req.getParameterMap().forEach((k, v) -> {
            System.out.println(k);
            for (String s : v) {
                System.out.println(s);
            }
        });
    }

}
