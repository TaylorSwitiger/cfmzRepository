<%@page language="java" isELIgnored="false" contentType="text/html; utf-8"
        pageEncoding="utf-8" import="java.util.*" %>

<script type="text/javascript">
    
    $(function () {
        $("#t_master").datagrid({
            height : 400,
            url:"${pageContext.request.contextPath }/master/findMaster.do",
            columns:[[
                 {title : "上师编号",field : "masterId" , sortable:true,width:30},
                 {title : "上师法名",field : "masterName",width:30},
                 {title : "上师电话",field : "masterPhoto",width:30},
                 {title : "上师概述",field : "masterrSummary",width:30,
                     /*formatter: function(value,row,index){
                         if (row.pictureStatus == "轮播"){
                             return "<span style='color: red'>" + row.pictureStatus + "<span>";
                         } else {
                             return row.pictureStatus;
                         }
                     }*/
                 },
                 {title : "操作",field : "m_operate",width:30,
                     formatter: function(value,row,index){
                         return "<a name='m_opera' class='easyui-linkbutton' >修改 <a>";
                     },
                 },
              ]],
            onLoadSuccess : function (data) {
                $("a[name='m_opera']").linkbutton({
                    text:'修改',
                    plain:true,
                    iconCls:'icon-edit',
                    onClick:function(){
                        //展示一个对话框窗口
                        $("#grid_master").dialog({
                            width : 525,
                            height : 300,
                            title : "更新上师",
                            toolbar : [{
                                iconCls : "icon-help",
                                text : "帮助",
                                handler : function(){
                                    alert("帮助");
                                },
                            }],
                            href : "${pageContext.request.contextPath }/main/updateMaster.jsp",
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
            toolbar:"#tool_master",
            singleSelect:true, //设置只能选择一行
            iconCls:"icon-ok",
            /*view:detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.picturePath + '" style="height:50px;"></td>' +
                '<td style="border:0">' +
                '<p>Attribute: ' + rowData.pictureDescription + '</p>' +
                '<p>Status: ' + rowData.pictureStatus + '</p>' +
                '</td>' +
                '</tr></table>';
            }*/
        });


        $("#m_b1").linkbutton({
            onClick:function(){
                //展示一个对话框窗口
                $("#grid_master").dialog({
                    width : 525,
                    height : 300,
                    title : "增加上师",
                    toolbar : [{
                        iconCls : "icon-help",
                        text : "帮助",
                        handler : function(){
                            alert("帮助");
                        },
                    }],
                    href : "${pageContext.request.contextPath }/main/addMaster.jsp",
                    model : true,
                    shadow : true ,

                });
            }
        });

        $("#m_b3").linkbutton({
            onClick:function(){
                //展示一个对话框窗口
                $("#grid_master").dialog({
                    width : 525,
                    height : 300,
                    title : "批量插入",
                    toolbar : [{
                        iconCls : "icon-help",
                        text : "帮助",
                        handler : function(){
                            alert("帮助");
                        },
                    }],
                    href : "${pageContext.request.contextPath }/main/addMasterBatch.jsp",
                    model : true,
                    shadow : true ,

                });
            }
        });

        $("#m_b4").linkbutton({
            onClick:function(){
                location.href = "${pageContext.request.contextPath }/master/exportMaster.do";
              }
        });

    });

    function search_master(value,name){
        $("#t_master").datagrid("load",{
            key:value,
            category:name,
        });
    }
    
</script>

    <table id="t_master"></table>

        <div id="tool_master">
            <a id="m_b1" class="easyui-linkbutton" data-options="iconCls:'icon-add',text:'新增上师'"></a>
            <a id="m_b3" class="easyui-linkbutton" data-options="iconCls:'icon-add',text:'批量插入'"></a>
            <a id="m_b4" class="easyui-linkbutton" data-options="iconCls:'icon-load',text:'下载表格'"></a>
            <a id="m_b2" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true,text:'帮助'"></a>
            <input id="ss" class="easyui-searchbox" style="width:300px" data-options="searcher:search_master,prompt:'Please Input Value',menu:'#mm'"></input>
            <div id="mm" style="width:120px">
                <div data-options="name:'master_id',iconCls:'icon-ok'">ID</div>
                <div data-options="name:'master_name',iconCls:'icon-ok'">Name</div>
            </div>
        </div>

    <div id="grid_master"></div>