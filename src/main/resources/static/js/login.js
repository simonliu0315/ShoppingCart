var login = {
	init : function() {
        $("#captchaImg").attr("src",  contextRoot+ "/generateCaptcha");
	    
	    $("#refreshCaptchaButton").on("click", function() {
	    	login.refreshCaptcha();
	    });
	    
	},
	refreshCaptcha: function() {
		$("#captchaImg").attr("src", contextRoot+"/generateCaptcha" + "?" + new Date().getTime());
	}
}



$(function() {
	login.init();
});