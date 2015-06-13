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
		
		if(kenteken.length !== 6) {
			$("#kenteken").parent().parent().addClass("has-error");
		}else{
			$("#kenteken").parent().parent().removeClass("has-error");
		}
	}
	
	function kentekenOpzoeken() {
		kentekenParsen();
		var kenteken = $("#kenteken").val(); 
		
		$.ajax({
			method : "GET",
			url : "https://api.datamarket.azure.com/opendata.rdw/VRTG.Open.Data/v1/KENT_VRTG_O_DAT('" + kenteken + "')?$format=json"
		}).done(
				function(data) {
					var merk = "";
					var handelsnaam = "";
					
					if('d' in data && 'Merk' in data.d) {
						merk = data.d.Merk;
					}
					if('d' in data && 'Handelsbenaming' in data.d) {
						handelsnaam = data.d.Handelsbenaming;
					}
					
					if((merk.length + handelsnaam.length) > 0) {
						$("#kentekendata").text("Merk: " + merk + ", Model: " + handelsnaam);
						
						// Velden vervangen indien leeg
						if($("#merk").val().length == 0) $("#merk").val(merk);
						if($("#model").val().length == 0) $("#model").val(handelsnaam);
					}else{
						$("#kentekendata").text("Onbekend...");
					}
					
					console.log(data);
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
		kentekenOpzoeken : kentekenOpzoeken
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
	
	if($("#kenteken").length > 0) {
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

});
