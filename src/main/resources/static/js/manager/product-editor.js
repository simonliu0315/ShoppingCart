var headers = {
	"Accept" : "application/json, text/plain, */*"
};
var csrf_header = $("[name='_csrf_header']").attr("content");
headers[csrf_header] = $("[name='_csrf']").attr("content");

var product_editor = {
	init : function() {
		var formData = {};
	},
	deleteColor : function(productId, color) {
      	var formData = {
  			  "productId": productId, "color":color
  			};
    	$.ajax({
			type : "POST",
			url : contextRoot + "/manager/product/deleteColor",
			dataType : "json",
			headers : headers,
			//contentType : 'text/plain;charset=utf-8',
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(formData),
			async : false,
			success : function(data) {
				alert("刪除:" + data.name + "成功.");
				location.reload();
			},
			error : function(e) {
				var obj = jQuery.parseJSON(e.responseText);
				console.log(obj.region);
			}
		});
    }
}
$(function() {
	product_editor.init();
});
