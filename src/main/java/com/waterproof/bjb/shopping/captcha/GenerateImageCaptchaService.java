package com.waterproof.bjb.shopping.captcha;
import java.awt.Color;
import java.awt.Font;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

public class GenerateImageCaptchaService {

    private static ImageCaptchaService instance;

    /**
     * Get default manageable image captcha service
     * 
     * @return ImageCaptchaService
     */
    public static ImageCaptchaService getInstance() {
        if (instance == null) {
            instance = new DefaultManageableImageCaptchaService(
                    new FastHashMapCaptchaStore(), new ECSImageCaptchaEngine(),
                    180, 1000, 750);
        }
        return instance;
    }
}

class ECSImageCaptchaEngine extends ListImageCaptchaEngine {
    protected void buildInitialFactories() {
        WordGenerator wgen = new RandomWordGenerator(
                "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(
                new int[] { 0, 100 }, new int[] { 0, 100 },
                new int[] { 0, 100 });
        TextPaster textPaster = new RandomTextPaster(new Integer(6),
                new Integer(6), cgen, true);

        // 原本是35，配合eBao的程式，所以第二個參數調成50
        BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(
                new Integer(100), new Integer(50), new Color(237, 237, 237));

        Font[] fontsList = new Font[] { new Font("Arial", 0, 20),
                new Font("Tahoma", 0, 10), new Font("Verdana", 0, 10), };

        // 原本是25，因為eBao的程式，所以第二個參數調成20
        FontGenerator fontGenerator = new RandomFontGenerator(new Integer(20),
                new Integer(20), fontsList);

        WordToImage wordToImage = new ComposedWordToImage(fontGenerator,
                backgroundGenerator, textPaster);
        this.addFactory(new GimpyFactory(wgen, wordToImage));
    }
}
