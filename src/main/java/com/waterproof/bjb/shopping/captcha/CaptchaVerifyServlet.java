package com.waterproof.bjb.shopping.captcha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;

import com.octo.captcha.service.image.ImageCaptchaService;

@Log4j
@WebServlet(name = "CaptchaVerifyServlet", urlPatterns = { "/verifyCaptcha" })
public class CaptchaVerifyServlet extends HttpServlet {

    private static final long serialVersionUID = 1085466866786569566L;
    private ImageCaptchaService imageCaptcha = GenerateImageCaptchaService.getInstance();
    
    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response){

        String sessionID = request.getSession(true).getId();
        String captcha = (String) request.getAttribute("captcha");
        
        boolean isVerify = imageCaptcha.validateResponseForID( 
                sessionID, captcha).booleanValue();
        log.info("Verify captcha: "+sessionID+" "+request.getParameter("captcha")+" "+request.getAttribute("captcha") +" result: "+ isVerify);
    }
    

}
