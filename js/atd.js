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

 return {
  inlog: inlog,
  gaterug: gaterug
 }

}();

$(function() {
  $(".next-step").click(function() {
    app.inlog();
  });

  $("#goback").click(function(){
    app.gaterug();
  });
});
