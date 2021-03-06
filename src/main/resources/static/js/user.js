var user = {
	init : function() {
        $("#captchaImg").attr("src",  contextRoot+ "/generateCaptcha");
	    
	    $("#refreshCaptchaButton").on("click", function() {
	    	create_user.refreshCaptcha();
	    });
	    $("#modifyBtn").on("click", function() {
	    	user.gogo();
	    });
	    $(".input-group.date").datepicker({
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
		
		$("#userForm").attr("action",contextRoot + "/member/modifyUser");
		
		$("#userForm").submit();
	},
	refreshCaptcha: function() {
		$("#captchaImg").attr("src", contextRoot+"/generateCaptcha" + "?" + new Date().getTime());
	}
}



$(function() {
	user.init();
});