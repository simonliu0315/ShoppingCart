package com.waterproof.bjb.shopping.manager.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.OrderDetail;
import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.manager.dto.ExportOrderDto;
import com.waterproof.bjb.shopping.manager.dto.OrderDto;
import com.waterproof.bjb.shopping.service.ProductService;
import com.waterproof.bjb.shopping.service.UserService;
import com.waterproof.bjb.shopping.utils.PasswordUtil;
import com.waterproof.bjb.shopping.utils.ShoppingDateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelReportOrderView extends AbstractXlsView {

	@Autowired
	private UserService userService;

	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment;filename=\"order.xls\"");
		List<CustomerOrder> customerOrderList = (List<CustomerOrder>) model.get("CustomerOrderList");
		Sheet sheet = workbook.createSheet("Order Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("單據日期");
		header.createCell(1).setCellValue("訂單單號");
		header.createCell(2).setCellValue("統一編號");
		header.createCell(3).setCellValue("客戶編號");
		header.createCell(4).setCellValue("客戶名稱");
		header.createCell(5).setCellValue("聯絡人");
		
		header.createCell(6).setCellValue("聯絡電話");
		header.createCell(7).setCellValue("送貨地址");
		header.createCell(8).setCellValue("表頭備註");
		header.createCell(9).setCellValue("產品編號");

		header.createCell(10).setCellValue("數量");
		header.createCell(11).setCellValue("單價");
		header.createCell(12).setCellValue("稅別");
		header.createCell(13).setCellValue("稅前合計");
		header.createCell(14).setCellValue("營業稅額");
		header.createCell(15).setCellValue("說明");
		int rowNum = 1;
		Row row = sheet.createRow(rowNum);
		for (CustomerOrder customerOrder : customerOrderList) {
			userService = (UserService)model.get("UserService");
			log.info("{}", customerOrder);
			User u = userService.findById(customerOrder.getUsername());
			
			ExportOrderDto dto = new ExportOrderDto();
			dto.setDocumentDate(ShoppingDateUtil.formatDate(customerOrder.getInserted(), "yyyy/MM/dd"));
			dto.setOrderNo(customerOrder.getOrderNo());
			if (customerOrder.getOrderInvoiceContract() != null && customerOrder.getOrderInvoiceContract().getInvoiceType() == 2) {
				dto.setTaxId(customerOrder.getOrderInvoiceContract().getVatId());
			} else {
				dto.setTaxId("");
			}
			
			dto.setCustomerNo(PasswordUtil.get_SHA_512_SecurePassword(customerOrder.getUsername(), "liquidrubber").substring(0,  10).toUpperCase());
			dto.setCustomerName(StringUtils.defaultIfBlank(u.getCName(),"[no name]"));
			dto.setPostName(customerOrder.getOrderContract().getPostName());
			dto.setMailTel(customerOrder.getOrderContract().getTel());
			dto.setMailAddress(customerOrder.getOrderContract().getZipCode() + 
					customerOrder.getOrderContract().getCounty()+
					customerOrder.getOrderContract().getDistrict()+
					customerOrder.getOrderContract().getAddress());
			dto.setMailNote("");
			
			for(OrderDetail orderDetail : customerOrder.getOrderDetails()) {
				log.info("rowNum: {}, dto: {}, orderDetail {}", rowNum, dto, orderDetail);
				row.createCell(0).setCellValue(dto.getDocumentDate());
				row.createCell(1).setCellValue(dto.getOrderNo());
				row.createCell(2).setCellValue(dto.getTaxId());
				row.createCell(3).setCellValue(dto.getCustomerNo());
				row.createCell(4).setCellValue(dto.getCustomerName());
				row.createCell(5).setCellValue(dto.getPostName());
				row.createCell(6).setCellValue(dto.getMailTel());
				row.createCell(7).setCellValue(dto.getMailAddress());
				row.createCell(8).setCellValue(dto.getMailNote());

				row.createCell(9).setCellValue(orderDetail.getProductColor() == null ? "" : orderDetail.getProductColor().getLinnerColor());
				row.createCell(10).setCellValue(orderDetail.getQuantity());
				row.createCell(11).setCellValue(orderDetail.getPrice().toPlainString());
				row.createCell(12).setCellValue("1");
				row.createCell(13).setCellValue(new BigDecimal(orderDetail.getQuantity()).multiply(orderDetail.getPrice()).divide(new BigDecimal("1.05"), RoundingMode.HALF_UP).toPlainString());
				row.createCell(14).setCellValue(new BigDecimal(orderDetail.getQuantity()).multiply(orderDetail.getPrice()).subtract
						(new BigDecimal(orderDetail.getQuantity()).multiply(orderDetail.getPrice()).divide(new BigDecimal("1.05"), RoundingMode.HALF_UP)).toPlainString());
				row.createCell(15).setCellValue("");
				row = null;
				row = sheet.createRow(++rowNum);
				
				log.info("rowNum {}", rowNum);
			}
				
			
			
		}
	}
}
