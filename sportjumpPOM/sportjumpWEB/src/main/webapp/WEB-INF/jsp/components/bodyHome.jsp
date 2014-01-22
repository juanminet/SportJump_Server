<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.body_home_header{
	text-align: center;
}
.body_home_container{
	text-align: center;
	line-height: 1.5em;
}
.body_home_img{
	height: 20em;
	margin: 2em;
}
</style>
<div id ="body_home">
	<div class="body_home_header">
		<img class="body_home_img" src="${pageContext.request.contextPath}/resources/img/carrera.jpg">
	</div>
	<div class="body_home_container">
	Android provides a rich application framework that allows you to build innovative apps and games for mobile devices in a Java language environment. The documents listed in the left navigation provide details about how to build apps using Android's various APIs.

If you're new to Android development, it's important that you understand the following fundamental concepts about the Android app framework:

Apps provide multiple entry points

Android apps are built as a combination of distinct components that can be invoked individually. For instance, an individual activity provides a single screen for a user interface, and a service independently performs work in the background.

From one component you can start another component using an intent. You can even start a component in a different app, such an activity in a maps app to show an address. This model provides multiple entry points for a single app and allows any app to behave as a user's "default" for an action that other apps may invoke.


	</div>

</div>
