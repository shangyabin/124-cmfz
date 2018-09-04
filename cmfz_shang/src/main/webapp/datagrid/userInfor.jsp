<%--
  Created by IntelliJ IDEA.
  User: 小尚
  Date: 2018/9/4
  Time: 1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<script type="text/javascript">
    var toolbar = [{
        iconCls: 'icon-add',
        text: "导入",
        handler: function () {
            //alert("")
            $("#userImport").dialog("open");
        }
    },'-',{
        iconCls: 'icon-cut',
        text: "导出",
        handler: function () {
            //alert("")
            $("#Export").dialog("open");
        }
    },'-',{
        iconCls: 'icon-cut',
        text: "自定义导出",
        handler: function () {
            //alert("")
            $("#customerExport").dialog("open");
        }
    },'-',{
        text: "修改",
        iconCls: 'icon-edit',
        handler: function () {
            //获取选中行
            var row = $('#userInfor').edatagrid('getSelected');
            //判断是否选中
            if (row != null) {
                //获取选中行的索引
                var index = $('#userInfor').edatagrid('getRowIndex', row);
                //将指定行变为可编辑
                $('#userInfor').edatagrid('editRow', index);
            } else {
                alert("请先选中行!!!");
            }
        }
    },'-',{
        text: "保存",
        iconCls: 'icon-reload',
        handler: function () {
            //保存编辑行并发送到服务器
            $('#userInfor').edatagrid('saveRow'),
                $('#userInfor').edatagrid('reload')
        }
    }]

    $(function () {
        $('#userInfor').edatagrid({
            updateUrl: "${pageContext.request.contextPath}/user/update",
            url:'${pageContext.request.contextPath}/user/user',
            columns:[[
                {field: 'id', title: '编号', width: 100},
                {field: 'name', title: '名字', width: 100},
                {field: 'dharmaName', title: '法名', width: 100},
                {field: 'password', title: '密码', width: 100},
                {field: 'sex', title: '性别', width: 100},
                {field: 'province', title: '省份', width: 100},
                {field: 'city', title: '城市', width: 100},
                {field: 'sign', title: '描述', width: 100},
                {field: 'phoneNum', title: '电话号码', width: 100},
                {field: 'salt', title: '私盐', width: 100},
                {
                    //双击修改status字段
                    field: 'status', title: '状态', width: 100,editor: {
                    type: 'text',
                    options: {
                        required: true
                    }
                }
                },
                {field: 'photoImg', title: '图片', width: 100},
                {field: 'registDate', title: '注册时间', width: 100},
            ]],
            pagination:true,
            pagePosition:"bottom",
            fitColumns: true,
            fit:true,
            toolbar:toolbar,
            pageNumber:1,
            pageSize:5,
            pageList:[5,10,15,20,25]
        });
        $('#Export').dialog({
            title: '全部导出',
            width: 400,
            height: 200,
            iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
                text:'确定',
                handler:function(){
                    Export();
                    $('#Export').dialog('close');
                }
            },{
                text:'取消',
                handler:function(){
                    $('#Export').dialog('close');
                }
            }]
        });
        $('#customerExport').dialog({
            title: '自定义导出',
            width: 400,
            height: 200,
            iconCls:'icon-save',
            resizable:true,
            modal:true,
            closed:true
        });
        $('#userImport').dialog({
            title: '导入',
            width: 400,
            height: 200,
            iconCls:'icon-save',resizable:true,modal:true,closed:true,
            buttons:[{
                text:'确定',
                handler:function(){
                    UserImport();
                    $('#userImport').dialog('close');
                }
            },{
                text:'取消',
                handler:function(){
                    $('#userImport').dialog('close');
                }
            }]
        });
        $("#importSuccess").dialog({
            title:"提示信息",
            iconCls:'icon-save',
            resizable:true,
            modal:true,
            closed:true,
            width: 400,
            height: 200,
            buttons:[{
                text:'确定',
                handler:function(){
                    $("#importSuccess").dialog("close");
                }
            }]
        });
        $("#customerSelect").combotree({
            multiple:true,
            onlyLeafCheck:true,
            checkbox:true,
            required:true,
            width:200,
            data:[{
                "id":"custome",
                "text": "请选择",
                "children": [{
                    "id":"id",
                    "text":"编号"
                },{
                    "id":"name",
                    "text":"用户名"
                },{
                    "id":"dharmaName",
                    "text":"法名"
                },{
                    "id":"password",
                    "text":"密码"
                },{
                    "id":"sex",
                    "text":"性别"
                },{
                    "id":"province",
                    "text":"省份"
                },{
                    "id":"city",
                    "text":"城市"
                },{
                    "id":"sign",
                    "text":"描述"
                },{
                    "id":"phoneNum",
                    "text":"电话号码"
                },{
                    "id":"salt",
                    "text":"盐值"
                },{
                    "id":"status",
                    "text":"状态"
                },{
                    "id":"photoImg",
                    "text":"图片"
                },{
                    "id":"registDate",
                    "text":"注册时间"
                }]
            }]

        });
        $("#btn").linkbutton({
            iconCls:'icon-search',
            onClick: function () {
                var title = $("#customerSelect").combotree("getText");
                var fileds = $("#customerSelect").combotree("getValues");
                alert(title);
                alert(fileds);
                $("#customerForm").form("submit", {
                    url: "${pageContext.request.contextPath}/user/customerExport",
                    queryParams: {
                        "title": title,
                        "fileds": fileds,
                    }
                })
                $("#customerSelect").combotree("clear");
                $("#customerExport").dialog("close");

            }
        })
    })

    function Export() {
        //alert();
        window.location.href="${pageContext.request.contextPath}/user/export"
    }
    function UserImport(){
        $("#importForm").form("submit",{
            url:"${pageContext.request.contextPath}/user/userImport",
            success:function(data){
                var data = eval('(' + data + ')');//将json字符串转化为js对象
                alert(data.isSuccess);
                if (data.isSuccess){
                    $("#importSuccess").dialog("open");
                    $("#importSuccess").html("导入成功");
                }else{
                    $("#importSuccess").html("导入失败");
                }
            }
        })
    }
</script>

<table id="userInfor"></table>

<div id="Export">是否确定导出全部用户?</div>

<div id="customerExport" style="text-align: center">
    <div style="text-align: center">
        <select id="customerSelect">
        </select>
        <form id="customerForm" method="post">
            <a id="btn" href="#"  data-options="">确定</a>
        </form>
    </div>
</div>

<div id="userImport">
    <form id="importForm" method="post" enctype="multipart/form-data">
        <div >
            <label for="excel">xsl文件:</label>
            <input id="excel"  class="easyui-filebox" type="file" name="file" data-options ="prompt：'选择文件...'">
        </div>
    </form>
</div>

<div id="importSuccess"></div>

