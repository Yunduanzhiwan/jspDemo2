<%@ page import="com.yefeng.experiment21.model.UserBean" %><%--
    Document   : reply
    Created on : 2022-5-6, 14:23:20
    Author     : 慢下来世界就是你的
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <% 
     UserBean user = (UserBean) session.getAttribute("user");
         if(user==null) {
%> 
        <script>
             alert('请您在登录后进行回帖！');
            location.href="login.jsp";  
         </script> 
<%
 }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/1.css" rel="stylesheet" type="text/css"/>
        <link href="css/2.css" rel="stylesheet" type="text/css"/>
        <title>回帖页</title>      
    </head>
    <body>
        
        <table width="90%" border="1" align="center">
  <tr>
    <jsp:include flush="true" page="head.jsp"></jsp:include>
  </tr>
  <form action="ReplyServlet" method="post">
  <%
         String Id  = request.getParameter("Id");
  %>
  <input type="hidden" name="Id" value="<%=Id%>"/>
  <tr>
    <td><table width="70%" border="1" align="center">
      <tr>
        <td colspan="2" class="tableHead"><div align="center">回复</div></td>
      </tr>
      <tr class="content">
        <td>内容</td>
        <td><textarea name="replyContent" id="replyContent" cols="70" rows="15"></textarea></td>
      </tr>
      <tr>
          <td colspan="2">
          <div align="center">
            <input type="reset" name="Submit" value="重置" />
            <input type="submit" name="Submit2" value="提交" onclick="return ReplyCheck()"/>
          </div>
       
        </td>
      </tr>
    </table></td>
  </tr>
  </form>
  <tr>
   <jsp:include flush="true" page="tail.jsp"></jsp:include>
  </tr>
</table>
  <script>
     function ReplyCheck(){
         var replyCount = document.getElementById("replyCount").value;
         if(replyCount==""||replyCount==null){
             alert("请输入回帖内容！")
             return false;
         }
        return true;
     }
  </script>
    </body>
</html>
