<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理员登陆</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/back/easyui/css/themes/gray/easyui.css'/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value='/back/easyui/css/themes/icon.css'/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value='/back/easyui/css/IconExtension.css'/> ">
    <script type="text/javascript" src="<c:url value='/back/easyui/js/jquery.min.js'/> "></script>
    <script type="text/javascript" src="<c:url value='/back/easyui/js/jquery.easyui.min.js'/> "></script>
    <script type="text/javascript" src="<c:url value='/back/easyui/js/easyui-lang-zh_CN.js'/> "></script>
    <style>
        .title{
            font-size: 13px;
            font-family: "Microsoft YaHei UI";
        }
    </style>
    <script type="text/javascript">

        var $dd,$name,$pwd,$imageCode;
        $(function(){
            $dd = $("#dd");
            $name = $("#name");
            $pwd = $("#pwd");
            $imageCode = $("#imageCode");

            $dd.dialog({
                title:"管理员登录",
                width:550,
                height:380,
                closable:false,
                buttons:[{
                    text:'登录',
                    width:60,
                    handler:function(){
                        $("#ff").form('submit',{
                            url:"<c:url value='/manager/login'/>",
                            onSubmit: function(){
                                console.log("已提交");
                            },
                        });
                    }
                },{
                    text:'取消',
                    width:60,
                    handler:function(){

                    }
                }]

            });

            $name.textbox({
                width:220,
                height:30,
                iconCls:'icon-man',
            });

            $pwd.textbox({
                width:220,
                height:30,
                iconCls:'icon-lock',
            });

            $imageCode.textbox({
                width:100,
                height:30,
            });
        });
    </script>
</head>
<body style="background-color: rgba(0,0,0,0.2)">
    <div id="dd">
        <div style="margin-top: 60px;margin-left: 120px">
            <form id="ff" action="<c:url value='/manager/login'/>" method="post" data-options="ajax:false">
                <div style="margin-bottom: 35px">
                    <span class="title">账&nbsp;号：</span><input id="name" class="easyui-textbox" type="text" name="userName" data-options="required:true" />
                </div>
                <div style="margin-bottom: 35px">
                    <span class="title">密&nbsp;码：</span><input id="pwd" class="easyui-textbox" type="password" name="password" data-options="required:true" />
                </div>
                <div style="margin-bottom: 35px">
                    <span class="title">验证码：</span><input id="imageCode" class="easyui-textbox" type="text" name="imageCode" data-options="required:true" />&nbsp;
                    <img src="<c:url value='/imageCode/code'/>" style="margin-bottom: -12px;width:90px;height:30px">
                </div>
                <div style="margin-bottom: 35px">
                    <span>${param.msg}</span>
                </div>
            </form>
        </div>
    </div>
</body>
</html>