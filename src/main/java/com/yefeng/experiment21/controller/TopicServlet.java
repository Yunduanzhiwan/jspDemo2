package com.yefeng.experiment21.controller;

import com.yefeng.experiment21.model.TopicBean;
import com.yefeng.experiment21.model.UserBean;
import com.yefeng.experiment21.model.action.TopicBeanAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName TopicServlet1.java
 * @Description TODO
 * @createTime 2022年06月09日 22:19:00
 */
@WebServlet(name = "TopicServlet", urlPatterns = {"/TopicServlet"})
public class TopicServlet extends BaseServlet {
    public void testMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("dsafasdf");
    }
    public void addLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Integer topicId = Integer.parseInt(request.getParameter("topicId"));
        Integer postId = Integer.parseInt(request.getParameter("postId"));
        UserBean user=(UserBean)request.getSession().getAttribute("user");


        if (user==null){
            response.getWriter().write("请先登录");
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
        if (topicId==null){
            response.getWriter().write("id为空");
            return;
        }
        if (postId==user.getId()){
            response.getWriter().write("自己不能点赞自己的帖子");
            return;
        }
        TopicBeanAction action = new TopicBeanAction();
        if (action.islikeTopic( user.getId(),topicId)){
            response.getWriter().write("已经点赞过了");
            return;
        }

        int flg =action.likeTopic(user.getId(),topicId);
        if (flg==1){
            response.getWriter().write("success");
        }
        else if(flg==0){
            response.getWriter().write("had like");
        }
        else{
            response.getWriter().write("fail");
        }
    }

    public void cancelLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Integer topicId = Integer.parseInt(request.getParameter("topicId"));
        Integer postId = Integer.parseInt(request.getParameter("postId"));
        UserBean user=(UserBean)request.getSession().getAttribute("user");

        if (user==null){
            response.getWriter().write("请先登录");
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
        if (topicId==null){
            response.getWriter().write("topicId为空");
            return;
        }
        if (postId==user.getId()){
            response.getWriter().write("取消点赞自己的帖子？");
            return;
        }
        TopicBeanAction action = new TopicBeanAction();
        boolean flag = action.islikeTopic(user.getId(),topicId);
        if(flag) {
            int flg = action.cancelLikeTopic(user.getId(), topicId);
            if (flg == 1) {
                response.getWriter().write("success");
            } else if (flg == -2) {
                response.getWriter().write("取消失败");
            } else {
                response.getWriter().write("fail");
            }
        }
        else{
            response.getWriter().write("你还没有点赞");
        }




    }
    public void getTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        if (id==null){
            response.getWriter().write("id is null");
        }
        TopicBean topicBean = new TopicBeanAction().getTopicById(id);
        request.setAttribute("topicBean",topicBean);
    }
}
