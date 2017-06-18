<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var $imtb;
    $(function(){
        $imtb = $("#imtb");


        $imtb.datagrid({
            toolbar: '#lbtools',
            url:"<c:url value='/image/getAll'/>",
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
                title:"所属商品",
                field:"name"
            },{
                align:"center",
                width:160,
                title:"图片路径",
                field:"url"
            },{
                align:"center",
                width:160,
                title:"图片类型",
                field:"type",
                formatter: function(value,row,index){
                    if(row.type ='1'){
                        return "商品详情图片"
                    }else{
                        return "商品轮播图";
                    }
                }
            },{
                align:"center",
                width:310,
                title:"图片预览",
                field:" ",
                formatter: function(value,row,index){
                    return "<img style='margin-top: 10px;margin-bottom:10px;width:100px;height: 10 0px' src='"+row.url+"'/>";
                }
            },{
                align:"center",
                width:200,
                title:"操作",
                field:"options",
                formatter: function(value,row,index){
                    return "<a class='delete' onClick=\"deleteIm('"+ row.id +"')\"  href='javascript:;'>删除</a>&nbsp;&nbsp;";
                }
            }]],
            onLoadSuccess:function(data){
                $(".delete").linkbutton({
                    plain:true,
                    iconCls:'icon-remove',
                });
            }
        });

    });

    function deleteIm(id){
        $.messager.confirm("提示","您确定要删除吗?",function(r){
            if(r){
                //发送异步请求删除数据
                $.get("<c:url value='/image/del'/>?id="+id,function(){
                    $imtb.datagrid('reload');
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
</script>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',split:false">
        <table id="imtb" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>


