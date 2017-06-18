<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    $(function (){

        //下拉列表数据
        $("#pillCategory").combobox({
            url:"<c:url value='/category/getAllCategory'/>",
            valueField:'id',
            textField:'name',
        });

    });

    function uploadSamllImg(){
        $("#smallImageForm").form('submit',{
            url:"<c:url value='/pill/uploadSmall'/>",
            success:function(data){
                $("#thumbnail").textbox({
                    value:$.parseJSON(data).thumbnail,
                });

                $("#smallImageShow").attr("src","/baizhi-kfsy-sys"+$.parseJSON(data).thumbnail)
            }
        })
    }

    function uploadDetailImg(){
        $("#detailImageForm").form('submit',{
            url:"<c:url value='/pill/uploadDetail'/>",
            success:function(data){
                var urls = $.parseJSON(data).urls;
                if(urls==undefined){
                    $("#smallDetailShow").empty();
                    $.messager.alert('系统信息','添加图片超过四张，请重新添加！','info');
                    $("#detailImage").filebox('clear');
                }else{
                    $.each(urls,function(i,u){
                        var img = $("<img style='display:inline-block;margin:5px;width:100px;height:90px'/>").attr("src","/baizhi-kfsy-sys/"+u);
                        var input = $("<input name='urls' type='hidden'/>").val("/baizhi-kfsy-sys"+u);
                        $("#smallDetailShow").append(img);
                        $("#addpillform").append(input);
                    });
                }
            }
        });
    }
</script>

<form id="addpillform" method="post" style="margin-top: 20px;margin-left: 150px">
    <div style="margin-bottom: 10px;">
        <input name="name" class="easyui-textbox" data-options="width:280,label:'药品名称',labelPosition: 'left',prompt:'请输入药品名称',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="treatment" class="easyui-textbox" data-options="width:280,label:'药效',labelPosition: 'left',prompt:'请输入药效',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="unit" class="easyui-textbox" data-options="width:280,label:'单位',labelPosition: 'left',prompt:'请输入药品单位(如：盒)',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="feature" class="easyui-textbox" data-options="width:280,label:'药品性状',labelPosition: 'left',prompt:'请输入药品性状',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="validDate" class="easyui-textbox" data-options="width:280,label:'保质期',labelPosition: 'left',prompt:'请输入保质期',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="ingredient" class="easyui-textbox" data-options="width:280,label:'药品成分',labelPosition: 'left',prompt:'请输入药品成分',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="nmpn" class="easyui-textbox" data-options="width:280,label:'国药准字号',labelPosition: 'left',prompt:'请输入国药准字',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="currentPrice" class="easyui-textbox" data-options="width:280,label:'商城价格',labelPosition: 'left',prompt:'请输入价格',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="oldPrice" class="easyui-textbox" data-options="width:280,label:'原始价格',labelPosition: 'left',prompt:'请输入原始价格',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="stock" class="easyui-textbox" data-options="width:280,label:'库存',labelPosition: 'left',prompt:'请输入库存',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="sales" class="easyui-textbox" data-options="width:280,label:'销量',labelPosition: 'left',prompt:'请输入销量',required:true"/>
    </div>
    <div style="margin-bottom: 10px;">
        <input name="entryDate" class="easyui-datebox" data-options="width:280,label:'入库时间',labelPosition: 'left',prompt:'请选择入库时间',required:true"/>
    </div>

    <div style="margin-bottom: 10px;">
        <label>药品类型</label>&nbsp;&nbsp;&nbsp;
        <select name="category.id" id="pillCategory" class="easyui-combobox" data-options="width:190,prompt:'请选择',required:true"></select>
    </div>

    <div style="margin-bottom: 10px;">
        <label>药品状态</label>&nbsp;&nbsp;&nbsp;
        <input name="status" class="easyui-switchbutton" data-options="handleWidth:50,width:120,onText:'在售',offText:'下架'">
    </div>
    <input name="thumbnail" type="hidden" id="thumbnail" class="easyui-textbox" />
</form>

<form id="smallImageForm" enctype="multipart/form-data" method="post" style="margin-top: 10px;margin-left: 150px">
    <div style="margin-bottom: 10px;">
        <input name="file" class="easyui-filebox" id="smallImage" data-options="onChange:uploadSamllImg,width:300,label:'药品缩略图',prompt:'请选择缩略图',labelPosition: 'left'"/>
    </div>
    <img id="smallImageShow" src="" style="width: 100px;height: 90px;margin-left: 80px"/>
</form>


<form id="detailImageForm" enctype="multipart/form-data" method="post" style="margin-top: 20px;margin-left: 150px">
    <div style="margin-bottom: 10px;">
        <input name="images" class="easyui-filebox" id="detailImage" data-options="onChange:uploadDetailImg,width:300,label:'药品详情图',prompt:'请选择(最多上传4张)',labelPosition: 'left',multiple:true"/>
    </div>
    <div id="smallDetailShow" style="width: 220px;height: 200px;border: 1px solid #C0C0C0;margin-left: 80px"></div>
</form>
