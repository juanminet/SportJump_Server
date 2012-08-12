$(document).ready(function() {
   
	$("#loginButton").click(function(e){
		if($(".login-error:visible")[0] != null ){
			e.preventDefault();
			$($(".login-error")[0]).css('display','none');
	    	$("#capaLogin").fadeIn('slow');
		}else{
		    if($('#usuario').val().length==0 || $('#password').val().length==0){
		    	e.preventDefault();
		    	$('#password').val('');
		    	$("#capaLogin").css('display','none');
		    	$($(".login-error")[0]).fadeIn('slow');
		    }
		}
   	});
});
