<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
        /*查询所有的菜单
         * */
        $(function () {
            //alert("asdas");
            $.ajax({
                url: "${pageContext.request.contextPath}/menu/showAll",   //路径
                dataType: "JSON",  //数据类型为JSON类型
                success: function (data) {  //数组 进行遍历
                    //一级分类二级分类
                    //集合  遍历元素下标  遍历的元素名称
                    $.each(data, function (index, first) {
                        // alert(first.title);
                        var c = "";  //设置一个变量
                        //一级分类下二级元素的遍历  遍历元素的下标  遍历的元素名称
                        $.each(first.secondMenu, function (index1, second) {
                            //添加按钮，居中，添加图标，点击事件（添加一个选项卡）
                            c += "<p style='text-align: center'><a href='#' class=\"easyui-linkbutton\"  data-options=\"iconCls:'icon-search'\" onclick=\"addTabs('" + second.title + "','" + second.iconcls + "','" + second.href + "')\"> " + second.title + " </a></p > ";
                            //console.log(second.title)  //控制台打印
                        });

                        //console.log(first+"sss")
                        //alert(first.title)
                        $('#aa').accordion('add', {  //一级类别下的属性值
                            title: first.title,
                            content: c,
                            iconCls: first.iconCls,
                            selected: false
                        });
                    })
                }
            })
        })
        function addTabs(title, iconCls, href) {
            /*
             * 判断选项卡是否存在
             * */
            var b = $("#tt").tabs("exists", title); //exists表明选项卡是否存在
            /*alert(b);*/
            if (b) {
                $("#tt").tabs("select", title)  //查询选项卡
            } else {
                // 添加一个未选中状态的选项卡面板
                $('#tt').tabs('add', {
                    title: title,
                    selected: true,  //选中
                    closable: true,  //显示关闭按钮
                    iconCls: iconCls,
                    href: "${pageContext.request.contextPath}" + href
                });
            }
        }
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 400px;float:right;padding-top:15px">欢迎您:${sessionScope.admin1.username} &nbsp;
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/admin/exit" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(${pageContext.request.contextPath}/main/image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>