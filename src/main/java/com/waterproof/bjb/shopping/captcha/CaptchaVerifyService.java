package com.waterproof.bjb.shopping.captcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octo.captcha.service.image.ImageCaptchaService;


@Service
public class CaptchaVerifyService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = 1085466866786569566L;

    private ImageCaptchaService service = GenerateImageCaptchaService.getInstance();

//    @Autowired
//    private EcpSecurityService ecpSecurityService;

    public boolean isVerify(String sessionId, String captcha) {

//        if (this.ecpSecurityService.isDevelopementMode()) {
//            return true;
//        } else {
            return service.validateResponseForID(sessionId, captcha).booleanValue();
//        }

    }

}
