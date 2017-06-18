<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>快方送药后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/back/easyui/css/themes/gray/easyui.css'/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value='/back/easyui/css/themes/icon.css'/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value='/back/easyui/css/IconExtension.css'/> ">
    <script type="text/javascript" src="<c:url value='/back/easyui/js/jquery.min.js'/> "></script>
    <script type="text/javascript" src="<c:url value='/back/easyui/js/jquery.easyui.min.js'/> "></script>
    <script type="text/javascript" src="<c:url value='/back/easyui/js/easyui-lang-zh_CN.js'/> "></script>

    <script type="text/javascript">
        var $ac;
        $(function(){
            $ac = $("#ac");
            $.post("<c:url value='/menu/getAll'/>",function(menus){
                $.each(menus,function(i,menu){
                    var $child= "<div style='text-align: center'>";
                    $.each(menu.children,function(i,child){
                        $child += "<div style='margin-top:10px;width:90%;' class='easyui-linkbutton' onclick=\"addTab('"+child.title+"','"+child.icon+"','"+child.href+"')\" data-options=\"plain:true,iconCls:'"+child.icon+"'\">"+child.title+"</div>";
                    });
                    $child += "</div>";

                    $ac.accordion('add',{
                        title:menu.title,
                        iconCls:menu.icon,
                        content:$child,
                    });
                })
            });

            $("#tbs").tabs('add',{
                title:"系统状态",
                iconCls:"icon-application_osx_terminal",
                closable:true,
                href:"<c:url value='/back/main/sys/sysStatus.jsp'/>",
            })
        });

        function addTab(title,icon,href){
            var f = $("#tbs").tabs('exists',title);
            if(!f){
                $("#tbs").tabs('add',{
                    title:title,
                    iconCls:icon,
                    closable:true,
                    href:"${pageContext.request.contextPath}"+href,
                });
            }else{
                $("#tbs").tabs('select',title);
            }
        }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true">
    <div id="northArea" data-options="region:'north',split:false,border:false" style="height:100px;">
        <div>
            <img src="<c:url value='/back/easyui/images/logo.png'/> " style="margin-left: 50px;margin-top: 25px">
            <div style="margin-left:65%;margin-top: 8px">
                <span>当前用户：${sessionScope.currentManager.userName}</span>&nbsp;
                <span>系统时间：<span id="data"></span></span>&nbsp;&nbsp;
                <span><a href="<c:url value='/manager/logout'/>" style="color: #000;">安全退出</a></span>
            </div>
        </div>
    </div>
    <div data-options="region:'south',split:false" style="height:30px;"></div>
    <div data-options="region:'west',title:'系统菜单',split:false" style="width:200px;">
        <div id="ac" class="easyui-accordion" data-options="border:false,fit:true"></div>
    </div>
    <div data-options="region:'center',title:'系统操作',iconCls:'icon-home'">
        <div id="tbs" class="easyui-tabs" data-options="fit:true,border:false"></div>
    </div>
</body>

<script type="text/javascript">
    oDiv = document.getElementById("data");
    var nowTime = function(){
        var nowTime = new Date();
        var y = nowTime.getFullYear();
        var M = nowTime.getMonth()+1;
        var d = nowTime.getDate();
        var H = nowTime.getHours();
        var m = nowTime.getMinutes();
        var s = nowTime.getSeconds();
        var timer = "<font>"+y+"年"+M+"月"+d+"日  "+H+"时"+m+"分"+s+"秒</font>";
        oDiv.innerHTML = timer;
        setTimeout("nowTime()",1000);
    };
    nowTime();
</script>
</html>
