var headers = {
	"Accept" : "application/json, text/plain, */*"
};
var csrf_header = $("[name='_csrf_header']").attr("content");
headers[csrf_header] = $("[name='_csrf']").attr("content");

var cart = {
	init : function() {
		var formData = {};
	},
	goOrder: function() {
		
	},
	changeQty: function(productId, qty, color) {
		var formData = {
				"productId" : productId, "quantity" : qty.value , "resetQuantity": true, "color": color
			};
			$.ajax({
				type : "POST",
				url : contextRoot + "/user/addCart",
				dataType : "json",
				headers : headers,
				contentType: "application/json; charset=utf-8",
				data : JSON.stringify(formData),
				async : false,
				success : function(data) {
					var url = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "" +contextRoot + "/cart/";
					window.location.href = url;
				},
				error : function(e) {
					var obj = jQuery.parseJSON(e.responseText);
					console.log(obj.region);
				}

			});
	}
}
$(function() {
	cart.init();
});
