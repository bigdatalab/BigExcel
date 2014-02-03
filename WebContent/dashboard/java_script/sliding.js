
$(document).ready(function(){
  $("#flip").click(function(){
    $("#panel").slideToggle("slow");   
    $("#panel_hdfs").slideUp("slow");
    $("#panel_analysis").slideUp("slow");
  });
});


$(document).ready(function(){
	  $("#flip_hdfs").click(function(){
	    $("#panel_hdfs").slideToggle("slow");    
	    $("#panel").slideUp("slow");
	    $("#panel_analysis").slideUp("slow");
	  });
	});


$(document).ready(function(){
	  $("#analysis_flip").click(function(){
	    $("#panel_analysis").slideToggle("slow");    
	    $("#panel_hdfs").slideUp("slow");
	    $("#panel").slideUp("slow");
	  });
	});