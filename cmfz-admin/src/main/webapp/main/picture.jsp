<%@page language="java" isELIgnored="false" contentType="text/html; utf-8"
        pageEncoding="utf-8" import="java.util.*" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<script type="text/javascript">
    
    $(function () {
        $("#t_picture").datagrid({
            height : 400,
            url:"${pageContext.request.contextPath }/pic/findPicture.do",
            columns:[[
                 {title : "标识编号",field : "pictureId" , sortable:true,width:30},
                 {title : "文件名",field : "picturePath",width:30},
                 {title : "描述信息",field : "pictureDescription",width:30},
                 {title : "轮播图状态",field : "pictureStatus",width:30,
                     formatter: function(value,row,index){
                         if (row.pictureStatus == "轮播"){
                             return "<span style='color: red'>" + row.pictureStatus + "<span>";
                         } else {
                             return row.pictureStatus;
                         }
                     }
                 },
                 {title : "更新时间",field : "pictureDate",width:30},
                 {title : "操作",field : "operate",width:30,
                     formatter: function(value,row,index){
                         return "<shiro:hasPermission name="picture:modify">" + "<a name='opera' class='easyui-linkbutton' >修改 <a>" + "</shiro:hasPermission>";
                     },
                 },
              ]],
            onLoadSuccess : function (data) {
                $("a[name='opera']").linkbutton({
                    text:'修改',
                    plain:true,
                    iconCls:'icon-edit',
                    onClick:function(){
                        //展示一个对话框窗口
                        $("#grid_picture").dialog({
                            width : 525,
                            height : 300,
                            title : "修改图片",
                            toolbar : [{
                                iconCls : "icon-help",
                                text : "帮助",
                                handler : function(){
                                    alert("帮助");
                                },
                            }],
                            href : "${pageContext.request.contextPath }/main/updatePicture.jsp",
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
            toolbar:"#tool_picture",
            singleSelect:true, //设置只能选择一行
            iconCls:"icon-ok",
            view:detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.picturePath + '" style="height:50px;"></td>' +
                '<td style="border:0">' +
                '<p>Attribute: ' + rowData.pictureDescription + '</p>' +
                '<p>Status: ' + rowData.pictureStatus + '</p>' +
                '</td>' +
                '</tr></table>';
            }
        });


        $("#p_b1").linkbutton({
            onClick:function(){
                //展示一个对话框窗口
                $("#grid_picture").dialog({
                    width : 525,
                    height : 300,
                    title : "增加图片",
                    toolbar : [{
                        iconCls : "icon-help",
                        text : "帮助",
                        handler : function(){
                            alert("帮助");
                        },
                    }],
                    href : "${pageContext.request.contextPath }/main/addPicture.jsp",
                    model : true,
                    shadow : true ,

                });
            }
        });
       // $(".datagrid-body table").css("color","yellow");

    });
    
    
</script>

    <table id="t_picture"></table>

        <div id="tool_picture">
            <shiro:hasPermission name="picture:add"><a id="p_b1" class="easyui-linkbutton" data-options="iconCls:'icon-add',text:'新增轮播图'"></a></shiro:hasPermission>
            <a id="p_b2" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true,text:'帮助'"></a>
        </div>

    <div id="grid_picture"></div>