/**
 * 
 */
$(document).ready(function()
{
    console.log('document ready');

    // MAKE SURE YOUR SELECTOR MATCHES SOMETHING IN YOUR HTML!!!
    $("a#trigger").each(function() {
        console.log('<a>', this);

        $(this).qtip({
           content: {
               text: function(event, api) {
                   console.log('qtip content text');

                   $.ajax({
                       url: "/project2/window.jsp?title=" + api.elements.target.attr('name'),
                    	   //api.elements.target.attr('href'), // Use href attribute as URL
                    	   dataType : "text"
                   })
                   .then(function(content) {
                       // Set the tooltip content upon successful retrieval
                       api.set('content.text', content);
                   }, function(xhr, status, error) {
                       // Upon failure... set the tooltip content to error
                       api.set('content.text', status + ': ' + error);
                   });
       
                   return 'Loading...'; // Set some initial text
               }
           },
           style: 'qtip-wiki'
           
        });
    });
});