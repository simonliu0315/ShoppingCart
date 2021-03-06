var headers = {
	"Accept" : "application/json, text/plain, */*"
};
var csrf_header = $("[name='_csrf_header']").attr("content");
headers[csrf_header] = $("[name='_csrf']").attr("content");

var index = {
	init : function() {
		var formData = {};
		$("[data-countdown]").each(function() {
		    var $this = $(this), finalDate = $(this).data("countdown");
			$this.countdown(finalDate, function(event) {
				$this
	              .html("<li><span class='days'>" + event.strftime("%D") +"天</span></li>" +
	            		  "<li><span class='hours'>" + event.strftime("%H") +"</span></li>" + 
	            		  "<li><span class='minutes'>" + event.strftime("%M") +"</span></li>" +
	            		  "<li><span class='seconds'>" + event.strftime("%S") +"</span></li>");
			});
		});
		
		$('#addCartModalCenter').on('show.bs.modal', function(){
	        var addCartModal = $(this);
	        clearTimeout(addCartModal.data('hideInterval'));
	        addCartModal.data('hideInterval', setTimeout(function(){
	        	addCartModal.modal('hide');
	        }, 1500));
	    });
		$('#removeCartModalCenter').on('show.bs.modal', function(){
	        var removeCartModal = $(this);
	        clearTimeout(removeCartModal.data('hideInterval'));
	        removeCartModal.data('hideInterval', setTimeout(function(){
	        	removeCartModal.modal('hide');
	        }, 1500));
	    });
	},
	addCart : function(productId) {
		
//		$("#addCartModalCenter").modal({
//			show: true
//		});
//		return ;
		$("#shopping-cart-list").empty();
		var loading = "<div class='product product-widget text-center'>" +
                "<img src='/img/loading.gif' alt='' />" +
            "</div>";
        $("#shopping-cart-list").html(loading);
        if ($("#input_quantity").val() == null ) {
        	$("#input_quantity").val(1);
        }
		var formData = {
			"productId" : productId, "quantity" : $("#input_quantity").val(), "color": $("#color").val()
		};
		if ($("#isColor").val() == "1" && $("#color").val() == "") {
			alert("請選擇顏色");
			return ;
		}
		$.ajax({
			type : "POST",
			url : contextRoot + "/user/addCart",
			dataType : "json",
			headers : headers,
			//contentType : 'text/plain;charset=utf-8',
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(formData),
			async : false,
			success : function(data) {
				$("#shopping-cart-list").empty();
				if(data != null && data.productsTempOrder.length > 0) {
					var html = "";
					$.each(data.productsTempOrder, function( index, value ) {
						html += "<div class='product product-widget'>" +
							"<div class='product-thumb'>" +
								"<img src='" + value.img + "' alt='' />" +
							"</div>" +
							"<div class='product-body'>" +
								"<h3 class='product-price'>" +
									"$" + (value.price) + "<span class='qty'>x" + value.quantity + "</span>" +
								"</h3>" +
								"<h2 class='product-name'>" +
									"<a href='#'>"+ value.productName +"</a>";
									if (value.color != null) {
										html +="<ul class='color-option'>"+
								        "<li><a href='#' style='background-color:" + value.color +"'></a></li>"+
									    "<li><span>" + value.colorName + "</span></li>"+
								    "</ul>";
									}
									
						html += "</h2>" +
							"</div>" +
							"<button class='cancel-btn' onClick='index.deleteCart(" + value.productId + ");'>" +
								"<i class='fa fa-trash'></i>" +
							"</button>" +
						  "</div>";				
						});
					$("#shopping-cart-list").html(html);
				}
				$("#qty").html(data.quantity);
				$("#totalAmt").html("$" + data.subTotalAmt);
				$("#addCartModalCenter").modal({
					show: true
				});
			},
			error : function(e) {
				var obj = jQuery.parseJSON(e.responseText);
				console.log(obj.region);
			}
		});
	},
	deleteCart : function(productId) {
		$("#shopping-cart-list").empty();
		var loading = "<div class='product product-widget text-center'>" +
                "<img src='/img/loading.gif' alt='' />" +
            "</div>";
        $("#shopping-cart-list").html(loading);
		var formData = {
				"productId" : productId
		};
		$.ajax({
			type : "POST",
			url : contextRoot + "/user/deleteCart",
			dataType : "json",
			headers : headers,
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(formData),
			async : false,
			success : function(data) {
				$("#shopping-cart-list").empty();
				if(data != null && data.productsTempOrder.length > 0) {
					var html = "";
					$.each(data.productsTempOrder, function( index, value ) {
						html += "<div class='product product-widget'>" +
							"<div class='product-thumb'>" +
								"<img src='" + value.img + "' alt='' />" +
							"</div>" +
							"<div class='product-body'>" +
								"<h3 class='product-price'>" +
									"$" + (value.price) + "<span class='qty'>x" + value.quantity + "</span>" +
								"</h3>" +
								"<h2 class='product-name'>" +
								"<a href='#'>"+ value.productName +"</a>";
								if (value.color != null) {
									html +="<ul class='color-option'>"+
							        "<li><a href='#' style='background-color:" + value.color +"'></a></li>"+
								    "<li><span>" + value.colorName + "</span></li>"+
							    "</ul>";
								}		
					html += "</h2>" +
							"</div>" +
							"<button class='cancel-btn' onClick='index.deleteCart(" + value.productId + ");'>" +
								"<i class='fa fa-trash'></i>" +
							"</button>" +
						  "</div>";
						});
					$("#shopping-cart-list").html(html);
				}
				$("#qty").html(data.quantity);
				$("#totalAmt").html("$" + data.subTotalAmt);
				$("#removeCartModalCenter").modal({
					show: true
				});
			},
			error : function(e) {
				var obj = jQuery.parseJSON(e.responseText);
				console.log(obj.region);
			}
		});
	},
	refashCart : function() {
		$("#shopping-cart-list").empty();
		var loading = "<div class='product product-widget text-center'>" +
                "<img src='/img/loading.gif' alt='' />" +
            "</div>";
        $("#shopping-cart-list").html(loading);
        var formData = {
		};
		$.ajax({
			type : "POST",
			url : contextRoot + "/user/refashCart",
			dataType : "json",
			headers : headers,
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(formData),
			async : false,
			success : function(data) {
				$("#shopping-cart-list").empty();
				if(data != null && data.productsTempOrder.length > 0) {
					var html = "";
					$.each(data.productsTempOrder, function( index, value ) {
						html += "<div class='product product-widget'>" +
							"<div class='product-thumb'>" +
								"<img src='" + value.img + "' alt='' />" +
							"</div>" +
							"<div class='product-body'>" +
								"<h3 class='product-price'>" +
									"$" + (value.price) + "<span class='qty'>x" + value.quantity + "</span>" +
								"</h3>" +
								"<h2 class='product-name'>" +
								"<a href='#'>"+ value.productName +"</a>";
								if (value.color != null) {
									html +="<ul class='color-option'>"+
							        "<li><a href='#' style='background-color:" + value.color +"'></a></li>"+
								    "<li><span>" + value.colorName + "</span></li>"+
							    "</ul>";
								}
					html += "</h2>" +
							"</div>" +
							"<button class='cancel-btn' onClick='index.deleteCart(" + value.productId + ");'>" +
								"<i class='fa fa-trash'></i>" +
							"</button>" +
						  "</div>";
						});
					$("#shopping-cart-list").html(html);
				}
				$("#qty").html(data.quantity);
				$("#totalAmt").html("$" + data.subTotalAmt);
			},
			error : function(e) {
				var obj = jQuery.parseJSON(e.responseText);
				console.log(obj.region);
			}
		});
	}, searchByCategory : function() {
		$("#searchForm").submit();
	}
}
$(function() {
	index.init();
	index.refashCart();
});
