var login = {
	init : function() {
        $("#captchaImg").attr("src",  contextRoot+ "/generateCaptcha");
	    
	    $("#refreshCaptchaButton").on("click", function() {
	    	login.refreshCaptcha();
	    });
	    $("#registerBtn").on("click", function() {
	    	login.gogo();
	    });
	    
	},
	gogo : function() {
		if($("#repassword").val() != $("#password").val()) {
			alert("第一次輸入密碼與第二次不同，請重新輸入");
			$("#password").val("");
			$("#repassword").val("");
			return ;
		}
		if($("#email").val() == "") {
			alert("請輸入email.");
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
	login.init();
});