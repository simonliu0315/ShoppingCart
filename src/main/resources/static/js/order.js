var headers = {
	"Accept" : "application/json, text/plain, */*"
};
var csrf_header = $("[name='_csrf_header']").attr("content");
headers[csrf_header] = $("[name='_csrf']").attr("content");

var order = {
	init : function() {
		var formData = {};
	},
	goPayment : function(orderNo) {
		$("#orderNo").val(orderNo);
		$("#payment-form").submit();
	}, 
	informAtmPayment : function(orderNo) {
		var formData = {
				"orderNo" : orderNo
			};
			$.ajax({
				type : "POST",
				url : contextRoot + "/payment/infoPayment",
				dataType : "json",
				headers : headers,
				contentType: "application/json; charset=utf-8",
				data : JSON.stringify(formData),
				async : false,
				success : function(data) {
					if (data) {
						var url = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "" +contextRoot + "/member/order/list";
						window.location.href = url;
					} else {
						$('#informAtmPayment').text("通知失敗，請點我。");
					}
				},
				error : function(e) {
					var obj = jQuery.parseJSON(e.responseText);
					console.log(obj.region);
				}

			});
	}
}
$(function() {
	order.init();
});
