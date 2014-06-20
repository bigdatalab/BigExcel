
$(document).ready(function(){
	$(document).on("click", "#load_form\\:load_link" ,function(){
	//$("#load_form\\:load_link").click(function(){		
		$("#right_side_div").load("./loaddata/loaddata.xhtml");
        });
	});


$(document).ready(function(){
	$(document).on("click", "#load_form_view\\:load_link_view" ,function(){
    //$("#load_form_view\\:load_link_view").click(function(){
    		$("#right_side_div").load("./viewdata/viewdatarich.xhtml");
    	
    	//alert($("#right_side_div").length);
    
    });
});


$(document).ready(function(){
	$(document).on("click", "#convert_form_view\\:convert_link" ,function(){
    //$("#load_form_view\\:load_link_view").click(function(){
    		$("#right_side_div").load("./loaddata/loadtxtfile.xhtml");
    	
    	//alert($("#right_side_div").length);
    
    });
});


//for view data javascript

$(document).ready(function(){
	$(document).on("click", "#table_view_form\\:view_table_btn" ,function(){
	$("#table_view_div").load("./viewdata/PartialLoadPaginatedData.xhtml");
	             //alert($("#table_view_div_spinner").length);
	//alert("clicked");
	    
	});
	});


//for query data
$(document).ready(function(){
	$(document).on("click", "#hive_query_form\\:hive_query_link" ,function(){		
	$("#right_side_div").load("./analysis/queryhive.xhtml");
	    
	});
	});



//for utility functions
$(document).ready(function(){
	$(document).on("click", "#utility_function_form\\:utility_function_link" ,function(){		
	$("#right_side_div").load("./analysis/utilityfunctions.xhtml");
	    
	});
	});



//for Interactive analytics
$(document).ready(function(){
	$(document).on("click", "#range_query_form\\:range_query_link" ,function(){		
	$("#right_side_div").load("./analysis/customdatatable.xhtml");
	});
	});


//for Custom Map Reduce
$(document).ready(function(){
	$(document).on("click", "#custom_map_reduce_form\\:custom_map_reduce" ,function(){		
	$("#right_side_div").load("./analysis/ModuleRespostirySelection.xhtml");
	});
	});


    
//for graph display
//$(document).ready(function(){
//$(document).on("click", "#analysis\\:ajax_process_btn" ,function(){		
//	$("#right_side_div").load("./analysis/graphvisualization.xhtml");
//	});
//	});



//for range loading table
$(document).ready(function(){
	$(document).on("click", "#form_view_btn\\:ajax_view_btn" ,function(){		
	$("#right_side_div").load("./analysis/loadCustomDataTable.xhtml");
		
	//	$.ajax({
	//		  url: "./analysis/loadCustomDataTable.xhtml",
	//		  context: document.body,
	//		  success: function(){
			    //$(this).addClass("done");
	//			  $("#right_side_div").load("./analysis/loadCustomDataTable.xhtml");
	//		  }
	//		});		    
	});
});



$(document).ready(function(){
	$(this).on("click", "#table_view_form\\:reload_table_btn" ,function(){
	$("#table_view_div").load("./viewdata/PartialLoadPaginatedData.xhtml");	
	    
	});
	});

/*$(document).ready(function (){	
	//$(this).on("click", "#table_view_form\\:view_table_btn" ,function(){
		
	$('#table_view_div_spinner').ajaxStart(function () {
		alert("spinner");
		$(this).show();
	}).ajaxStop(function () {
		$(this).hide();
	});	
});*/
//});


//for loading HDFS
$(document).ready(function(){
    
	$("#view_form_hdfs\\:view_hdfs").click(function(){		
		$("#right_side_div").load("./treeview/treeview.xhtml");
		
        });
	});


$(document).ready(function (){	
	$("#load_form\\:load_link").ajaxSend (function () {
		$('#div_spinner').show();
		//alert("ajax request");
	}).ajaxStop(function () {
		$('#div_spinner').hide();
	});	
});

$(document).ready(function (){
$( document ).ajaxComplete(function() {
	  //alert("ajax completes");
	});
});


$.ajax({
    
    global: 'true',
    ajaxSend: function() {
    	//alert("Hey its new");
    }
}); 
