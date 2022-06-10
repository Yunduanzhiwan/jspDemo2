package com.yefeng.experiment21.controller; /**
 * @ClassName ${NAME}.java
 * @author yefeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月07日 10:49:00
 */

import com.yefeng.experiment21.model.FollowBean;
import com.yefeng.experiment21.model.UserBean;
import com.yefeng.experiment21.model.action.FollowBeanAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FollowServlet", urlPatterns = {"/FollowServlet"})
public class FollowServlet extends BaseServlet {
    public void testMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("dsafasdf");
    }
    /**
     * @param req
     * @param resp
     * @throws @throws     ServletException servlet异常
     * @throws IOException ioexception
     * @title addFollow
     * @author yefeng
     * @description TODO 添加关注
     * @updateTime 2022/06/07
     */
    public void addFollow(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        FollowBean follow = new FollowBean();
        int followUserId = Integer.parseInt(req.getParameter("followUserId"));
        follow.setFollowUserId(followUserId);
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        PrintWriter out = resp.getWriter();


        if (user == null) {
            resp.getWriter().write("请先登录");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        follow.setUserId(user.getId());


        System.out.println(follow.getUserId() + " " + follow.getFollowUserId());
        System.out.println(follow.getUserId() == follow.getFollowUserId());

        if (user.getId() == followUserId) {
            out.write("自己不能关注自己");
            out.flush();
            out.close();
            return;
        }




        FollowBeanAction action = new FollowBeanAction();

        if (action.isFollow(user.getId(),followUserId)) {
            out.write("已经关注过了");
            out.flush();
            out.close();
            return;
        }

        boolean flag = action.isFollow(user.getId(), followUserId);

        if (flag){
            out.write("已经关注过了");
            out.flush();
            out.close();
            return;
        }


         flag = action.addFollow(follow);
        if (flag) {
            out.write("success");
        } else {
            out.write("fail");
        }
        out.flush();
        out.close();
    }


//    是否关注过
    public void isFollow(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        int followUserId = Integer.parseInt(req.getParameter("followUserId"));
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null) {
            resp.getWriter().write("请先登录");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }

        System.out.println(followUserId);

        if (followUserId == user.getId()) {
            resp.getWriter().write("自己不能关注自己");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }


        FollowBeanAction action = new FollowBeanAction();




        boolean flag = action.isFollow(user.getId(), followUserId);
        if (flag) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }



    }


    /**
     * @param req
     * @param resp
     * @throws @throws     ServletException servlet异常
     * @throws IOException ioexception
     * @title deleteFollow
     * @author yefeng
     * @description TODO 取消关注
     * @updateTime 2022/06/07
     */
    public void deleteFollow(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        int followUserId = Integer.parseInt(req.getParameter("followUserId"));
        FollowBean follow = new FollowBean();
        Integer.parseInt(req.getParameter("followUserId"));
        follow.setFollowUserId(followUserId);
        FollowBeanAction action = new FollowBeanAction();

        if (user == null) {
            resp.getWriter().write("请先登录");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }


        boolean flag1 = action.isFollow(user.getId(), followUserId);
        if (!flag1) {
            resp.getWriter().write("您还没有关注过该用户");
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }

        boolean flag = action.cancelFollow(user.getId(), followUserId);
        PrintWriter out = resp.getWriter();
        if (flag) {
            out.write("success");
        } else {
            out.write("fail");
        }
    }


}
