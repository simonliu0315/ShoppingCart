var headers = {
	"Accept" : "application/json, text/plain, */*"
};
var csrf_header = $("[name='_csrf_header']").attr("content");
headers[csrf_header] = $("[name='_csrf']").attr("content");

var product_list = {
	init : function() {
		var formData = {};
	},
	reload : function() {
		var tagId = $.map($(':checkbox[name=tagId\\[\\]]:checked'), function(n, i){
		      return n.value;
		}).join(',');
		var formData = {};
		var url = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "" +contextRoot + "/products/search?";
		if ($("#orderby").val() != null) {
			url += "orderby="+$("#orderby").val();
		}
		if ($("#q").val() != null) {
			url += "&p="+$("#q").val();
		}
		if ($("#category").val() != null) {
			url += "&category="+$("#category").val();
		}
		if ($("#productId").val() != null) {
			url += "&productId="+$("#productId").val();
		}
		/*
		if ($("#price_low").val() != null) {
			url += "&price_low="+$("#price_low").val();
		}
		if ($("#price_high").val() != null) {
			url += "&price_high="+$("#price_high").val();
		}*/
		if ($("#pageSize").val() != $('#pageSize_1 :selected').val()) {
			url += "&pageSize="+ $('#pageSize_1 :selected').val();
			url += "&page=1";
		} else {
			url += "&page="+$("#page").val();
		}
		if ($("#pageSize").val() != $('#pageSize_2 :selected').val()) {
			url += "&pageSize="+$('select#pageSize option:selected').val();
			url += "&page=1";
		} else {
			url += "&page="+$("#page").val();
		}
		if (tagId != "") {
			url += "&tagId="+ tagId;
		}
		window.location.href = url;
	}, filter : function () {
		var tagId = $.map($(':checkbox[name=tagId\\[\\]]:checked'), function(n, i){
		      return n.value;
		}).join(',');
		var formData = {};
		var url = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "" +contextRoot + "/products/search?";
		if ($("#orderby").val() != null) {
			url += "orderby="+$("#orderby").val();
		}
		if ($("#q").val() != null) {
			url += "&p="+$("#q").val();
		}
		if ($("#category").val() != null) {
			url += "&category="+$("#category").val();
		}
		if ($("#productId").val() != null) {
			url += "&productId="+$("#productId").val();
		}
		if ($("#price_low").val() != null) {
			url += "&price_low="+$("#price_low").val();
		}
		if ($("#price_high").val() != null) {
			url += "&price_high="+$("#price_high").val();
		}
		if ($("#pageSize").val() != $('#pageSize_1 :selected').val()) {
			url += "&pageSize="+ $('#pageSize_1 :selected').val();
			url += "&page=1";
		} else {
			url += "&page="+$("#page").val();
		}
		if ($("#pageSize").val() != $('#pageSize_2 :selected').val()) {
			url += "&pageSize="+$('select#pageSize option:selected').val();
			url += "&page=1";
		} else {
			url += "&page="+$("#page").val();
		}
		if (tagId != "") {
			url += "&tagId="+ tagId;
		}
		window.location.href = url;
	}
}
$(function() {
	product_list.init();
	
});
//noUiSlider.on('update', function( values, handle ) {
//	product_list.reload();
//});
