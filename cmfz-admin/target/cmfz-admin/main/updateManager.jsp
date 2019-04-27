<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>


<script type="text/javascript">

    $(function(){

        var item = "${sessionScope.manager.mgrName}";
        console.log(item);
//        alert(item);
        $("#mgr_up_son1").prop("value",item);

        $("#mgr_up1").linkbutton({
            iconCls:"icon-load",
            onClick:function(){
                //console.log($("#p_son2").val());
                //console.log($("#p_form"));
                $("#mgr_up_form").form("submit",{
                    url : "${pageContext.request.contextPath }/mgr/changePwd.do",
                    success : function(res){
                        $.messager.show({
                            title : "我的消息",
                            msg : "保存成功，窗口将自动关闭",
                            timeout : 1000,
                            showType : "silder",
                        });
                        $("#grid_main").dialog("close");
                    }
                });
            },
        });

        $("#mgr_up2").linkbutton({
            iconCls : "icon-cancel",
            text : "撤消",
            onClick : function(){
                //出现消息窗口，并关闭对话框
                $.messager.show({
                    title : "我的消息",
                    msg : "请重新填写",
                    timeout : 1000,
                    showType : "silder",
                });
                $("#grid_main").dialog("close");
            }
        })

    });

</script>
<div style="text-align: center;margin-top: 40px">
    <form id="mgr_up_form" method="post" enctype="multipart/form-data">
        用户名:<input id="mgr_up_son1" class="easyui-textbox" name="mgrName" data-options="label:'用户名:'" readonly/><br><br>
        新密码:<input id="mgr_up_son2" class="easyui-passwordbox" name="mgrPwd" data-options="label:'新密码:'"/><br><br>
    </form><br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="mgr_up1">提交</a>&nbsp;&nbsp;
    <a id="mgr_up2">撤消</a>
</div>
