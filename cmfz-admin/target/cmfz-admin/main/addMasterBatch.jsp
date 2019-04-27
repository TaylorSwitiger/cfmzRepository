<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>


<script type="text/javascript">

    $(function(){

        $("#m_b_add1").linkbutton({
            iconCls:"icon-load",
            onClick:function(){
                //console.log($("#p_son2").val());
                //console.log($("#p_form"));
                $("#m_b_add_form").form("submit",{
                    url : "${pageContext.request.contextPath }/master/addMasterBatch.do",
                    //测试获取表格数据
                    <%--url : "${pageContext.request.contextPath }/importUser.do",--%>
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

        $("#m_b_add2").linkbutton({
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
    <form id="m_b_add_form" method="post" enctype="multipart/form-data">
        选择Excel:<input id="m_b_add_son1" class="easyui-filebox" name="masterFile" data-options="label:'选择Excel:'"/>
    </form><br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="m_b_add1">添加</a>&nbsp;&nbsp;
    <a id="m_b_add2">取消</a>
</div>
