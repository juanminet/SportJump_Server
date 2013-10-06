
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

function initSimpleSortableDataTableButton(id, valueButton, size, jsFunction){	
	initSimpleDataTable(id,size);
	addButtonToTable(id, valueButton, jsFunction);
	convertSortableTable(id) ;
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

function convertSortableTable(idTable) {
	$('#' + idTable + ' tbody').sortable({
		axis : "y",
		revert : true,
		stop : function(event, ui) {			
			ui.item.css("background", "");
			refreshTable(idTable);

		},
		sort : function(event, ui) {

			// Set border colour of cell to red (this works)
			ui.placeholder.css("border", "1px solid #D19290");
			ui.item.css("background", "#F2E3E3");
		}
	});
}

function refreshTable(idTabla){
	$('#' + idTabla + " tbody tr").each(function (index) {
		$(this).removeClass( "even odd gradeU" );
		 if (index % 2 == 0){
			$(this).addClass("gradeU odd");
		 }else{
			$(this).addClass("gradeU even");
		 }
	});	
}


