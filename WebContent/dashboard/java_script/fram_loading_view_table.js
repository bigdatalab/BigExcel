
/*//loading frame in two the division
$(document).on("click", '#table_view_form\\:view_table_btn' ,function(event){
    $("#table_view_form\\:view_table_btn").click(function(){
    $("#table_view_div").load("./loadtable_rich.xhtml");	
    alert("clicked");
        
    });*/
/*});*/


//loading frame in two the division
//$(document).ready(function(){
    $("#table_view_form\\:view_table_btn").click(function(){
    $("#table_view_div").load("./loadtable_rich.xhtml");	
    alert("clicked");
        
    });
//});


/*//for view table spinner
$(document).ready(function(){
	$('#table_view_div_spinner').ajaxStart(function () {		
		$(this).show();
	}).ajaxStop(function () {
		$(this).hide();
	});	
	
});*/


//for view table spinner
$(document).ready(function (){
	$('#table_view_div_spinner').ajaxStart(function () {		
		$(this).show();
	}).ajaxStop(function () {
		$(this).hide();
	});	
	
});
