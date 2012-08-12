<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	errorUsuarioVacio='<fmt:message key="error.autenticacion.usuario.vacio"/>';
	errorPasswordVacio='<fmt:message key="error.autenticacion.password.vacio"/>';
</script>
<script type="text/javascript" src="<c:url value="/recursos/js/login.js"/>"></script>

<form action="<c:url value="j_security_check" />" method="post">
	<!-- Caja login -->
	<div class="caja-login">
	    <!--esquinas redondeadas sup-->
	    <div class="cajaTop"><div class="cajaTopLeft"></div><div class="cajaTopRight"></div></div>
	        <div class="cajaCen">
	            <div class="mod_home_bottom">
	                <!--esquinas redondeadas sup-->
	                <div class="cajaTop"><div class="cajaTopLeft"></div><div class="cajaTopRight"></div></div>
	                    <div class="mod_home">
	                        <div class="mod_home_formulario">
	                        <span class="titulo-log"><fmt:message key="login.form.mensaje"/></span>
	                            <!--esquinas redondeadas sup-->
	                            <div class="cajaTop"><div class="cajaTopLeft"></div><div class="cajaTopRight"></div></div>
	                            
	                            
	                            <div id="capaLogin" class="cajaCen" <c:if test="${not empty param.login_error}">style="display:none;"</c:if>>
	                                <fieldset>
	                                    <legend>LOGIN</legend>
                                        <label for="usuario">
                                        	<span><fmt:message key="login.form.user"/></span>
                                        	<span class="input_home">
                                            	<input class="user" name="j_username" id="usuario" maxlength="23" type="text">
                                            </span>
                                        </label>
                                        <label for="password">
											<span><fmt:message key="login.form.password"/></span>
											<span class="input_home">
												<input type="password" maxlength="23" id="password" name="j_password" class="password">
											</span>
										</label>
	                                </fieldset>
	                            </div>
	                            
	                            <div class="login-error" <c:if test="${empty param.login_error}">style="display:none;"</c:if>>
		                            <div class="txtMensaje error">
		                                <h2>Acceso <span>denegado</span></h2>
		                                <p>No se ha podido iniciar la sesión.</p>
		                            </div>             
		                       		<p>Inténtelo de nuevo con un ID de usuario y una contraseña válidos.</p>
		                        </div>
	                            
	                            
	                            <!--esquinas redondeadas inf-->
	                            <div class="cajaPie"><div class="cajaPieLeft"></div><div class="cajaPieRight"></div></div>
	                            <div class="boton">
	                                <input class="btn_aceptar" name="aceptar" id="loginButton" value="Aceptar" type="submit">
	                            </div>
	                        </div>
	                    </div>
	                <!--esquinas redondeadas inf-->
	                <div class="cajaPie">
	                        <div class="cajaPieLeft"></div>
	                        <div class="cajaPieRight"></div>
	                    </div>
	            </div>
	        </div>
	    <!--esquinas redondeadas inf-->
	    <div class="cajaPie"><div class="cajaPieLeft"></div><div class="cajaPieRight"></div></div>
	</div>
	<!-- Caja login -->  

</form>

<!-- 
<div class="caja">
	<div class="cajaTop">
		<div class="cajaTopLeft"></div>
		<div class="cajaTopRight"></div>
	</div>
	<div class="cajaCen">
		<div class="cajaInt">
	
			<c:if test="${not empty param.login_error}">
				<div id="capaValidacionServidor" class="validationErrors">
					<p>
						<fmt:message key="error.autenticacion.credenciales"/>
					</p>
				</div>
			</c:if>
			
			<div id="capaValidacionCliente" class="validationErrors no">
				<p>
					<fmt:message key="error.autenticacion.credenciales.vacias"/>
				</p>
				<p>
					<fmt:message key="error.sesion.invalidada"/>
				</p>
			</div>
		
			<form action="<c:url value="j_security_check" />" method="post">
	
  
			</form>
			
		      	<!-- 
		   	<div class="cajaGrupo edicion">
		        <form action="<c:url value="j_security_check" />" method="post">
		        	<fieldset>
		        		<legend><fmt:message key="login.form.mensaje"/></legend>
		        		<p><label for="username" class="usu"><fmt:message key="login.form.user"/></label>
		                <input name="j_username" type="text" id="userName" maxlength="20"/></p>
		                <p><label for="pwd" class="pass"><fmt:message key="login.form.password"/></label>
		                <input name="j_password" type="password" id="pwd" maxlength="16" /></p>
		                <span class="btn">
		       				<input name="submit" id="loginButton" type="submit" value="Login"/>
		       			</span>
		        	</fieldset>
		   		</form>
		  	</div>
		  	
			<div id="capaEtiquetas">
				<a href="#"><img id="imagenXHTML" src="<common:cfg name="web.resources.url.http"/>/img/valid-xhtml10.png" alt="Valid XHTML 1.0 Strict"/></a>
			</div> 
		</div>
	</div>
	<div class="cajaPie">
		<div class="cajaPieLeft"></div>
		<div class="cajaPieRight"></div>
	</div>
</div><!-- caja-->