package com.waterproof.bjb.shopping.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.manager.dto.OrderDto;

public class ExcelReportUserView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment;filename=\"user.xls\"");
		List<User> userList = (List<User>) model.get("userList");
		Sheet sheet = workbook.createSheet("User Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("使用者名稱");
		header.createCell(1).setCellValue("中文姓名");
		header.createCell(2).setCellValue("郵遞區號");
		header.createCell(3).setCellValue("地址");
		header.createCell(4).setCellValue("生日");
		header.createCell(5).setCellValue("是否管理者(0:否,1:是)");

		int rowNum = 1;
		for (User user : userList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getUsername());
			row.createCell(1).setCellValue(user.getCName());
			row.createCell(2).setCellValue(user.getZipCode());
			row.createCell(3).setCellValue(user.getCounty()+user.getDistrict()+user.getAddress());
			row.createCell(4).setCellValue(user.getBirthdayStr());
			row.createCell(5).setCellValue(user.getIsAdmin());
		}
	}
}
