/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yefeng.experiment21.controller;

import com.yefeng.experiment21.model.action.TopicBeanAction;
import com.yefeng.experiment21.model.UserBean;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AddTopicServlet", urlPatterns = {"/AddTopicServlet"})
public class AddTopicServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            request.setCharacterEncoding("UTF-8");
            String title=request.getParameter("title");
            String content=request.getParameter("content");
            int forumId= Integer.parseInt(request.getParameter("forumId"));
            UserBean user=(UserBean)request.getSession().getAttribute("user");

            if(user==null){
                response.getWriter().write("<script>alert('请先登录！');window.location.href='login.jsp';</script>");
                return;
            }

            String username=user.getUsername();
            TopicBeanAction tba=new TopicBeanAction();
//             boolean flag=tba.addTopic(username, title, content);

            System.out.println(user);

             boolean flag=tba.addTopicToForum( title, content,username,user.getId(),forumId);
            System.out.println("your username="+username);
            out.println("<script>");
            if(flag){
                out.println("alert('发帖成功！');");
                out.println("location.href='index.jsp';");
            }
            else{
                out.println("alert('发帖失败，请重试！');");
                out.println("location.href='addTopic.jsp';");
            }
            out.println("</script>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
