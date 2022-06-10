package com.yefeng.experiment21.controller; /**
 * @ClassName ${NAME}.java
 * @author yefeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月07日 22:05:00
 */

import com.yefeng.experiment21.model.ForumBean;
import com.yefeng.experiment21.model.UserBean;
import com.yefeng.experiment21.model.action.ForumBeanAction;
import jdk.nashorn.internal.parser.JSONParser;
import sun.plugin.javascript.JSObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "forumServlet", value = "/forumServlet")
public class ForumServlet extends BaseServlet {

    public void testMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("dsafasdf");
    }
    public void addForum(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String  ForumName=req.getParameter("name");
//        UserBean user = (UserBean) req.getSession().getAttribute("user");

        ForumBeanAction forumBeanAction = new ForumBeanAction();
        boolean flag = forumBeanAction.addForum(ForumName);
        PrintWriter out = resp.getWriter();
        if (flag) {
            out.print("添加论坛成功");
        } else {
            out.print("添加论坛失败");
        }
    }

    public void deleteForum(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int forumId = Integer.parseInt(req.getParameter("forumId"));


        ForumBeanAction forumBeanAction = new ForumBeanAction();
        boolean flag = forumBeanAction.deleteForum(forumId);

        PrintWriter out = resp.getWriter();

        if (flag) {
            out.print("删除论坛成功");
        } else {
            out.print("删除论坛失败");
        }


    }


    public void updateForum(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int forumId = Integer.parseInt(req.getParameter("forumId"));
        String ForumName = req.getParameter("forumName");

        ForumBeanAction forumBeanAction = new ForumBeanAction();
        boolean flag = forumBeanAction.updateForum(forumId,ForumName);

        PrintWriter out = resp.getWriter();

        if (flag) {
            out.print("修改论坛成功");
        } else {
            out.print("修改论坛失败");
        }


    }

    public void getForum(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        ForumBeanAction forumBeanAction = new ForumBeanAction();
        ArrayList<ForumBean> forumList = forumBeanAction.getForumList();
        PrintWriter out = resp.getWriter();
        out.print(forumList);
    }
}
