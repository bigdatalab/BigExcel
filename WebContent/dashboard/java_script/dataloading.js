
$(document).ready(function(){
	$('#message_upper:\\message_div').ajaxStart(function() {
		$(this).show();
	}).ajaxStop(function () {
		$(this).hide();
	});	
	
});