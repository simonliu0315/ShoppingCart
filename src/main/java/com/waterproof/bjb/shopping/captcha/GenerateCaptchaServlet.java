package com.waterproof.bjb.shopping.captcha;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;

import com.octo.captcha.service.image.ImageCaptchaService;

@Log4j
@WebServlet(name = "GenerateCaptchaServlet", urlPatterns = { "/generateCaptcha" })
public class GenerateCaptchaServlet extends HttpServlet {

    private ImageCaptchaService service = GenerateImageCaptchaService.getInstance();
    
    /**
     * 
     */
    private static final long serialVersionUID = 6728693644095438700L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String sessionID = request.getSession(true).getId();
        BufferedImage bufferedImage = service
                .getImageChallengeForID(sessionID);

        ServletOutputStream servletOutputStream = response.getOutputStream();

        ImageIO.write(bufferedImage, "jpg", servletOutputStream);

        try {
            servletOutputStream.flush();
        } finally {
            servletOutputStream.close();
        }

    }

}
