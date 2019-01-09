package com.waterproof.bjb.shopping.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.service.ProductService;
import com.waterproof.bjb.shopping.service.UserService;
import com.waterproof.bjb.shopping.utils.PasswordUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelReportCustomView extends AbstractXlsView {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment;filename=\"custom.xls\"");
		List<CustomerOrder> customerOrderList = (List<CustomerOrder>) model.get("CustomerOrderList");
		Sheet sheet = workbook.createSheet("Custom Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("客戶編號(10)");
		header.createCell(1).setCellValue("客戶名稱(50)");
		header.createCell(2).setCellValue("客戶簡稱(10)");
		header.createCell(3).setCellValue("業務編號(10)");
		header.createCell(4).setCellValue("客戶類別(5)");
		header.createCell(5).setCellValue("負責人(10)");

		header.createCell(6).setCellValue("聯絡人1(10)");
		header.createCell(7).setCellValue("電話1(16)");
		header.createCell(8).setCellValue("大哥大1(16)");
		header.createCell(9).setCellValue("聯絡人2(16)");
		header.createCell(10).setCellValue("電話2(16)");
		header.createCell(11).setCellValue("大哥大2(16)");
		header.createCell(12).setCellValue("傳真機(16)");
		header.createCell(13).setCellValue("統編(8)");
		header.createCell(14).setCellValue("營業稅(1.外加稅 2.內含稅 3.零稅 4.免稅)");
		header.createCell(15).setCellValue("發票種類(1.三聯 2.二聯 3.收銀 空白)");
		header.createCell(16).setCellValue("公司郵地區號(5)");
		header.createCell(17).setCellValue("公司地址(60)");
		header.createCell(18).setCellValue("發票郵遞區號(5)");
		header.createCell(19).setCellValue("發票地址(60)");
		header.createCell(20).setCellValue("送貨郵遞區號(5)");
		header.createCell(21).setCellValue("送貨地址(60)");
		
		header.createCell(22).setCellValue("電子信箱(68)");
		header.createCell(23).setCellValue("自定欄位一(68)");
		header.createCell(24).setCellValue("自定欄位二(68)");
		header.createCell(25).setCellValue("自定欄位三(68)");
		header.createCell(26).setCellValue("自定欄位四(68)");
		header.createCell(27).setCellValue("自定欄位五(68)");
		header.createCell(28).setCellValue("自定欄位六(68)");
		header.createCell(29).setCellValue("自定欄位七(68)");
		header.createCell(30).setCellValue("自定欄位八(68)");
		header.createCell(31).setCellValue("自定欄位九(68)");
		header.createCell(32).setCellValue("自定欄位十(68)");
		
		int rowNum = 1;
		for (CustomerOrder customerOrder : customerOrderList) {
			userService = (UserService)model.get("UserService");
			log.info("userService {}", userService);
			log.info("{}", customerOrder);
			User u = userService.findById(customerOrder.getUsername());
			log.info("{}", u);
			if (u == null) {
				u = new User();
			}
			Row row = sheet.createRow(rowNum++);
			//客戶編號(10)							
			row.createCell(0).setCellValue(PasswordUtil.get_SHA_512_SecurePassword(customerOrder.getUsername(), "liquidrubber").substring(0,  10).toUpperCase());
			//客戶名稱(50)
			row.createCell(1).setCellValue(customerOrder.getUsername());
			//客戶簡稱(10)
			row.createCell(2).setCellValue(StringUtils.defaultIfBlank(u.getCName(),"[no name]"));
			//業務編號(10)
			row.createCell(3).setCellValue("");
			//客戶類別(5)
			row.createCell(4).setCellValue("");
			//負責人(10)
			row.createCell(5).setCellValue("");
			
			//聯絡人1(10)
			row.createCell(6).setCellValue(StringUtils.defaultIfBlank(u.getCName(),"[no name]"));
			//電話1(16)	
			row.createCell(7).setCellValue("");
			//大哥大1(16)
			row.createCell(8).setCellValue(StringUtils.defaultIfBlank(u.getMobile(),"[no mobile]"));
			
			//聯絡人2(10)
			row.createCell(9).setCellValue("");
			//電話2(16)	
			row.createCell(10).setCellValue("");
			//大哥大2(16)
			row.createCell(11).setCellValue("");
			//傳真機(16)								
			row.createCell(12).setCellValue("");
			//統編(8)	
			if (customerOrder.getOrderInvoiceContract() != null && customerOrder.getOrderInvoiceContract().getInvoiceType() == 2) {
				row.createCell(13).setCellValue(customerOrder.getOrderInvoiceContract().getVatId());
			} else {
				row.createCell(13).setCellValue("");
			}
			//營業稅(1.外加稅 2.內含稅 3.零稅 4.免稅)
			row.createCell(14).setCellValue("2");
			//發票種類(1.三聯 2.二聯 3.收銀 空白)
			if (customerOrder.getOrderInvoiceContract() != null && customerOrder.getOrderInvoiceContract().getInvoiceType() == 2) {
				row.createCell(15).setCellValue("1");
			} else {
				row.createCell(15).setCellValue("2");
			}
			//公司郵地區號(5)
			row.createCell(16).setCellValue("");
			//公司地址(60)
			row.createCell(17).setCellValue("");
			//發票郵遞區號(5)
			row.createCell(18).setCellValue(customerOrder.getOrderContract().getZipCode());
			//發票地址(60)
			row.createCell(19).setCellValue(customerOrder.getOrderContract().getCounty()+
					customerOrder.getOrderContract().getDistrict()+
					customerOrder.getOrderContract().getAddress());
			//送貨郵遞區號(5)	
			row.createCell(20).setCellValue(customerOrder.getOrderContract().getZipCode());
			//送貨地址(60)
			row.createCell(21).setCellValue(
					customerOrder.getOrderContract().getCounty()+
					customerOrder.getOrderContract().getDistrict()+
					customerOrder.getOrderContract().getAddress());
			
			//電子信箱(68)							
			row.createCell(22).setCellValue(StringUtils.defaultIfBlank(u.getEmail(), "[no email]"));
			//自定欄位一(68)
			row.createCell(23).setCellValue("");
			//自定欄位二(68)
			row.createCell(24).setCellValue("");
			//自定欄位三(68)
			row.createCell(25).setCellValue("");
			//自定欄位四(68)
			row.createCell(26).setCellValue("");
			//自定欄位五(68)
			row.createCell(27).setCellValue("");
			//自定欄位六(68)	
			row.createCell(28).setCellValue("");
			//自定欄位七(68)
			row.createCell(29).setCellValue("");
			//自定欄位八(68)	
			row.createCell(30).setCellValue("");
			//自定欄位九(68)	
			row.createCell(31).setCellValue("");
			//自定欄位十(68)
			row.createCell(32).setCellValue("");
		}
	}
}
