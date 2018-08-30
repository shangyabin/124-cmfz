<%--
  Created by IntelliJ IDEA.
  User: 小尚
  Date: 2018/8/29
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<script type="text/javascript">
    $(function(){
        //通过数组定义工具栏
        $('#dg').datagrid({
            toolbar: [{
                iconCls: 'icon-edit',
                text:"添加",
                handler: function(){

                }
            },'-',{
                iconCls: 'icon-edit',
                text:"修改",
                handler: function(){
                    alert('帮助按钮')
                }
            },'-',{
                iconCls: 'icon-edit',
                text:"删除",
                handler: function(){
                    alert('帮助按钮')
                }
            },'-',{
                iconCls: 'icon-help',
                text:"保存",
                handler: function(){
                    alert('帮助按钮')
                }
            }]
        });



        //数据表格显示----------------------------------
        $('#dg').datagrid({
            url:'${pageContext.request.contextPath}/banner/showAll',  //路径
            method:"get",  //请求
            columns:[[   //显示样式
                {field:'id',title:'编号',width:100},
                {field:'title',title:'名称',width:100},
                {field:'status',title:'状态',width:100},
                {field:'description',title:'图片描述',width:100},
                {field:'createDate',title:'创建日期',width:100}
            ]],
            fitColumns:true,  //适应网格的宽度
            pagination:true,  //显示分页工具栏。
            fit:true,  //自适应父容器
            pageSize:5,
            pageList:[5,10,15,20,25]
        });
    })
</script>
<table id="dg"></table>