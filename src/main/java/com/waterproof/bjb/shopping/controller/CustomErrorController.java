package com.waterproof.bjb.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomErrorController extends AbstractErrorController {

	private static final String ERROR_PATH=  "/error";

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes) {
    	super(errorAttributes);
    }

    /**
     * Just catching the {@linkplain NotFoundException} exceptions and render
     * the 404.jsp error page.
     */
    @ExceptionHandler(NotFoundException.class)
    public String notFound() {
    	log.info("****notFound****");
        return "404";
    }

    /**
     * Responsible for handling all errors and throw especial exceptions
     * for some HTTP status codes. Otherwise, it will return a map that
     * ultimately will be converted to a json error.
     * @throws NotFoundException 
     */
    @RequestMapping(ERROR_PATH)
    public ResponseEntity<?> handleErrors(HttpServletRequest request) throws NotFoundException {
        HttpStatus status = getStatus(request);
        log.info("****handleErrors**** status: {}", status);
        if (status.equals(HttpStatus.NOT_FOUND))
            throw new NotFoundException();

        return ResponseEntity.status(status).body(getErrorAttributes(request, false));
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
