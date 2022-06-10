/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yefeng.experiment21.controller;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yefeng.experiment21.model.action.UserBeanAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 97549
 */
@WebServlet(name = "RegServlet", urlPatterns = {"/RegServlet"})
public class RegServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */



            // 上传文件 头像
            SmartUpload mySmartUpload = new SmartUpload();
            mySmartUpload.initialize(this.getServletConfig(), request, response);
            mySmartUpload.setAllowedFilesList("jpg,gif,png,JPG,GIF,PNG");
            String root = getServletContext().getRealPath("/");
            String savePath = root + "\\Avatar\\";
            System.out.println(savePath);
            java.io.File dir = new java.io.File(savePath);
            if(!dir.exists())
                dir.mkdirs();
            try {
                mySmartUpload.upload();
            } catch (SmartUploadException ex) {
                Logger.getLogger(RegServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            com.jspsmart.upload.File myFile;
            myFile = mySmartUpload.getFiles().getFile(0);
            String fileExt = myFile.getFileExt();
            String userName = mySmartUpload.getRequest().getParameter("username");
            String fileName = userName + "."+fileExt;
            try {
                myFile.saveAs(savePath + fileName, SmartUpload.SAVE_PHYSICAL);
            } catch (SmartUploadException ex) {
                Logger.getLogger(RegServlet.class.getName()).log(Level.SEVERE, null, ex);
            }



            //变量对应数据库  注册用户
            String username=mySmartUpload.getRequest().getParameter("username");
            String password=mySmartUpload.getRequest().getParameter("password1");
            String sex=mySmartUpload.getRequest().getParameter("sex");
            String email=mySmartUpload.getRequest().getParameter("email");
            String phone=mySmartUpload.getRequest().getParameter("phone");
            UserBeanAction uba=new UserBeanAction();


            out.println("<script>");
          //  if(uba.checkUser(phone)){
                 boolean flag=uba.regUser( username, password, sex, email, phone,fileName);
              
            if(flag){
                out.println("alert('注册成功，请登录！');");
                out.println("location.href='login.jsp';");
            }else{
                out.println("alert('注册失败，请重试！');");
                out.println("location.href='reg.jsp';");
            }
             
        //    }
/*           else
            {
                out.println("alert('用户名被占用，请更fg换！');");
                out.println("location.href='reg.jsp';");
            }*/
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
