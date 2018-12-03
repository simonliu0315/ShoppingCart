package com.waterproof.bjb.shopping.manager.dto;

import lombok.Data;

@Data
public class ExportOrderDto {

	//單據日期
	private String documentDate;
	
	//訂單單號
	private String orderNo;
	//統一編號
	private String taxId;
	//客戶編號
	private String customerNo;
	//客戶名稱
	private String customerName;
	//聯絡人
	private String postName;
	//聯絡電話
	private String mailTel;
	//送貨地址
	private String mailAddress;
    //表頭備註
	private String mailNote;
	//產品編號
	private String productNo;
	//數量
	private String quantity;
	//單價
	private String price;
	//稅別
	private String taxType;
	//稅前合計
	private String totalPreTax;
	//營業稅額
	private String businessTax;
	//說明
	private String description;
}
