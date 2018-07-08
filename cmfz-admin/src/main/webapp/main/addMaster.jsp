<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>


<script type="text/javascript">

    $(function(){

        $("#m_add1").linkbutton({
            iconCls:"icon-load",
            onClick:function(){
                //console.log($("#p_son2").val());
                //console.log($("#p_form"));
                $("#m_add_form").form("submit",{
                    url : "${pageContext.request.contextPath }/master/addMaster.do",
                    success : function(res){
                        $.messager.show({
                            title : "我的消息",
                            msg : "保存成功，窗口将自动关闭",
                            timeout : 1000,
                            showType : "silder",
                        });
                        $("#t_master").datagrid("reload",{});
                        $("#grid_master").dialog("close");
                    }
                });
            },
        });

        $("#m_add2").linkbutton({
            iconCls : "icon-cancel",
            text : "取消",
            onClick : function(){
                //出现消息窗口，并关闭对话框
                $.messager.show({
                    title : "我的消息",
                    msg : "请重新填写",
                    timeout : 1000,
                    showType : "silder",
                });
                $("#grid_picture").dialog("close");
            }
        })

    });

</script>
<div style="text-align: center;margin-top: 40px">
    <form id="m_add_form" method="post" enctype="multipart/form-data">
        上师法名:<input id="m_add_son1" class="easyui-textbox" name="masterName" data-options="label:'上师法名:'"/><br><br>
        上师电话:<input id="m_add_son2" class="easyui-textbox" name="masterPhoto" data-options="label:'上师电话:'"/><br><br>
        上师概述:<input id="m_add_son3" class="easyui-textbox" name="masterrSummary" data-options="label:'上师概述:'"/>
    </form><br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="m_add1">添加</a>&nbsp;&nbsp;
    <a id="m_add2">取消</a>
</div>
