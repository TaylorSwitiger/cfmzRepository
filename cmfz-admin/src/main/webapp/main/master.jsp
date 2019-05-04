<%@page language="java" isELIgnored="false" contentType="text/html; utf-8"
        pageEncoding="utf-8" import="java.util.*" %>

<script type="text/javascript">
    
    $(function () {
        $("#t_master").datagrid({
            height : 400,
            url:"${pageContext.request.contextPath }/master/findMaster.do",
            columns:[[
                 {title : "社员编号",field : "masterId" , sortable:true,width:30},
                 {title : "社员姓名",field : "masterName",width:30},
                 {title : "社员电话",field : "masterPhoto",width:30},
                 {title : "社员概述",field : "masterrSummary",width:30,
                 },
                 {title : "操作",field : "m_operate",width:30,
                     formatter: function(value,row,index){
                         return "<a name='m_opera' class='easyui-linkbutton' >修改 <a>" + "<a name='m_remove' class='easyui-linkbutton' >删除 <a>";
                     },
                 },
              ]],
            onLoadSuccess : function (data) {
                $("a[name='m_opera']").linkbutton({
                    text:'修改',
                    plain:true,
                    iconCls:'icon-edit',
                    onClick:function(){
                        //展示一个对话框窗口
                        $("#grid_master").dialog({
                            width : 525,
                            height : 300,
                            title : "更新社员",
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
                $("a[name='m_remove']").linkbutton({
                    text:'删除',
                    plain:true,
                    iconCls:'icon-edit',
                    onClick:function(){
                        $.ajax({
                            type : "post",
                            url : "${pageContext.request.contextPath }/master/removeMaster.do",
                            data: $("#t_master").datagrid("getSelected"),
                            success : function(message){
                                $.messager.show({
                                    title : "我的消息",
                                    msg : message +",窗口将自动关闭",
                                    timeout : 1000,
                                    showType : "silder",
                                });
                                $("#t_master").datagrid("reload",{});
                            }
                        });
                    }
                });
            },
            pagination:true ,
            fitColumns:true ,
            pageList:[4,7,10],
            pageSize:4,
            toolbar:"#tool_master",
            singleSelect:true, //设置只能选择一行
            iconCls:"icon-ok",
        });


        $("#m_b1").linkbutton({
            onClick:function(){
                //展示一个对话框窗口
                $("#grid_master").dialog({
                    width : 525,
                    height : 300,
                    title : "增加社员",
                    toolbar : [{
                        iconCls : "icon-help",
                        text : "帮助",
                        handler : function(){
                            alert("帮助");
                        },
                    }],
                    href : "${pageContext.request.contextPath }/main/addMaster.jsp",
                    model : true,
                    shadow : true ,

                });
            }
        });

        $("#m_b3").linkbutton({
            onClick:function(){
                //展示一个对话框窗口
                $("#grid_master").dialog({
                    width : 525,
                    height : 300,
                    title : "批量插入",
                    toolbar : [{
                        iconCls : "icon-help",
                        text : "帮助",
                        handler : function(){
                            alert("帮助");
                        },
                    }],
                    href : "${pageContext.request.contextPath }/main/addMasterBatch.jsp",
                    model : true,
                    shadow : true ,

                });
            }
        });

        $("#m_b4").linkbutton({
            onClick:function(){
                location.href = "${pageContext.request.contextPath }/master/exportMaster.do";
              }
        });

    });

    function search_master(value,name){
        $("#t_master").datagrid("load",{
            key:value,
            category:name,
        });
    }
    
</script>

    <table id="t_master"></table>

        <div id="tool_master">
            <a id="m_b1" class="easyui-linkbutton" data-options="iconCls:'icon-add',text:'新增社员'"></a>
            <a id="m_b3" class="easyui-linkbutton" data-options="iconCls:'icon-add',text:'批量插入'"></a>
            <a id="m_b4" class="easyui-linkbutton" data-options="iconCls:'icon-load',text:'下载表格'"></a>
            <a id="m_b2" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true,text:'帮助'"></a>
            <input id="ss" class="easyui-searchbox" style="width:300px" data-options="searcher:search_master,prompt:'Please Input Value',menu:'#mm'"></input>
            <div id="mm" style="width:120px">
                <div data-options="name:'master_id',iconCls:'icon-ok'">ID</div>
                <div data-options="name:'master_name',iconCls:'icon-ok'">Name</div>
            </div>
        </div>

    <div id="grid_master"></div>