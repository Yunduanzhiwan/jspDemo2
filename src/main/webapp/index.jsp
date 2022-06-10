<%@page import="java.util.ArrayList" %>
<%@ page import="com.yefeng.experiment21.model.action.TopicBeanAction" %>
<%@ page import="com.yefeng.experiment21.model.TopicBean" %>
<%@ page import="com.yefeng.experiment21.model.action.ForumBeanAction" %>
<%@ page import="com.yefeng.experiment21.model.ForumBean" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    <link href="css/1.css" rel="stylesheet" type="text/css"/>
    <link href="css/2.css" rel="stylesheet" type="text/css"/>


</head>
<%
    TopicBeanAction tba = new TopicBeanAction();
    request.setCharacterEncoding("UTF-8");
    int pageSize = 3;
    int currentPage = 1;
    if (request.getParameter("currentPage") != null)
        currentPage = Integer.parseInt(request.getParameter("currentPage"));

    ArrayList al;
    int pageCount = 0;
    int totalCount = 0;

    if (request.getParameter("type")!=null){
        if (request.getParameter("type").equals("search")){
            String searchKey = request.getParameter("searchKey");
            String searchType = request.getParameter("searchType");
            if (searchKey == null||searchKey.equals("")) {
                searchKey = "";
            }
            searchKey = new String(searchKey.getBytes("iso-8859-1"), "utf-8");
//    String searchKey = request.getParameter("searchKey");
            System.out.println("searchKey"+searchKey);
            al = tba.getTopicByPage(pageSize, currentPage, searchKey,searchType);
            System.out.println(al);
            totalCount=tba.getCountBySearch(searchKey,searchType);
        }
        else if(request.getParameter("type").equals("forum")){
//                根据版块id获取该版块下的帖子
            int forumId = Integer.parseInt(request.getParameter("forumId"));
            al = tba.getTopicByForumId(forumId,pageSize,currentPage);
            totalCount= tba.getForumCount(forumId);
        }else {
            al = tba.getTopicByPage(pageSize, currentPage);
            totalCount=tba.getRowCount();
//            totalCount=al.size();
//
//            pageCount = tba.getRowCount() / pageSize + 1;
//            System.out.println("pageCount:"+pageCount);
        }
    }else {
        al = tba.getTopicByPage(pageSize, currentPage);
        totalCount=tba.getRowCount();
    }

    pageCount  = (totalCount / pageSize);


    if (totalCount % pageSize != 0)
        pageCount++;
    System.out.println("totalCount:"+totalCount);
    System.out.println("pageSize:"+pageSize);
    System.out.println("pageCount="+pageCount);

%>

<body>

<tr>
    <table width="90%" border="1" align="center">
        <tr>
            <jsp:include flush="true" page="head.jsp"></jsp:include>
        </tr>
        <tr>
            <td colspan="4">
                <div >
<%--                    搜索框--%>
                    <form action="index.jsp?type=search" method="post" style="flex-direction: row; display: initial;">
                        <input type="text" style="width: 400px;" name="searchKey" id="searchTopic" size="20" value=""/>
<%--                        下拉--%>
                        <select name="searchType" id="searchType">
                            <option value="title">标题</option>
                            <option value="content">内容</option>
                            <option value="postname">用户</option>
                        </select>
                        <input type="submit" value="搜索"/>
                    </form>
                    <input type="submit" name="postMassage" value="发帖" onclick="location.href='addTopic.jsp';"/>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <%
                    ArrayList<ForumBean> forumList = new ForumBeanAction().getForumList();
                    for (ForumBean forumBean : forumList) {
                        System.out.println(forumBean.getName());
                %>
                    <a href="index.jsp?type=forum&forumId=<%=forumBean.getId()%>"><%=forumBean.getName()%></a>
                <%
                    }
                %>
            </td>
        </tr>

        <tr>
            <td>
                <table width="90%" border="1" align="center">
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
                            <div align="center">点赞数</div>
                        </td>
                        <td class="tableHead">
                            <div align="center">回复数</div>
                        </td>

                    </tr>
                    <%
                        for (int n = 0; n < al.size(); n++) {
                            TopicBean tb = (TopicBean) al.get(n);
                    %>
                    <tr class="content">
                        <td><%=tb.getForumName()%></td>
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
        <tr>
            <jsp:include flush="true" page="tail.jsp"></jsp:include>
        </tr>
    </table>
</body>
</html>
