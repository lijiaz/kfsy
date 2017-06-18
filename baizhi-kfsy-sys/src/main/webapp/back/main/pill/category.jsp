<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var $ctb,$addPC,$fmc;//,$udc;
    $(function(){
        $ctb = $("#ctb");
        $addPC = $("#addPillCategory");
        $fmc = $("#cff");
        //$udc = $("#updateCategory");

        $ctb.datagrid({
            toolbar: '#categorytools',
            url:"<c:url value='/category/getAll'/>",
            pagination:true,
            pageList:[5,7,9],
            pageNumber:1,//当前页
            pageSize:5,
            method:'get',
            columns:[[{
                width:160,
                align:"center",
                title:"编号",
                field:"id"
            },{
                align:"center",
                width:160,
                title:"药品种类名称",
                field:"name"
            },{
                align:"center",
                width:160,
                title:"图片路径",
                field:"url"
            },{
                align:"center",
                width:350,
                title:"图片预览",
                field:" ",
                formatter: function(value,row,index){
                    return "<img src='/baizhi-kfsy-sys"+row.url+"'/>";
                }
            },{
                align:"center",
                width:300,
                title:"操作",
                field:"options",
                formatter: function(value,row,index){
                    return "<a class='dele' onClick=\"deleteCategory('"+ row.id +"')\"  href='javascript:;'>删除</a>&nbsp;&nbsp;"+
                            "<a class='det' onClick=\"get('"+ row.id +"')\" href='javascript:;'>详情</a>";
                }
            }]],
            onLoadSuccess:function(data){
                $(".dele").linkbutton({
                    plain:true,
                    iconCls:'icon-remove',
                })

                $(".det").linkbutton({
                    plain:true,
                    iconCls:'icon-table',
                })
            }
        });
        
    });

    function deleteCategory(id){
        $.messager.confirm("提示","您确定要删除吗?",function(r){
            if(r){
                //发送异步请求删除数据
                $.get("<c:url value='/category/del'/>?id="+id,function(data){
                    $ctb.datagrid('reload');
                    $.messager.show({
                        title:'系统消息',
                        msg:'删除成功！',
                        timeout:3000,
                        showType:'slide'
                    });
                });
            }
        });
    }

    function addCategory(){
        $addPC.dialog({
            title:"添加药品类型",
            width:550,
            height:400,
            buttons:[{
                text:'保存',
                width:50,
                handler:function(){
                    $fmc.form('submit',{
                        url:"<c:url value='/category/save'/>",
                        success:function(){
                            $addPC.dialog('close');
                            $ctb.datagrid('load');
                            $("#cimgf").form('clear');
                            $("#backshowImg").attr("src"," ");
                            $fmc.form('clear');
                        }
                    })
                }
            },{
                text:'关闭',
                width:50,
                handler:function(){
                    $("#cimgf").form('clear');
                    $("#backshowImg").attr("src"," ");
                    $fmc.form('clear');
                    $addPC.dialog('close');
                }
            }]
        })
    }

    /*function doUpdate(){
        $("#").form('submit',{
            url:"",
            success:function(){
                $ud.dialog('close');
                $ctb.datagrid('load');
                $.messager.show({
                    title:'修改状态',
                    msg:'修改成功！',
                    timeout:3000,
                    showType:'slide'
                });
            }
        })
    }*/
    
    function upload() {
        $("#cimgf").form('submit',{
            url:"<c:url value='/category/upload'/>",
            success:function(data){
                $("#imgUrl").textbox({
                    value:$.parseJSON(data).url,
                });
                $("#backshowImg").attr("src","/baizhi-kfsy-sys"+$.parseJSON(data).url)
            }
        });
    }
</script>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',split:false">
        <table id="ctb" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>

<div id="categorytools">
    <div style="margin-left: 450px;margin-top: 15px;margin-bottom: 15px">
        <a style="margin-left: -430px;margin-right: 300px;" href="#" class="easyui-linkbutton" data-options="onClick:addCategory,iconCls:'icon-add',plain:true">添加药品类别</a>
    </div>
</div>

<div id="updateCategory"></div>

<div id="addPillCategory">
    <form method="post" id="cff" action="<c:url value='/category/save'/>" style="margin-left: 100px;margin-top:30px;">
        <div style="margin-bottom: 20px;">
            <input name="name" class="easyui-textbox" data-options="width:280,label:'药品种类名',labelPosition: 'left',prompt:'请输入药品种类名',required:true"/>
        </div>
        <input name="url" class="easyui-textbox" type="hidden" id="imgUrl" />
    </form>
    <form id="cimgf" style="margin-left: 100px;" method="post" enctype="multipart/form-data">
        <div style="margin-bottom: 20px;">
            <input id="uploadImg" name="file" class="easyui-filebox" data-options="onChange:upload,width:280,label:'药品种类图片',labelPosition: 'left'"/>
        </div>
    </form>

    <p style="margin-left: 100px;">图片预览</p>
    <img id="backshowImg" src="" style="width: 122px;height: 122px;margin-left: 180px;">
</div>
