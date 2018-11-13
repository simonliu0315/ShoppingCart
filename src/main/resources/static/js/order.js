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
	}
}
$(function() {
	order.init();
});
