<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/back/easyui/js/echarts.min.js"></script>
<script type="text/javascript">

    $(function(){
        var myChart = echarts.init($("#charts")[0]);

        $.get("<c:url value='/sys/getCount'/>").done(function (data) {
            myChart.setOption({
                title: {
                    text: '注册人数展示'
                },
                tooltip: {},
                legend: {
                    data:['时间段']
                },
                xAxis: {
                    data: data.category
                },
                yAxis: {},
                series: [{
                    name: '人数',
                    type: 'bar',
                    data: data.count
                }]
            });
        });
    });
</script>

<div>
   <div id="charts" style="width:350px;height: 300px;margin-left: 50px;margin-top:20px;"></div>
</div>
