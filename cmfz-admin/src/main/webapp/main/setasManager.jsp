<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>


<script type="text/javascript">

    $(function(){

       var item = $("#t_manager").datagrid("getSelected");

        $("#mgr_setas1").linkbutton({
            iconCls:"icon-load",
            onClick:function(){
                $.ajax({
                    type : "post",
                    url : "${pageContext.request.contextPath }/mgr/setasManager.do",
                    data: item,
                    success : function(message){
                        $.messager.show({
                            title : "我的消息",
                            msg : message +",窗口将自动关闭",
                            timeout : 1000,
                            showType : "silder",
                        });
                        $("#t_manager").datagrid("reload",{});
                        $("#grid_manager").dialog("close");
                    }
                });
            },
        });

    });

</script>
<div style="text-align: center;margin-top: 40px">
   <br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="mgr_setas1">确认</a>&nbsp;&nbsp;
</div>
