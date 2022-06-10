<%@ page import="com.yefeng.experiment21.model.ForumBean" %>
<%@ page import="com.yefeng.experiment21.model.action.ForumBeanAction" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <link rel="stylesheet" type="text/css" href="test1.css">
    <script type="text/javascript">

        function check() {
            var title1 = document.getElementById("title").value;
            if (title1 == "" | title1 == null) {
                alert("请输入标题!");
                // return false;
            }
            var content = document.getElementById("content").value;
            if (content == "" | content == null) {
                alert("请输入帖子内容!");
                //return false;
            }
            return true;
        }

    </script>
</head>
<body>
<table width="90%" border="0">
    <tr>
        <td>
            <jsp:include flush="true" page="head.jsp"></jsp:include>
        </td>
    </tr>
    <form action="AddTopicServlet" method="post">
        <tr>
            <td>
                <table width="70%" border="1" align="center">
                    <tr>
                        <td colspan="2" class="tableHead">
                            <div align="center">发表新帖</div>
                        </td>
                    </tr>
                    <tr>
                        <td class="tableContent">
                            <div align="right">标题:</div>
                        </td>
                        <td><input name="title" type="text" id="title"/></td>
                    </tr>
                    <tr>
                        <td class="tableContent">
                            <div align="right">论坛:</div>
                        </td>
                        <td >
<%--                            下拉选择--%>
                            <select name="forumId" id="forumType">
                                <%
                                    ArrayList<ForumBean> forumList = new ForumBeanAction().getForumList();
                                    for (ForumBean forumBean : forumList) {
                                        System.out.println(forumBean.getName());
                                %>
                                <option value="<%=forumBean.getId()%>"><%=forumBean.getName()%></option>
<%--                                <a href="index.jsp?type=forum&forumId=<%=forumBean.getId()%>"><%=forumBean.getName()%></a>--%>
                                <%
                                    }
                                %>
<%--                                <option value="title">标题</option>--%>
<%--                                <option value="content">内容</option>--%>
<%--                                <option value="postname">用户</option>--%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="tableContent">
                            <div align="right">内容:</div>
                        </td>
                        <td><textarea name="content" cols="30" rows="5" id="content"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div align="center" class="tableHead">
                                <input type="submit" name="submit" value="提交" class="btn1" id="submit"
                                       onclick="return check()"/>&nbsp;
                                <input name="reset" type="reset" class="btn1" id="reset" value="重置"/>
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
