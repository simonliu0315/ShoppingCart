var userForm = {
	init : function() {
      
	    $("#modifyBtn").on("click", function() {
	    	userForm.gogo();
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
		
		if($("#mobile").val() == "") {
			alert("請輸入連絡電話.");
			return ;
		}
		
		
		//$("#userForm").attr("action",contextRoot + "/member/modifyUser");
		
		$("#userForm").submit();
	},
	refreshCaptcha: function() {
		$("#captchaImg").attr("src", contextRoot+"/generateCaptcha" + "?" + new Date().getTime());
	}
}



$(function() {
	userForm.init();
});