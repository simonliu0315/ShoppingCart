var create_user = {
	init : function() {
        $("#captchaImg").attr("src",  contextRoot+ "/generateCaptcha");
	    
	    $("#refreshCaptchaButton").on("click", function() {
	    	create_user.refreshCaptcha();
	    });
	    $("#registerBtn").on("click", function() {
	    	create_user.gogo();
	    });
	    $('.input-group.date').datepicker({
	    	format: "yyyy/mm/dd",
	    	startView: 2,
	        language: "zh-TW",
	        clearBtn: true,
	        autoclose: true
	    });
	},
	gogo : function() {
		if($("#username").val() == "") {
			alert("請輸入帳號.");
			return ;
		}
		if($("#password").val() == "") {
			alert("請輸入密碼.");
			return ;
		}
		if($("#cName").val() == "") {
			alert("請輸入姓名.");
			return ;
		}
		if($("#email").val() == "") {
			alert("請輸入email.");
			return ;
		}
		if($("#zipcode").val() == "") {
			alert("請輸入郵遞區號.");
			return ;
		}
		if($("#address").val() == "") {
			alert("請輸入地址.");
			return ;
		}
		
		if($("#mobile").val() == "") {
			alert("請輸入連絡電話.");
			return ;
		}
		if($("#repassword").val() != $("#password").val()) {
			alert("第一次輸入密碼與第二次不同，請重新輸入");
			$("#password").val("");
			$("#repassword").val("");
			return ;
		}
		
		$("#userForm").attr("action",contextRoot + "/member/createUser");
		
		$("#userForm").submit();
	},
	refreshCaptcha: function() {
		$("#captchaImg").attr("src", contextRoot+"/generateCaptcha" + "?" + new Date().getTime());
	}
}



$(function() {
	create_user.init();
});