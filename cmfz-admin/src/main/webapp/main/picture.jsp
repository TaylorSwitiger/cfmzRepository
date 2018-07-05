<%@page language="java" isELIgnored="false" contentType="text/html; utf-8"
        pageEncoding="utf-8" import="java.util.*" %>

<script type="text/javascript">
    
    $(function () {

        /*$("t_picture").datagrid({
            url:"${pageContext.request.contextPath }/mgr/findPicture.do",
            columns : [[
                {title : "标识编号",field : "pictureId" , sortable:true},
                {title : "文件名",field : "picturePath"  },
                {title : "描述信息",field : "pictureDescription" , },
                {title : "轮播图状态",field : "pictureStatus" },
                {title : "创建时间",field : "pictureDate" },
                //{title : "操作",field : "" },

            ]],
            toolbar : "#tool_picture",
            /!*detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="images/' + rowData.itemid + '.png" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.attr1 + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }*!/

        });*/
        $("#t_picture").datagrid({
            url:"${pageContext.request.contextPath }/mgr/findPicture.do",
            columns:[[
                 {title : "标识编号",field : "pictureId" , sortable:true},
                 {title : "文件名",field : "picturePath"  },
                 {title : "描述信息",field : "pictureDescription" , },
                 {title : "轮播图状态",field : "pictureStatus" },
                 {title : "创建时间",field : "pictureDate" },
                 //{title : "操作",field : "" },
              ]],
            pagination:true,
            fitColumns :true,
            pageList:[4,7,10],
            pageSize:4,
            toolbar:"#tool_picture",
            singleSelect:true, //设置只能选择一行
            iconCls:"icon-ok"
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

        console.log($(".datagrid-body"));
        $(".datagrid-body table").css("color","yellow");

    });
    
    
</script>

    <table id="t_picture"></table>

        <div id="tool_picture">
            <a id="p_b1" class="easyui-linkbutton" data-options="iconCls:'icon-add',text:'新增轮播图'"></a>
            <a id="p_b2" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true,text:'帮助'"></a>
        </div>

    <div id="grid_picture"></div>