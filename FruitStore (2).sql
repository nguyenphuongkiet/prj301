use master 
 alter database [FruitStore] set single_user with rollback immediate

 drop database [FruitStore]

raiserror('Creating FruitStore database....',0,1)
SET NOCOUNT ON
GO
USE [master]
GO
CREATE DATABASE [FruitStore]
GO
USE [FruitStore]
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[description] [nvarchar](300),
	[img] [nvarchar](500) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[price] [float] NOT NULL,
	[discount] [float] NOT NULL,
	)
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[address] [nvarchar](50) NOT NULL,
	[phone] [nvarchar](12) NOT NULL,
	[email] [nvarchar](30) NOT NULL,
	[password] [nvarchar](64) NOT NULL,
	[role] [nvarchar](255) NOT NULL
)
GO
create table Orders (
	id int identity(1,1) primary key not null,
	cId int references Account(id) not null,
	buyDate date not null,
	totalMoney money
)
CREATE TABLE [dbo].[OrderDetail](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[orderId] [int] references Orders(id) NOT NULL,
	[productId] [int] references Product(id) NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL
)
GO

SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (1, 'Very nice clean apples that harvested in US' ,'https://suckhoedoisong.qltns.mediacdn.vn/Images/haiyen/2018/12/10/tao.jpg',N'California Apple', 100, 0.5)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (2, 'Very nice clean apples that harvested in France' ,'https://th.bing.com/th/id/R.cc218d0b9f75747dcba3124f6a27c057?rik=f6nta9TqcKBw8A&riu=http%3a%2f%2f2.bp.blogspot.com%2f-hdkXfWeRV4A%2fTdizZhkLhhI%2fAAAAAAAAHUA%2fGlC3S4HL8vE%2fs1600%2f139865_1.jpg&ehk=bbwjHMEioXz7X1Fo4e%2b7q5zksOCAv8h5Rw8gKs7sGw8%3d&risl=&pid=ImgRaw&r=0',N'French Apple', 200, 0.2)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (3, 'Sweet Pineapple from Costa Rica', 'https://th.bing.com/th/id/OIP.dsgoAOgGcmdPk_iZCWee4wHaFe?pid=ImgDet&rs=1', N'Costarican Pineapple', 180, 0.15)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (4, 'Juicy Mango from India', 'https://th.bing.com/th/id/R.2c9274b83612e59843af6cc903e43db0?rik=omU2XYPk9%2b76lQ&pid=ImgRaw&r=0', N'Indian Mango', 200, 0.2)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (5, 'Tart Granny Smith Apples from Washington', 'https://th.bing.com/th/id/R.1e0d49b34fa5932812666c94f2a2cc4c?rik=%2blk6H023p4%2bUsg&riu=http%3a%2f%2fnammamaligai.com%2fwp-content%2fuploads%2f2016%2f10%2fapple-type1.jpg&ehk=entuOgYTDWbXLaRJ3%2fRimWn1IyijD6ele24xBPeaBM0%3d&risl=&pid=ImgRaw&r=0', N'Granny Smith Apple', 80, 0.05)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (6, 'Sweet Red Apples from New York', 'https://th.bing.com/th/id/OIP.Adoyt0l-A2Hl4nBhETXT9gHaHa?pid=ImgDet&w=620&h=620&rs=1', N'New York Red Apple', 90, 0.02)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (7, 'Tangy Lemons from California', 'https://th.bing.com/th/id/OIP.35swSJ0tsKDyWTqoqsLlJwHaHa?pid=ImgDet&rs=1', N'California Lemon', 100, 0.1)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (8, 'Sweet Blueberries from Michigan', 'https://th.bing.com/th/id/R.557c0faa0ea031aac89555cfebb8c7c0?rik=p8yqfea6X6%2bM5A&riu=http%3a%2f%2fwww.newdirections.com.au%2fWebRoot%2fStore%2fShops%2fnewdirections%2f5E85%2f4A73%2f5618%2fB723%2fB338%2fC0A8%2fDA57%2f2D7C%2fEXTR500BLUEBERRPOW-500-g-Blueberry-Powder-Fruit-Herbal-Powder-Extracts-L-20200408.jpg&ehk=WRdrCReY6REJY1O64aKH2t%2fxzyxU%2bSul54J%2bcnQo1Ek%3d&risl=&pid=ImgRaw&r=0', N'Michigan Blueberry', 120, 0.05)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (9, 'Juicy Peaches from Georgia', 'https://th.bing.com/th/id/R.4b7a144069fa8f57d4faa50214e98a06?rik=OdGGIx9q%2fftujw&pid=ImgRaw&r=0', N'Georgia Peach', 150, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (10, 'Ripe Avocado from Mexico', 'https://th.bing.com/th/id/R.c72d7edfe419495937dc5c02bbcd85e7?rik=hRc18WgImzh3iw&riu=http%3a%2f%2fmassageinflorida.com%2fblog%2fwp-content%2fuploads%2f2012%2f04%2fAvocado-5918425_l1.jpg&ehk=kQRbjSajWN54cFW8D1%2fm6wLdOE3Fz8mu5K7aGxN12Q0%3d&risl=&pid=ImgRaw&r=0', N'Mexican Avocado', 200, 0.2)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (11, 'Freshly picked bananas from South America', 'https://article.innovamarketinsights360.com/articleimgs/article_images/637707518350315897rodrigo-dos-reis-DkTuGvgPotA-unsplash.jpg', N'South American Banana', 120, 0.3)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (12, 'Juicy oranges directly imported from Spain', 'https://www.ouinolanguages.com/thumb/Spanish/vocab/12/images/pic12.jpg', N'Spanish Orange', 80, 0.2)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (13, 'Red cherries handpicked from Canadian orchards', 'https://media.kidadl.com/Canadian_Red_Cherry_Problems_55fd66392c.jpg', N'Canadian Cherry', 150, 0.4)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (14, 'Sweet pineapples from tropical Hawaii', 'https://www.cierracandles.com/assets/images/hawaiianpineapple.jpg', N'Hawaiian Pineapple', 200, 0.1)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (15, 'Organic strawberries grown in California', 'https://th-thumbnailer.cdn-si-edu.com/XZ5lC_kAE9F_dtm9BrB8Vx_c9lA=/1000x750/filters:no_upscale()/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/39/3c/393c51d9-ce11-49ce-9d41-5ef599dfabea/bn8e34.jpg', N'California Strawberry', 90, 0.15)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (16, 'Succulent Pineapple from Hawaii', 'https://th.bing.com/th/id/R.fd77d91ee4b6dc52dae813eafc4e9cb5?rik=oiQ6LzIYr7pmrA&pid=ImgRaw&r=0', N'Vietnamese Pineapple', 200, 0.1)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (17, 'Juicy Watermelon from California', 'https://th.bing.com/th/id/R.e164dd5b52b1988f2ddc05ae3e6959e8?rik=h6y3NRH46iGPKA&riu=http%3a%2f%2fstatic.dailyforest.com%2fwp-content%2fuploads%2f2016%2f02%2f25190143%2fWatermelon.jpg&ehk=0IhGh6GfNTCl3dSyiMHpXajZHQ84yXe37xD%2f7d%2bO6CA%3d&risl=&pid=ImgRaw&r=0', N'California Watermelon', 150, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (18, 'Crisp Fuji Apples from Japan', 'https://th.bing.com/th/id/OIP.4R5nvmpaGQIvQpX_UQlKYAHaH2?pid=ImgDet&rs=1', N'Japanese Fuji Apple', 100, 0.2)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (19, 'Sweet Mangoes from the Philippines', 'https://th.bing.com/th/id/R.e755ce1c98e999584544f1ce8535fa30?rik=bPMQ8BBCaGY%2frA&riu=http%3a%2f%2fweknowyourdreams.com%2fimages%2fmango%2fmango-14.jpg&ehk=LflCTuwxBsQB6V0qYdjgMri8YzIggL6VT5Z%2bKqrYzWM%3d&risl=&pid=ImgRaw&r=0', N'Philippine Mango', 180, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (20, 'Tangy Oranges from Florida', 'https://th.bing.com/th/id/R.7ec7b9274628685518ed6bc00716f7f2?rik=flPSi63gfyTjUQ&pid=ImgRaw&r=0', N'Florida Orange', 120, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (21, 'Sweet Grapes from Chile', 'https://th.bing.com/th/id/R.e5514862fc6327e8c25fdf7ab93a0918?rik=Fed1lftGU28hTQ&pid=ImgRaw&r=0', N'Chilean Grape', 170, 0.15)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (22, 'Fresh Pears from France', 'https://th.bing.com/th/id/R.c4789ceeadf4d5111200b397d8d5b2c0?rik=Or%2btRhKM8294%2bg&pid=ImgRaw&r=0', N'French Pear', 150, 0.1)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (23, 'Ripe Papayas from Mexico', 'https://th.bing.com/th/id/R.3b8d3c95a31f3bea94e4127d0d2e03cc?rik=zkI2Th4Awow1xg&riu=http%3a%2f%2fproducemadesimple.ca%2fwp-content%2fuploads%2f2015%2f05%2fpapaya-cut-ss.jpg&ehk=4ThJemjmIlIQ72LKFVcY6tAf3m1r%2f2Bc73etnLNy%2bZU%3d&risl=&pid=ImgRaw&r=0', N'Mexican Papaya', 190, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (24, 'Pungent Durians from Thailand', 'https://healthjade.com/wp-content/uploads/2017/10/durian.jpg', N'Thai Durian', 250, 0.2)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (25, 'Sweet Lychees from China', 'https://th.bing.com/th/id/R.41abd67a0faf365977eab4730a5fb87f?rik=HHkeXEojHNtnRA&pid=ImgRaw&r=0', N'Chinese Lychee', 220, 0.05)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (26, 'Fresh Guavas from India', 'https://4.bp.blogspot.com/-tofBBnHbr7Y/V7z-pJ_Mh8I/AAAAAAAAIzo/nm1535fxPhcRewGlols5npAEwGqxvzO4QCLcB/s1600/guava.jpg', N'Indian Guava', 130, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (27, 'Tart Lemons from Italy', 'https://th.bing.com/th/id/OIP.xMN89Z68SvEEyujhctOYCwHaGb?pid=ImgDet&rs=1', N'Italian Lemon', 110, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (28, 'Sweet Cherries from Turkey', 'https://th.bing.com/th/id/R.296b8c36ffb90612432b66cb418f6896?rik=BUQD2mdOPLzRlA&pid=ImgRaw&r=0', N'Turkish Cherry', 160, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (29, 'Fresh Kiwis from New Zealand', 'https://th.bing.com/th/id/R.87779d6ba838846eb9b41bcbc131d1d9?rik=E8JYT%2f64z18sRg&riu=http%3a%2f%2fmomobud.sg%2fwp-content%2fuploads%2f2014%2f12%2fOrganic-Green-Kiwi-Cut.jpg&ehk=7Geyy8780plbcMamAV%2f30yiTM6EoacUEUeE9fRoz%2bwI%3d&risl=&pid=ImgRaw&r=0', N'New Zealand Kiwi', 90, 0.02)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (30, 'Juicy Grapefruits from Texas', 'https://th.bing.com/th/id/OIP.ARM0X3lKHYWKZ6oKAaI-SgHaFp?pid=ImgDet&rs=1', N'Texas Grapefruit', 80, 0.05)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (31, 'Fresh Blueberries from Maine', 'https://cdn11.bigcommerce.com/s-13r9u32/images/stencil/original/products/812/2627/Blueberry__05971.1438905420.jpg?c=2', N'Maine Blueberry', 100, 0.1)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (32, 'Sweet Cantaloupes from Guatemala', 'https://th.bing.com/th/id/OIP.Bu2eOY24n1HxFy7_bJYx5AHaFk?pid=ImgDet&rs=1', N'Guatemalan Cantaloupe', 150, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (33, 'Juicy Honeydews from Honduras', 'https://th.bing.com/th/id/R.548a932e44f2bc33642506c521563d95?rik=7PAd1FOe1qFJwg&pid=ImgRaw&r=0', N'Honduran Honeydew', 120, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (34, 'Sour lime from Vietnam', 'https://th.bing.com/th/id/OIP.sxAUIWVTn4V__wlBmtSe-AHaFG?pid=ImgDet&w=1280&h=882&rs=1', N'Vietnamese Lime', 90, 0)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (35, 'Fresh Raspberries from Oregon', 'https://th.bing.com/th/id/R.b3ea5e55867ac78bfb5fa330fdce5af2?rik=sl2ajsWZtKkN1A&riu=http%3a%2f%2f3.bp.blogspot.com%2f-_No_WTI5ZJw%2fUGFxQSOyBtI%2fAAAAAAAAAGA%2fsrx0exFYjoA%2fs1600%2fraspberry-fruit.jpg&ehk=D4xg3kHSguIVQ2kcQw4xdd30DqQUXzoIUS0TVKFzcjE%3d&risl=&pid=ImgRaw&r=0', N'Oregon Raspberry', 130, 0.05)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (36, 'Organic Mangoes from Peru', 'https://www.clipartkey.com/mpngs/m/76-766704_mango-slice-png-transparent-mango-slice-png.png', N'Peruvian Mango', 180, 0.1)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (37, 'Sweet Clementines from Morocco', 'https://th.bing.com/th/id/R.c84e3c5ad15a522a2be7baeca38178c7?rik=Na3SL4paEvdtRA&pid=ImgRaw&r=0', N'Moroccan Clementine', 100, 0.02)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (38, 'Juicy Blackberries from Georgia', 'https://th.bing.com/th/id/R.460b12a08a06bd6fcb3182bde9780f33?rik=dZXE4xyACUNWiQ&pid=ImgRaw&r=0', N'Georgia Blackberry', 120, 0.05)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (39, 'Tangy Grapefruits from Arizona', 'https://th.bing.com/th/id/R.b5834b4488a33b3f2819f80d9f973f45?rik=NFmtI83zRbYRfQ&pid=ImgRaw&r=0', N'Arizona Grapefruit', 90, 0.01)
INSERT [dbo].[Product] ([id], [description], [img], [name], [price], [discount]) VALUES (40, 'Sweet Tangerines from Florida', 'https://th.bing.com/th/id/R.b8cda5bfebd919c0d437c113d3568dcc?rik=VSM6RbIYwOXWLA&riu=http%3a%2f%2fweknowyourdreams.com%2fimages%2ftangerine%2ftangerine-01.jpg&ehk=u%2fUD01TRsi2eBgieec5mc7UFElOvLjTQTDLHdltffK4%3d&risl=&pid=ImgRaw&r=0', N'Florida Tangerine', 110, 0.03)
SET IDENTITY_INSERT [dbo].[Product] OFF;

SET IDENTITY_INSERT [dbo].[Account] ON 
INSERT [dbo].[Account] ([id], [name], [address], [phone], [email], [password], [role])VALUES (1, N'Admin', N'6/2 Vo Chi Cong', N'0123456789', N'a@gmail.com','1', 'ROLE_ADMIN')
INSERT [dbo].[Account] ([id], [name], [address], [phone], [email], [password], [role])VALUES (2, N'Tarynne Stone', N'5747 Shirley Drive', N'1234567890', N'c1@gmail.com','1', 'ROLE_CUSTOMER')
INSERT [dbo].[Account] ([id], [name], [address], [phone], [email], [password], [role])VALUES (3, N'Lukas Mercer', N'34/2/4 Huynh Tan Phat', N'2345678901', N'c2@gmail.com','1', 'ROLE_CUSTOMER')
INSERT [dbo].[Account] ([id], [name], [address], [phone], [email], [password], [role])VALUES (4, N'Kaida Banks', N'1873 Lion Circle', N'5678901234', N'c3@gmail.com','1','ROLE_CUSTOMER')
INSERT [dbo].[Account] ([id], [name], [address], [phone], [email], [password], [role])VALUES (5, N'Aveline Woods', N'5747 Shirley Drive', N'6789872314', N'c4@gmail.com', '1','ROLE_CUSTOMER')

GO

CREATE TABLE Comments (
id int identity(1,1) primary key,
[content] nvarchar(200),
userId int,
productId int,
username varchar(50),
cmtDate date,
foreign key (userId) references Account(id),
foreign key (productId) references Product(id)
)

GO
SET NOCOUNT OFF
raiserror('The FruitStore database in now ready for use.',0,1)
GO

