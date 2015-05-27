var app = function() {
  function inlog() {
    var usertype = $("#user-type").val();
    $("#user-type2").val(usertype);
    $("#type-user").fadeOut(900,function(){
      $("#inlog").fadeIn(900);
    });
  }

  function gaterug() {
    $("#inlog").fadeOut(900,function(){
      $("#type-user").fadeIn(900);
    });
  }
  
  function autolijst() {
	  $.ajax({
		  method: "GET",
		  url: "ajaxgetklantautos",
		  data: { klant: $("#klantlijst option:selected").val() }
		})
		  .done(function( msg ) {
		    alert( "Data Saved: " + msg );
		});
  }

 return {
  inlog: inlog,
  gaterug: gaterug,
  autolijst: autolijst
 }

}();

$(function() {
  $(".next-step").click(function() {
    app.inlog();
  });

  $("#goback").click(function(){
    app.gaterug();
  });
  
  if($("#autolijst").length > 0) {
	  $("#klantlijst").change(function() {
		 app.autolijst();
	  });
  }
});
