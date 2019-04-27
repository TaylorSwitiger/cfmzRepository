<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>高校社团主页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wangEditor.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
<script type="text/javascript">

	var img_url = new Array();
	
    function add(name,icon,url){

        if($("#tt").tabs("exists",name)){
            $("#tt").tabs("select",name);
		} else {
			 $("#tt").tabs("add",{
			     title : name,
				 iconCls : icon,
				 closable : true,
				 href : url,
				 fit:true ,
			});
		}

	}
	
	function changePic() {
        //已发布图片数量
        var img_num = img_url.length;
        //随机设置图片
        var random =  Math.floor(Math.random()*img_num);
        $("#main_img").prop("src","${pageContext.request.contextPath}" + img_url[random]);
    }

	$(function(){

	    $.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/mgr/menu.do",
			success : function(message){
			    //console.log(message);
				var menus = message.menuList;
                var father = document.getElementById("aa");
                $(menus).each(function (index,element) {
					//console.log(element);
                    var children = document.createElement("div");
                    var title = element.menuName ;
                    var icon = element.menuIcon;
                    var childs = element.childrens;
                   // console.log(childs);
                    var chContent = '';
                    $(childs).each(function (index,element) {
                       // console.log(element);
                        var childName = element.menuName;
                        var childIcon = element.menuIcon;
                        var url = "${pageContext.request.contextPath}"+element.menuUrl;
                        chContent = chContent + "</br>" + "<a class=\"easyui-linkbutton\" data-options=\"iconCls : '" + childIcon + "' , plain : true \" onclick=\"add('" + childName + "','" + childIcon + "','" + url + "')\">" + childName + "</a>";
                    })

					//console.log(chContent);
                    $('#aa').accordion('add', {
                        title: title,
                        content : chContent,
                        selected: false,
                        iconCls:icon,
                    });
                });
                //记录下已发布的图片地址
                img_url = message.imgUrl;
            }

		});

        $("a[name='change_pwd']").linkbutton({
            onClick:function(){
                //展示一个对话框窗口
                $("#grid_main").dialog({
                    width : 525,
                    height : 300,
                    title : "修改密码",
                    toolbar : [{
                        iconCls : "icon-help",
                        text : "帮助",
                        handler : function(){
                            alert("帮助");
                        },
                    }],
                    href : "${pageContext.request.contextPath }/main/updateManager.jsp",
                    model : true,
                    shadow : true ,

                });
            }
        });

	})

</script>
<style type="text/css">
	div.popup span{display:none;position:absolute;top:29px;left:2px;z-index: 99;border: 1px solid; padding:4px;width:100px;}
	div.popup:hover span{display:block;}
</style>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >高校社团管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:<shiro:principal type="com.xuyiming.cmfz.entity.Manager" property="mgrName"></shiro:principal> &nbsp;<a name="change_pwd" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/login.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" ><a href=" http://www.henu.edu.cn" style="color: lightyellow ; text-decoration: none">&copy;河南大学</a></div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">

		</div>  
    </div>   
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true ,narrow:true,pill:true,justified : false">
		    <div class="popup" id="main_father" title="主页" data-options="width:100,iconCls:'icon-neighbourhood',"  style="background-repeat: no-repeat;background-size:100% 100%;">
				<img id="main_img" src="${pageContext.request.contextPath}/main/image/1.jpg" width="100%" height="100%" onclick="changePic()" />
				<span style="background-color: #F1867C;color:black;width: 100px;height:20px;">点击切换封面海报</span>
			</div>
		</div>  
    </div>

	<div id="grid_main"></div>
</body> 
</html>