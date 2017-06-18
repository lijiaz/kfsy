<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var $evtb,$fm,$evsf,$de;
    $(function(){
        $evtb = $("#evtb");
        $fm = $("#ff");
        $evsf = $("#evalform");

        $evtb.datagrid({
            toolbar: '#evaltools',
            url:"<c:url value='/evaluate/getAll'/>",
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
                field:"orderNum",
                formatter:function(value,row,index){
                    return row.order.orderNum;
                }
            },{
                align:"center",
                width:120,
                title:"用户手机号",
                field:"userPhone",
                formatter:function(value,row,index){
                    return row.user.phone;
                }
            },{
                align:"center",
                width:250,
                title:"评价内容",
                field:"content"
            },{
                align:"center",
                width:80,
                title:"星级",
                field:"star"
            },{
                align:"center",
                width:180,
                title:"评价时间",
                field:"createDate"
            },{
                align:"center",
                width:200,
                title:"操作",
                field:"options",
                formatter: function(value,row,index){
                    return "<a class='evalDetail' onClick=\"evalDetail('"+ row.id +"')\" href='javascript:;'>详情</a>";
                }
            }
            ]],
            onLoadSuccess:function(data){
                $(".evalDetail").linkbutton({
                    plain:true,
                    iconCls:'icon-table',
                })
            }
        });

        $("#ev").searchbox({
            searcher:function (value,name) {
                $evtb.datagrid({
                    url:"<c:url value='/evaluate/getAllBy'/>",
                    queryParams:{
                        name:name,
                        value:value,
                    }
                })
            }
        });

    });

    function evalDetail(id) {

    }


</script>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',split:false">
        <table id="evtb" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>

<div id="evaltools">
    <div style="margin-left: 450px;margin-top: 15px">
        <form id="evalform" method="post">
            <input style="display: inline-block" id="ev" class="easyui-searchbox" data-options="width:300,prompt:'请输入关键字',menu:'#ee'"/>
            <div id="ee" style="width:120px">
                <div data-options="name:'content'">内容关键字</div>
                <div data-options="name:'star'">评价星级</div>
            </div>
        </form>
    </div>
</div>

<div id="detailEval"></div>

