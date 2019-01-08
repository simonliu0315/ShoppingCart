package com.waterproof.bjb.shopping.manager.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.waterproof.bjb.shopping.entity.AuditLog;
import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.OrderDetail;
import com.waterproof.bjb.shopping.manager.service.AuditLogService;
import com.waterproof.bjb.shopping.manager.service.OrderService;
import com.waterproof.bjb.shopping.service.UserService;
import com.waterproof.bjb.shopping.utils.ShoppingDateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/manager/audit")
public class AuditLogController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuditLogService auditLogService;


	@Transactional
	@RequestMapping(value = "/search", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSearchNameList(@RequestParam(value = "userName", required=false, defaultValue = "") String userName, 
    		@RequestParam(value = "status", required=false, defaultValue = "0") int status, 
    		@RequestParam(value = "startDate", required=false, defaultValue = "") String startDate,
    		@RequestParam(value = "endDate", required=false, defaultValue = "") String endDate,
    		@RequestParam(value = "orderby", required=false, defaultValue = "1") int orderby,
    		@RequestParam(value = "page", required=false, defaultValue = "0") int page,
    		@RequestParam(value = "pageSize", required=false, defaultValue = "10") int pageSize,
    		HttpServletRequest request) {
        log.info("search getPage");
        
        ModelAndView mav = new ModelAndView();
        if (page != 0) {
        	page = page - 1;
        }
        log.info("page {}, pageSize {}", page, pageSize);
        Pageable pageable = new PageRequest(page, pageSize);
        Date sStartDate = null;
        Date sEndDate = null;
        String defaultUrlPage = "";
        if (StringUtils.isNotBlank(startDate)) {
        	sStartDate = ShoppingDateUtil.parseDateTime(startDate + " 00:00:00", "yyyy/MM/dd hh:mm:ss");
        	defaultUrlPage += "&startDate=" + startDate;
        }
        if (StringUtils.isNotBlank(endDate)) {
        	sEndDate = ShoppingDateUtil.parseDateTime(endDate + " 23:59:59", "yyyy/MM/dd hh:mm:ss");
        	defaultUrlPage += "&endDate=" + endDate;
        }
        if (StringUtils.isNotBlank(userName)) {
        	defaultUrlPage += "&userName=" + userName;
        }
        if (status != 0) {
        	defaultUrlPage += "&status=" + status;
        }
        log.info("userName: {}, status: {}, startDate: {}, endDate: {}", userName, status, sStartDate, sEndDate);
        Page<AuditLog> pAuditLog = auditLogService.getFilterAuditLog(sStartDate, sEndDate, userName, status, 1, pageable);
        
        
        defaultUrlPage += "&pageSize="+ pageSize + "&page=";
        mav.addObject("default_url_page", defaultUrlPage);
        mav.addObject("totalPages", pAuditLog.getTotalPages());
        mav.addObject("pageSize", pAuditLog.getSize());
        mav.addObject("pageNumber", pageable.getPageNumber());
        mav.addObject("pageable", pageable);
        log.info("size {}", pAuditLog.getContent().size());
        mav.addObject("auditList", pAuditLog.getContent());
        
        log.info("SET session ExportOrder");
        mav.setViewName("manager/audit/auditList");
        return mav;
	}
        
	
}
