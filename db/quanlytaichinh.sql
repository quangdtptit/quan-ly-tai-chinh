/*
Navicat SQL Server Data Transfer

Source Server         : sql_server
Source Server Version : 140000
Source Host           : :1433
Source Database       : quanlytaichinh
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 140000
File Encoding         : 65001

Date: 2020-06-08 09:24:15
*/


-- ----------------------------
-- Table structure for [dbo].[sysdiagrams]
-- ----------------------------
DROP TABLE [dbo].[sysdiagrams]
GO
CREATE TABLE [dbo].[sysdiagrams] (
[name] sysname NOT NULL ,
[principal_id] int NOT NULL ,
[diagram_id] int NOT NULL IDENTITY(1,1) ,
[version] int NULL ,
[definition] varbinary(MAX) NULL 
)


GO

-- ----------------------------
-- Records of sysdiagrams
-- ----------------------------
SET IDENTITY_INSERT [dbo].[sysdiagrams] ON
GO
SET IDENTITY_INSERT [dbo].[sysdiagrams] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_bangcong]
-- ----------------------------
DROP TABLE [dbo].[tbl_bangcong]
GO
CREATE TABLE [dbo].[tbl_bangcong] (
[maBangCong] int NOT NULL IDENTITY(1,1) ,
[day] date NULL ,
[soCong] int NULL ,
[moTa] nvarchar(255) NULL ,
[maBangLuong] int NULL 
)


GO

-- ----------------------------
-- Records of tbl_bangcong
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_bangcong] ON
GO
SET IDENTITY_INSERT [dbo].[tbl_bangcong] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_bangluong]
-- ----------------------------
DROP TABLE [dbo].[tbl_bangluong]
GO
CREATE TABLE [dbo].[tbl_bangluong] (
[maBangLuong] int NOT NULL IDENTITY(1,1) ,
[thuong] bigint NULL ,
[phat] bigint NULL ,
[moTa] nvarchar(255) NULL ,
[maNhanVien] int NULL 
)


GO

-- ----------------------------
-- Records of tbl_bangluong
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_bangluong] ON
GO
SET IDENTITY_INSERT [dbo].[tbl_bangluong] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_chinhanh]
-- ----------------------------
DROP TABLE [dbo].[tbl_chinhanh]
GO
CREATE TABLE [dbo].[tbl_chinhanh] (
[maChiNhanh] int NOT NULL IDENTITY(1,1) ,
[tenChiNhanh] nvarchar(255) NULL ,
[moTa] nvarchar(255) NULL 
)


GO

-- ----------------------------
-- Records of tbl_chinhanh
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_chinhanh] ON
GO
INSERT INTO [dbo].[tbl_chinhanh] ([maChiNhanh], [tenChiNhanh], [moTa]) VALUES (N'1', N'Chi Nhánh 1', null);
GO
SET IDENTITY_INSERT [dbo].[tbl_chinhanh] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_hanghoa]
-- ----------------------------
DROP TABLE [dbo].[tbl_hanghoa]
GO
CREATE TABLE [dbo].[tbl_hanghoa] (
[maHangHoa] int NOT NULL IDENTITY(1,1) ,
[tenHangHoa] nvarchar(255) NULL ,
[price] bigint NULL ,
[moTa] nvarchar(255) NULL ,
[maKho] int NULL ,
[soLuong] int NULL ,
[maHoaDonNhapKho] int NULL ,
[createDate] date NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[tbl_hanghoa]', RESEED, 2)
GO

-- ----------------------------
-- Records of tbl_hanghoa
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_hanghoa] ON
GO
INSERT INTO [dbo].[tbl_hanghoa] ([maHangHoa], [tenHangHoa], [price], [moTa], [maKho], [soLuong], [maHoaDonNhapKho], [createDate]) VALUES (N'2', N'Áo Kaki', N'250000', N'None', null, N'56', null, null);
GO
SET IDENTITY_INSERT [dbo].[tbl_hanghoa] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_hanghoadaban]
-- ----------------------------
DROP TABLE [dbo].[tbl_hanghoadaban]
GO
CREATE TABLE [dbo].[tbl_hanghoadaban] (
[maHangHoaDaBan] int NOT NULL IDENTITY(1,1) ,
[tenHangHoa] nvarchar(255) NULL ,
[price] bigint NULL ,
[moTa] nvarchar(255) NULL ,
[soLuong] int NULL ,
[maHangHoa] int NULL ,
[maHoaDonBanHang] int NULL 
)


GO

-- ----------------------------
-- Records of tbl_hanghoadaban
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_hanghoadaban] ON
GO
SET IDENTITY_INSERT [dbo].[tbl_hanghoadaban] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_hoadonbanhang]
-- ----------------------------
DROP TABLE [dbo].[tbl_hoadonbanhang]
GO
CREATE TABLE [dbo].[tbl_hoadonbanhang] (
[maHoaDonBanHang] int NOT NULL IDENTITY(1,1) ,
[soLuong] int NULL ,
[amount] bigint NULL ,
[moTa] nvarchar(255) NULL ,
[time] timestamp NOT NULL ,
[tenNhanVien] nvarchar(255) NULL ,
[maKhachHang] int NULL 
)


GO

-- ----------------------------
-- Records of tbl_hoadonbanhang
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_hoadonbanhang] ON
GO
SET IDENTITY_INSERT [dbo].[tbl_hoadonbanhang] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_hoadonnhapkho]
-- ----------------------------
DROP TABLE [dbo].[tbl_hoadonnhapkho]
GO
CREATE TABLE [dbo].[tbl_hoadonnhapkho] (
[maHoaDonNhapKho] int NOT NULL IDENTITY(1,1) ,
[tenNguoiNhap] nvarchar(255) NULL ,
[total] int NULL ,
[amount] bigint NULL ,
[moTa] nvarchar(255) NULL ,
[maNhaCungCap] int NULL ,
[time] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[tbl_hoadonnhapkho]', RESEED, 5)
GO

-- ----------------------------
-- Records of tbl_hoadonnhapkho
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_hoadonnhapkho] ON
GO
INSERT INTO [dbo].[tbl_hoadonnhapkho] ([maHoaDonNhapKho], [tenNguoiNhap], [total], [amount], [moTa], [maNhaCungCap], [time]) VALUES (N'2', N'admin', N'51', N'250000', null, N'1', N'2020-06-06 09:56:08.597');
GO
INSERT INTO [dbo].[tbl_hoadonnhapkho] ([maHoaDonNhapKho], [tenNguoiNhap], [total], [amount], [moTa], [maNhaCungCap], [time]) VALUES (N'3', N'admin', N'51', N'250000', null, N'1', N'2020-06-06 09:58:49.287');
GO
INSERT INTO [dbo].[tbl_hoadonnhapkho] ([maHoaDonNhapKho], [tenNguoiNhap], [total], [amount], [moTa], [maNhaCungCap], [time]) VALUES (N'4', N'admin', N'51', N'250000', null, N'1', N'2020-06-06 09:59:51.207');
GO
INSERT INTO [dbo].[tbl_hoadonnhapkho] ([maHoaDonNhapKho], [tenNguoiNhap], [total], [amount], [moTa], [maNhaCungCap], [time]) VALUES (N'5', N'admin', N'51', N'250000', null, N'1', N'2020-06-06 10:01:30.307');
GO
SET IDENTITY_INSERT [dbo].[tbl_hoadonnhapkho] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_khachhang]
-- ----------------------------
DROP TABLE [dbo].[tbl_khachhang]
GO
CREATE TABLE [dbo].[tbl_khachhang] (
[maKhachHang] int NOT NULL IDENTITY(1,1) ,
[tenKhachHang] nvarchar(255) NULL ,
[diaChi] nvarchar(255) NULL ,
[moTa] nvarchar(255) NULL 
)


GO

-- ----------------------------
-- Records of tbl_khachhang
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_khachhang] ON
GO
INSERT INTO [dbo].[tbl_khachhang] ([maKhachHang], [tenKhachHang], [diaChi], [moTa]) VALUES (N'1', N'Lã Văn Dũng', N'Thanh Xuân - Hà Nội', null);
GO
SET IDENTITY_INSERT [dbo].[tbl_khachhang] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_kho]
-- ----------------------------
DROP TABLE [dbo].[tbl_kho]
GO
CREATE TABLE [dbo].[tbl_kho] (
[maKho] int NOT NULL IDENTITY(1,1) ,
[tenKho] nvarchar(255) NULL ,
[moTa] nvarchar(255) NULL ,
[maChiNhanh] int NULL 
)


GO

-- ----------------------------
-- Records of tbl_kho
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_kho] ON
GO
INSERT INTO [dbo].[tbl_kho] ([maKho], [tenKho], [moTa], [maChiNhanh]) VALUES (N'1', N'Kho số  1', null, N'1');
GO
SET IDENTITY_INSERT [dbo].[tbl_kho] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_nhacungcap]
-- ----------------------------
DROP TABLE [dbo].[tbl_nhacungcap]
GO
CREATE TABLE [dbo].[tbl_nhacungcap] (
[maNhaCungCap] int NOT NULL IDENTITY(1,1) ,
[tenNCC] nvarchar(255) NULL ,
[moTa] nvarchar(255) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[tbl_nhacungcap]', RESEED, 3)
GO

-- ----------------------------
-- Records of tbl_nhacungcap
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_nhacungcap] ON
GO
INSERT INTO [dbo].[tbl_nhacungcap] ([maNhaCungCap], [tenNCC], [moTa]) VALUES (N'1', N'Nhà cung cấp ABC', null);
GO
INSERT INTO [dbo].[tbl_nhacungcap] ([maNhaCungCap], [tenNCC], [moTa]) VALUES (N'2', N'Nhà cung cấp XYZ', null);
GO
INSERT INTO [dbo].[tbl_nhacungcap] ([maNhaCungCap], [tenNCC], [moTa]) VALUES (N'3', N'Nha cung cap1', null);
GO
SET IDENTITY_INSERT [dbo].[tbl_nhacungcap] OFF
GO

-- ----------------------------
-- Table structure for [dbo].[tbl_nhanvien]
-- ----------------------------
DROP TABLE [dbo].[tbl_nhanvien]
GO
CREATE TABLE [dbo].[tbl_nhanvien] (
[maNhanVien] int NOT NULL IDENTITY(1,1) ,
[diaChi] nvarchar(255) NULL ,
[sdt] varchar(10) NULL ,
[hoTen] nvarchar(255) NULL ,
[username] varchar(255) NULL ,
[password] varchar(255) NULL ,
[moTa] nvarchar(255) NULL ,
[maChiNhanh] int NULL ,
[role] varchar(255) NOT NULL 
)


GO

-- ----------------------------
-- Records of tbl_nhanvien
-- ----------------------------
SET IDENTITY_INSERT [dbo].[tbl_nhanvien] ON
GO
INSERT INTO [dbo].[tbl_nhanvien] ([maNhanVien], [diaChi], [sdt], [hoTen], [username], [password], [moTa], [maChiNhanh], [role]) VALUES (N'1', N'Thanh Xuân - Hà Nội', N'0966924822', N'Đỗ Thanh Quang', N'admin', N'$2a$10$RPw7IF72GJ.yPdc0jxvefOedLbg0jmxhxajA3XZ11onOd3pruI5wK', null, N'1', N'ADMIN');
GO
SET IDENTITY_INSERT [dbo].[tbl_nhanvien] OFF
GO

-- ----------------------------
-- Indexes structure for table sysdiagrams
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[sysdiagrams]
-- ----------------------------
ALTER TABLE [dbo].[sysdiagrams] ADD PRIMARY KEY ([diagram_id])
GO

-- ----------------------------
-- Uniques structure for table [dbo].[sysdiagrams]
-- ----------------------------
ALTER TABLE [dbo].[sysdiagrams] ADD UNIQUE ([principal_id] ASC, [name] ASC)
GO

-- ----------------------------
-- Indexes structure for table tbl_bangcong
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_bangcong]
-- ----------------------------
ALTER TABLE [dbo].[tbl_bangcong] ADD PRIMARY KEY ([maBangCong])
GO

-- ----------------------------
-- Indexes structure for table tbl_bangluong
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_bangluong]
-- ----------------------------
ALTER TABLE [dbo].[tbl_bangluong] ADD PRIMARY KEY ([maBangLuong])
GO

-- ----------------------------
-- Indexes structure for table tbl_chinhanh
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_chinhanh]
-- ----------------------------
ALTER TABLE [dbo].[tbl_chinhanh] ADD PRIMARY KEY ([maChiNhanh])
GO

-- ----------------------------
-- Indexes structure for table tbl_hanghoa
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_hanghoa]
-- ----------------------------
ALTER TABLE [dbo].[tbl_hanghoa] ADD PRIMARY KEY ([maHangHoa])
GO

-- ----------------------------
-- Indexes structure for table tbl_hanghoadaban
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_hanghoadaban]
-- ----------------------------
ALTER TABLE [dbo].[tbl_hanghoadaban] ADD PRIMARY KEY ([maHangHoaDaBan])
GO

-- ----------------------------
-- Indexes structure for table tbl_hoadonbanhang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_hoadonbanhang]
-- ----------------------------
ALTER TABLE [dbo].[tbl_hoadonbanhang] ADD PRIMARY KEY ([maHoaDonBanHang])
GO

-- ----------------------------
-- Indexes structure for table tbl_hoadonnhapkho
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_hoadonnhapkho]
-- ----------------------------
ALTER TABLE [dbo].[tbl_hoadonnhapkho] ADD PRIMARY KEY ([maHoaDonNhapKho])
GO

-- ----------------------------
-- Indexes structure for table tbl_khachhang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_khachhang]
-- ----------------------------
ALTER TABLE [dbo].[tbl_khachhang] ADD PRIMARY KEY ([maKhachHang])
GO

-- ----------------------------
-- Indexes structure for table tbl_kho
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_kho]
-- ----------------------------
ALTER TABLE [dbo].[tbl_kho] ADD PRIMARY KEY ([maKho])
GO

-- ----------------------------
-- Indexes structure for table tbl_nhacungcap
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_nhacungcap]
-- ----------------------------
ALTER TABLE [dbo].[tbl_nhacungcap] ADD PRIMARY KEY ([maNhaCungCap])
GO

-- ----------------------------
-- Indexes structure for table tbl_nhanvien
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[tbl_nhanvien]
-- ----------------------------
ALTER TABLE [dbo].[tbl_nhanvien] ADD PRIMARY KEY ([maNhanVien])
GO

-- ----------------------------
-- Uniques structure for table [dbo].[tbl_nhanvien]
-- ----------------------------
ALTER TABLE [dbo].[tbl_nhanvien] ADD UNIQUE ([username] ASC)
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[tbl_bangcong]
-- ----------------------------
ALTER TABLE [dbo].[tbl_bangcong] ADD FOREIGN KEY ([maBangLuong]) REFERENCES [dbo].[tbl_bangluong] ([maBangLuong]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[tbl_bangluong]
-- ----------------------------
ALTER TABLE [dbo].[tbl_bangluong] ADD FOREIGN KEY ([maNhanVien]) REFERENCES [dbo].[tbl_nhanvien] ([maNhanVien]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[tbl_hanghoa]
-- ----------------------------
ALTER TABLE [dbo].[tbl_hanghoa] ADD FOREIGN KEY ([maHoaDonNhapKho]) REFERENCES [dbo].[tbl_hoadonnhapkho] ([maHoaDonNhapKho]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [dbo].[tbl_hanghoa] ADD FOREIGN KEY ([maKho]) REFERENCES [dbo].[tbl_kho] ([maKho]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[tbl_hanghoadaban]
-- ----------------------------
ALTER TABLE [dbo].[tbl_hanghoadaban] ADD FOREIGN KEY ([maHangHoa]) REFERENCES [dbo].[tbl_hanghoa] ([maHangHoa]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [dbo].[tbl_hanghoadaban] ADD FOREIGN KEY ([maHoaDonBanHang]) REFERENCES [dbo].[tbl_hoadonbanhang] ([maHoaDonBanHang]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[tbl_hoadonbanhang]
-- ----------------------------
ALTER TABLE [dbo].[tbl_hoadonbanhang] ADD FOREIGN KEY ([maKhachHang]) REFERENCES [dbo].[tbl_khachhang] ([maKhachHang]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[tbl_hoadonnhapkho]
-- ----------------------------
ALTER TABLE [dbo].[tbl_hoadonnhapkho] ADD FOREIGN KEY ([maNhaCungCap]) REFERENCES [dbo].[tbl_nhacungcap] ([maNhaCungCap]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[tbl_kho]
-- ----------------------------
ALTER TABLE [dbo].[tbl_kho] ADD FOREIGN KEY ([maChiNhanh]) REFERENCES [dbo].[tbl_chinhanh] ([maChiNhanh]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[tbl_nhanvien]
-- ----------------------------
ALTER TABLE [dbo].[tbl_nhanvien] ADD FOREIGN KEY ([maChiNhanh]) REFERENCES [dbo].[tbl_chinhanh] ([maChiNhanh]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
