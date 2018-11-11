var headers = {
	"Accept" : "application/json, text/plain, */*"
};
var csrf_header = $("[name='_csrf_header']").attr("content");
headers[csrf_header] = $("[name='_csrf']").attr("content");

var product = {
	init : function() {
		var formData = {};
	},
	changeColor : function(id) {
		$("li[id^='color_']" ).removeAttr("class");
		$('#color_'+ id).attr("class", "active");
	}
}
$(function() {
	product.init();
});
