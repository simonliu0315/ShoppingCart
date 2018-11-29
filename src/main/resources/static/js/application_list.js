var headers = {
	"Accept" : "application/json, text/plain, */*"
};
var csrf_header = $("[name='_csrf_header']").attr("content");
headers[csrf_header] = $("[name='_csrf']").attr("content");

var application_list = {
	init : function() {
		var formData = {};
	},
	reload : function() {
		var formData = {};
		var url = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "" +contextRoot + "/products/search?category=4";
		if ($("#orderby").val() != null) {
			url += "orderby="+$("#orderby").val();
		}
		if ($("#q").val() != null) {
			url += "&p="+$("#q").val();
		}
		
		if ($("#pageSize").val() != $('#pageSize_1 :selected').val()) {
			url += "&pageSize="+ $('#pageSize_1 :selected').val();
			url += "&page=1";
		} else {
			url += "&page="+$("#page").val();
		}
		
		window.location.href = url;
	}
}
$(function() {
	application_list.init();
	
});
