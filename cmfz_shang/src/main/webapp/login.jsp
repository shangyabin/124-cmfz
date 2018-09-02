<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css"></link>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">

        $(function () {
            //用户名验证
            $('#username').validatebox({
                required: true,  //必填
                validType: ['length[4,18]'],  //字段验证类型
                missingMessage: "请输入用户名",  //当文本框未填写时出现的提示信息
                invalidMessage: "请正确输入用户名" //当文本框的内容被验证为无效时出现的提示
            });
            //密码验证
            $('#password').validatebox({
                required: true,  //必填
                validType: ['length[4,8]'],  //字段验证类型
                missingMessage: "请输入密码",  //当文本框未填写时出现的提示信息
                invalidMessage: "请正确输入密码" //当文本框的内容被验证为无效时出现的提示
            });
            //验证码验证
            $('#enCode').validatebox({
                required: true,
                missingMessage: '验证码不能为空',
            });

            $("#login").click(function () {
                // alert("自己做");
                $('#loginForm').submit();

            });
        });
        //验证码刷新
        function changeImage() {
            $("#kaptchaImage").prop("src", "${pageContext.request.contextPath}/getKaptcha.do?t=" + new Date().getTime());
        }
    </script>
</head>
<body>
<div class="login">
    <form id="loginForm" action="${pageContext.request.contextPath}/admin/loginin" method="post">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="${pageContext.request.contextPath}/img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input id="username" type="text" name="username" class="text" value="请输入用户名" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input id="password" type="text" name="password" class="text" value="请输入密码" maxlength="20"
                           autocomplete="off"/>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" id="enCode" name="enCode" class="text captcha" maxlength="4" autocomplete="off"/>
                    <img id="kaptchaImage" class="captchaImage"
                         src="${pageContext.request.contextPath}/getKaptcha.do?t='+new Date().getTime()"
                         onclick="changeImage()" title="点击更换验证码"/>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit"
                                                                                                        class="loginButton"
                                                                                                        value="登录">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>