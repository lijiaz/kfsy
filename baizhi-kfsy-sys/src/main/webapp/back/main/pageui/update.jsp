<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
    $(function(){
        $.post("<c:url value='/lbimage/getOne'/>",{"id":"${param.id}"},function(data) {
            console.log(data);
            $("#lb").attr("src","/baizhi-kfsy-sys"+data.url);

            $("#lbid").textbox({
                value:data.id,
            });

            $("#lbname").textbox({
                value:data.name,
            });

            if(data.status=='on'){
                $("#lbstatus").switchbutton({
                    checked:true,
                });
            }
        },"JSON");
    });
</script>

<form method="post" id="lbupff" action="" style="margin-left: 100px;margin-top:50px;">
    <input id="lbid" type="hidden" class="easyui-textbox" name="id">
    <div style="margin-bottom: 20px;">
        <img style="width: 200px;height: 90px;" id="lb" src="">
    </div>
    <div style="margin-bottom: 20px;">
        <input id="lbname" name="name" class="easyui-textbox" data-options="width:280,label:'轮播图描述',labelPosition: 'left',prompt:'请输入轮播图描述',required:true"/>
    </div>
    <div style="margin-bottom: 20px;">
        <label>是否使用</label>&nbsp;&nbsp;&nbsp;
        <input id="lbstatus" name="status" class="easyui-switchbutton" data-options="handleWidth:50,width:120,onText:'使用',offText:'不使用'">
    </div>
</form>
