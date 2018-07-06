<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>


<script type="text/javascript">

    $(function(){

        var item = $("#t_picture").datagrid("getSelected");
        //console.log(item);
        $("#p_up_son1").prop("value",item.pictureId);
        $("#p_up_son2").prop("value",item.pictureDescription);
        $("#p_up_son3").prop("value",item.pictureStatus);

        $("#p_up1").linkbutton({
            iconCls:"icon-load",
            onClick:function(){
                //console.log($("#p_son2").val());
                //console.log($("#p_form"));
                $("#p_up_form").form("submit",{
                    url : "${pageContext.request.contextPath }/mgr/updatePicture.do",
                    success : function(res){
                        $.messager.show({
                            title : "我的消息",
                            msg : "保存成功，窗口将自动关闭",
                            timeout : 1000,
                            showType : "silder",
                        });
                        $("#t_picture").datagrid("reload",{});
                        $("#grid_picture").dialog("close");
                    }
                });
            },
        });

        $("#p_up2").linkbutton({
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
    <form id="p_up_form" method="post" enctype="multipart/form-data">
        轮播图 ID :<input id="p_up_son1" class="easyui-textbox" name="pictureId" data-options="label:'轮播图 ID :'" readonly/><br><br>
        轮播图描述:<input id="p_up_son2" class="easyui-textbox" name="pictureDescription" data-options="label:'轮播图描述:'"/><br><br>
        轮播图状态:<input id="p_up_son3" class="easyui-textbox" name="pictureStatus" data-options="label:'轮播图状态:'"/>
    </form><br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="p_up1">提交</a>&nbsp;&nbsp;
    <a id="p_up2">取消</a>
</div>
