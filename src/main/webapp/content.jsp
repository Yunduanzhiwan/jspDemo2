<%@ page import="com.yefeng.experiment21.model.action.TopicBeanAction" %>
<%@ page import="com.yefeng.experiment21.model.TopicBean" %>
<%@ page import="com.yefeng.experiment21.model.action.ReplyBeanAction" %>
<%@ page import="com.yefeng.experiment21.model.ReplyBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yefeng.experiment21.model.action.FollowBeanAction" %>
<%@ page import="com.yefeng.experiment21.model.UserBean" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    String Id = (String) request.getAttribute("Id");
    if (Id == null | Id == "") {
        Id = request.getParameter("Id");
    }
    TopicBeanAction tba = new TopicBeanAction();
    TopicBean tb = tba.getTopicById(Integer.parseInt(Id));
    ReplyBeanAction rba = new ReplyBeanAction();
    int pageSize = 3;
    int currentPage = 1;
    if (request.getParameter("currentPage") != null)
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
    int replyCount = rba.getReplyCount(Id);
    int pageCount = replyCount / pageSize + 1;
    if (replyCount % pageSize == 0)
        pageCount--;
    ArrayList al = rba.getReplyById(Id, pageSize, currentPage);




%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/1.css" rel="stylesheet" type="text/css"/>
    <link href="css/2.css" rel="stylesheet" type="text/css"/>
    <title>内容页</title>
</head>
<body>
<table width="90%" border="1" align="center">
    <tr>
        <jsp:include flush="true" page="head.jsp"></jsp:include>
    </tr>
    <tr>
        <td>
            <table width="90%" border="1" align="center">
                <tr class="tableHead">
                    <td>
                        <div align="center">作者</div>
                    </td>
                    <td>
                        <div align="center">内容</div>
                    </td>
                </tr>
                <%
                    if (tb != null) {
                %>
                <tr class="content">
                    <td>
                        <a href="Mine.jsp?userId=<%=tb.getPostId()%>" >
                            <%=tb.getUsername()%>
                    </a>
                    </td>
                    <td>发表时间：<%=tb.getTopicdate()%>
                    </td>
                </tr>
                <%
                    String fileName = "Avatar/" + tb.getAvatar();

                %>
                <tr>
                    <td height="118"><img src="<%=fileName%>" width="100" height="100"/></td>
                    <td><%=tb.getContent()%>
                    </td>
                </tr>


                <script>
                    var isAddLike = false;
                    var isFollow = false;

                    function cancelFollow(){
                        if (isFollow) {
                            var xmlhttp;
                            if (window.XMLHttpRequest) {
                                xmlhttp = new XMLHttpRequest();
                            } else {
                                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                            }
                            xmlhttp.open("GET", "FollowServlet?method=deleteFollow&followUserId=" + <%=tb.getId()%>, true);
                            xmlhttp.send();
                            xmlhttp.onload = function () {
                                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                                    var result = xmlhttp.responseText;
                                    if (result == "success") {
                                        alert('取消关注成功');
                                        isFollow = true
                                        const button = document.getElementById("follow");
                                        button.textContent = '关注'
                                        button.onclick=cancelFollow
                                    } else {
                                        alert(result);
                                    }
                                }else {
                                    alert('网络错误')
                                }
                            }
                        }
                    }

                    function addFollow() {
                        if (isFollow) {
                            alert('已经关注过了')
                            const button = document.getElementById("follow");
                            button.textContent = '取消关注'
                            button.onclick=cancelFollow
                            return
                        }
                        //网络请求
                        var xmlhttp;
                        if (window.XMLHttpRequest) {
                            xmlhttp = new XMLHttpRequest();
                        } else {
                            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        xmlhttp.open("GET", "FollowServlet?method=addFollow&followUserId=" + <%=tb.getPostId()%>, true);
                        xmlhttp.send();
                        xmlhttp.onload = function () {
                            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                                var result = xmlhttp.responseText;
                                if (result == "success") {
                                    alert('关注成功')
                                    isFollow = true
                                    const button = document.getElementById("follow");
                                    button.textContent = '取消关注'
                                    button.onclick=cancelFollow
                                } else {
                                    alert(result);
                                    if (result=="已经关注过了"){
                                        const button = document.getElementById("follow");
                                        button.textContent = '取消关注'
                                        button.onclick=cancelFollow
                                        isFollow = true
                                    }
                                }
                            }else {
                                alert('网络错误')
                            }
                        }
                    }

                    function cancelAddLike(){
                        if (isAddLike) {
                            var xmlhttp;
                            if (window.XMLHttpRequest) {
                                xmlhttp = new XMLHttpRequest();
                            } else {
                                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                            }
                            xmlhttp.open("GET", "TopicServlet?method=cancelLike&topicId=<%=tb.getId()%>&postId=<%=tb.getPostId()%>", true);
                            xmlhttp.send();
                            xmlhttp.onload = function () {
                                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                                    var result = xmlhttp.responseText;
                                    if (result == "success") {
                                        alert('取消点赞成功')
                                        isAddLike = false
                                        const button = document.getElementById("like");
                                        button.textContent = '点赞'
                                        button.onclick=addLike
                                    } else {
                                        alert(result);
                                    }
                                }else {
                                    alert('网络错误')
                                }
                            }
                        }
                    }

                    function addLike() {
                        if (isAddLike) {

                            const button = document.getElementById("like");
                            button.textContent = '取消点赞'
                            button.onclick=cancelAddLike
                            alert("已经点过赞了");
                            return
                        }
                        //网络请求
                        var xmlhttp;
                        if (window.XMLHttpRequest) {
                            xmlhttp = new XMLHttpRequest();
                        } else {
                            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        xmlhttp.open("get", "TopicServlet?method=addLike&topicId=<%=tb.getId()%>&postId=<%=tb.getPostId()%>", true);
                        xmlhttp.send();
                        xmlhttp.onload = function () {
                            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                                var result = xmlhttp.responseText;3

                                if (result == "success") {
                                    alert('点赞成功')
                                    isAddLike = true
                                    const button = document.getElementById("like");
                                    button.textContent = '取消点赞'
                                    button.onclick=cancelAddLike
                                } else {
                                    alert(result);
                                    if (result == "已经点赞过了") {
                                        const button = document.getElementById("like");
                                        button.textContent = '取消点赞'
                                        isAddLike = true
                                        button.onclick=cancelAddLike
                                    }
                                }
                            }else {
                                alert("网络错误")
                            }
                        }
                    }
                </script>

                <tr>
                    <td colspan="2" style="text-align: center">
                        <%
                            //判断已经关注过了
                        %>
                        <button id="follow" onclick="addFollow()">关注</button>
                        <button id="like" onclick="addLike()">
                            点赞
                        </button>
                    </td>
                </tr>
                <%
                    }
                %>
                <%--            //评论--%>

                <tr>
                    <td colspan="2" style="text-align: center">评论区</td>
                </tr>
                <%
                    for (int i = 0; i < al.size(); i++) {
                        ReplyBean rb = (ReplyBean) al.get(i);
                %>
                <tr>
                    <td>
                        <a href="Mine.jsp?userId=<%=rb.getUserId()%>" >
                            <%=rb.getUserName()%>
                    </a>
                    </td>
                    <td>发表于：<%=rb.getReplyDate()%>
                    </td>
                </tr>
                <%
                    //String fileName2 = "UserFiles/images/" + rb.getUserName() + ".jpg";
                    String fileName2 = "Avatar/" + rb.getUserAvatar();
                %>
                <tr>
                    <td height="118"><img src="<%=fileName2%>" width="100" height="100"/></td>
                    <td>
                        <%=rb.getReplyContent()%>
                    </td>
                </tr>
                <%
                    }
                %>
                <%--                <tr>--%>
                <%--                    <td colspan="2">--%>
                <%--                        <table width="100%" border="1">--%>
                <%--                            <%--%>
                <%--                                for (int i = 0; i < al.size(); i++) {--%>
                <%--                                    ReplyBean rb = (ReplyBean) al.get(i);--%>
                <%--                            %>--%>
                <%--                            <tr>--%>
                <%--                                <td><%=rb.getUserName()%>--%>
                <%--                                </td>--%>
                <%--                                <td>发表于：<%=rb.getReplyDate()%>--%>
                <%--                                </td>--%>
                <%--                            </tr>--%>
                <%--                            <%--%>
                <%--                                //String fileName2 = "UserFiles/images/" + rb.getUserName() + ".jpg";--%>
                <%--                                String fileName2 = "Avatar/" + rb.getUserName() + ".jpg";--%>
                <%--                            %>--%>
                <%--                            <tr>--%>
                <%--                                <td height="118"><img src="<%=fileName2%>" width="100" height="100"/></td>--%>
                <%--                                <td>--%>
                <%--                                    <%=rb.getReplyContent()%>--%>
                <%--                                </td>--%>
                <%--                            </tr>--%>
                <%--                            <%--%>
                <%--                                }--%>
                <%--                            %>--%>
                <%--                        </table>--%>
                <%--                    </td>--%>
                <%--                </tr>--%>


                <tr class="titleTable">
                    <td colspan="2">
                        <div align="right">
                            <a style="color: white" href="content.jsp?Id=<%=Id%>&currentPage=1">首页</a>
                            <%
                                if (currentPage < pageCount & currentPage > 0) {
                            %>
                            <a style="color: white" href="content.jsp?Id=<%=Id%>&currentPage=<%=currentPage+1%>">下一页</a>
                            <%
                                }
                                if (currentPage > 1) {
                            %>
                            <a style="color: white" href="content.jsp?Id=<%=Id%>&currentPage=<%=currentPage-1%>">上一页</a>
                            <%
                                }
                            %>
                            总页数:<%=pageCount%>
                            当前页:<%=currentPage%>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="button" name="Submit" value="回复"
                                   onclick="location.href='reply.jsp?Id=<%=tb.getId()%>'"/>
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
