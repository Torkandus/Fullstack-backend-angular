INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)VALUES ('123 Maple St','London','On', 'N1N-1N1','(555)555-5555','Trusted','ABC Supply Co.','abc@supply.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('543 Sycamore Ave','Toronto','On', 'N1P-1N1','(999)555-5555','Trusted','Big Bills Depot','bb@depot.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('922 Oak St','London','On', 'N1N-1N1','(555)555-5599','Untrusted','Shady Sams','ss@underthetable.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('123 Ex St','London','On', 'E1E-1X1','(555)555-5566','Trusted','James Place','jh@place.com');
--add products into database
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('12X45',1,'Cardboard Box',129.98,159.99,45,15,50,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('14X45',1,'Wooden Box',229.98,259.99,35,14,40,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('16X45',1,'Aluminum Box',329.98,359.99,25,13,30,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('18X45',1,'Glass Box',429.98,459.99,15,12,20,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('12Y45',2,'Cardboard Box',129.98,159.99,45,15,40,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('14Y45',2,'Wooden Box',229.98,259.99,35,14,40,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('16Y45',2,'Aluminum Box',329.98,359.99,25,13,30,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('18Y45',2,'Glass Box',429.98,459.99,15,12,20,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('12Z45',3,'Cardboard Box',129.98,159.99,45,15,50,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('14Z45',3,'Wooden Box',229.98,259.99,35,14,40,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('16Z45',3,'Aluminum Box',329.98,359.99,25,13,30,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('18Z45',3,'Glass Box',429.98,459.99,15,12,20,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('12J45',4,'Cardboard Box',129.98,159.99,45,15,40,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('14J45',4,'Wooden Box',229.98,259.99,35,14,40,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('16J45',4,'Aluminum Box',329.98,359.99,25,13,30,0,' ',' ');
INSERT INTO Product (Id,VendorId,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO,QrCode,QrCodeTxt) VALUES ('18J45',4,'Glass Box',429.98,459.99,15,12,20,0,' ',' ');