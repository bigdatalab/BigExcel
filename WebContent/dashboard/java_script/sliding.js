
$(document).ready(function(){
  $("#flip").click(function(){
    $("#panel").slideToggle("slow");   
    $("#panel_hdfs").slideUp("slow");
  });
});


$(document).ready(function(){
	  $("#flip_hdfs").click(function(){
	    $("#panel_hdfs").slideToggle("slow");    
	    $("#panel").slideUp("slow");
	  });
	});
