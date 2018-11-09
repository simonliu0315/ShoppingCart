var login = {
	init : function() {
        $("#captchaImg").attr("src",  contextRoot+ "/generateCaptcha");
	    
	    $("#refreshCaptchaButton").on("click", function() {
	    	login.refreshCaptcha();
	    });
	    
	},
	gogo : function() {
		
	},
	refreshCaptcha: function() {
		$("#captchaImg").attr("src", contextRoot+"/generateCaptcha" + "?" + new Date().getTime());
	}
}



$(function() {
	login.init();
});