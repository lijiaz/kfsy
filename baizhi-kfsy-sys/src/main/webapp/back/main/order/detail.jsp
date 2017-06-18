<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(function(){
        $.post("<c:url value='/order/getOne'/>",{"id":"${param.id}"},function(data){
            $("#orderNum").html("订单号：&nbsp;"+data.orderNum);
            $("#totalPrice").html("订单金额：&nbsp;"+data.totalPrice);
            $("#createDate").html("订单创建时间：&nbsp;"+data.createDate);
            $("#remarks").html("备注：&nbsp;"+data.remarks);
            var $t="";
            $.each(data.orderItems,function(i,d){
                $t += "商品名称："+d.name+"&nbsp;商品数量："+d.num+"<br/>商品单价："+d.price+"&nbsp;商品小计："+d.total+"<br/><hr/>";
            });
            $("#orderItem").html($t);
        },"JSON");
    });
</script>

<div style="margin-top: 20px;margin-left:80px;line-height: 30px">
    <p id="orderNum"></p>
    <p id="totalPrice"></p>
    <p id="createDate"></p>
    <p id="remarks"></p>
    <p id="orderItem"></p>
</div>