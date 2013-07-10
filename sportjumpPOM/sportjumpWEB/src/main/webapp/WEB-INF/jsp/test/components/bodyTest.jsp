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
	<fmt:message key="test.label.check_user_name"/> <input id = "input-check-name" onkeyup="checkUser()"/> 	<br/>
	<div id = "user_exists" style="color: #3ADF00; display: none"><fmt:message key="test.label.user_exists"/></div><div id = "user_not_exists" style="color: #FF0000; display: none"><fmt:message key="test.label.user_not_exists"/></div>
	
</div>
	

<script type="text/javascript">


	function insertUser(){		
		
		var name = document.getElementById("input-add-name").value;	
		
		TestAjaxService.addUser(name);
		
		alert (name + '<fmt:message key="test.alert.insert_user_name_success"/>');  
	}

	
	
	function checkUser(){
		
		var name = document.getElementById("input-check-name").value;
	
		TestAjaxService.existsUser(name,{
			callback:function(res) {				
			   if (res == true){
				   document.getElementById('user_exists').style.display="inline"; 
				   document.getElementById('user_not_exists').style.display="none";
			   } else{
				   document.getElementById('user_exists').style.display="none"; 
				   document.getElementById('user_not_exists').style.display="inline";
			   }
			}
		});
		
	}
</script>

<script type='text/javascript' src='<c:url value="/dwr/interface/TestAjaxService.js" />'></script>
	
	
