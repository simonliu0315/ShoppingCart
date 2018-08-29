package com.waterproof.bjb.shopping.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.waterproof.bjb.shopping.entity.UserDetails;
import com.waterproof.bjb.shopping.service.UserDetailServiceImpl;

@Component
public class UserValidator implements Validator {
	
	@Autowired
    private UserDetailServiceImpl userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDetails.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	UserDetails user = (UserDetails) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getId().length() < 6 || user.getId().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findById(user.getId()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}