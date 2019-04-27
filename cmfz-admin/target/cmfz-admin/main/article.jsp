<%@page language="java" isELIgnored="false" contentType="text/html; utf-8"
        pageEncoding="utf-8" import="java.util.*" %>

<script type="text/javascript">

    $(function () {
        $("#t_article").datagrid({
            height : 400,
            url:"${pageContext.request.contextPath }/article/findArticle.do",
            columns:[[
                {title : "文章编号",field : "articleId" , sortable:true,width:30},
                {title : "文章名称",field : "articleName",width:30},
                {title : "所属社员",field : "masterId",width:30},
                {title : "创建时间",field : "publishDate",width:30},
                {title : "操作",field : "art_operate",width:30,
                    formatter: function(value,row,index){
                        return "<a name='art_opera' class='easyui-linkbutton' >修改 <a>";
                    },
                },
            ]],
            onLoadSuccess : function (data) {
                $("a[name='art_opera']").linkbutton({
                    text:'修改',
                    plain:true,
                    iconCls:'icon-edit',
                    onClick:function(){
                        //展示一个对话框窗口
                        $("#grid_article").dialog({
                            width : 525,
                            height : 300,
                            title : "更新文章",
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
            singleSelect:true, //设置只能选择一行
            iconCls:"icon-ok",
            view:detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0">' + rowData.introduction + '</td>' + '</tr></table>';
            }
        });

    });

</script>

<table id="t_article"></table>

<div id="grid_article"></div>