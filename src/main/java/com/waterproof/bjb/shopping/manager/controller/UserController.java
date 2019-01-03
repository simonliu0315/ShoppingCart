package com.waterproof.bjb.shopping.manager.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.entity.UserRole;
import com.waterproof.bjb.shopping.manager.dto.ExportCustomDto;
import com.waterproof.bjb.shopping.manager.dto.OrderDto;
import com.waterproof.bjb.shopping.manager.dto.UserDto;
import com.waterproof.bjb.shopping.manager.service.OrderService;
import com.waterproof.bjb.shopping.service.UserDetailServiceImpl;
import com.waterproof.bjb.shopping.service.UserService;
import com.waterproof.bjb.shopping.utils.PasswordUtil;
import com.waterproof.bjb.shopping.utils.ShoppingDateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/manager/users")     // 通过这里配置使下面的映射都在 /users
public class UserController {

    @Autowired
    private UserService userService;          // 用户服务层

    /**
     *  获取用户列表
     *    处理 "/users" 的 GET 请求，用来获取用户列表
     *    通过 @RequestParam 传递参数，进一步实现条件查询或者分页查询
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        map.addAttribute("userList", userService.findAll());
        return "manager/user/userList";
    }

    /**
     * 显示创建用户表单
     *
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUserForm(ModelMap map) {
        map.addAttribute("user", new User());
        map.addAttribute("action", "create");
        return "manager/user/userForm";
    }

    /**
     *  创建用户
     *    处理 "/users" 的 POST 请求，用来获取用户列表
     *    通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
     * @throws ParseException 
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postUser(@ModelAttribute UserDto user) throws ParseException {
    	log.info("user: {}", user);
    	User u = new User();
    	BeanUtils.copyProperties(user, u);
    	u.setBirthday(DateUtils.parseDate(user.getBirthdayStr(), "yyyy/MM/dd"));
        userService.insertByUser(u);
        userService.createUserRole(user.getUsername(), "ROLE_USER");
        userService.createUserRole(user.getUsername(), "ROLE_ANONYMOUS");
        if(user.getIsAdmin() == 1) {
        	UserRole uerRole = userService.getUserRole(user.getUsername(), "ROLE_ADMIN");
        	if (uerRole == null) {
        		userService.createUserRole(user.getUsername(), "ROLE_ADMIN");
        	}
        }
        return "redirect:/manager/users/";
    }

    /**
     * 显示需要更新用户表单
     *    处理 "/users/{id}" 的 GET 请求，通过 URL 中的 id 值获取 User 信息
     *    URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id, ModelMap map) {
    	if (id.indexOf("**********") > 1) {
    		id = id.replace("**********", "@");
    	}
    	if (id.indexOf("----------") > 1) {
    		id = id.replace("----------", ".");
    	}
    	log.info("q: {}", id);
    	User u = userService.findById(id);
    	log.info("user:{}", u);
    	//u.setBirthday(DateUtils.(u.getBirthday(), "yyyy/MM/dd"));
    	if (u.getBirthday() != null) {
    		u.setBirthdayStr(ShoppingDateUtil.date2String(u.getBirthday(), "yyyy/MM/dd"));
    	}
    	log.info("getUser {}", u);
        map.addAttribute("user", u);
        
        map.addAttribute("action", "update");
        map.addAttribute("isAdmin", false);
        for(UserRole r: u.getUserRoles()) {
        	if(StringUtils.equals(r.getId().getRole(), "ROLE_ADMIN") && r.getStatus() == 1) {
        		map.addAttribute("isAdmin", true);
        	}
        }
        return "manager/user/userForm";
    }

    /**
     * 处理 "/users/{id}" 的 PUT 请求，用来更新 User 信息
     * @throws ParseException 
     *
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putUser(@ModelAttribute UserDto user) throws ParseException {
    	User u = userService.findById(user.getUsername());
    	BeanUtils.copyProperties(user, u, "verifyDate", "birthday");
    
    	
    	u.setBirthday(DateUtils.parseDate(user.getBirthdayStr(), "yyyy/MM/dd"));
    	
    	log.info("update User:{}", u);
        userService.update(u);
        if(user.getIsAdmin() != 1) {
        	UserRole uerRole = userService.getUserRole(user.getUsername(), "ROLE_ADMIN");
        	if (uerRole != null) {
        		userService.deleteUserRole(user.getUsername(), "ROLE_ADMIN");
        	}
        	
        }
        if(user.getIsAdmin() == 1) {
        	UserRole uerRole = userService.getUserRole(user.getUsername(), "ROLE_ADMIN");
        	if (uerRole == null) {
        		userService.createUserRole(user.getUsername(), "ROLE_ADMIN");
        	}
        }
        return "redirect:/manager/users/";
    }

    /**
     * 处理 "/users/{id}" 的 GET 请求，用来删除 User 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id) {

        userService.delete(id);
        return "redirect:/manager/users/";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView getExcel() {
		return new ModelAndView(new ExcelReportUserView(), "userList", userService.findAll());
	}
}