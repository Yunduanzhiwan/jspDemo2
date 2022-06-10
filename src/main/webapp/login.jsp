<%--
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
    <link href="css/reg.css" rel="stylesheet" type="text/css"/>
    <script src="js/login.js" type="text/javascript"></script>
</head>
<body>
<table width="100%" border="1">
    <tr>
        <td bordercolor="#003300">
            <jsp:include flush="true" page="head.jsp"></jsp:include>
        </td>
    </tr>
    <form action="LoginServlet" method="post" name="form1">
        <tr>
            <td bordercolor="#003300">
                <table width="60%" border="1" align="center">
                    <tr>
                        <td height="33" colspan="2" bordercolor="#3300CC" bgcolor="#FFFFFF">
                            <div align="center" class="tableHead">登录</div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" bordercolor="#3300CC" bgcolor="#FFFFFF" class="tableContent">
                            <div align="center" class="STYLE1 STYLE1">账号：
                                <span class="STYLE1 STYLE1">
                                     <input name="account" type="text" id="account" placeholder="手机号/邮箱/账号">
                              </span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" bordercolor="#3300CC" bgcolor="#FFFFFF" class="tableContent">
                            <div align="center" class="STYLE1 STYLE1">密码：
                                <span class="STYLE1 STYLE1">
                                   <input name="password" type="password" id="password" placeholder="密码"/>
                              </span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" bordercolor="#3300CC" bgcolor="#FFFFFF">
                            <div align="right" class="STYLE1 STYLE1">
                                <div align="center" class="tableHead">
                                    <input name="submit" type="submit" class="btn1" id="submit" value="登录"
                                           onclick="return check()"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </form>
    <tr>
        <td bordercolor="#003300" style="text-align: center">
            <jsp:include flush="true" page="tail.jsp"></jsp:include>
        </td>
    </tr>
</table>
</body>
</html>
