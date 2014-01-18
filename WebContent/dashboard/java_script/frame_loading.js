
$(document).ready(function(){
	    
	$("#load_form\\:load_link").click(function(){
		
		$("#right_side_div").load("./loaddata/loaddatarich.xhtml");
		
        });
	});


$(document).ready(function(){
    $("#load_form_view\\:load_link_view").click(function(){
    		$("#right_side_div").load("./viewdata/viewdatarich.xhtml");
    	
    	//alert($("#right_side_div").length);
    
    });
});


//for view data javascript

$(document).ready(function(){
	$(this).on("click", "#table_view_form\\:view_table_btn" ,function(){
	$("#table_view_div").load("./viewdata/loadtable_rich.xhtml");
	             //alert($("#table_view_div_spinner").length);
	    
	});
	});

$(document).ready(function(){
	$(this).on("click", "#table_view_form\\:reload_table_btn" ,function(){
	$("#table_view_div").load("./viewdata/loadtable_rich.xhtml");	
	    
	});
	});

$(document).ready(function (){	
	//$(this).on("click", "#table_view_form\\:view_table_btn" ,function(){
		
	$('#table_view_div_spinner').ajaxStart(function () {
		alert("spinner");
		$(this).show();
	}).ajaxStop(function () {
		$(this).hide();
	});	
});
//});
