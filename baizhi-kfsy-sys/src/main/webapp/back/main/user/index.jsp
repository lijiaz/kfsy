<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var $tb,$addBtn,$fm,$sf,$ud;
    $(function(){
        $tb = $("#tb");
        $addBtn = $("#add");
        $fm = $("#ff");
        $sf = $("#searchForm");
        $ud = $("#update");

        $tb.datagrid({
            toolbar: '#usertools',
            url:"<c:url value='/user/getAll'/>",
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
                title:"昵称",
                field:"nickName"
            },{
                align:"center",
                width:160,
                title:"电话",
                field:"phone"
            },{
                align:"center",
                width:160,
                title:"注册时间",
                field:"createDate"
            },{
                align:"center",
                width:160,
                title:"用户状态",
                field:"status"
            },{
                align:"center",
                width:300,
                title:"操作",
                field:"options",
                formatter: function(value,row,index){
                    return "<a class='del' onClick=\"del('"+ row.id +"')\"  href='javascript:;'>删除</a>&nbsp;&nbsp;"+
                            "<a class='detail' onClick=\"detail('"+ row.id +"')\" href='javascript:;'>详情</a>";
                }
            }]],
            onLoadSuccess:function(data){
                $(".del").linkbutton({
                    plain:true,
                    iconCls:'icon-remove',
                });

                $(".detail").linkbutton({
                    plain:true,
                    iconCls:'icon-table',
                });
            }
        });

        $("#ss").searchbox({
            searcher:function (value,nickName) {
                console.log(name);
                $tb.datagrid({
                    url:"<c:url value='/user/getAllBy'/>",
                    queryParams:{
                        name:nickName,
                        value:value,
                    }
                })
            }
        })

    });

    function del(id){
        $.messager.confirm("提示","您确定要删除吗?",function(r){
            if(r){
                //发送异步请求删除数据
                $.get(""+id,function(data){
                    $tb.datagrid('reload');
                    $.messager.show({
                        title:'删除状态',
                        msg:'删除成功！',
                        timeout:3000,
                        showType:'slide'
                    });
                });
            }
        });
    }

    function detail(id){
        $ud.dialog({
            href:""+id,
            width:600,
            height:400,
            title:"用户详细信息",
        });
    }

    function doUpdate(){
        $("#uff").form('submit',{
            url:"",
            success:function(){
                $ud.dialog('close');
                $tb.datagrid('load');
                $.messager.show({
                    title:'修改状态',
                    msg:'修改成功！',
                    timeout:3000,
                    showType:'slide'
                });
            }
        })
    }
</script>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',split:false">
        <table id="tb" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>

<div id="usertools">
    <div style="margin-left: 450px;margin-top: 15px">
        <form id="searchForm" method="post">
            <input style="display: inline-block" id="ss" class="easyui-searchbox" data-options="width:300,prompt:'请输入关键字',menu:'#mm'"/>
            <div id="mm" style="width:120px">
                <div data-options="name:'nickName'">昵称</div>
                <div data-options="name:'phone'">电话</div>
            </div>
        </form>
    </div>
</div>

<div id="update"></div>

