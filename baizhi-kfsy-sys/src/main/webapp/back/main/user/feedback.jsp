<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    var $fb,$ud;
    $(function(){
        $fb = $("#fb");
        $ud = $("#update");

        $fb.datagrid({
            url:"",
            pagination:true,
            pageList:[4,6,8],
            pageNumber:1,//当前页
            pageSize:6,
            method:'get',
            columns:[[{
                width:160,
                align:"center",
                title:"编号",
                field:"id"
            },{
                align:"center",
                width:160,
                title:"用户昵称",
                field:"nickName"
            },{
                align:"center",
                width:160,
                title:"用户电话",
                field:"phone"
            },{
                align:"center",
                width:160,
                title:"反馈时间",
                field:"createDate"
            },{
                align:"center",
                width:160,
                title:"反馈状态",
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
                })

                $(".detail").linkbutton({
                    plain:true,
                    iconCls:'icon-table',
                })
            }
        });

        $("#ss").searchbox({
            searcher:function (value,name) {
                $fb.datagrid({
                    url:"",
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
                    $fb.datagrid('reload');
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
                $fb.datagrid('load');
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
        <table id="fb" class="easyui-datagrid" data-options="fit:true"></table>
    </div>
</div>


<div id="update"></div>
