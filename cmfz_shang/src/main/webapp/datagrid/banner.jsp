<%--
  Created by IntelliJ IDEA.
  User: 小尚
  Date: 2018/8/29
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        $('#dg').edatagrid({
            destroyUrl: "${pageContext.request.contextPath}/banner/delete",
            updateUrl: "${pageContext.request.contextPath}/banner/update",
            url: "${pageContext.request.contextPath}/banner/showAll",
            columns: [[

                {field: 'title', title: '标题', width: 100},
                {field: 'url', title: '路径', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {//双击修改status字段
                    type: 'text',
                    options: {
                        required: true
                    }
                }
                },
                {field: 'createDate', title: '创建时间', width: 100},
                {field: 'description', title: '描述', width: 100},
            ]],
            pagination: true,//底部分页工具栏
            fitColumns: true,//页面自适应，左右
            fit: true,//页面自适应，上下


            //顶部工具栏
            toolbar: [{
                text: "添加",
                iconCls: 'icon-add',
                handler: function () {
                    $("#title").val("");
                    $("#description").val("");
                    $("#img").val("");

                    $('#addDialog').dialog('open');  //打开添加窗口

                }
            }, '-', {
                text: "修改",
                iconCls: 'icon-edit',
                handler: function () {
                    //获取选中行
                    var row = $('#dg').edatagrid('getSelected');
                    //判断是否选中
                    if (row != null) {
                        //获取选中行的索引
                        var index = $('#dg').edatagrid('getRowIndex', row);
                        //将指定行变为可编辑
                        $('#dg').edatagrid('editRow', index);
                    } else {
                        alert("请先选中行!!!");
                    }
                }
            }, '-', {
                text: "删除",
                iconCls: 'icon-cancel',
                handler: function () {
                    //获取选中行
                    var row = $('#dg').edatagrid('getSelected');
                    if (row != null) {
                        //销毁选中行
                        $('#dg').edatagrid('destroyRow');
                    } else {
                        alert("请先选中行!!!");
                    }
                }
            }, '-', {
                text: "保存",
                iconCls: 'icon-reload',
                handler: function () {
                    //保存编辑行并发送到服务器
                    $('#dg').edatagrid('saveRow'),
                    $('#dg').edatagrid('reload')
                }
            }],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0">' +
                    '<img src="' + "${pageContext.request.contextPath}/upload/banner/" + rowData.url + '" style="height:50px;">' +
                    '</td>' +
                    '<td style="border:0">' +
                    '<p>创建时间: ' + rowData.createDate + '</p>' +
                    '<p>状态: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });

    });


    //对话框
    $('#addDialog').dialog({
        title: '添加轮播图',
        width: 400,
        height: 250,
        closed: true,
        modal: true,
        buttons: [{
            text: '保存',
            handler: function () {
                toSubmit();

            }
        }, {
            text: '关闭',
            handler: function () {
                $('#addDialog').dialog('close');
            }
        }]
    });
    //保存
    function toSubmit() {
        //alert("!~~~~~~~~~~~~~~~~~~~~~~~~~");
        $('#addDialogFrom').form('submit', {
            url: "${pageContext.request.contextPath}/banner/add",
            success: function (data) {
                $('#addDialog').dialog('close');
                $('#dg').edatagrid('reload')
            }
        });

    }
</script>

<table id="dg"></table>

//添加对话框
<div id="addDialog">
    <form id="addDialogFrom" method="post" enctype="multipart/form-data">
        <br/>
        标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：
        <input type="text" name="title" id="title"><br/><br/>

        描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：
        <input type="text" name="description" id="description"><br/><br/>

        状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：&nbsp;&nbsp;&nbsp;
        <input type="radio" name="status" id="status1" checked value="Y">展示&nbsp;&nbsp;
        <input type="radio" name="status" id="status2" value="N">不展示<br/><br/>

        请选择图片：
        <input type="file" name="img" id="img" style="width:300px"><br/><br/>

    </form>

</div>
