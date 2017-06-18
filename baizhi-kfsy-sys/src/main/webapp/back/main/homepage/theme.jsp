<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var $ttb,$addPT,$fmt;
    $(function(){
        $ttb = $("#ttb");
        $addPT = $("#addPillTheme");
        $fmt = $("#tff");

        $ttb.datagrid({
            toolbar: '#themetools',
            url:"<c:url value='/theme/getAll'/> ",
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
                title:"药品主题名称",
                field:"name"
            },{
                align:"center",
                width:160,
                title:"主题描述",
                field:"description"
            },{
                align:"center",
                width:160,
                title:"主题图片路径",
                field:"url"
            },{
                align:"center",
                width:310,
                title:"主题图片预览",
                field:" ",
                formatter: function(value,row,index){
                    return "<img style='margin-top: 10px;margin-bottom:10px;width:200px;height: 90px' src='/baizhi-kfsy-sys"+row.url+"'/>";
                }
            },{
                align:"center",
                width:200,
                title:"操作",
                field:"options",
                formatter: function(value,row,index){
                    return "<a class='del' onClick=\"deleteTheme('"+ row.id +"')\"  href='javascript:;'>删除</a>&nbsp;&nbsp;"+
                            "<a class='detail' onClick=\"gett('"+ row.id +"')\" href='javascript:;'>详情</a>";
                }
            }]],
            onLoadSuccess:function(data){
                $(".del").linkbutton({
                    plain:true,
                    iconCls:'icon-remove',
                })

                $(".detail").linkbutton({
                    plain:true,
                    iconCls:'icon-table',
                })
            }
        });

    });

    function deleteTheme(id){
        $.messager.confirm("提示","您确定要删除吗?",function(r){
            if(r){
                //发送异步请求删除数据
                $.get("<c:url value='/theme/del'/>?id="+id,function(data){
                    $ttb.datagrid('reload');
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

    function addTheme(){
        $addPT.dialog({
            title:"添加药品主题",
            width:550,
            height:350,
            buttons:[{
                text:'保存',
                width:50,
                handler:function(){
                    $fmt.form('submit',{
                        url:"<c:url value='/theme/upload'/>",
                        success:function(){
                            $addPT.dialog('close');
                            $ttb.datagrid('load');
                            $fmt.form('clear');
                        }
                    })
                }
            },{
                text:'关闭',
                width:50,
                handler:function(){
                    $addPC.dialog('close');
                }
            }]
        })
    }

    /*function doUpdate(){
        $("#").form('submit',{
            url:"",
            success:function(){
                $ut.dialog('close');
                $ttb.datagrid('load');
                $.messager.show({
                    title:'修改状态',
                    msg:'修改成功！',
                    timeout:3000,
                    showType:'slide'
                });
            }
        });
    }*/
</script>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',split:false">
        <table id="ttb" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>

<div id="themetools">
    <div style="margin-left: 450px;margin-top: 15px;margin-bottom: 15px">
        <a style="margin-left: -430px;margin-right: 300px;" href="#" class="easyui-linkbutton" data-options="onClick:addTheme,iconCls:'icon-add',plain:true">添加药品主题</a>
    </div>
</div>

<div id="updateTheme"></div>

<div id="addPillTheme">
    <form method="post" id="tff" action="<c:url value='/theme/upload'/>" style="margin-left: 100px;margin-top:50px;" enctype="multipart/form-data">
        <div style="margin-bottom: 20px;">
            <input name="name" class="easyui-textbox" data-options="width:280,label:'药品主题名',labelPosition: 'left',prompt:'请输入药品主题名',required:true"/>
        </div>
        <div style="margin-bottom: 20px;">
            <input name="description" class="easyui-textbox" data-options="width:280,label:'主题描述',labelPosition: 'left',prompt:'请输入主题描述',required:true"/>
        </div>
        <div style="margin-bottom: 20px;">
            <input name="file" class="easyui-filebox" data-options="width:280,label:'药品主题图片',labelPosition: 'left'"/>
        </div>
    </form>
</div>
