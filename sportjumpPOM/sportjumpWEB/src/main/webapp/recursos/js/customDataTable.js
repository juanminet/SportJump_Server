
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

function initSimpleDataTable(id,size){
	var tables = $.fn.dataTable.fnTables(true);

	$('#'+id).dataTable( {
		"bJQueryUI": true,
	    "bFilter": false,
	    "bPaginate": false,
	    "bSort": false,
	    "bLengthChange": false,
	    "iDisplayLength": size,		
	    'sDom': 't' 
	});
}



function initSimpleDataTableButton(id, valueButton, size, jsFunction){	
	initSimpleDataTable(id,size);
	addButtonToTable(valueButton, jsFunction);
}

function initDataTableButton(id,urlButton,valueButton,size){
	initDataTable(id,size);
	addButtonToTable(valueButton, function (){location.ref = urButton;});	
}

function initSimpleSortableDataTableButton(id, valueButton, size, jsFunction,jsFunctionSort,jsFunctionStop){	
	initSimpleDataTable(id,size);
	addButtonToTable(id, valueButton, jsFunction);
	convertSortableTable(id,jsFunctionSort,jsFunctionStop) ;
}


/*
 * 
 */


function addButtonToTable(idTable, valueButton, jsFunction) {
	$('<a />')
		.addClass('button')
		.css({	'margin-left' : '1em'})
		.html(valueButton)
		.appendTo($('#' + idTable + ' th'))
		.bind('click', jsFunction);
}

function convertSortableTable(idTable,jsFunctionSort,jsFunctionStop) {
	$('#' + idTable + ' tbody').sortable({
		axis : "y",
		sort : jsFunctionSort,
		stop : jsFunctionStop					
	});
}




