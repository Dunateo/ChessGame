var interval = 1000; // 1000 = 1 second, 3000 = 3 seconds
 function doAjax() {
     $.ajax({ type: 'GET',
         url: window.location.origin+'/update/timer/'+$( "#id-partie" ).val(),
         dataType: 'json',
         success: function (data) {
            console.log("ca marche ");

             console.log(data.responseText);
              },
         complete: function (data) { // Schedule the next
             $(".updatable").empty();
             $(".updatable").append(data.responseText);
          setTimeout(doAjax, interval); } });
 } setTimeout(doAjax, interval);


$(document).ready(function() {

        doAjax();


});