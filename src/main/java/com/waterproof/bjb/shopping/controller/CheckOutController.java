package com.waterproof.bjb.shopping.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.controller.dto.CheckoutForm;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.dto.ProductTempOrder;
import com.waterproof.bjb.shopping.service.CustomerOrderService;
import com.waterproof.bjb.shopping.service.SimpleUserService;
import com.waterproof.bjb.shopping.service.dto.UserContractDto;
import com.waterproof.bjb.shopping.utils.MailUtil;
import com.waterproof.bjb.shopping.utils.NotifyUtil;
import com.waterproof.bjb.shopping.utils.ShoppingDateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/checkout")
public class CheckOutController {

	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private SimpleUserService userService;
	
	@Autowired
	private NotifyUtil util;
	
	@Autowired
	private Environment environment;

	@Autowired
	private MailUtil mailUtil;
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(@RequestParam(value = "postName", required=false) String postName, 
    		@RequestParam(value = "email", required=false) String email, @RequestParam(value = "zipCode", required=false) String zipCode,
    		@RequestParam(value = "city", required=false) String city, @RequestParam(value = "district", required=false) String district, 
    		@RequestParam(value = "address", required=false) String address, @RequestParam(value = "tel", required=false) String tel, 
    		@RequestParam(value = "paymentMethod", required=false, defaultValue = "1") int paymentMethod, 
    		@RequestParam(value = "invoiceType", required=false, defaultValue = "1") int invoiceType,
    		@RequestParam(value = "vatId", required=false, defaultValue = "") String vatId,
    		@RequestParam(value = "businessName", required=false, defaultValue = "") String businessName,
    		HttpServletRequest request) {
        log.info("checkout index getPage");
        ModelAndView mav = new ModelAndView();
        ProductInCartDto productInCartDto = (ProductInCartDto)request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART);
        if (productInCartDto == null) {
        	productInCartDto = new ProductInCartDto();
        }
        CheckoutForm checkoutForm = new CheckoutForm(postName, email, zipCode, city, district, address, tel, paymentMethod, invoiceType, vatId, businessName);
        log.info("session: {}", productInCartDto);
		mav.addObject("order", productInCartDto);
		mav.addObject("CheckoutForm", checkoutForm);
		mav.addObject("ShippingMethods", customerOrderService.getShipping());
        mav.setViewName("checkout/checkout");
        return mav;
    }
	
	@RequestMapping(value = "", method = {RequestMethod.POST})
    public ModelAndView getPagePost(@ModelAttribute CheckoutForm formBean, HttpServletRequest request) throws Exception {
        log.info("getPagePost page form {}", formBean);
        ModelAndView mav = new ModelAndView();
        ProductInCartDto productInCartDto = (ProductInCartDto)request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART);
        if (productInCartDto == null) {
        	productInCartDto = new ProductInCartDto();
        }
        productInCartDto.setPaymentMethod(formBean.getPaymentMethod());
        String username = userService.getUser().getUsername();
        if (StringUtils.isBlank(username)) {
        	mav.setViewName("/");
        	return mav;
        }
        if (formBean.getPaymentMethod() == 0) {
        	mav.addObject("order", productInCartDto);
    		mav.addObject("CheckoutForm", new CheckoutForm());
            mav.setViewName("checkout/checkout");
        	return mav;
        }
        productInCartDto.setUserId(username);
        log.info("session: {}", productInCartDto);
        UserContractDto userContractDto = new UserContractDto();
        BeanUtils.copyProperties(formBean, userContractDto);
        
        log.info("login user {}", userService.getUser());
        log.info("userContractDto {}", userContractDto);
        String orderNumber = customerOrderService.saveToOrder(userContractDto, productInCartDto, username);
        request.getSession().setAttribute(SessionParameter.PRODUCTS_IN_CART, null);
        ProductInCartDto dto = customerOrderService.queryOrderByNo(orderNumber);
        log.info("dto :{}", dto);
        List<ProductInCartDto> list = new ArrayList<ProductInCartDto>();
        list.add(dto);
        mav.addObject("orderNumber", orderNumber);
		mav.addObject("orders", list);
		mav.addObject("fromPage", "checkout");
		mav.addObject("tip", util.getBankTransferInfo());
		mav.addObject("bankCode", util.getBankCode());
		mav.addObject("accountNo", util.getAccountCode());
		mav.addObject("accountName", util.getAccountName());
		;
		mav.addObject("expiryDate", DateUtils.addDays(new Date(), 7));
        mav.setViewName("order/list");
        
        
        //寄信
       
        
        
        
        StringBuilder mailMessage = new StringBuilder();
        mailMessage.append("");
        //空白行
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td align=\"center\" height=\"20\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        //訂購資訊文字描述
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\">");
        mailMessage.append("<table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append(" <tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;text-align:left;line-height:18px\">");
        mailMessage.append("嗨 "+username+",");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;text-align:left;line-height:18px\">");
        mailMessage.append("感謝您訂購相關商品訂單編號"+ orderNumber + "<wbr>請完成相關付款作業。<wbr>若您付款完成且收到商品無誤，請確認商品，<wbr>如有問題，請洽客服。");
        mailMessage.append(" 提醒您，若你在七天內沒有任何回應，<wbr>系統將自動進行訂單完成流程。");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"1\" bgcolor=\"#ffffff\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        //訂單明細中文
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\">");
        mailMessage.append("<table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"0\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#889098;text-align:center;line-height:16px\"><table width=\"px\" align=\"left\">");
        mailMessage.append("<tbody><tr><td colspan=\"2\" style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#1f1f1f;font-size:16px;font-weight:bold;height:10px\"> </td></tr>");
        mailMessage.append("<tr><td colspan=\"2\" style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#1f1f1f;font-size:13px;font-weight:bold\">訂單明細</td></tr>");
        mailMessage.append("</tbody></table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        //訂單編號
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\">");
        mailMessage.append("<table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"0\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#889098;text-align:center;line-height:16px\"><table width=\"px\" align=\"left\">");
        mailMessage.append("<tbody><tr><td colspan=\"2\" style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#1f1f1f;font-size:16px;font-weight:bold;height:px\"> </td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">訂單編號: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;vertical-align:top\">"+orderNumber+"</td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">訂單日期: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">"+ShoppingDateUtil.date2String(new Date(), "yyyy-MM-dd hh:mm:ss")+"</td></tr>");
        //mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">賣家名稱: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">linjeffery890628</td></tr>");
        mailMessage.append("</tbody></table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        //訂單明細清單
        for(ProductTempOrder productTempOrder : dto.getProductsTempOrder()) {
        	mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td>");
            mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"100%\">");
            mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"left\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td>");
            mailMessage.append("<table width=\"180\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"180\" height=\"140\" align=\"left\" style=\"padding-left:20px\">");
            mailMessage.append("<img src=\"http://www.liquidrubber-diy.com.tw" + productTempOrder.getImg().replaceAll(" ", "%20")+"\" alt=\"\" border=\"0\" width=\"140\" height=\"140\" style=\"display:block;border:none;outline:none;text-decoration:none\">");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"100%\" height=\"15\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("<table width=\"280\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td>");
            mailMessage.append("<table width=\"100%\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;text-align:left;line-height:px;padding-left:10px\"> "+productTempOrder.getProductName()+"");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"\" height=\"15\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;text-align:left;line-height:px\">");
            mailMessage.append("<table width=\"100%\" align=\"left\">");
            mailMessage.append("<tbody><tr>");
            mailMessage.append("<td style=\"text-align:left;padding-left:10px;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px;white-space:nowrap;width:80px\">Variation: </td>");
            mailMessage.append("<td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px\">"+productTempOrder.getColorName()+"</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td style=\"text-align:left;padding-left:10px;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px;white-space:nowrap;width:80px\">Quantity: </td>");
            mailMessage.append("<td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px\">"+productTempOrder.getQuantity()+"</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td style=\"text-align:left;padding-left:10px;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px;white-space:nowrap;width:80px\">Price: </td>");
            mailMessage.append("<td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px\">NT$ "+productTempOrder.getPrice()+"</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody></table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"100%\" height=\"15\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
        }
        
        
        //總金額
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\">");
        mailMessage.append("<table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"0\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#889098;text-align:center;line-height:16px\"><table width=\"\" align=\"left\">");
        mailMessage.append("<tbody><tr><td colspan=\"2\" style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#1f1f1f;font-size:16px;font-weight:bold;height:10px\"> </td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">商品總額: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;vertical-align:top\">NT$ "+dto.getSubTotalAmt()+"</td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">物流費用: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;vertical-align:top\">NT$ +"+dto.getShipment()+"</td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">總金額: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">NT$ "+dto.getTotalAmt()+"</td></tr>");
        mailMessage.append("</tbody></table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        mailUtil.sendByGmail(
				"築城國際網路訂購中心-購物清單!", mailMessage.toString(),
				formBean.getEmail());

        return mav;
    }
}
