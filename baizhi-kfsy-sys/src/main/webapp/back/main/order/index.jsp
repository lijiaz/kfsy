<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var $odtb,$odsf,$do;
    $(function(){
        $odtb = $("#odtb");
        $odsf = $("#orderform");
        $do = $("#detailOrder");   //订单详情

        $odtb.datagrid({
            toolbar: '#ordertools',
            url:"<c:url value='/order/getAll'/>",
            pagination:true,
            pageList:[5,7,9],
            pageNumber:1,//当前页
            pageSize:5,
            method:'get',
            columns:[[{
                width:180,
                align:"center",
                title:"编号",
                field:"id"
            },{
                align:"center",
                width:150,
                title:"订单号",
                field:"orderNum"
            },{
                align:"center",
                width:120,
                title:"订单金额",
                field:"totalPrice",
            },{
                align:"center",
                width:150,
                title:"创建时间",
                field:"createDate"
            },{
                align:"center",
                width:80,
                title:"订单状态",
                field:"orderStatus",
                formatter:function(value,row,index){
                    if(row.orderStatus =="false"){
                        return "未完成";
                    }else{
                        return "已完成";
                    }
                }
            },{
                align:"center",
                width:80,
                title:"支付状态",
                field:"payStatus",
                formatter:function(value,row,index){
                    if(row.payStatus =="false"){
                        return "未支付";
                    }else{
                        return "已支付";
                    }
                }

            },{
                align:"center",
                width:250,
                title:"备注",
                field:" ",
                formatter:function(value,row,index){
                    if(row.remarks == null){
                        return "无";
                    }else{
                        return row.remarks;
                    }
                }
            },{
                align:"center",
                width:250,
                title:"操作",
                field:"options",
                formatter: function(value,row,index){
                    return "<a class='orderDetail' onClick=\"orderDetail('"+ row.id +"')\" href='javascript:;'>详情</a>";
                }
            }
            ]],
            onLoadSuccess:function(data){
                $(".orderDetail").linkbutton({
                    plain:true,
                    iconCls:'icon-table',
                })
            }
        });

        $("#ov").searchbox({
            searcher:function (value,name) {
                $odtb.datagrid({
                    url:"<c:url value='/order/getAllBy'/>",
                    queryParams:{
                        name:name,
                        value:value,
                    }
                })
            }
        });

    });

    function orderDetail(id) {
        $do.dialog({
            href:"<c:url value='/back/main/order/detail.jsp'/>?id="+id,
            width:450,
            height:350,
            title:"订单详情",
            buttons:[{
                text:'关闭',
                width:50,
                handler:function(){
                    $do.dialog('close');
                }
            }]
        });
    }


</script>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',split:false">
        <table id="odtb" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>

<div id="ordertools">
    <div style="margin-left: 450px;margin-top: 15px">
        <form id="orderform" method="post">
            <input style="display: inline-block" id="ov" class="easyui-searchbox" data-options="width:300,prompt:'请输入关键字',menu:'#oo'"/>
            <div id="oo" style="width:120px">
                <div data-options="name:'orderStatus'">订单状态</div>
                <div data-options="name:'payStatus'">支付状态</div>
            </div>
        </form>
    </div>
</div>

<div id="detailOrder"></div>

