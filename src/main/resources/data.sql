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

insert into product values(1 ,'液態橡膠防水密封塗料(中塗層)','Waterproof Sealant', 'LRWS-1-02', 201, 100, 201, '防水密封膠塗料Liquid_Rubber_Waterproof_Sealant_3.78L-1加侖-簡單描述', '防水密封膠塗料Liquid_Rubber_Waterproof_Sealant_3.78L-1加侖-描述', '/img/product/1.防水密封膠塗料Liquid_Rubber_Waterproof_Sealant_3.78L-1加侖1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(1, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(1, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(2 ,'液態橡膠防水密封塗料(中塗層)','Waterproof Sealant', 'LRWS-5-02', 202, 100, 202, '強效02描述', '強效02描述', '/img/product/1.防水密封膠塗料Waterproof Sealant-Coating-_18.9L-5加侖1000.jpg', 1, 1, 1, 1, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 1, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(2, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(2, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(3 ,'防水密封膠塗料-946ML-1_Quart','Waterproof Sealant-Coating', 'LRWS-1-02', 203, 80, 162, '強效03簡單描述', '<p>來自加拿大的防水材料</p><br/><img class="img-responsive" src="/img/484810.jpg"></img>', '/img/product/1.防水密封膠塗料Waterproof Sealant-Coating-946ML-1_Quart1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(3, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(3, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(4 ,'彩色防水密封膠.塗料_3.78L-1加侖','Colour Waterproof Sealant-Coating', 'LRWS-1-02', 204, 100, 204, '強效04簡單描述', '強效04描述', '/img/product/2.彩色防水密封膠.塗料Colour Waterproof Sealant-  Coating_3.78L-1加侖1000.jpg', 1, 1, 1, 1, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 1, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(4, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#FFFFFF;', '白色', 'while', 'HR-11001', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(4, '#C1B6A9;', '淺灰', 'light-gray', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(5 ,'彩色防水密封膠.塗料_18.9L-5加侖','Colour Waterproof Sealant-Coating', 'LRWS-1-02', 205, 100, 205, '強效05簡單描述', '強效05描述', '/img/product/2.彩色防水密封膠.塗料Colour Waterproof Sealant-  Coating_18.9L-5加侖1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(5, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(5, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(6 ,'RV屋頂塗料-_18.9L-5加侖','RV Roof Coating', 'LRWS-1-02', 206, 100, 206, '強效06簡單描述', '強效06描述', '/img/product/3.RV屋頂塗料RV Roof Coating-_18.9L-5加侖1000.jpg', 1, 1, 1, 1, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(6, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(6, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(7 ,'RV屋頂塗料-3.78L-1加侖','RV Roof Coating', 'LRWS-1-02', 207,  80, 166, '強效07簡單描述', '強效07描述', '/img/product/3.RV屋頂塗料RV Roof Coating-3.78L-1加侖1000.jpg', 1, 1, 1, 1, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 1, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(7, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(7, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(8 ,'甲板和碼頭塗料_3.78L-1加侖','Cool Foot Deck_Dock Coating', 'LRWS-1-02', 208, 100, 208, '強效08簡單描述', '強效08描述', '/img/product/4.甲板和碼頭塗料Cool Foot Deck _ Dock Coating_3.78L-1加侖1000.jpg', 1, 1, 1, 1, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 1, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(8, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(8, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(9 ,'甲板和碼頭塗料_18.9L-5加侖','Cool Foot Deck_Dock Coating', 'LRWS-1-02', 209, 100, 209, '強效09簡單描述', '強效09描述', '/img/product/4.甲板和碼頭塗料Cool Foot Deck _ Dock Coating_18.9L-5加侖1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(9, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(9, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(10,'聚氨酯甲板塗料_18.9L-5加侖','Polyurethane Deck Coating', 'LRWS-1-02', 210, 50, 105, '強效010簡單描述', '強效10描述', '/img/product/5.聚氨酯甲板塗料Polyurethane Deck Coating_18.9L-5加侖1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(10, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(10, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(11,'聚氨酯甲板塗料-3.78L-1加侖','Polyurethane Deck Coating', 'LRWS-1-02', 211, 40, 100, '強效011簡單描述', '強效11描述', '/img/product/5.聚氨酯甲板塗料Polyurethane Deck Coating-3.78L-1加侖1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(11, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(11, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(12,'基礎密封膠.地下室塗料_3.78L-1加侖','Foundation Sealant-Basement Coating', 'LRWS-1-02', 212, 40, 100, '強效12簡單描述', '強效12描述', '/img/product/6.基礎密封膠.地下室塗料Foundation Sealant-Basement Coating_3.78L-1加侖1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(12, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(12, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(13,'基礎密封膠.地下室塗料-18.9L-5加侖','Foundation Sealant-Basement Coating', 'LRWS-1-02', 213, 50, 107, '強效13簡單描述', '強效13描述', '/img/product/6.基礎密封膠.地下室塗料Foundation Sealant-Basement Coating-18.9L-5加侖1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(13, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(13, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(14,'金屬安全密封膠-3.78L-1加侖','MetalSafe Sealant', 'LRWS-1-02', 214, 30, 73, '強效14簡單描述', '強效14描述', '/img/product/7.金屬安全密封膠MetalSafe Sealant-3.78L-1加侖1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(14, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(14, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(15,'金屬安全密封膠-18.9L-5加侖','MetalSafe Sealant', 'LRWS-1-02', 215, 90, 200, '強效15簡單描述', '強效15描述', '/img/product/7.金屬安全密封膠MetalSafe Sealant-18.9L-5加侖1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(15, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(15, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(16,'膠帶-大包裝','Seam Tape', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/8.膠帶Seam Tape-大包裝-1000.jpg', 4, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(16, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(16, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(17,'膠帶-小包裝','Seam Tape', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/8.膠帶Seam Tape-小包裝-1000.jpg', 4, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(17, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(17, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(18,'土工布','Products Geo Textile', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/9.土工布Products Geo Textile-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(18, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(18, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(19,'多用途底漆_18.9L-5加侖','Multi-Purpose Primer', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/10.多用途底漆Multi-Purpose Primer_18.9L-5加侖-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(19, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(19, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(20,'多用途底漆-1加侖','Multi-Purpose Primer-3.78L', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/10.多用途底漆Multi-Purpose Primer-3.78L-1加侖-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(20, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(20, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(21,'EPDM TPO Primer','EPDM TPO Primer', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/11.EPDM TPO Primer.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(21, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(21, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(22,'EPDM TPO Primer-3.78L-1加侖','EPDM TPO Primer-3.78L', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/11.EPDM TPO Primer-3.78L-1加侖-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(22, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(22, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(23,'EPDM TPO Primer-14oz盎司噴霧罐','EPDM TPO Primer-14oz', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/11.EPDM TPO Primer-14oz盎司噴霧罐.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(23, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(23, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(24,'防水密封劑5G工具包5件組-5加侖','Waterproof Sealant 5G Kit-18.9L', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/12.防水密封劑5G工具包5件組Waterproof Sealant 5G Kit-18.9L-5加侖-1000.jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(24, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(24, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(25,'防水密封劑1G工具包5件組-1加侖','Waterproof Sealant 1G Kit-3.78L', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/13.防水密封劑1G工具包5件組Waterproof Sealant 1G Kit-3.78L-1加侖1000.jpg', 2, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(25, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(25, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(26,'RV屋頂塗層工具包10件組-5加侖','RV Roof Coating Kit-18.9L', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/14.RV屋頂塗層工具包10件組RV Roof Coating Kit-18.9L-5加侖-1000.jpg', 3, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(26, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(26, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(27,'RV屋頂維修工具包8件組-1加侖','RV Roof Repair Kit-3.78L', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/15.RV屋頂維修工具包8件組RV Roof Repair Kit-3.78L-1加侖-1000.jpg', 3, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(27, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(27, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(28,'天溝維修工具5件組','Products Gutter Repair Kit', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/16.天溝維修工具5件組Products Gutter Repair Kit-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(28, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(28, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(30,'透明塗層-1加侖','Clear Coat-3.78L', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/17.透明塗層Clear Coat-3.78L-1加侖-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(30, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(30, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(31,'紋理輥','Liquid_Rubber_Textured_Roller', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/18.紋理輥Liquid_Rubber_Textured_Roller-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(31, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(31, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(32,'Enviro輥編織加套管','Enviro-Roller Woven Refill Sleeve', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/19.Enviro輥編織加套管Enviro-Roller Woven Refill Sleeve-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(32, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(32, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(33,'4吋毛刷(100公釐)','4_Inch_Bristle_Brush', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/20.4吋毛刷(100公釐)4_Inch_Bristle_Brush-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(33, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(33, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(34,'丁腈手套','Nitrile-Gloves', 'LRWS-1-02', 215, 90, 200, '強效15描述', '強效03簡單描述', '/img/product/21.丁腈手套Nitrile-Gloves-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(34, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(34, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(35,'2.5吋角度刷(64公釐)','2.5inchAngle-Brush', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/22.2.5吋角度刷(64公釐)2.5inchAngle-Brush-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(35, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(35, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(36,'混凝土蝕刻劑','Concrete-Etch', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/23.混凝土蝕刻劑Concrete-Etch-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(36, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(36, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(37,'Rapidfix瞬間膠','Rapidfix-Instant-Glue', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/24.Rapidfix瞬間膠Rapidfix-Instant-Glue-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(37, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
insert into PRODUCT_COLOR VALUES(37, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');

insert into product values(38,'環氧樹脂濕粘膠水','Epoxy-Wet-Bond-Glue', 'LRWS-1-02', 215, 90, 200, '強效03簡單描述', '強效15描述', '/img/product/25.環氧樹脂濕粘膠水Epoxy-Wet-Bond-Glue-1000.jpg', 1, 1, 1, 0, to_timestamp('01-08-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), to_timestamp('30-09-2018 18:47:52.69', 'dd-MM-yyyy HH24:MI:ss'), 60, 50, 0, now(), 'sys', now(), 'sys', 100);
insert into PRODUCT_CAPACITY values(38, '3.78', 'L', '1加侖(3.78L)', '1加侖(3.78L)', 1, now(), 'sys', now(), 'sys');
--insert into PRODUCT_COLOR VALUES(38, '#000000;', '黑色', 'black', 'HR-11001', 1, now(), 'sys', now(), 'sys');


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

insert into CUSTOMER_ORDER VALUES(1, '20180916010101001', 201, 'simon', 1, now(), 'simon', now(), 'simon', 100, 2);

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

insert into SHIPPING_METHOD values(1, 1, '宅配免運 ', 'balck cat', '黑貓宅急便寄送.',  1, 0);
insert into SHIPPING_METHOD values(2, 2, '快遞 ', 'FEDX','TEST', 1, 0);

commit;