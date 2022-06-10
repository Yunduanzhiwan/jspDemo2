package com.yefeng.experiment21.controller; /**
 * @ClassName ${NAME}.java
 * @author yefeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月09日 20:55:00
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("你搜索的关键字是：" + keyword);

    }
}