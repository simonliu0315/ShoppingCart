package com.waterproof.bjb.shopping.manager.dto;

import lombok.Data;

@Data
public class ExportCustomDto {
	//客戶編號(10)							
	private String customerNo;
	//客戶名稱(50)
	private String customerName;
	//客戶簡稱(10)
	private String customerAbbr;
	//業務編號(10)
	private String businessNo;
	//客戶類別(5)
	private String customerType;
	//負責人(10)
	private String principal;
	
	//聯絡人1(10)
	private String contactPerson1;
	//電話1(16)	
	private String telephone1;
	//大哥大1(16)
	private String mobile1;
	
	//聯絡人2(10)
	private String contactPerson2;
	//電話2(16)	
	private String telephone2;
	//大哥大2(16)
	private String mobile2;
	//傳真機(16)								
	private String fax;
	//統編(8)	
	private String taxId;
	//營業稅(1.外加稅 2.內含稅 3.零稅 4.免稅)
	private String taxType;
	//發票種類(1.三聯 2.二聯 3.收銀 空白)
	private String invoiceType;
	//公司郵地區號(5)
	private String companyZipCode;
	//公司地址(60)
	private String companyAddress;
	//發票郵遞區號(5)
	private String invoiceZipCode;
	//發票地址(60)
	private String invoiceAddress;
	//送貨郵遞區號(5)	
	private String mailZipCode;
	//送貨地址(60)
	private String mailAddress;
	
	//電子信箱(68)							
	private String email;
	//自定欄位一(68)
	private String selfDef1;
	//自定欄位二(68)
	private String selfDef2;
	//自定欄位三(68)
	private String selfDef3;
	//自定欄位四(68)
	private String selfDef4;
	//自定欄位五(68)
	private String selfDef5;
	//自定欄位六(68)	
	private String selfDef6;
	//自定欄位七(68)
	private String selfDef7;
	//自定欄位八(68)	
	private String selfDef8;
	//自定欄位九(68)	
	private String selfDef9;
	//自定欄位十(68)
	private String selfDef10;
	
	
}
