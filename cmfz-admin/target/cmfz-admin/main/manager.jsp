<%@page language="java" isELIgnored="false" contentType="text/html; utf-8"
        pageEncoding="utf-8" import="java.util.*" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
    
    $(function () {
        $("#t_manager").datagrid({
            height : 400,
            url:"${pageContext.request.contextPath }/mgr/findManager.do",
            columns:[[
                 {title : "社员编号",field : "mgrId" , sortable:true,width:30},
                 {title : "社员姓名",field : "mgrName",width:30},
                 {title : "是否是管理员",field : "status",width:30},
                 {title : "操作",field : "mgr_operate",width:30,
                     formatter: function(value,row,index){
                         if (row.status == "y") {
                             return "<shiro:hasPermission name="manager:cancel">" + "<a name='mgr_cancel_opera' class='easyui-linkbutton' >取消管理员 <a>" + "</shiro:hasPermission>";
                         } else {
                             return "<shiro:hasPermission name="manager:setas">" + "<a name='mgr_setas_opera' class='easyui-linkbutton' >设为管理员 <a>" + "</shiro:hasPermission>";
                         }
                     },
                 },
              ]],
            onLoadSuccess : function (data) {
                $("a[name='mgr_cancel_opera']").linkbutton({
                    plain:true,
                    iconCls:'icon-edit',
                    onClick:function(){
                        $.ajax({
                            type : "post",
                            url : "${pageContext.request.contextPath }/mgr/cancelManager.do",
                            data: $("#t_manager").datagrid("getSelected"),
                            success : function(message){
                                $.messager.show({
                                    title : "我的消息",
                                    msg : message +",窗口将自动关闭",
                                    timeout : 1000,
                                    showType : "silder",
                                });
                                $("#t_manager").datagrid("reload",{});
                            }
                        });
                    }
                });
                $("a[name='mgr_setas_opera']").linkbutton({
                    iconCls:'icon-edit',
                    onClick:function(){
                        $.ajax({
                            type : "post",
                            url : "${pageContext.request.contextPath }/mgr/setasManager.do",
                            data: $("#t_manager").datagrid("getSelected"),
                            success : function(message){
                                $.messager.show({
                                    title : "我的消息",
                                    msg : message +",窗口将自动关闭",
                                    timeout : 1000,
                                    showType : "silder",
                                });
                                $("#t_manager").datagrid("reload",{});
                            }
                        });
                    }
                });
            },
            pagination:true ,
            fitColumns:true ,
            pageList:[4,7,10],
            pageSize:4,
            toolbar:"#tool_manager",
            singleSelect:true, //设置只能选择一行
            iconCls:"icon-ok",
        });

    });

    function search_manager(value,name){
        $("#t_manager").datagrid("load",{
            key:value,
            category:name,
        });
    }
    
</script>

    <table id="t_manager"></table>

        <div id="tool_manager">
            <input id="mgr_manage" class="easyui-searchbox" style="width:300px" data-options="searcher:search_manager,prompt:'Please Input Value',menu:'#mgr_menu'"></input>
            <div id="mgr_menu" style="width:120px">
                <div data-options="name:'mgr_id',iconCls:'icon-ok'">ID</div>
                <div data-options="name:'mgr_name',iconCls:'icon-ok'">Name</div>
            </div>
        </div>

    <div id="grid_manager"></div>