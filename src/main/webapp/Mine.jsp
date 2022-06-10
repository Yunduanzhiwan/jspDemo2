<%@ page import="com.yefeng.experiment21.model.action.PersonalSpaceAction" %>
<%@ page import="com.yefeng.experiment21.model.PersonalSpace" %>
<%@ page import="com.yefeng.experiment21.model.UserBean" %>
<%@ page import="com.yefeng.experiment21.model.TopicBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: yefeng
  Date: 2022/6/9
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的空间</title>
</head>
<body>

<%

    String sUserId = request.getParameter("userId");
    int userId = 0;
    boolean isMine = false;
    if (sUserId == null) {
        UserBean myUser = (UserBean) request.getSession().getAttribute("user");

        if (myUser == null) {

            response.getWriter().write("<script>alert('请先登录！');window.location.href='login.jsp';</script>");

            return;
        }


        userId = myUser.getId();
        isMine = true;
    } else {
        userId = Integer.parseInt(sUserId);
    }
    PersonalSpaceAction personalSpaceAction = new PersonalSpaceAction();

    PersonalSpace personalSpace = personalSpaceAction.getPersonalSpace(userId);
    UserBean user = personalSpace.getUser();

    int pageSize = 3;
    int currentPage = 1;

    List<TopicBean> myTopicList = personalSpace.getMyTopicList();
    int pageCount = 0;
    int totalCount = myTopicList.size();


    String type = request.getParameter("type");
    if (type == null) {
        type = "";
    }
    ArrayList al = null;
    switch (type) {
        case "myReply":
            al = (ArrayList) personalSpace.getMyReplyTopicList();
            totalCount = al.size();
            break;
//        case "myFan":
//            al = (ArrayList) personalSpace.getMyFanList();
//            break;
        case "myFollow":
            al = (ArrayList) personalSpace.getFollowTopicList();
            totalCount = al.size();
            break;
        case "myTopic":
        default:
            al = (ArrayList) myTopicList;
            totalCount = al.size();
            break;
    }


    pageCount = (totalCount / pageSize);

    if (totalCount % pageSize != 0)
        pageCount++;
    System.out.println("rowCount:" + totalCount);
    System.out.println("pageSize:" + pageSize);
    System.out.println("pageCount=" + pageCount);

%>

<table width="90%" border="1" align="center">
    <tr>
        <jsp:include flush="true" page="head.jsp"></jsp:include>
    </tr>


    <%--            个人信息--%>
    <tr>
        <td colspan="4">
            <%--个人信息--%>
            <table width="100%" border="1" align="center">
                <tr>
                    <td colspan="4">
                        <%--头像--%>
                        <div>
                            <label>头像:</label>
                            <img src="Avatar/<%=user.getAvatar()%>" width="100" height="100"/>
                        </div>
                        <div>
                            <%--用户名--%>
                            <label>用户名:</label>
                            <%=user.getUsername()%>
                        </div>
                        <div>
                            <%--性别--%>
                            <label>性别:</label> <%=user.getSex()%>
                        </div>
                            <div>
                                <%--性别--%>
                                <label>获赞数:</label> <%=user.getLikeCount()%>
                            </div>
                    </td>
                </tr>
                <tr>

                    <%
                        if (isMine) {
                    %>
                    <td>
                        <a href="Mine.jsp?type=myTopic" >我的帖子</a>
                    </td>
                    <td>
                        <a href="Mine.jsp?type=myReply">我的回复</a>
                    </td>
                    <td>
                        <a href="Mine.jsp?type=myFan">我的粉丝</a>
                    </td>
                    <td>
                        <a href="Mine.jsp?type=myFollow">我的关注</a>
                    </td>
                    <%
                        }else{
                    %>
                    <td>
                        <a href="javascript:volid(0);" >我的帖子</a>
                    </td>
                    <%
                        }
                    %>
                </tr>
                <tr>

                    <td colspan="4">
                        <table width="100%" border="1" align="center">
                            <tr class="titleTable">
                                <td class="tableHead">
                                    <div align="center">论坛</div>
                                </td>
                                <td class="tableHead">
                                    <div align="center">标题</div>
                                </td>
                                <td class="tableHead">
                                    <div align="center">作者</div>
                                </td>
                                <td class="tableHead">
                                    <div align="center">发表日期</div>
                                </td>
                                <td class="tableHead">
                                    <div align="center">回复数</div>
                                </td>
                                <td class="tableHead">
                                    <div align="center">点赞数</div>
                                </td>
                            </tr>
                            <%
                                for (int n = 0; n < totalCount; n++) {
                                    TopicBean tb = (TopicBean) al.get(n);
                            %>
                            <tr class="content">
                                <td><%=tb.getForumName()%>
                                </td>
                                <td><a href="ContentServlet?Id=<%=tb.getId()%>"><%= tb.getTitle()%>
                                </a></td>

                                <td><%= tb.getUsername()%>
                                </td>
                                <td><%= tb.getTopicdate()%>
                                </td>
                                <td><%= tb.getReplycount()%>
                                </td>
                                <td><%= tb.getLikeCount()%>
                                </td>
                            </tr>
                            <%
                                }
                            %>

                            <tr>
                                <td colspan="6">
                                    <div align="right" class="tableHead">
                                        <a style="color: white" href="index.jsp?currentPage=1">首页</a>
                                        <%
                                            if (currentPage < pageCount & currentPage > 0) {
                                        %>
                                        <a style="color: white" href="index.jsp?currentPage=<%=currentPage+1%>">下一页</a>
                                        <%
                                            }
                                            if (currentPage > 1) {
                                        %>
                                        <a style="color: white" href="index.jsp?currentPage=<%=currentPage-1%>">上一页</a>
                                        <%
                                            }
                                        %>
                                        总页数:<%=pageCount%>
                                        当前页:<%=currentPage%>
                                    </div>
                                </td>
                            </tr>
                        </table>

                    </td>

                </tr>

            </table>
        </td>
</body>
</html>
