
/*
 * 
 */
function initDataTable(id,size){
	$('#'+id).dataTable( {
		"bJQueryUI": true,
	    "bFilter": true,
	    "bPaginate": true,
	    "bSort": false,
	    "bLengthChange": false,
	    "iDisplayLength": size,
		"sPaginationType": "full_numbers"	
	});
}

/*
 * 
 */
function initDataTableButton(id,urlButton,valueButton,size){
	initDataTable(id,size);
	     
	 	   
	   $('<a />')
	   		.addClass('button')
	        .css({'margin-left' : '1em'})
	   		.attr("href", urlButton)
	   		.html(valueButton)
		    .appendTo($('.dataTables_filter'));
	
}