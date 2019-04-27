<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>


<script type="text/javascript">

    $(function(){

        var item = $("#t_master").datagrid("getSelected");
        //console.log(item);
        $("#m_up_son1").prop("value",item.masterId);
        $("#m_up_son2").prop("value",item.masterName);
        $("#m_up_son3").prop("value",item.masterPhoto);
        $("#m_up_son4").prop("value",item.masterrSummary);

        $("#m_up1").linkbutton({
            iconCls:"icon-load",
            onClick:function(){
                //console.log($("#p_son2").val());
                //console.log($("#p_form"));
                $("#m_up_form").form("submit",{
                    url : "${pageContext.request.contextPath }/master/updateMaster.do",
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

        $("#m_up2").linkbutton({
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
                $("#grid_master").dialog("close");
            }
        })

    });

</script>
<div style="text-align: center;margin-top: 40px">
    <form id="m_up_form" method="post" enctype="multipart/form-data">
        社员编号:<input id="m_up_son1" class="easyui-textbox" name="masterId" data-options="label:'社员编号:'" readonly/><br><br>
        社员姓名:<input id="m_up_son2" class="easyui-textbox" name="masterName" data-options="label:'社员姓名:'" readonly/><br><br>
        社员电话:<input id="m_up_son3" class="easyui-textbox" name="masterPhoto" data-options="label:'社员电话:'"/><br><br>
        社员概述:<input id="m_up_son4" class="easyui-textbox" name="masterrSummary" data-options="label:'社员概述:'"/>
    </form><br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="m_up1">提交</a>&nbsp;&nbsp;
    <a id="m_up2">取消</a>
</div>
