package com.yefeng.experiment21.controller;


import com.yefeng.experiment21.model.PersonalSpace;
import com.yefeng.experiment21.model.UserBean;
import com.yefeng.experiment21.model.action.PersonalSpaceAction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName PersonalSpace.java
 * @Description TODO
 * @createTime 2022年06月10日 00:19:00
 */
@WebServlet(name = "PersonalSpaceServlet", urlPatterns = {"/PersonalSpaceServlet"})
public class PersonalSpaceServlet extends BaseServlet {

    public void getPersonalSpace(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        UserBean userBean= (UserBean) request.getSession().getAttribute("user");
        PrintWriter out = response.getWriter();
        if (userBean==null){
           out.write("请先登录");
            return;
        }

        PersonalSpaceAction personalSpaceAction=new PersonalSpaceAction();
        PersonalSpace personalSpace = personalSpaceAction.getPersonalSpace(userBean.getId());
        System.out.println(personalSpace);
        request.getSession().setAttribute("personalSpace",personalSpace);
        out.write("success");
    }

}
