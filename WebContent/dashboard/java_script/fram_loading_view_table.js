
//loading frame in two the division
$(document).ready(function(){
    $("#table_view_form\\:view_table_btn").click(function(){
    $("#table_view_div").load("./loadtable_rich.xhtml");	
    });
});



/*$(document).ready(function(){
(this).on("click", "#table_view_form\\:view_table_btn" ,function(){
$("#table_view_div").load("./loadtable_rich.xhtml");	
    
});
});*/

$(document).ready(function(){
    $(this).on("click", "#table_view_form\\:reload_table_btn" ,function(){
	$("#table_view_div").load("./loadtable_rich.xhtml");	
	    
	});
	});


//for view table spinner
$(document).ready(function (){
	
	$('#table_view_div_spinner').ajaxStart(function () {
		$(this).show();
	}).ajaxStop(function () {
		$(this).hide();
	});	
	
});

