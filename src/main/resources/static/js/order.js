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
	informAtmPayment : function() {
		var formData = {
				"orderNo" : $("#orderNo").val(), 
				"lastFiveAccountNo" : $("#lastFiveAccountNo").val(), 
				"accountName" : $("#accountName").val()
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
	},
	informAtmPaymentModal : function(orderNo) {
		$("#orderNo").val(orderNo);
		$("#infoAtmModalCenter").modal({
			show: true
		});
	},
	sendAtmInfo : function() {
		$("#lastFiveAccountNo").val($("#lastFiveAccountNoTmp").val());
		$("#accountName").val($("#accountNameTmp").val());
		order.informAtmPayment();
		$("#infoAtmModalCenter").modal('hide');
	}
}
$(function() {
	order.init();
});
