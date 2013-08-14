
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

	   $('<div />').addClass('button')
	   		.css({'margin-left' : '1em'})
	   		.attr({'id' : 'new_group'})
	   		.prependTo($('.dataTables_filter'));
	     
	 	   
	   $('<a />')
	   		.attr("href", urlButton)
	   		.html(valueButton)
		    .appendTo($('#new_group'));
	
}