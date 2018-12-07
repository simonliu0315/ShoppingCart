delete from Category;
delete from Product;
delete from CUSTOMER_ORDER;
delete from ORDER_DETAIL;
delete from ORDER_STATUS;
delete from PAYMENT_METHOD;
delete from APP_USER;
delete from USER_CONTRACT;
delete from USER_ROLE;
delete from APPLICATION_LIST;
delete from APPLICATION_LIST_IMAGE;
delete from Product_Color;
delete from Product_TAG;
delete from Product_TAG_RELATION;
delete from SHIPPING_METHOD;

insert into Category values(1,'防水塗料', 1, '/img/category/category01.jpg', '', now(), 'sys', now(), 'sys', 1);
insert into Category values(2,'防水工具', 1, '/img/category/category02.jpg', '', now(), 'sys', now(), 'sys', 1);
insert into Category values(3,'防水套件', 1, '/img/category/category03.jpg', '', now(), 'sys', now(), 'sys', 1);
insert into Category values(4,'應用', 1, '/img/category/category04.jpg', 'category/application_list', now(), 'sys', now(), 'sys', 1);

insert into product values(1 ,'液態橡膠防水密封塗料1加侖','Waterproof Sealant', 'LRWS-1-02', 1799, 90, 1699, '', '<img class="img-responsive" src="/img/product/1/descript/1.防水密封膠塗料_1加侖_all.jpg"></img>', '/img/product/1/product/1.防水密封塗料_Waterproof_Sealant_1加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(1, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(1, '#000000;', '黑色', 'black', 'LRWS-1-02', 1, now(), 'sys', now(), 'sys');

insert into product values(2 ,'液態橡膠防水密封塗料5加侖','Waterproof Sealant', 'LRWS-5-02', 5099, 90, 4899, '', '<img class="img-responsive" src="/img/product/2/descript/1.防水密封膠塗料_5加侖_all.jpg"></img>', '/img/product/2/product/1.防水密封塗料_Waterproof_Sealant_5加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(2, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(2, '#000000;', '黑色', 'black', 'LRWS-5-02', 1, now(), 'sys', now(), 'sys');

insert into product values(3 ,'液態橡膠彩色防水密封塗料1加侖','Colour Waterproof Sealant Coating', 'LRCWS-1-01', 1799, 80, 1699, '', '<img class="img-responsive" src="/img/product/3/descript/2.彩色防水密封膠.塗料_1加侖_all.jpg"></img>', '/img/product/3/product/2.彩色防水密封膠.塗料_Colour_Waterproof_Sealant_Coating_1加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(3, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(3, '#FFFFFF;', '白色', 'A', 'LRCWS-1-01', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(3, '#000000;', '黑色', 'B', 'LRCWS-1-02', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(3, '#C1B6A9;', '淺灰色', 'C', 'LRCWS-1-04', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(3, '#433C32;', '中灰色', 'D', 'LRCWS-1-16', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(3, '#38342F;', '深灰色', 'E', 'LRCWS-1-05', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(3, '#1B2810;', '森林綠色', 'F', 'LRCWS-1-06', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(3, '#C09B54;', '黃褐色', 'G', 'LRCWS-1-07', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(3, '#381C06;', '褐色', 'H', 'LRCWS-1-08', 1, now(), 'sys', now(), 'sys');

insert into product values(4 ,'液態橡膠彩色防水密封塗料5加侖','Colour Waterproof Sealant Coating', 'LRWS-1-02', 5999, 90, 5699, '', '<img class="img-responsive" src="/img/product/4/descript/2.彩色防水密封膠.塗料_5加侖_all.jpg"></img>', '/img/product/4/product/2.彩色防水密封膠.塗料_Colour_Waterproof_Sealant_Coating_5加侖.jpg', 1, 1, 1, 1, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(4, '18.9', 'L', '5加侖', '5加侖(18.9L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#FFFFFF;', '白色', 'A', 'LRCWS-1-01', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#000000;', '黑色', 'B', 'LRCWS-1-02', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#C1B6A9;', '淺灰色', 'C', 'LRCWS-1-04', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#433C32;', '中灰色', 'D', 'LRCWS-1-16', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#38342F;', '深灰色', 'E', 'LRCWS-1-05', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#1B2810;', '森林綠色', 'F', 'LRCWS-1-06', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#C09B54;', '黃褐色', 'G', 'LRCWS-1-07', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#381C06;', '褐色', 'H', 'LRCWS-1-08', 1, now(), 'sys', now(), 'sys');

insert into product values(5 ,'液態橡膠RV車頂密封塗料1加侖','RV Roof Coating', 'LRRV-1-01', 2099, 100, 1999, '', '<img class="img-responsive" src="/img/product/5/descript/3.液態橡膠RV車頂密封塗料_1加侖_all.jpg"></img>', '/img/product/5/product/3.RV屋頂塗料_RV_Roof_Coating_1加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(5, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(5, '#FFFFFF;', '白色', 'black', 'LRRV-1-01', 1, now(), 'sys', now(), 'sys');

insert into product values(6 ,'液態橡膠RV車頂密封塗料5加侖','RV Roof Coating', 'LRWS-1-02', 7499, 100, 6999, '', '<img class="img-responsive" src="/img/product/6/descript/3.液態橡膠RV車頂密封塗料_5加侖_all.jpg"></img>', '/img/product/6/product/3.RV屋頂塗料_RV_Roof_Coating_5加侖.jpg', 1, 1, 1, 1, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(6, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(6, '#FFFFFF;', '白色', 'black', 'LRRV-5-01', 1, now(), 'sys', now(), 'sys');

insert into product values(7 ,'液態橡膠甲板密封塗料1加侖','Polyurethane Deck Coating', 'LRPDS-1-04', 2249,  100, 2099, '', '<img class="img-responsive" src="/img/product/7/descript/5.甲板塗料_1加侖_all.jpg"></img>', '/img/product/7/product/5.甲板塗料_Polyurethane_Deck_Coating_1加侖.jpg', 1, 1, 1, 1, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(7, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#A29A8F;', '光滑淺灰色', 'A', 'LRPDS-1-04', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#595D56;', '光滑石灰色', 'B', 'LRPDS-1-03', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#5E3719;', '光滑焦糖棕色', 'C', 'LRPDS-1-74', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#2A1407;', '光滑巧克力棕色', 'D', 'LRPDS-1-08', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#FFFFFF;', '光滑白色', 'E', 'LRPDS-1-01', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#665D4B;', '紋理淺灰色', 'F', 'LRPDT-1-04', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#868071;', '紋理石灰色', 'G', 'LRPDT-1-03', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#AD854A;', '紋理黃褐色', 'H', 'LRPDT-1-54', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#8A7148;', '紋理裸光棕色', 'I', 'LRPDT-1-29', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#3F2710;', '紋理焦糖棕色', 'J', 'LRPDT-1-74', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#261407;', '紋理巧克力棕色', 'K', 'LRPDT-1-08', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#FFFFFE;', '紋理白色', 'L', 'LRPDT-1-01', 1, now(), 'sys', now(), 'sys');


insert into product values(8 ,'液態橡膠甲板密封塗料5加侖','Polyurethane Deck Coating', 'LRPDS-5-04', 8999, 100, 8599, '', '<img class="img-responsive" src="/img/product/8/descript/5.甲板塗料_5加侖_all.jpg"></img>', '/img/product/8/product/5.甲板塗料_Polyurethane_Deck_Coating_5加侖.jpg', 1, 1, 1, 1, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(8, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#A29A8F;', '光滑淺灰色', 'A', 'LRPDS-5-04', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#595D56;', '光滑石灰色', 'B', 'LRPDS-5-03', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#5E3719;', '光滑焦糖棕色', 'C', 'LRPDS-5-74', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#2A1407;', '光滑巧克力棕色', 'D', 'LRPDS-5-08', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#FFFFFF;', '光滑白色', 'E', 'LRPDS-5-01', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#665D4B;', '紋理淺灰色', 'F', 'LRPDT-5-04', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#868071;', '紋理石灰色', 'G', 'LRPDT-5-03', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#AD854A;', '紋理黃褐色', 'H', 'LRPDT-5-54', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#8A7148;', '紋理裸光棕色', 'I', 'LRPDT-5-29', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#3F2710;', '紋理焦糖棕色', 'J', 'LRPDT-5-74', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#261407;', '紋理巧克力棕色', 'K', 'LRPDT-5-08', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#FFFFFE;', '紋理白色', 'L', 'LRPDT-5-01', 1, now(), 'sys', now(), 'sys');

insert into product values(9 ,'液態橡膠基礎密封塗料1加侖','Foundation Sealant Basement Coating', 'LRFS-1-02', 1049, 100, 999, '', '<img class="img-responsive" src="/img/product/9/descript/6.基礎密封膠.地下室塗料_1加侖_all.jpg"></img>', '/img/product/9/product/6.基礎密封膠.地下室塗料_Foundation_Sealant_Basement_Coating_1加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(9, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(9, '#000000;', '黑色', 'black', 'LRFS-1-02', 1, now(), 'sys', now(), 'sys');


insert into product values(10,'液態橡膠基礎密封塗料5加侖','Foundation_Sealant_Basement_Coating', 'LRFS-5-02', 2999, 100, 2899, '', '<img class="img-responsive" src="/img/product/10/descript/6.基礎密封膠.地下室塗料_5加侖_all.jpg"></img>', '/img/product/10/product/6.基礎密封膠.地下室塗料_Foundation_Sealant_Basement_Coating_5加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(10, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(10, '#000000;', '黑色', 'black', 'LRFS-5-02', 1, now(), 'sys', now(), 'sys');

insert into product values(11,'液態橡膠金屬安全密封塗料1加侖','MetalSafe Sealant', 'LRMS-1-02', 1799, 100, 1699, '', '<img class="img-responsive" src="/img/product/11/descript/7.金屬安全密封膠_1加侖_all.jpg"></img>', '/img/product/11/product/7.金屬安全密封膠_MetalSafe_Sealant_1加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(11, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(11, '#000000;', '黑色', 'black', 'LRMS-1-02', 1, now(), 'sys', now(), 'sys');

insert into product values(12,'液態橡膠金屬安全密封塗料5加侖','MetalSafe Sealant', 'LRMS-5-02', 4799, 100, 4599, '', '<img class="img-responsive" src="/img/product/12/descript/7.金屬安全密封膠_5加侖_all.jpg"></img>', '/img/product/12/product/7.金屬安全密封膠_MetalSafe_Sealant_5加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(12, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(12, '#000000;', '黑色', 'black', 'LRMS-5-02', 1, now(), 'sys', now(), 'sys');

insert into product values(13,'液態橡膠多用途底漆1加侖','Multi Purpose Primer', 'LRMP1', 2099, 100, 1999, '', '', '/img/product/13/product/8.多用途底漆_Multi_Purpose_Primer_1加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(13, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(13, '#FFFFFF;', '透明色', 'black', 'LRMP1', 1, now(), 'sys', now(), 'sys');

insert into product values(14,'液態橡膠多用途底漆5加侖','Multi Purpose Primer', 'LRMP5', 7499, 100, 6999, '', '', '/img/product/14/product/8.多用途底漆_Multi_Purpose_Primer_5加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(14, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(14, '#FFFFFF;', '透明色', 'black', 'LRMP5', 1, now(), 'sys', now(), 'sys');

insert into product values(15,'皇家黏合劑EPDM，TPO底漆1加侖','EPDM TPO Primer', 'LREP1', 2549, 100, 2399, '', '', '/img/product/15/product/9.EPDM_TPO_Primer_1加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(15, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(15, '#FFFFFF;', '透明色', 'black', 'LREP1', 1, now(), 'sys', now(), 'sys');

insert into product values(16,'透明塗層1加侖','Clear Coat', 'LRCC1', 2099, 100, 1999, '', '', '/img/product/16/product/10.透明塗層_Clear_Coat_1加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(16, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(16, '#FFFFFF;', '透明色', 'black', 'LRCC1', 1, now(), 'sys', now(), 'sys');

insert into product values(17,'透明塗層5加侖','Clear Coat', 'LRCC5', 7499, 100, 6999, '', '', '/img/product/17/product/10.透明塗層_Clear_Coat_5加侖.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(17, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(17, '#FFFFFF;', '透明色', 'black', 'LRCC5', 1, now(), 'sys', now(), 'sys');

insert into product values(18,'液態橡膠接縫膠帶2”x5’ (1.5m)','Liquid Rubber Seam Tape', 'LRST-2X5-20', 299, 100, 299, '', '', '/img/product/18/product/11.液態橡膠接縫膠帶2”x5’ (1.5m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(18, '1.5', 'm', '2”x5’ (1.5m)', '2”x5’ (1.5m)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(18, '#000000;', '黑色', 'black', 'LRST-2X5-20', 1, now(), 'sys', now(), 'sys');

insert into product values(19,'液態橡膠接縫膠帶2”x15’ (4.5m)','Liquid Rubber Seam Tape', 'LRST-2X15-20', 599, 100, 599, '', '', '/img/product/19/product/11.液態橡膠接縫膠帶2”x15’ (4.5m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(19, '1.5', 'm', '2”x5’ (1.5m)', '2”x5’ (1.5m)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(18, '#000000;', '黑色', 'black', 'LRST-2X5-20', 1, now(), 'sys', now(), 'sys');

insert into product values(20,'液態橡膠接縫膠帶4”x5’ (1.5m)','Liquid Rubber Seam Tape', 'LRST-4X5-20', 599, 100, 599, '', '', '/img/product/20/product/11.液態橡膠接縫膠帶4”x5’ (1.5m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(20, '1.5', 'm', '2”x5’ (1.5m)', '2”x5’ (1.5m)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(18, '#000000;', '黑色', 'black', 'LRST-2X5-20', 1, now(), 'sys', now(), 'sys');

insert into product values(21,'液態橡膠接縫膠帶4”x15’ (4.5m)','Liquid Rubber Seam Tape', 'LRST-4X15-20', 1199, 100, 1199, '', '', '/img/product/21/product/11.液態橡膠接縫膠帶4”x15’ (4.5m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(21, '1.5', 'm', '2”x5’ (1.5m)', '2”x5’ (1.5m)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(18, '#000000;', '黑色', 'black', 'LRST-2X5-20', 1, now(), 'sys', now(), 'sys');

insert into product values(22,'液態橡膠接縫膠帶6”x5’ (1.5m)','Liquid Rubber Seam Tape', 'LRST-6X5-20', 899, 100, 899, '', '', '/img/product/22/product/11.液態橡膠接縫膠帶6”x5’ (1.5m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(22, '1.5', 'm', '2”x5’ (1.5m)', '2”x5’ (1.5m)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(18, '#000000;', '黑色', 'black', 'LRST-2X5-20', 1, now(), 'sys', now(), 'sys');

insert into product values(23,'液態橡膠接縫膠帶6”x15’ (4.5m)','Liquid Rubber Seam Tape', 'LRST-6X15-20', 1649, 100, 1649, '', '', '/img/product/23/product/11.液態橡膠接縫膠帶6”x15’ (4.5m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(23, '1.5', 'm', '2”x5’ (1.5m)', '2”x5’ (1.5m)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(18, '#000000;', '黑色', 'black', 'LRST-2X5-20', 1, now(), 'sys', now(), 'sys');

insert into product values(24,'液態橡膠防潑水布4”x50’(15m)','Liquid Rubber Geo Textile', 'LRGT-4X50-10', 449, 100, 449, '', '', '/img/product/24/product/12.液態橡膠防潑水布4”x50’(15m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(24, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(24, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(25,'液態橡膠防潑水布4”x300’(91m)','Liquid Rubber Geo Textile', 'LRGT-4X300-10', 1949, 100, 1949, '', '', '/img/product/25/product/12.液態橡膠防潑水布4”x300’(91m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(25, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(24, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(26,'液態橡膠防潑水布40”x50’(15m)','Liquid Rubber Geo Textile', 'LRGT3', 1499, 100, 1499, '', '', '/img/product/26/product/12.液態橡膠防潑水布40”x50’(15m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(26, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(24, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(27,'液態橡膠防潑水布_40”x324’(99m)','Liquid Rubber Geo Textile', 'LRGT4', 7499, 100, 7499, '', '', '/img/product/27/product/12.液態橡膠防潑水布40”x324’(99m).jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(27, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(24, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(28,'紋理滾筒','Textured_Roller', 'LRTR', 359, 100, 359, '', '', '/img/product/28/product/13.紋理滾筒.jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(28, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(28, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(29,'Enviro輥編織加套管','Enviro Roller Woven Refill Sleeve', 'LRER', 150, 100, 150, '', '', '/img/product/29/product/14.編織滾筒補充.jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(29, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(28, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');


insert into product values(30,'4吋毛刷(100公釐)','4"Bristle Brush (100mm)', 'LRB-4', 210, 100, 210, '', '', '/img/product/30/product/15.4吋毛刷.jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(30, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(30, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(31,'2.5吋角度刷(64公釐)','2.5"Angle_Brush_(64mm)', 'LRB-2.5', 180, 100, 180, '', '', '/img/product/31/product/16.2.5吋角刷.jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(31, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(31, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(32,'液態橡膠溝槽修復套組','Liquid Rubber Gutter Repair Kit', 'LRGRK', 599, 100, 569, '', '', '/img/product/32/product/17.溝槽修復套組.jpg', 3, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(32, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(32, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(33,'防水密封劑1G工具包','Waterproof Sealant 1G Kit', 'LRWK-1', 2849, 100, 2699, '', '', '/img/product/33/product/18.防水密封劑1加侖工具包.jpg', 3, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(33, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(33, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(34,'防水密封劑5G工具包','Waterproof Sealant 5G Kit', 'LRWK-5', 6749, 100, 6399, '', '', '/img/product/34/product/19.防水密封劑5加侖工具包.jpg', 3, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(34, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(34, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(35,'液態橡膠RV車頂密封5G工具包','RV Roof 5G Kit', 'LRRK', 11999, 100, 11399, '', '', '/img/product/35/product/20.RV車頂密封5加侖工具包.jpg', 3, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(35, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(35, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');


insert into APP_USER values('simon', '王大明', 'cyliu0315@gmail.com', '$2a$10$NnlqpBH.dJZLqG//0IWoG.W8IowSknbv4yIoW0rHpvbQRrHg3LeFC', '116', '臺北市','文山區','地址',to_timestamp('01-08-1981 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'),NULL, 'sys',NULL,'sys', '1', to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 1, '09123456789');
insert into APP_USER values('simon1', '王大明', 'cyliu0315@gmail.com', '$2a$10$NnlqpBH.dJZLqG//0IWoG.W8IowSknbv4yIoW0rHpvbQRrHg3LeFC', '116', '臺北市','文山區','地址',to_timestamp('01-08-1981 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'),NULL, 'sys',NULL,'sys', '1', to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 1, '09123456789');
insert into APP_USER values('liquidrubbertaiwan', 'liquidrubbertaiwan', 'liquidrubbertaiwan@gmail.com', '$2a$10$NnlqpBH.dJZLqG//0IWoG.W8IowSknbv4yIoW0rHpvbQRrHg3LeFC', '247', '新北市','蘆洲區','復興路410巷11-15號',to_timestamp('01-08-1981 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'),NULL, 'sys',NULL,'sys', '1', to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 1, '09123456789');

insert into USER_ROLE values('simon', 'ROLE_ADMIN', 1);
insert into USER_ROLE values('simon', 'ROLE_USER', 1);
insert into USER_ROLE values('simon', 'ROLE_ANONYMOUS', 1);

insert into USER_ROLE values('simon1', 'ROLE_ADMIN', 1);
insert into USER_ROLE values('simon1', 'ROLE_USER', 1);
insert into USER_ROLE values('simon1', 'ROLE_ANONYMOUS', 1);

insert into USER_ROLE values('liquidrubbertaiwan', 'ROLE_ADMIN', 1);
insert into USER_ROLE values('liquidrubbertaiwan', 'ROLE_USER', 1);
insert into USER_ROLE values('liquidrubbertaiwan', 'ROLE_ANONYMOUS', 1);

insert into ORDER_STATUS values(1, 1, '訂單成立，待付款');
insert into ORDER_STATUS values(2, 2, '付款確認中');
insert into ORDER_STATUS values(3, 3, '已付款，待出貨');
insert into ORDER_STATUS values(4, 4, '已出貨');
insert into ORDER_STATUS values(5, 5, '待收貨');
insert into ORDER_STATUS values(6, 6, '完成');
insert into ORDER_STATUS values(7, 7, '取消');

insert into CUSTOMER_ORDER VALUES(1, '20180916010101001', 502, 'simon', 1, now(), 'simon', now(), 'simon', 100, 2);

insert into ORDER_DETAIL VALUES('20180916010101001', 1, 201, 100, '防水漆01', 2, '#000000;');
insert into ORDER_CONTRACT VALUES('20180916010101001', '王郵寄', 'aaa@gmail.com', '116', '台北市', '文山區', '羅斯福路五萬218號', '0931886655');
insert into APPLICATION_LIST VALUES (1, '基礎密封', '工程：地基密封<br/>
說明：解決任何可能滲漏的地下水問題是地基的重點。<br/>
<br/>
能將地基完善密封防堵地下水是非常重要的。<br/>
<br/>
在清潔地基灰塵和污垢後，沿邊緣和接縫處塗上一層液態橡膠基礎密封密封塗料。<br/>
在塗上的液態橡膠前施作一層防潑水布用於加強且妥善橋接裂縫/接縫。<br/>
全面用2mm的厚度覆蓋整個表面。<br/>
<br/>
感謝使用液體橡膠，客戶得以擁有完全無縫、友善環境極為防水的塗層。<br/>', 1, '/common/image/應用案例/1.Foundation_sealing基礎密封/1.Waterproofing-basement.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST VALUES (2, '立面牆防水', '工程：使用液體橡膠施作外牆防水<br/>
描述：目前荷蘭最複雜的建築之一是由BAM建造。<br/>
這座建築建在格羅寧根市中心，涵蓋許多難度高的瀝青立面。由於這些複雜的角度，完善的防水是非常重要的。<br/>
液態橡膠用於密封所有接縫、連點、窗框連接處甚至整個立面體。<br/>
<br/>
液態橡膠因具有無縫且高度彈性的防護膜而被挑選使用。<br/>
在用液態橡膠密封外牆後，安裝隔熱材和天然石材。<br/>
<br/>
這棟超高建築也成為了格羅寧根市的宏偉景點。<br/>', 1, '/common/image/應用案例/2.Forum_Groningen立面牆防水/1.Facade-sealant.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST VALUES (3, '地面細節', '工程：地面細節<br/>
描述：為了確保地下水不會滲漏到地面，建議用液態橡膠密封地面細節。<br/>
液態橡膠形成防堵水滲流的防水膜。<br/>
<br/>
首先徹底清潔地面，去除沙子和其它鬆脫的物質。<br/>
然後將一層液態橡膠基礎密封密封塗料與防潑水布結合使用補強立柱和角落。<br/>
再施作第二層液態橡膠基礎密封密封塗料完成表面。<br/>', 1, '/common/image/應用案例/3.Ground_level_detail地面細節/1.Fundering-lekkage.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST VALUES (4, '氣密窗框', '工程：氣密窗框<br/>
描述：空氣滲入窗框周圍是非常普遍的問題。許多產品無法落實氣密密封。<br/>
使用液態橡膠基礎密封密封塗料可以密封難度高的窗緣和框架錨。<br/>
<br/>
使用刷子塗抹液態橡膠基礎密封密封塗料在窗框周圍。必要時，使用一層防潑水布來補強。<br/>
幾乎在所有表面都具有很好的附著力，液態橡膠基礎密封密封塗料創造了出色的氣密密封性。<br/>', 1, '/common/image/應用案例/4.Airtight_window_frames氣密窗框/1.hbs200-geotextiel.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST VALUES (5, '陽台密封', '工程：噴塗使用液態橡膠防水密封塗料密封陽台<br/>
描述：陽台總是暴露在水和溫度變化中。因此，在表層設置前施作優良的防水層是非常重要的。<br/>
<br/>
在露台上，首先使用液態橡膠基礎密封密封塗料結合防潑水布預先處理。<br/>
將防潑水布放置液態橡膠基礎密封密封塗料上後，第二層液態橡膠基礎密封密封塗料直接施作上去。如此操作，是完全防水的關鍵細節。<br/>
然後，噴塗液態橡膠防水密封塗料作全面處理。<br/>
選用液態橡膠防水密封塗料是因為表層瓷磚層受液態橡膠防護不會暴露於紫外線下。<br/>
液態橡膠基礎密封密封塗料和液態橡膠防水密封塗料固化成保證900％的極高彈性性的無縫膜。<br/>
當產品固化時，液體橡膠膜將黏附在水泥基底的瓷磚上。<br/>
<br/>
高品質防水層在瓷磚系統下將持續多年。<br/>', 1, '/common/image/應用案例/5.Balcony_sealing陽台密封/1.1-component-balkon-coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST VALUES (6, '屋頂防水', '工程：使用液體橡膠防水密封塗料噴塗屋頂。<br/>
描述：液態橡膠的許多獨特產品特性之一是幾乎在各種表面上都能貼合。此特性與900％的高彈性相結合，極適合橋接2個不同表面。 <br/>
<br/>
這種混凝土屋頂有木造立柱，表面通常難以處理防水。液態橡膠具有高品質且簡易施作。<br/>
首先，使用液態橡膠基礎密封密封塗料結合防潑水布處理立柱。在防水層上直接施作第二層液態橡膠。<br/>
然後，噴塗2mm厚度液態橡膠防水密封塗料在整個表面上。產品在固化後可落實為完全防水，抗紫外線和彈性膜層。<br/>
這是一種極簡便且高品質的防水模式方式。<br/>', 1, '/common/image/應用案例/6.Roof_waterproofing屋頂防水/1.Dakafdichting.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST VALUES (7, '防水地下室', '工程：地下室使用液態橡膠防水密封塗料防水<br/>
說明：如上所述，仔細密封地下室是很重要的。如果客戶希望整個地下室100％防水，那麼可以用液態橡膠覆蓋整個地窖。<br/>
如果整個地下室都覆蓋著液態橡膠，那麼價格當然會高於僅密封接縫的價格。但是通過密封整個地下室，客戶將獲取整100％全面的防水保證。<br/>
<br/>
在許多地方，地下水層太高，地下室牆壁處於恆定的水壓下。如在地下室混凝土牆出現小裂縫，只密封接縫是不夠的，因為水分會滲透到裂縫中。當液態橡膠的高彈性防水膜覆蓋整個地下室牆壁時，則保證100％防水。<br/>
<br/>
首先使用液態橡膠基礎密封密封塗料與防潑水布結合。這個關鍵細節確保以紮實的方式密封。<br/>
再直接噴塗液態橡膠防水密封塗料於地下室牆面上。該產品的塗層厚度為2mm，永久韌性為900％。<br/>
此高彈性確保能含蓋住裂縫移動的可能性。<br/>
 <br/>
液態橡膠防水密封塗料和液態橡膠基礎密封塗料作用形成一個固體膜層。高品質的密封可持續使用多年。<br/>', 1, '/common/image/應用案例/7.Waterproofing_basement防水地下室/1.Liquid-Rubber.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST VALUES (8, '排水溝槽裝修', '工程：使用液態橡膠基礎密封密封塗料改造排水溝  <br/>
描述：液態橡膠適用於許多建築用途。從地下到一直到建築的最高點。在此工程中，我們的施作人員不能懼高。這項工作是翻新教堂塔樓的溝槽。<br/>
<br/>
混凝土排水溝長時間滲漏。由於排水溝的高度和復雜邊角，液態橡膠防水密封膠塗料正適用。<br/>
只需輕裝準備些桶裝液態橡膠和一捲防潑水布。不需要攜帶沉重的瀝青滾輪或EPDM至高點。<br/>
在徹底清潔表面後，將液態橡膠防水密封膠塗料從水平表面施作到支柱上。防潑水布確保兩個平面之間的良好橋接。然後塗上2mm厚的液態橡膠防水密封膠塗料層處理整個排水溝。<br/>
以相當簡單的方法處理排水溝的防水問題。在施作期間施塗者有著優美景色相伴。<br/>', 1, '/common/image/應用案例/8.Gutter_renovation天溝裝修/1.1-component-coating-1.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST VALUES (9, '儲水池防水', '工程：採用液態橡膠防水密封塗料噴塗防水儲水池。<br/>
描述：談到飲用水方面，正是南非市長所面臨的問題。水的儲存量很低，家庭有義務節約用水，才不至於將緩存的水用完。<br/>
巨大的水壩儲存雨水和山水。這些水被過濾到飲用水中。很多酒廠都有自己儲水供自己使用。儲水是非常重要的事，不可以洩漏半滴。液態橡膠防水密封塗料非常適合密封整個儲水池。<br/>
噴塗厚度2mm的液態橡膠防水密封塗料在整個存儲器內部。液態橡膠噴防水密封塗料塗完成後，完全防水且100％防紫外線。<br/>
<br/>
這樣整個儲水池完全防水。將一滴不漏的度過南非困難乾旱時期。<br/>', 1, '/common/image/應用案例/9.Waterproofing_water_storages防水儲水/1.Liquid-Rubber-on-concrete.png', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST_IMAGE VALUES (1, '甲板1', '/common/image/應用案例/1.Foundation_sealing基礎密封/1.Waterproofing-basement.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (1, '甲板2', '/common/image/應用案例/1.Foundation_sealing基礎密封/2.Sealing-fundation.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (1, '甲板3', '/common/image/應用案例/1.Foundation_sealing基礎密封/3.Liquid-Rubber-Coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (1, '甲板4', '/common/image/應用案例/1.Foundation_sealing基礎密封/4.Sealing-a-basement.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (1, '甲板5', '/common/image/應用案例/1.Foundation_sealing基礎密封/5.Seamless-basement-coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (1, '甲板6', '/common/image/應用案例/1.Foundation_sealing基礎密封/6.Waterproofing-coating-basement.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (1, '甲板7', '/common/image/應用案例/1.Foundation_sealing基礎密封/7.Liquid-Coating-for-waterproofing.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST_IMAGE VALUES (2, '甲板1', '/common/image/應用案例/2.Forum_Groningen立面牆防水/1.Facade-sealant.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (2, '甲板2', '/common/image/應用案例/2.Forum_Groningen立面牆防水/2.Liquid-Coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (2, '甲板3', '/common/image/應用案例/2.Forum_Groningen立面牆防水/3.Seamless-rubber-coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (2, '甲板4', '/common/image/應用案例/2.Forum_Groningen立面牆防水/4.Rubber-coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (2, '甲板5', '/common/image/應用案例/2.Forum_Groningen立面牆防水/5.Flexible-coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (2, '甲板6', '/common/image/應用案例/2.Forum_Groningen立面牆防水/6.Rubbercoating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (2, '甲板7', '/common/image/應用案例/2.Forum_Groningen立面牆防水/7.Liquid-Rubber-coating.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST_IMAGE VALUES (3, '甲板1', '/common/image/應用案例/3.Ground_level_detail地面細節/1.Fundering-lekkage.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (3, '甲板2', '/common/image/應用案例/3.Ground_level_detail地面細節/2.Fundering-waterdicht-maken.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (3, '甲板3', '/common/image/應用案例/3.Ground_level_detail地面細節/3.Maaiveld-detail.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST_IMAGE VALUES (4, '甲板1', '/common/image/應用案例/4.Airtight_window_frames氣密窗框/1.hbs200-geotextiel.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (4, '甲板2', '/common/image/應用案例/4.Airtight_window_frames氣密窗框/2.Kozijnafdichting.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (4, '甲板3', '/common/image/應用案例/4.Airtight_window_frames氣密窗框/3.kozijnen-inpakken-met-liquid-rubber.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (4, '甲板4', '/common/image/應用案例/4.Airtight_window_frames氣密窗框/4.liquid-rubber-bestand-tegen-hoge-belasting.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (4, '甲板5', '/common/image/應用案例/4.Airtight_window_frames氣密窗框/5.Luchtdicht-bouwen.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST_IMAGE VALUES (5, '甲板1', '/common/image/應用案例/5.Balcony_sealing陽台密封/1.1-component-balkon-coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (5, '甲板2', '/common/image/應用案例/5.Balcony_sealing陽台密封/2.Balkon-sealen.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (5, '甲板3', '/common/image/應用案例/5.Balcony_sealing陽台密封/3.Blijvend-elastische-coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (5, '甲板4', '/common/image/應用案例/5.Balcony_sealing陽台密封/4.Milieuvriendelijke-balkon-coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (5, '甲板5', '/common/image/應用案例/5.Balcony_sealing陽台密封/5.vloeibaar-rubber-liquid-rubber.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST_IMAGE VALUES (6, '甲板1', '/common/image/應用案例/6.Roof_waterproofing屋頂防水/1.Dakafdichting.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (6, '甲板2', '/common/image/應用案例/6.Roof_waterproofing屋頂防水/2.dakdetails-waterdicht-maken.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (6, '甲板3', '/common/image/應用案例/6.Roof_waterproofing屋頂防水/3.duurzame-waterafdichting.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (6, '甲板4', '/common/image/應用案例/6.Roof_waterproofing屋頂防水/4.hb-s-200.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (6, '甲板5', '/common/image/應用案例/6.Roof_waterproofing屋頂防水/5.Makkelijk-appliceerbaar.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (6, '甲板6', '/common/image/應用案例/6.Roof_waterproofing屋頂防水/6.vloeibaar-rubber-aanbrengen.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST_IMAGE VALUES (7, '甲板1', '/common/image/應用案例/7.Waterproofing_basement防水地下室/1.Liquid-Rubber.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (7, '甲板2', '/common/image/應用案例/7.Waterproofing_basement防水地下室/2.Liquid-Rubber-Belowgrade.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (7, '甲板3', '/common/image/應用案例/7.Waterproofing_basement防水地下室/3.kelderwand-waterdicht-maken.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (7, '甲板4', '/common/image/應用案例/7.Waterproofing_basement防水地下室/4.Kelder-afdichting.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST_IMAGE VALUES (8, '甲板1', '/common/image/應用案例/8.Gutter_renovation天溝裝修/1.1-component-coating-1.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (8, '甲板2', '/common/image/應用案例/8.Gutter_renovation天溝裝修/2.beton-waterafdichting-1.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (8, '甲板3', '/common/image/應用案例/8.Gutter_renovation天溝裝修/3.corrosie-bescherming-1.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (8, '甲板4', '/common/image/應用案例/8.Gutter_renovation天溝裝修/4.hbs200-aanbrengen-1.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (8, '甲板5', '/common/image/應用案例/8.Gutter_renovation天溝裝修/5.milieuvriendelijke-coating.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (8, '甲板6', '/common/image/應用案例/8.Gutter_renovation天溝裝修/6.Renovatie-goot.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (8, '甲板7', '/common/image/應用案例/8.Gutter_renovation天溝裝修/7.vloeibaar-rubber.jpg', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (8, '甲板8', '/common/image/應用案例/8.Gutter_renovation天溝裝修/8.waterdicht-bouwen.jpg', now(), 'sys', now(), 'sys');

insert into APPLICATION_LIST_IMAGE VALUES (9, '甲板1', '/common/image/應用案例/9.Waterproofing_water_storages防水儲水/1.Liquid-Rubber-on-concrete.png', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (9, '甲板2', '/common/image/應用案例/9.Waterproofing_water_storages防水儲水/2.Concrete-protection.png', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (9, '甲板3', '/common/image/應用案例/9.Waterproofing_water_storages防水儲水/3.Spraygrade-on-concrete.png', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (9, '甲板4', '/common/image/應用案例/9.Waterproofing_water_storages防水儲水/4.Waterbasins.png', now(), 'sys', now(), 'sys');
insert into APPLICATION_LIST_IMAGE VALUES (9, '甲板5', '/common/image/應用案例/9.Waterproofing_water_storages防水儲水/5.Waterstorage-with-liquid-rubber.png', now(), 'sys', now(), 'sys');


insert into PAYMENT_METHOD values(1, 1, '信用卡一次付清', 'credit card', null,  1);
insert into PAYMENT_METHOD values(2, 2, 'ATM轉帳', 'ATM','提供底下帳戶轉帳000-00000000000', 1);

insert into SHIPPING_METHOD values(1, 1, '宅配 ', 'black cat', '',  1, 0);
insert into SHIPPING_METHOD values(2, 2, '快遞 ', 'FEDX','TEST', 0, 0);

insert into Product_TAG_RELATION values (13, 1, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (14, 1, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (22, 1, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (1, 1, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (2, 1, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (4, 1, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (5, 1, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (30, 1, now(), 'sys', now(), 'sys' );
insert into Product_TAG values (1, '防水密封塗料', 1);

insert into Product_TAG_RELATION values (20, 2, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (19, 2, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (22, 2, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (10, 2, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (11, 2, now(), 'sys', now(), 'sys' );
--insert into Product_TAG_RELATION(19, 2, now(), 'sys', now(), 'sys' );
--insert into Product_TAG_RELATION(4, 2, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (5, 2, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (30, 2, now(), 'sys', now(), 'sys' );
insert into Product_TAG values (2, '甲板密封塗料', 1);

insert into Product_TAG_RELATION values (20, 3, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (19, 3, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (22, 3, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (6, 3, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (7, 3, now(), 'sys', now(), 'sys' );
--insert into Product_TAG_RELATION(19, 3, now(), 'sys', now(), 'sys' );
--insert into Product_TAG_RELATION(4, 3, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (5, 3, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (30, 3, now(), 'sys', now(), 'sys' );
insert into Product_TAG values (3, 'RV車頂密封塗料', 1);

insert into Product_TAG_RELATION values (13, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (14, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (15, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (11, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (12, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (3, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (4, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (16, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (17, 4, now(), 'sys', now(), 'sys' );

insert into Product_TAG_RELATION values (18, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (19, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (20, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (21, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (22, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (23, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (24, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (25, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (26, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (27, 4, now(), 'sys', now(), 'sys' );
insert into Product_TAG values (4, '金屬安全密封塗料', 1);

insert into Product_TAG_RELATION values (20, 5, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (19, 5, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (22, 5, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (4, 5, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (5, 5, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (30, 5, now(), 'sys', now(), 'sys' );
insert into Product_TAG values (5, '彩色防水密封塗料', 1);

insert into Product_TAG_RELATION values (13, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (14, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (15, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (9, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (10, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (3, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (4, 6, now(), 'sys', now(), 'sys' );

insert into Product_TAG_RELATION values (18, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (19, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (20, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (21, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (22, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (23, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (24, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (25, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (26, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG_RELATION values (27, 6, now(), 'sys', now(), 'sys' );
insert into Product_TAG values (6, '基礎密封塗料', 1);
commit;