var headers = {
	"Accept" : "application/json, text/plain, */*"
};
var csrf_header = $("[name='_csrf_header']").attr("content");
headers[csrf_header] = $("[name='_csrf']").attr("content");

var checkout = {
	init : function() {
		var formData = {};
	},
	goOrder: function() {
		
	},
	changeQty: function(productId, qty) {
		var formData = {
				"productId" : productId, "quantity" : qty.value , "resetQuantity": true
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
					var url = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "" +contextRoot + "/checkout/";
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
	checkout.init();
});
