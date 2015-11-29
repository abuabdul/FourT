/* Menu Toggle Script */
 
$(function(){
	
	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
	});
	
    $('#tasktracker').DataTable();
});