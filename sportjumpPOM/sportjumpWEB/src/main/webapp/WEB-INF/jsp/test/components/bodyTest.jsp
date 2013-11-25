<div class = "test_body" style="background-color: gray;">
 	<h1><fmt:message key="test.body.message"/></h1>
	
	contextPath:${pageContext.request.contextPath} <br>
	localAddr:${pageContext.request.localAddr} <br>
	localName:${pageContext.request.localName} <br>
	localPort:${pageContext.request.localPort} <br>
	method:${pageContext.request.method} <br>
	pathInfo:${pageContext.request.pathInfo} <br>
	protocol:${pageContext.request.protocol} <br>
	remoteAddr:${pageContext.request.remoteAddr} <br>
	serverName:${pageContext.request.serverName} <br>
	
	
	<h3><fmt:message key="test.label.insert_user_tittle"/>fasdasdf</h3>
	<fmt:message key="test.label.insert_user_name"/> <input id = "input-add-name" /> 	<br/>	 
	<button onclick="insertUser();"><fmt:message key="test.button.insert_user"/></button> <br/>
	
	
	<h3><fmt:message key="test.label.check_user_tittle"/></h3>
	<fmt:message key="test.label.check_user_name"/> <input id = "input-check-name" onkeyup="checkUser();"/> 	<br/>
	<div id = "user_exists" style="color: #3ADF00; display: none"><fmt:message key="test.label.user_exists"/></div><div id = "user_not_exists" style="color: #FF0000; display: none"><fmt:message key="test.label.user_not_exists"/></div>
	 
</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-1.10.3/js/jquery-1.9.1.js"></script>
 <script type="text/javascript">


	function insertUser(){		
		alert("insert user");
		var name = document.getElementById("input-add-name").value;	
		
		//LLAMADA AJAS PARA CREAR
		
		alert (name + '<fmt:message key="test.alert.insert_user_name_success"/>');  
	}

	
	
	function checkUser(){
		alert("Check user");
		var name = document.getElementById("input-check-name").value;
	
		//LLAMADA AJAX PARA COMPROBAR
		$.ajax({
			  url: "test/ajax/find/" + name,
			  type: 'GET',
			  cache: false,
			  data: 'parametro1=valor1&parametro2=valor2',
			  success: successFunction,
			  error: errorFunction
		});		
	}
	
	function successFunction(){
		alert("Ajax correcto con success");
	}
	
	function errorFunction(){
		alert("Error de ajax");
	}
</script>

	
	
