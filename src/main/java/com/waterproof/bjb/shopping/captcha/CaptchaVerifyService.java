package com.waterproof.bjb.shopping.captcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;


@Service
public class CaptchaVerifyService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = 1085466866786569566L;

    private ImageCaptchaService service = GenerateImageCaptchaService.getInstance();

    public boolean isVerify(String sessionId, String captcha) {
    	logger.info("圖形驗證碼驗證參數: sessionId: {}, {}", sessionId, captcha);
        try {
        boolean isVerify = service.validateResponseForID(sessionId, captcha).booleanValue();
        logger.info("圖形驗證碼驗證結果: sessionId: {}, 帶入驗證碼: {}, 是否驗證成功: {}", sessionId, captcha, isVerify);
        return isVerify;
        } catch (CaptchaServiceException e) {
            logger.error("", e);
        }
       return false;

    }

}
