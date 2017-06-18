<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(function(){
        $.post("<c:url value='/pill/getDetail'/>",{"id":"${param.id}"},function(data){
            $("#nmpn").html("国药准字号：&nbsp;"+data.nmpn);
            $("#unit").html("单位：&nbsp;"+data.unit);
            $("#feature").html("性状：&nbsp;"+data.feature);
            $("#validDate").html("保质期：&nbsp;"+data.validDate);
            $("#ingredient").html("成分：&nbsp;"+data.ingredient);
        },"JSON");
    });
</script>

<div style="margin-top: 20px;margin-left:150px;line-height: 30px">
    <p id="nmpn"></p>
    <p id="unit"></p>
    <p id="feature"></p>
    <p id="validDate"></p>
    <p id="ingredient"></p>
</div>