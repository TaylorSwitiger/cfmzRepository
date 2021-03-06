<%@ page import="java.net.URLDecoder" %>
<%@page pageEncoding="utf-8" language="java" contentType="text/html;utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>高校社团后台管理中心</title>
			
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
	<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/common.js"></script>
	<script type="text/javascript">



		$(function(){

			//点击更换验证码：
			$("#captchaImage").click(function(){//点击更换验证码

                var ac = $("#captchaImage").prop("src");
                $("#captchaImage").prop("src",ac+"?"+Math.random());
			});
			
			//  form 表单提交
			$("#loginForm").bind("submit",function(){
				var value = false;
				if ($("#isRememberUsername").prop("checked")) {
				    value = true ;
				}
                $("#isRememberUsername").prop("value",value);

			});
		});
	</script>
</head>
<body>
		<div style="color: #5C160C;font-size: 30px; text-align:center; margin-top: 90px; margin-bottom: -70px" >高校社团管理系统</div>
		<div class="login">
			<form id="loginForm" action="${pageContext.request.contextPath}/mgr/login.do" method="post" accept-charset="utf-8">
				
				<table>
					<tbody>
						<tr>
							<td width="190" rowspan="2" align="center" valign="bottom">
								<img src="${pageContext.request.contextPath}/img/timg.jpg" />
							</td>
							<th>
								用户名:
							</th>
							<td>
								<%
									Cookie[] cookies = request.getCookies();
									String name = "";
									if (cookies!=null) {
										for (Cookie cookie : cookies) {
											if (cookie.getName().equals("mgrName")){
												if (cookie.getValue() != null) {
													name = URLDecoder.decode(cookie.getValue(),"utf-8");
												}
											}
										}
									}
								%>
								<input type="text"  name="mgrName" class="text" value="<%=name%>" maxlength="20"/>
							</td>
					  </tr>
					  <tr>
							<th>
								密&nbsp;&nbsp;&nbsp;码:
							</th>
							<td>
								<input type="password" name="mgrPwd" class="text" maxlength="20" autocomplete="off"/>
							</td>
					  </tr>
					
						<tr>
							<td>&nbsp;</td>
							<th>验证码:</th>
							<td>
								<input type="text" id="enCode" name="enCode" class="text captcha" maxlength="4" autocomplete="off"/>
								<img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/mgr/getVcode.do" title="点击更换验证码"/>
							</td>
						</tr>					
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
						<td>
							<label>
								<input type="checkbox" id="isRememberUsername" name="check" value="true"/> 记住用户名
								<input type="checkbox" id="isRememberMeSevenDay" name="rememberMe" value="true"/> 七天免登陆
							</label>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit" class="loginButton" value="登录">
						</td>
					</tr>
				</tbody></table>
				<div class="link">
					<a href="">关于我们</a> |
					<a href="">联系我们</a>
				</div>
			</form>
		</div>
</body>
</html>