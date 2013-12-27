
/*
 * 
 */
function initDataTable(id,size){
	var oTable = $('#'+id).dataTable( {
		"bJQueryUI": true,
	    "bFilter": true,
	    "bPaginate": true,
	    "bSort": false,
	    "bLengthChange": false,
	    "iDisplayLength": size,
		"sPaginationType": "full_numbers"	
	});
	
	return oTable;
}

function initSimpleDataTable(id,size){
	var tables = $.fn.dataTable.fnTables(true);

	var oTable = $('#'+id).dataTable( {
		"bJQueryUI": true,
	    "bFilter": false,
	    "bPaginate": false,
	    "bSort": false,
	    "bLengthChange": false,
	    "iDisplayLength": size,		
	    'sDom': 't' 
	});
	
	return oTable;
}


function initSimplePaginationDataTable(id,size){
	var oTable = $('#'+id).dataTable( {
		"bJQueryUI": true,
	    "bFilter": true,
	    "bPaginate": true,
	    "bSort": false,
	    "bLengthChange": false,
	    "iDisplayLength": size,
		"sPaginationType": "two_button"	
	});
	
	return oTable;
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

function initSelectableDataTable(idTable, size){
	var oTable = initSimplePaginationDataTable(idTable);
	setSelectRow(idTable, oTable);
	
	return oTable;
}


/*
 * 
 */


function addButtonToTable(idTable, valueButton, jsFunction) {
	$('<a />')
		.addClass('button')
		.css({	'margin-left' : '1em'})		
		.appendTo($('#' + idTable + ' th'))
		.bind('click', jsFunction)
		.append($('<span>')
					.html(valueButton)
		);
}

function convertSortableTable(idTable,jsFunctionSort,jsFunctionStop) {
	$('#' + idTable + ' tbody').sortable({
		axis : "y",
		sort : jsFunctionSort,
		stop : jsFunctionStop					
	});
}



function setSelectRow(idTable, oTable){
	$("#" + idTable +" tbody").click(function(event) {
		var anSelected = fnGetSelected( oTable );
		var selected = event.target.parentNode;
		
		$(oTable.fnSettings().aoData).each(function (){				
			$(this.nTr).removeClass('row_selected');
		});
		
		if (anSelected[0] != selected){					
			$(event.target.parentNode).addClass('row_selected');
		}			
	});	
}

function fnGetSelected( oTableLocal )
{
	var aReturn = new Array();
	var aTrs = oTableLocal.fnGetNodes();
	
	for ( var i=0 ; i<aTrs.length ; i++ )
	{
		if ( $(aTrs[i]).hasClass('row_selected') )
		{
			aReturn.push( aTrs[i] );
		}
	}
	return aReturn;
}







