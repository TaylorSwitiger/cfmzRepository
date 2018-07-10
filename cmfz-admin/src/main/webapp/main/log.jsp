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
                {title : "操作",field : "log_operate",width:30,
                    formatter: function(value,row,index){
                        return "<a name='log_opera' class='easyui-linkbutton' >修改 <a>";
                    },
                },
            ]],
            onLoadSuccess : function (data) {
                $("a[name='log_opera']").linkbutton({
                    text:'修改',
                    plain:true,
                    iconCls:'icon-edit',
                    onClick:function(){
                        //展示一个对话框窗口
                        $("#grid_log").dialog({
                            width : 525,
                            height : 300,
                            title : "更新文章",
                            toolbar : [{
                                iconCls : "icon-help",
                                text : "帮助",
                                handler : function(){
                                    alert("帮助");
                                },
                            }],
                            href : "${pageContext.request.contextPath }/main/updateMaster.jsp",
                            model : true,
                            shadow : true ,

                        });
                    }
                });
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