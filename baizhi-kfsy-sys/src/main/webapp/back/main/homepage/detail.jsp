<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(function(){
        $.post("<c:url value='/pill/getDetail'/>",{"id":"${param.id}"},function(data){
            $("#nmpn1").html("国药准字号：&nbsp;"+data.nmpn);
            $("#unit1").html("单位：&nbsp;"+data.unit);
            $("#feature1").html("性状：&nbsp;"+data.feature);
            $("#validDate1").html("保质期：&nbsp;"+data.validDate);
            $("#ingredient1").html("成分：&nbsp;"+data.ingredient);
        },"JSON");
    });
</script>

<div style="margin-top: 20px;margin-left:150px;line-height: 30px">
    <p id="nmpn1"></p>
    <p id="unit1"></p>
    <p id="feature1"></p>
    <p id="validDate1"></p>
    <p id="ingredient1"></p>
</div>