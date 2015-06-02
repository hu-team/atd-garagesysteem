/*
 * Framework
 */
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
		alertFirst : alertFirst
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

	if ($("#autolijst").length > 0) {
		app.autolijst();
		$("#klantlijst").change(function() {
			app.autolijst();
		});
	}

});
