<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link href="${pageContext.request.contextPath }/user/css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!--webfonts-->
		<!--//webfonts-->
		<script src="js/setDate.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="main" align="center">
			<div class="header">
				<h1>创建一个免费的新帐户！</h1>
			</div>
			<p></p>
			<form method="post" action="${pageContext.request.contextPath }/UserServlet?op=regist">
				<input type="hidden" name="op" value="regist" />
				<ul class="left-form">
					<li>
						${msg.error.username }<br/>
						<input type="text" name="username" id="username" placeholder="用户名" value="${msg.username}" required="required" onblur="isExist"/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>
					<li>
						${msg.error.nickname }<br/>
						<input type="text" name="nickname" placeholder="昵称" value="${msg.nickname}" required="required"/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>
					<li>
						${msg.error.email }<br/>
						<input type="text" name="email" placeholder="邮箱" value="${msg.email}" required="required"/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>
					<li>
						${msg.error.password }<br/>
						<input type="password" name="password" placeholder="密码" value="${msg.password}" required="required"/>
						<a href="#" class="icon into"> </a>
						<div class="clear"> </div>
					</li>
					<li>
						${msg.error.birthday }<br/>
						<input type="text" placeholder="出生日期" name="birthday" value="${msg.birthday}" size="15" />
						<div class="clear"> </div>
					</li>
					<li>
						<input type="submit" value="创建账户">
						<div class="clear"> </div>
					</li>
			</ul>

			<div class="clear"> </div>

			</form>

		</div>
		<!-----start-copyright---->

		<!-----//end-copyright---->

	</body>
<script type="text/javascript">
	function isExist() {
		var usernameValue = document.getElementById("username");
		//发送ajax异步请求
		//1、创建XMLHttpResponse对象

		var xhr = new XMLHttpRequest();
		//2、注册状态监控回调函数：
		xhr.onreadystatechange = function () {
		    var responseText = xhr.responseText;

		    var msg = document.getElementById("msg");
		    if(responseText == "true"){
		        msg.innerText="可以使用的用户名";
			}else {
		        msg.innerText="用户名已存在";
			}
		};
		//3.建立与服务器的异步链接
		xhr.open("GET","http://localhost:80/AjaxServlet?op=checkUsername&username="+usernameValue.value);
		//4、发出异步请求
		xhr.send();
    }


</script>

</html>