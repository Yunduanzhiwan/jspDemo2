<%@ page import="com.yefeng.experiment21.model.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: yefeng
  Date: 2022/4/30
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <%--        <link href="css/reg.css" rel="stylesheet" type="text/css"/>--%>
    <script type="text/javascript">
        function login() {
            location.href = 'login.jsp';
        }

        function reg() {
            location.href = 'reg.jsp';
        }
    </script>
</head>
<%
    UserBean user = (UserBean) session.getAttribute("user");
%>
<body>
<tr>
    <td>
        <table width="100%" border="1" align="center" bordercolor="#009900">
            <tr>
                <td width="23%" class="index"><img src="img/logo.png" width="260" height="80"/></td>
                <td width="35%" class="index">编程语言：Java C++ PHP</td>
                <td width="30%" class="index">操作系统：Windows Android Mac</td>
                <td width="12%" class="index">
                    <%
                        if (user == null) {
                    %>
                    <button value="登录" onclick="login()" class="btn1">登录</button>
                    <button value="注册" onclick="reg()" class="btn1">注册</button>
                    <%
                    } else {
                    %>
                    <span>
                          <%=user.getUsername()%>你好！
                   </span>

                    <script>
                        function logout() {
                            //网络请求
                            var xhr = new XMLHttpRequest();
                            xhr.open("GET", "logout", true);
                            xhr.send();
                            xhr.onload = function () {
                                if (xhr.readyState == 4 && xhr.status == 200) {
                                    var result = xhr.responseText;
                                    if (result == "success") {
                                        location.href = "index.jsp";
                                    }
                                }
                            }
                        }
                    </script>
                    <button value="退出" onclick="logout()" class="btn1">退出</button>
                    <%
                        }
                    %>
                </td>
            </tr>
            <tr>
                <td class="index"><a href="index.jsp">首页</a></td>
                <td class="index">论坛</td>
                <td class="index">站点导航</td>
                <td class="index"><a href="Mine.jsp">个人空间</a></td>
            </tr>
        </table>
    </td>
</tr>
</body>
</html>
