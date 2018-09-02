<%--
  Created by IntelliJ IDEA.
  User: 小尚
  Date: 2018/8/30
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        $('#ta').treegrid({
            onDblClickRow:function(row){
                $("#audio_dd").dialog("open");
                $("#audio_ff").prop("src","${pageContext.request.contextPath}"+row.audioPath);
            },
            url: '${pageContext.request.contextPath}/album/getAllAlbum',
            idField: 'id',
            treeField: 'title',
            columns: [[
                {field: 'title', title: '名称', width: 100},
                {field: 'audioPath', title: '下载路径', width: 100},
                {field: 'size', title: '章节大小', width: 100,},
                {field: 'duration', title: '章节时长', width: 100},
            ]],
            pagination: true,//底部分页工具栏
            toolbar: [{
                iconCls: 'icon-edit',
                text: "专辑详情",
                handler: function () {
                    // alert("")
                    var row = $("#ta").treegrid("getSelected");
                    console.log(row);
                    if (row != null && row.brief != null) {
                        showdata(row);
                        $("#treeAlbum").dialog("open");
                    } else {
                        alert("请选中专辑后再查看");
                    }
                }
            }, '-', {
                text: "添加专辑",
                iconCls: 'icon-cut',
                handler: function () {
                    // alert('帮助按钮')
                    /*alert("添加专辑开始"),*/
                        $("#addAlbumDialog").dialog("open");
                }
            }, '-', {
                text: "添加章节",
                iconCls: 'icon-lock',
                handler: function () {
                    // alert('帮助按钮')
                    var row = $("#ta").treegrid("getSelected");
                    // alert(row);
                    console.log(row);
                    if (row != null) {
                        if (row.brief != null) {
                            $("#addChapterDialog").dialog("open")
                            $("#albumId").val(row.id)
                        } else {
                            alert("请选中专辑")
                        }
                    }
                }
            }, '-', {
                text: "下载音频",
                iconCls: 'icon-help',
                handler: function () {
                    // alert('帮助按钮')
                    /*选中章节*/
                    var row = $("#ta").treegrid("getSelected");
                    if (row != null) {
                        if (row.brief == null) {
                            location.href = "${pageContext.request.contextPath}/chapter/down?title=" + row.title + "&url=" + row.audioPath;
                        }
                    }
                }
            }],
            fitColumns: true,
            fit: true
        });
        $("#treeAlbum").dialog({
            title: "专辑详情",
            iconCls: 'icon-save',
            resizable: true,
            modal: true,
            closed: true,
            width: 380,
            height: 450,
            buttons: [{
                text: '关闭',
                handler: function () {
                    $('#treeAlbum').dialog('close');
                }
            }]

        });
        $('#addAlbumDialog').dialog({
            title: '添加专辑',
            width: 400,
            height: 300,
            iconCls: 'icon-save', resizable: true, modal: true, closed: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    /*alert("准备提交"),*/
                        submitalbum();
                    /*alert("提交成功"),*/
                        $('#addAlbumDialog').dialog('close');
                    /*$('#ta').edatagrid('reload')*/
                    /*$('#ta').treegrid('reload')*/
                }
            }, {
                text: '关闭',
                handler: function () {
                    $('#addAlbumDialog').dialog('close');
                }
            }]
        });
        $('#addChapterDialog').dialog({
            title: '添加章节',
            width: 400,
            height: 200,
            iconCls: 'icon-save', resizable: true, modal: true, closed: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    submitchapter();
                    $('#ta').edatagrid('reload'),
                        $('#addChapterDialog').dialog('close')
                }
            }, {
                text: '关闭',
                handler: function () {
                    $('#addChapterDialog').dialog('close');
                }
            }]
        });
        function showdata(row) {
            /*console.log(row);
             console.log(row.coverImg);*/
            $("#coverImgs").prop("src", "${pageContext.request.contextPath}/upload/album/" + row.coverImg);
            console.log("---" + row.title);
            $("#titles").html("标题：" + row.title);
            $("#scores").html("推荐指数：" + row.score + "星");
            $("#authors").html("作者：" + row.author);
            $("#broadCasts").html("播音：" + row.broadCast);
            $("#briefs").html("内容简介：" + row.brief);
            $("#statuss").html("状态：" + row.status);
            $("#counts").html("集数：" + row.count);
            $("#publicDates").html("发布日期：" + row.publicDate);
            $("#createDates").html("发布日期：" + row.createDate);


        }
        //提交专辑表单
        function submitalbum() {
            $('#addAlbumForm').form('submit', {
                url:"${pageContext.request.contextPath}/album/insertAlbum",

            });
        };
        //提交章节表单
        function submitchapter() {
            $('#addChapterForm').form('submit', {
                url:"${pageContext.request.contextPath}/chapter/addChapter"
            });
        };

    });


</script>
<table id="ta" style="width:600px;height:400px"></table>
<%--专辑详情表单--%>
<div id="treeAlbum">
    <div id="showdata">
        <div style="height: 150px;width: 350px ;">
            <div style="display:inline;float:left;height: 150px;width: 55%;border:1px">
                <img id="coverImgs" src="" style="height: 150px;width: 150px">
            </div>
            <div style="display:inline;float:left;height: 150px;width: 45%;border:1px">
                <div id="titles" style="height:auto; font:bold 14px 宋体;color:black;margin-top:10px"></div>
                <div id="counts" style="height:auto;margin-top:10px"></div>
                <div id="scores" style="height:auto;margin-top:10px"></div>
                <div id="authors" style="height:auto;margin-top:10px"></div>
                <div id="broadCasts" style="height:auto;margin-top:10px"></div>
            </div>
        </div>

        <div style="height: 250px;width: 350px">
            <div style="height: 50px;width: 350px ;">
                <div id="statuss" style="display:inline;float:left;"></div>
                <div id="publicDates" style="display:inline;float:right;"></div>
                <div id="createDates" style="display:inline;float:right;"></div>
            </div>
            <div style="height:auto;width: 350px;">
                <p id="briefs"></p>
            </div>
        </div>
    </div>
</div>
<%--专辑表单--%>
<div id="addAlbumDialog">
    <form id="addAlbumForm" method="post" enctype="multipart/form-data">
        <br/>
        标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：
        <input type="text" name="title" id="title"><br/><br/>
        集&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：
        <input type="text" name="count" id="count"><br/><br/>
        作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：
        <input type="text" name="author" id="author"><br/><br/>
        播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;音：
        <input type="text" name="broadCast" id="broadCast"><br/><br/>
        描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：
        <input type="text" name="brief" id="brief"><br/><br/>
        状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：&nbsp;&nbsp;&nbsp;
        <input type="radio" name="status" id="status1" checked value="Y">展示&nbsp;&nbsp;
        <input type="radio" name="status" id="status2" value="N">不展示<br/><br/>
        评&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分：
        <select name="score" id="score">
            <option value="1">一星</option>
            <option value="2">二星</option>
            <option value="3">三星</option>
            <option value="4">四星</option>
            <option value="5">五星</option>
        </select><br/><br/>
        <%--发布日期：
        <input id="publicDate" type="date"><br/><br/>
        创建日期：
        <input id="createDate" type="date"><br/><br/>--%>
        封&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;面：
        <input type="file" name="img" id="img" style="width:200px">
    </form>
</div>
<%--添加章节--%>
<div id="addChapterDialog"><%--对话框--%>
    <form id="addChapterForm" method="post" enctype="multipart/form-data"><%--form表单--%>
        <div>
            标题：
            <input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
            文件：&nbsp;<input type="file" class="easyui-filebox" name="audio" style="width:200px">
        <div>
            <input id="albumId" class="easyui-validatebox" type="hidden" name="albumId" data-options="required:true"/>
        </div>
</div>
<%--双击音频播放--%>
<div id="audio_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <audio src="" id="audio_ff" controls="controls">

    </audio>
</div>
