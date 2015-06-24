/*
 * Framework
 */

// Kenteken: https://api.datamarket.azure.com/opendata.rdw/VRTG.Open.Data/v1/KENT_VRTG_O_DAT('xnrr85')?$format=json
var app = function() {
  function inlog() {
    var usertype = $("#user-type").val();
    $("#user-type2").val(usertype);
    $("#type-user").fadeOut(900, function() {
      $("#inlog").fadeIn(900);
    });
  }

  function gaterug() {
    $("#inlog").fadeOut(900, function() {
      $("#type-user").fadeIn(900);
    });
  }

  function artikelSelectGroupLeft() {
    var selectedItem = $("#rightValues option:selected");
    $("#artikelbox").find("input").removeClass("currSelect");
    $("#leftValues").append(selectedItem);
    _removeAantalInput(selectedItem.val());
  }

  function artikelSelectGroupRight() {
    var selectedItem = $("#leftValues option:selected");
    var artikelen = $("#artikelbox");

    if (_artikelAantal(selectedItem.val())) {
      var selected = $("#artikelbox").find("input")
          .hasClass("currSelect");

      if (selected) {
        $("#artikelbox").find("input").removeClass("currSelect");
      }
      $("#artikelbox").append(
          '<input type="text" class="currSelect" data-name="'
              + selectedItem.val() + '" name="data['
              + selectedItem.val() + '][aantal]">');
    }

    $("#rightValues").append(selectedItem);
  }

  function showAantal() {
    var selected = $("#rightValues option:selected");
    _hideAantal(selected.val());
  }

  function kentekenParsen() {
    var kenteken = $("#kenteken").val();
    kenteken = kenteken.replace(/\W+/g, "");
    kenteken = kenteken.toUpperCase();
    $("#kenteken").val(kenteken);

    if (kenteken.length !== 6) {
      $("#kenteken").parent().parent().addClass("has-error");
    } else {
      $("#kenteken").parent().parent().removeClass("has-error");
    }
  }

  function kentekenOpzoeken() {
    kentekenParsen();
    var kenteken = $("#kenteken").val();

    $
        .ajax(
            {
              method : "GET",
              url : "https://api.datamarket.azure.com/opendata.rdw/VRTG.Open.Data/v1/KENT_VRTG_O_DAT('"
                  + kenteken + "')?$format=json"
            }).done(
            function(data) {
              var merk = "";
              var handelsnaam = "";

              if ('d' in data && 'Merk' in data.d) {
                merk = data.d.Merk;
              }
              if ('d' in data && 'Handelsbenaming' in data.d) {
                handelsnaam = data.d.Handelsbenaming;
              }

              if ((merk.length + handelsnaam.length) > 0) {
                $("#kentekendata").text(
                    "Merk: " + merk + ", Model: "
                        + handelsnaam);

                // Velden vervangen met kentekendata
                $("#merk").val(merk);
                $("#model").val(handelsnaam);
              } else {
                $("#kentekendata").text("Onbekend...");
              }
            });
  }

  function _artikelAantal(name) {
    var find = $("#artikelbox").find("input");
    var ok = true;

    if (find.length === 0) {
      return true;
    }

    find.each(function(i, val) {
      var inputName = find.eq(i).data("name");
      if (name === inputName) {
        ok = false;
        return false;
      }
    });

    if (ok) {
      return true;
    }
  }

  function _removeAantalInput(name) {
    var find = $("#artikelbox").find("input");
    find.each(function(i, val) {
      var inputName = find.eq(i).data("name");
      if (name === inputName) {
        find.eq(i).remove();
      }
    });
  }

  function _hideAantal(name) {
    var find = $("#artikelbox").find("input");

    $("#artikelbox").find("input").removeClass("currSelect");
    find.each(function(i, val) {
      var inputName = find.eq(i).data("name");
      if (name === inputName) {
        find.eq(i).addClass('currSelect');
      }
    });
  }

  function autolijst() {
    $.ajax({
      method : "GET",
      url : "ajaxgetklantautos",
      data : {
        klant : $("#klantlijst option:selected").val()
      }
    }).done(
        function(data) {
          $("#autolijst").html("");
          var html = "";

          $.each(data, function(i, auto) {
            html += "<option value='" + auto.kenteken + "'>";
            html += auto.merk + " - " + auto.model + " ("
                + auto.kenteken + ")";
            html += "</option>";
          });

          $("#autolijst").html(html);
        });
  }

  function zoekPlek() {
    $("#hide-parkeerplek").fadeOut(300);
    if (_zoekPlekInput()) {
      _AjaxGetPlekken();
    }

  }

  function _AjaxGetPlekken() {
    $.ajax({
      type : 'POST',
      url: 'ajaxgetplekken',
      data : {
        vanDatum : $("#van").val(),
        totDatum : $("#tot").val(),
        vanTijdstip : $("#vantijd").val(),
        totTijdstip : $("#tottijd").val()
      },
      success: function(data) {
        if(data.length === 0) {
          $("#hide-parkeerplek").fadeOut(300);
          $("#hide-error").css("display", "block");
          $("#hide-error .controls").html("Geen plekken gevonden")
        } else {
          $("#hide-error").css("display", "none");
          _setPlekken(data);
        }
      }
    })
  }

  function _setPlekken(data) {
    var $select = $("#parkeerplek-select");
    var html = "";

    data.forEach(function(val, i) {
      if(i === 0) {
        $("#inputPlek").val(data[i].plek);
        $("#inputRij").val(data[i].rij);
      }
      html += "<option data-rij="+data[i].rij+" data-plek="+data[i].plek+" >Rij: "+data[i].rij+" Plek: "+data[i].plek+"</optiob>";
    });

    $select.html(html);
    $("#aantal-plekken").html("Aantal plekken: " + data.length);
    $("#hide-parkeerplek").fadeIn(500);
  }

  function _zoekPlekInput() {
    var $vanDatum = $("#van")
    var $totDatum = $("#tot")
    var $vanTijd = $("#vantijd")
    var $totTijd = $("#tottijd")
    var error = {
      border : "3px solid #ff0000"
    };
    var ok = {
      border : "0px"
    };
    var arr = [ $vanDatum, $totDatum, $vanTijd, $totTijd ];
    var tmp = [];

    arr.forEach(function(val, i) {
      if (arr[i].val() === "") {
        arr[i].css(error);
        tmp.push("err");
      } else {
        arr[i].css(ok);
      }
    })

    if (tmp.length === 0) {
      return true;
    }

    return false;

  }

  function veranderPlek() {
    var selected = $("#parkeerplek-select option:selected");
    var rij = selected.data("rij");
    var plek = selected.data("plek");
    var inputRij = $("#inputRij");
    var inputPlek = $("#inputPlek");

    inputRij.val(rij);
    inputPlek.val(plek);
  }

  function zoekFactuurOnderdelen() {
    var klantnaam = $("#factuur-klant").val();

    $.ajax({
      type : 'POST',
      url: 'ajaxfactuuronderdeel',
      data: {
        klant: klantnaam
      }
    }).done(function(data) {
    	console.log(data);
      _setFactuurOnderdelen(data);
    });

  }
  
  function resetZoekFactuur() {
      $("#factuur-items-klus").html('');
      $("#factuur-items-reservering").html('');
      $("#hide-error").css("display", "none");
      $("#factuur-onderdeel-klus").css("display", "none");
      $("#factuur-onderdeel-reservering").css("display", "none")
      $("#factuur-onderdeel-search").css("display", "block");
  }

  function _setFactuurOnderdelen(data) {
    var klussen = data[0];
    var reservering = data[1];
    var html = "";
    var htmlklus = "";
    var htmlreservering = "";
    var error = false;

    if(klussen.length === 0 && reservering.length === 0) {
      error = true;
      html = "Geen onderdelen kunnen vinden";

    }

    if(klussen.length) {
      klussen.forEach(function(val, i){
        var $string = klussen[i]['id'];
        var item = '<option value="'+$string+'">Klus: "'+klussen[i]['omschrijving']+'"</option>';
        htmlklus += item;
      });
    }

    if(reservering.length) {
      reservering.forEach(function(val, i){
        var $string = reservering[i]['id'];
        var item = '<option value="'+$string+'">Reservering: "'+reservering[i]['omschrijving']+'"</option>';
        htmlreservering += item;
      });
    }
    
    if(!error) {
      $("#hide-error").css("display", "none");
      $("#factuur-items-klus").html(htmlklus);
      $("#factuur-items-reservering").html(htmlreservering);
      $("#factuur-onderdeel-search").fadeOut(500,function(){
        $("#factuur-onderdeel-klus").fadeIn(500);
        $("#factuur-onderdeel-reservering").fadeIn(500);
      });
      $("#factuur-add").prop("disabled", false);
    } else {
      $("#factuur-add").prop("disabled", true);
      $("#hide-error .controls").find(".alert").html(html);
      $("#hide-error").fadeIn(500);
    }

  }

  function alertFirst(link) {
    var obj = {
      title : "Let op!",
      text : "Weet u zeker dat u het proces wilt afbreken?",
      type : "warning",
      confirmButtonText : "Ja",
      showCancelButton : true,
      cancelButtonText : "Annuleren",
      inputType : "hidden"
    };
    swal(obj, function() {
      window.location.replace(link);
    });
  }
  
  function genereerWachtwoord() {
	  var random = Math.random().toString(36).slice(-8);
	  
	  $("#wachtwoord, #wachtwoord2").val(random);
  }
  
  ;

  return {
    inlog : inlog,
    gaterug : gaterug,
    artikelSelectGroupLeft : artikelSelectGroupLeft,
    artikelSelectGroupRight : artikelSelectGroupRight,
    showAantal : showAantal,
    autolijst : autolijst,
    alertFirst : alertFirst,
    kentekenParsen : kentekenParsen,
    kentekenOpzoeken : kentekenOpzoeken,
    zoekPlek : zoekPlek,
    veranderPlek : veranderPlek,
    zoekFactuurOnderdelen : zoekFactuurOnderdelen,
    resetZoekFactuur : resetZoekFactuur,
    genereerWachtwoord : genereerWachtwoord
  }

}();

/*
 * EventListener
 */
$(function() {
  $(".next-step").click(function() {
    app.inlog();
  });

  $("#goback").click(function() {
    app.gaterug();
  });

  $("#factuur-voorbeeld").click(function() {
    window.print();
  });

  $("#btnLeft").click(function() {
    app.artikelSelectGroupLeft();
  });

  $("#btnRight").click(function() {
    app.artikelSelectGroupRight();
  });

  $("#rightValues").change(function() {
    app.showAantal();
  });

  $(".promise-me").click(function(e) {
    e.preventDefault();
    app.alertFirst($(this).attr("href"));
  });

  if ($("#kenteken").length > 0) {
    $("#kenteken").change(function() {
      app.kentekenParsen();
    })
    $("#kentekenopzoeken").click(function() {
      app.kentekenOpzoeken();
    })
  }

  if ($("#autolijst").length > 0) {
    app.autolijst();
    $("#klantlijst").change(function() {
      app.autolijst();
    });
  }

  $("#zoekPlek").click(function(e) {
    e.preventDefault();
    app.zoekPlek();
  });

  $("#factuur-search-items").click(function(e){
    e.preventDefault();
    app.zoekFactuurOnderdelen();
  });

  $("#parkeerplek-select").change(function(){
    app.veranderPlek();
  });
  
  $("#factuur-klant").change(function(){
	 app.resetZoekFactuur(); 
  });
  
  $("#genereerwachtwoord").click(function() {
	 app.genereerWachtwoord();
  });

});
