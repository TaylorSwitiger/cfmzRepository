<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

    function add(name,icon,url){

        if($("#tt").tabs("exists",name)){
            $("#tt").tabs("select",name);
		} else {

                $("#tt").tabs("add",{
                title : name,
				iconCls : icon,
				closable : true,
				href : url,
			});
		}

	}

	$(function(){

	    $.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/mgr/menu.do",
			success : function(message){
			    //console.log(message);
                var father = document.getElementById("aa");
                $(message).each(function (index,element) {
					//console.log(element);
                    var children = document.createElement("div");
                    var title = element.menuName ;
                    var icon = element.menuIcon;
                    var childs = element.childrens;
                   // console.log(childs);
                    var chContent = '';
                    $(childs).each(function (index,element) {
                        console.log(element);
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


                   // father.innerHTML = "<div title=\"" + title + "\" data-options=\"iconCls:'" + icon + "'\" style=\"overflow:auto;padding:10px;\"><a class=\"easyui-linkbutton\" data-options=\"iconCls : 'icon-search' , plain : true \" onclick=\"examine(this)\">" + childName + "</a></div>";

                    //father.appendChild("<div title=\"公告管理\" data-options=\"iconCls:'icon-save'\" style=\"overflow:auto;padding:10px;\"></div>");
                });
            }

		});

	})

    /*function examine(item){
        var flag = $("#tt").tabs("exists",item.text);

        if (!flag) {
            //调用选项卡的addTab新增选项卡
            $("#tt").tabs("add",{
                title : item.text,
                iconCls : "icon-ok",
                closable : true ,
                href : "/examine.jsp"
            });
        } else {
            $("#tt").tabs("select",item.text);
        }
    }*/

</script>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.manager.mgrName } &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 gaozhy@zparkhr.com.cn</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">

		</div>  
    </div>   
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:false ,narrow:true,pill:true,justified : true">
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(${pageContext.request.contextPath}/main/image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>  
    </div>   
</body> 
</html>