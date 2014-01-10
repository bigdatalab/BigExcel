
$(document).on('click', function(){
	    
	$("#load_form\\:load_link").click(function(){
		$("#right_side_div").load("./loaddata/load_data.xhtml");
	    
        });
	});


$(document).on('click' , function(){
    $("#load_form_view\\:load_link_view").click(function(){
    	$("#right_side_div").load("./viewdata/viewdatarich.xhtml");
    
    });
});

