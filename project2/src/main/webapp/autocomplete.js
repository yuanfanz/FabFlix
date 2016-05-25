/**
 * 
 */
$(document).ready(function() {
	$(function() {
		$("#movietitle").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "ajaxServlet",
					type : "POST",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(data) {
						/*var $ul = $("<ul>").appendTo($("#movietitle")); // Create HTML <ul> element and append it to HTML DOM element with ID "somediv".
				        $.each(data, function(key, value) { // Iterate over the JSON array.
				            $("<li>").text(value).appendTo($ul);
				        });*/
						response(data);
				        
				        /*$("ul#results").html(data);*/
					}
					
					
				});
			}
		});
	});
});