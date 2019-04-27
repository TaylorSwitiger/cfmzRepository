<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>


<script type="text/javascript">

    $(function(){

        $("#p_add1").linkbutton({
            iconCls:"icon-load",
            onClick:function(){
                //console.log($("#p_son2").val());
                //console.log($("#p_form"));
                $("#p_add_form").form("submit",{
                    url : "${pageContext.request.contextPath }/pic/addPicture.do",
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

        $("#p_add2").linkbutton({
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
    <form id="p_add_form" method="post" enctype="multipart/form-data">
        海报描述:<input id="p_add_son1" class="easyui-textbox" name="pictureDescription" data-options="label:'海报描述:'"/><br><br>
        海报状态:<input id="p_add_son2" class="easyui-textbox" name="pictureStatus" data-options="label:'海报状态:'"/><br><br>
        上传海报:<input id="p_add_son3" class="easyui-filebox" name="picturePath" data-options="label:'上传海报:'"/>
    </form><br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="p_add1">添加</a>&nbsp;&nbsp;
    <a id="p_add2">取消</a>
</div>
