/* Menu Toggle Script */

$(function() {

	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
	});

	 var tasktracker = $('#tasktracker').DataTable( {
	        "columnDefs": [ {
	            "searchable": false,
	            "orderable": false,
	            "targets": 0
	        } ],
	        "order": [[ 1, 'asc' ], [ 2, 'asc' ]]
	    } );
	 
	    tasktracker.on( 'order.dt search.dt', function () {
	        tasktracker.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
	            cell.innerHTML = i+1;
	        } );
	    } ).draw();
});