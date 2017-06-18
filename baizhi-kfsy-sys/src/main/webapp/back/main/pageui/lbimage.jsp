<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var $lbtb,$addLb,$lbfm,$uplbdg;
    $(function(){
        $lbtb = $("#lbtb");
        $addLb = $("#addLb");
        $lbfm = $("#lbff");
        $uplbdg = $("#updateLb");

        $lbtb.datagrid({
            toolbar: '#lbtools',
            url:"<c:url value='/lbimage/getAll'/>",
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
                title:"轮播图描述",
                field:"name"
            },{
                align:"center",
                width:160,
                title:"状态",
                field:"status",
                formatter: function(value,row,index){
                    if (value=='on'){
                        return "使用";
                    } else {
                        return "未使用";
                    }
                }

            },{
                align:"center",
                width:160,
                title:"轮播图路径",
                field:"url"
            },{
                align:"center",
                width:310,
                title:"图片预览",
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
                    return "<a class='delet' onClick=\"deleteLb('"+ row.id +"')\"  href='javascript:;'>删除</a>&nbsp;&nbsp;"+
                            "<a class='deta' onClick=\"updateLb('"+ row.id +"')\" href='javascript:;'>修改</a>";
                }
            }]],
            onLoadSuccess:function(data){
                $(".delet").linkbutton({
                    plain:true,
                    iconCls:'icon-remove',
                });

                $(".deta").linkbutton({
                    plain:true,
                    iconCls:'icon-table',
                });
            }
        });

    });

    function deleteLb(id){
        $.messager.confirm("提示","您确定要删除吗?",function(r){
            if(r){
                //发送异步请求删除数据
                $.get("<c:url value='/lbimage/del'/>?id="+id,function(){
                    $lbtb.datagrid('reload');
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

    function addLb(){
        $addLb.dialog({
            title:"添加主页轮播图",
            width:550,
            height:350,
            buttons:[{
                text:'保存',
                width:50,
                handler:function(){
                    $lbfm.form('submit',{
                        url:"<c:url value='/lbimage/upload'/>",
                        success:function(){
                            $addLb.dialog('close');
                            $lbtb.datagrid('load');
                            $lbfm.form('clear');
                        }
                    })
                }
            },{
                text:'关闭',
                width:50,
                handler:function(){
                    $addLb.dialog('close');
                    $lbfm.form('clear');
                }
            }]
        })
    }

    //展示修改轮播图的dialog
    function updateLb(id) {
        $uplbdg.dialog({
            title:"修改轮播图信息",
            href:"<c:url value='/back/main/pageui/update.jsp'/>?id="+id,
            width:550,
            height:350,
            buttons:[{
                text:'保存',
                width:50,
                handler:function(){
                    $("#lbupff").form('submit',{
                        url:"<c:url value='/lbimage/update'/>",
                        success:function(){
                            $uplbdg.dialog('close');
                            $lbtb.datagrid('load');
                            $.messager.show({
                                title:'修改状态',
                                msg:'修改成功！',
                                timeout:3000,
                                showType:'slide'
                            });
                        }
                    });
                }
            },{
                text:'关闭',
                width:50,
                handler:function(){
                    $uplbdg.dialog('close');
                }
            }]
        });
    }

</script>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',split:false">
        <table id="lbtb" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>

<div id="lbtools">
    <div style="margin-left: 450px;margin-top: 15px;margin-bottom: 15px">
        <a style="margin-left: -430px;margin-right: 300px;" href="#" class="easyui-linkbutton" data-options="onClick:addLb,iconCls:'icon-add',plain:true">添加首页轮播图</a>
    </div>
</div>

<div id="updateLb">

</div>

<div id="addLb">
    <form method="post" id="lbff" action="" style="margin-left: 100px;margin-top:50px;" enctype="multipart/form-data">
        <div style="margin-bottom: 20px;">
            <input name="name" class="easyui-textbox" data-options="width:280,label:'轮播图描述',labelPosition: 'left',prompt:'请输入轮播图描述',required:true"/>
        </div>
        <div style="margin-bottom: 20px;">
            <input name="file" class="easyui-filebox" data-options="width:280,label:'轮播图',labelPosition: 'left'"/>
        </div>
        <div style="margin-bottom: 20px;">
            <label>是否使用</label>&nbsp;&nbsp;&nbsp;
            <input name="status" class="easyui-switchbutton" data-options="handleWidth:50,width:120,onText:'使用',offText:'不使用'">
        </div>
    </form>
</div>
