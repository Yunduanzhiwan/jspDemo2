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
    <script src="js/reg.js" type="text/javascript"></script>
</head>
<body>
<table width="90%" border="0">
    <tr>
        <td bordercolor="#003300">
            <jsp:include flush="true" page="head.jsp"></jsp:include>
        </td>
    </tr>
    <form action="RegServlet" method="post" name="form1" enctype="multipart/form-data">
        <tr>
            <td bordercolor="#003300">
                <table width="60%" border="1" align="center">
                    <tr>
                        <td height="33" colspan="2" bordercolor="#3300CC" bgcolor="#FFFFFF">
                            <div align="center" class="tableHead">注册</div>
                        </td>
                    </tr>
                    <tr>
                        <td width="28%" bordercolor="#3300CC" bgcolor="#FFFFFF" class="tableContent">
                            <div align="right" class="STYLE1 STYLE1">用户名：</div>
                        </td>
                        <td width="72%" bordercolor="#3300CC" bgcolor="#FFFFFF"><span class="STYLE1 STYLE1">
          <input name="username" type="text" id="username"/>
        </span></td>
                    </tr>
                    <tr>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF" class="tableContent">
                            <div align="right" class="STYLE1 STYLE1">密码：</div>
                        </td>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF"><span class="STYLE1 STYLE1">
          <input name="password1" type="password" id="password1"/>
        </span></td>
                    </tr>
                    <tr>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF" class="tableContent">
                            <div align="right" class="STYLE1 STYLE1">确认密码：</div>
                        </td>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF"><span class="STYLE1 STYLE1">
          <input name="password2" type="password" id="password2"/>
        </span></td>
                    </tr>
                    <tr>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF" class="tableContent">
                            <div align="right" class="STYLE1 STYLE1">性别：</div>
                        </td>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF"><span class="tableContent">
          <input name="sex" type="radio" value="男" id="boy" checked=""/>
          男
            <input type="radio" name="sex" value="女" id="girl"/>
          女</span></td>
                    </tr>
                    <tr>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF" class="tableContent">
                            <div align="right" class="STYLE1 STYLE1">手机号：</div>
                        </td>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF"><span class="STYLE1 STYLE1">
          <input name="phone" type="text" id="phone"/>
        </span></td>
                    </tr>
                    <tr>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF" class="tableContent">
                            <div align="right" class="STYLE1 STYLE1">邮箱：</div>
                        </td>
                        <td bordercolor="#3300CC" bgcolor="#FFFFFF"><span class="STYLE1 STYLE1">
          <input name="email" type="text" id="email"/>
        </span></td>
                    </tr>
                    <tr>
                        <td class="content"><div align = "right">头像：</div></td>
                        <td><input type="file" name="headIcon" value="headIcon"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" bordercolor="#3300CC" bgcolor="#FFFFFF">
                            <div align="right" class="STYLE1 STYLE1">
                                <div align="center" class="tableHead">
                                    <input name="reset" type="reset" class="btn1" id="reset" value="重置"/>
                                    &nbsp;
                                    <input name="submit" type="submit" class="btn1" id="submit" value="提交"
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
