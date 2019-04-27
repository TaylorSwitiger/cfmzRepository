<%@page language="java" isELIgnored="false" contentType="text/html; utf-8"
        pageEncoding="utf-8" import="java.util.*" %>

<script type="text/javascript">

    $(function () {
        $("#t_log").datagrid({
            height : 400,
            url:"${pageContext.request.contextPath }/log/findLog.do",
            columns:[[
                {title : "日志编号",field : "logId" , sortable:true,width:30},
                {title : "操作人员",field : "operateManager",width:30},
                {title : "操作时间",field : "operateTime",width:30},
                {title : "操作对象",field : "resource",width:30,},
                {title : "操作动作",field : "action",width:30,},
                {title : "操作信息",field : "message",width:30,},
                {title : "操作结果",field : "result",width:30,},
            ]],
            onLoadSuccess : function (data) {

            },
            pagination:true ,
            fitColumns:true ,
            pageList:[4,7,10],
            pageSize:4,
            singleSelect:true, //设置只能选择一行
            iconCls:"icon-ok",
        });

    });

</script>

<table id="t_log"></table>

<div id="grid_log"></div>