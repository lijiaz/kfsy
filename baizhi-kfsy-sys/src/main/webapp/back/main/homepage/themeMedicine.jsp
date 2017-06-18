<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var $tmdtb,$selform,$sel,$tmd,$atm,$atmform;
    $(function(){
        $tmdtb = $("#themeMedicine");   //主题列表数据表格
        $sel = $("#sel");  //下拉框
        $selform = $("#selectform");    //选择主题下拉框表单
        $tmd = $("#tMedicineDetail");   //主题商品的详细信息
        $atm = $("#addThemeMedicine");   //添加主题商品按钮
        $atmform = $("#atmform");    //选择商品的表单

        $tmdtb.datagrid({
            toolbar: '#themeMedicinetools',
            url:"<c:url value=''/>",
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
                width:100,
                title:"商品名称",
                field:"name"
            },{
                align:"center",
                width:100,
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
                    return "<a class='delthemepill' onClick=\"delthemepill('"+ row.id +"')\"  href='javascript:;'>删除</a>&nbsp;&nbsp;"+
                            "<a class='pillthemedetail' onClick=\"pdeta('"+ row.id +"')\" href='javascript:;'>详情</a>";
                }
            }]],
            onLoadSuccess:function(data){
                $(".delthemepill").linkbutton({
                    plain:true,
                    iconCls:'icon-remove',
                });

                $(".pillthemedetail").linkbutton({
                    plain:true,
                    iconCls:'icon-table',
                })
            }
        });

        $sel.combobox({
            url:"<c:url value='/theme/getAllTheme'/>",
            valueField:'id',
            textField:'name'
        });


    });

    function pdeta(id){
        $tmd.dialog({
            href:"<c:url value='/back/main/homepage/detail.jsp'/>?id="+id,
            width:450,
            height:350,
            title:"药品详情",
            buttons:[{
                text:'关闭',
                width:50,
                handler:function(){
                    $tmd.dialog('close');
                }
            }]
        });
    }


    //删除主题下商品
    function delthemepill() {
        $.messager.confirm("提示","您确定要删除吗?",function(r){
            if(r){
                //发送异步请求删除数据
                $.get("<c:url value=''/>?id="+id,function(data){
                    $tmdtb.datagrid('reload');
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

    function addthemePill() {

        $("#MedicineId").combobox({
            url:"<c:url value='/pill/getAllProduct'/>",
            valueField:'id',
            textField:'name'
        });

        $atm.dialog({
            title:"选择商品",
            width:500,
            height:250,
            buttons:[{
                text:'确定',
                width:50,
                handler:function(){
                    $("#atmform").form('submit',{
                       url:"<c:url value='/theme/add'/>",
                       success:function(data){
                           $atm.dialog('close');
                           $("#MedicineId").combobox('clear');
                           $tmdtb.datagrid('load');
                       }
                    });
                }

            }, {
                text:'关闭',
                width:50,
                handler:function(){
                    $atm.dialog('close');
                    $("#MedicineId").combobox('clear');
                }
            }]
        })
    }

    function changeTheme(){
        $("#itm").textbox({
            value:$("#sel").combobox('getValue'),
        });

        console.log($("#sel").combobox('getValue'));

        $tmdtb.datagrid({
            url:"<c:url value='/theme/getAllProduct'/>?id="+$("#sel").combobox('getValue'),
            method:'get',
        });
        $tmdtb.datagrid('load');


    }
</script>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',split:false">
        <table id="themeMedicine" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>

<div id="themeMedicinetools">
    <div style="margin-left: 450px;margin-top: 15px">
        <form id="selectform" method="post">
            <a style="margin-left: -430px;margin-right: 300px;" href="#" class="easyui-linkbutton" data-options="onClick:addthemePill,iconCls:'icon-add',plain:true">添加当前主题药品</a>
            <select id="sel" class="easyui-combobox" data-options="onChange:changeTheme,width:190,prompt:'请选择一个主题'"></select>
        </form>
    </div>
</div>

<div id="tMedicineDetail"></div>

<div id="addThemeMedicine">
    <form id="atmform" method="post" style="margin-top: 70px;margin-left: 100px">
        <label>选择一个商品</label>
        <select id="MedicineId" name="MedicineId" class=class="easyui-combobox" data-options="width:190,prompt:'请选择一个商品'"></select>
        <input name="themeId" type="hidden" id="itm" class="easyui-textbox"/>
    </form>
</div>


