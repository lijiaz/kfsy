<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var $pilltb,$addPill,$scp,$spf,$pd,$apf;
    $(function(){
        $pilltb = $("#pilltb");
        $addPill = $("#addPill");   //添加药品按钮
        $scp = $("#searchPill");   //搜索input
        $spf = $("#searchPillForm");  //搜索表单
        $pd = $("#pilld");    //药品详情
        $apf = $("#addpillform");   //添加药品的表单

        $pilltb.datagrid({
            toolbar: '#pilltools',
            url:"<c:url value='/pill/getAll'/>",
            pagination:true,
            pageList:[5,7,9],
            pageNumber:1,//当前页
            pageSize:5,
            method:'get',
            columns:[[{
                width:100,
                align:"center",
                title:"编号",
                field:"id"
            },{
                align:"center",
                width:80,
                title:"药品名称",
                field:"name"
            },{
                align:"center",
                width:200,
                title:"药效",
                field:"treatment"
            },{
                align:"center",
                width:80,
                title:"当前价格",
                field:"currentPrice"
            },{
                align:"center",
                width:50,
                title:"原价",
                field:"oldPrice"
            },{
                align:"center",
                width:50,
                title:"库存",
                field:"stock"
            },{
                align:"center",
                width:50,
                title:"销量",
                field:"sales"
            },{
                align:"center",
                width:50,
                title:"有效期",
                field:"entryDate"
            },{
                align:"center",
                width:50,
                title:"状态",
                field:"status",
                formatter: function(value,row,index){
                    if(row.status =='on'){
                        return "在售";
                    }else{
                        return "下架";
                    }
                }
            },{
                align:"center",
                width:200,
                title:"药品缩略图",
                field:" ",
                formatter: function(value,row,index){
                    return "<img style='margin-top: 10px;margin-bottom:10px;width:90px;height: 90px' src='/baizhi-kfsy-sys"+row.thumbnail+"'/>";
                }
            },{
                align:"center",
                width:250,
                title:"操作",
                field:"options",
                formatter: function(value,row,index){
                    return "<a class='delpill' onClick=\"delpill('"+ row.id +"')\"  href='javascript:;'>删除</a>&nbsp;&nbsp;"+
                            "<a class='pilldetail' onClick=\"pillde('"+ row.id +"')\" href='javascript:;'>详情</a>";
                }
            }]],
            onLoadSuccess:function(data){
                $(".delpill").linkbutton({
                    plain:true,
                    iconCls:'icon-remove',
                })

                $(".pilldetail").linkbutton({
                    plain:true,
                    iconCls:'icon-table',
                })
            }
        });

        $scp.searchbox({
            searcher:function (value,name) {
                $pilltb.datagrid({
                    url:"<c:url value=''/>",
                    queryParams:{
                        name:name,
                        value:value,
                    }
                })
            }
        })

    });

    function delpill(id){
        $.messager.confirm("提示","您确定要删除吗?",function(r){
            if(r){
                //发送异步请求删除数据
                $.get("<c:url value='/pill/del'/>?id="+id,function(data){
                    $pilltb.datagrid('reload');
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

    function pillde(id){
        $pd.dialog({
            href:"<c:url value='/back/main/pill/detail.jsp'/>?id="+id,
            width:450,
            height:350,
            title:"药品详情",
            buttons:[{
                text:'关闭',
                width:50,
                handler:function(){
                    $pd.dialog('close');
                }
            }]
        });
    }
    
    function addPill() {
        $addPill.dialog({
            title:"添加药品",
            href:"<c:url value='/back/main/pill/add.jsp'/>",
            width:700,
            height:550,
            buttons:[{
                text:'保存',
                width:50,
                handler:function(){

                    $("#addpillform").form('submit',{
                        url:"<c:url value='/pill/save'/>",
                        success:function(){
                            $addPill.dialog('close');
                            $pilltb.datagrid('load');
                            $apf.form('clear');
                        }
                    })
                }
            },{
                text:'关闭',
                width:50,
                handler:function(){
                    $addPill.dialog('close');
                    $apf.form('clear');
                }
            }]
        })
    }

</script>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',split:false">
        <table id="pilltb" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>

<div id="pilltools">
    <div style="margin-left: 450px;margin-top: 15px">
        <form id="searchPillForm" method="post">
            <a style="margin-left: -430px;margin-right: 300px;" href="#" class="easyui-linkbutton" data-options="onClick:addPill,iconCls:'icon-add',plain:true">添加药品</a>
            <input style="display: inline-block" id="searchPill" class="easyui-searchbox" data-options="width:300,prompt:'请输入药品名',menu:'#pp'"/>
            <div id="pp" style="width:120px">
                <div data-options="name:'name'">药品名</div>
            </div>
        </form>
    </div>
</div>

<div id="pilld">

</div>
<div id="addPill"></div>

