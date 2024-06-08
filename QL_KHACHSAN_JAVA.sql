Create database ql_ks
go
use ql_ks



CREATE TABLE PHANQUYEN (
  MAPQ varchar(10) PRIMARY KEY NOT NULL,
  CHUCNANG nvarchar(255) not null,
);

INSERT INTO PHANQUYEN (MAPQ, CHUCNANG) VALUES
('PQ01', N'Admin'),
('PQ02', N'Nhân viên');

CREATE TABLE NHOMQUYEN (
  MANHOM varchar(10) PRIMARY KEY NOT NULL,
  MAPQ varchar(10) NOT NULL,
  constraint fk_nhomquyen_phanquyen foreign key(MAPQ) references PHANQUYEN(MAPQ)
);

INSERT INTO NHOMQUYEN (MANHOM, MAPQ) VALUES
('N01', 'PQ01'),
('N02', 'PQ02');


CREATE TABLE NHANVIEN (
  MANV varchar(255) PRIMARY KEY NOT NULL,
  HOTEN nvarchar(50) NOT NULL,
  GIOITINH nvarchar(5) NOT NULL,
  NGAYSINH date NOT NULL,
  DIACHI nvarchar(255) NOT NULL,
  SDT varchar(13) NOT NULL UNIQUE,
  EMAIL varchar(50) NOT NULL UNIQUE,
  MANHOM varchar(10) NOT NULL,
  PASSWORD varchar(255) NOT NULL,
  ISDELETE INT NOT NULL
  constraint fk_nhanvien_nhomquyen FOREIGN KEY (MANHOM) REFERENCES NHOMQUYEN(MANHOM)
);

INSERT INTO NHANVIEN (MANV, HOTEN, GIOITINH, NGAYSINH, DIACHI, SDT, EMAIL, PASSWORD, ISDELETE, MANHOM) VALUES
('20240916718902', N'Nguyễn Văn An', 'Nam', '1980-10-15', N'456A Phạm Thị Riêng, Quận Đống Đa, Hà Nội', '023456789109', 'nguyena@gmail.com', 'an123',1, 'N02'),
('20240936782190', N'Trần Thị Bình', N'Nữ', '1990-05-20', N'76/12B Tân Hưng Thuận Quận 9, TP.HCM', '03456789209', 'tranb@gmail.com', 'binh123',1, 'N02'),
('2024093678921', N'Phạm Văn Trí', 'Nam', '1985-07-23', N'12/23 Hồ Văn Nghi, Cần Thơ', '0456789390', 'phamc@gmail.com', 'tri123',1, 'N02'),
('20240946712890', N'Lê Thị Hà', N'Nữ', '1988-04-26', N'555 Nguyễn Trư Phương, Quận 1 TP.HCM', '056789487', 'led@gmail.com', 'ha123',1, 'N02'),
('20240988558043', N'Đào Văn Vinh', 'Nam', '1985-12-25', N'24/157 Phạm Văn Quá, Quận 12, TP.HCM', '0297381234', 'vinhdao@gmail.com', 'vinh123',1, 'N01');


CREATE TABLE KHACHHANG (
  MAKH varchar(255) PRIMARY KEY NOT NULL,
  HOTEN nvarchar(255) NOT NULL,
  SDT varchar(13) UNIQUE DEFAULT NULL,
  NGAYSINH date DEFAULT NULL,
  GIOITINH nvarchar(5) NOT NULL,
  CCCD varchar(12) DEFAULT NULL,
  GHICHU varchar(255) DEFAULT NULL,
  ISDELETE INT NOT NULL ---0 là false 1 là true hoặc null--
);

INSERT INTO KHACHHANG (MAKH, HOTEN, GIOITINH, NGAYSINH, SDT, CCCD ,ISDELETE) VALUES
('20241234', N'Nguyễn Thị Thu Hà', N'Nữ', '2003-12-16', '0988558043', '',1),
('202412345', N'Trần Hoàng Anh Khôi', 'Nam', '2003-08-08', '0937481238', '', 1),
('2024123456', N'Đỗ Bảo Toàn', 'Nam', '2003-12-17', '0984612378', '', 1),
('20241234567', N'Nguyễn Phương Điền', 'Nam', '2003-10-09', '0937812890', '', 1),
('202412345678', N'Trần Thị Phương Thảo', N'Nữ', '2001-09-23', '0936123489', '', 1);
UPDATE KHACHHANG SET GHICHU = N'Bình thường' WHERE MAKH = '20241234';
UPDATE KHACHHANG SET GHICHU = N'Cần kiểm tra thông tin' WHERE MAKH = '202412345';
UPDATE KHACHHANG SET GHICHU = N'Khách hàng tiềm năng' WHERE MAKH = '2024123456';
UPDATE KHACHHANG SET GHICHU = N'Cần liên hệ để xác nhận' WHERE MAKH = '20241234567';
UPDATE KHACHHANG SET GHICHU = N'Chăm chỉ đặt phòng' WHERE MAKH = '202412345678';

CREATE TABLE LOAIPHONG (
  MALP VARCHAR(50) PRIMARY KEY NOT NULL,
  TENLOAIPHONG NVARCHAR(255) NOT NULL,
  MOTA NVARCHAR(MAX) NOT NULL,
  SUCCHUA INT NOT NULL,
  SOLUONG INT NOT NULL,
  GIATHUE FLOAT NOT NULL,
  ISDELETE INT  NOT NULL
);
ALTER TABLE LOAIPHONG ADD DIENTICH nvarchar(50) NOT NULL;
ALTER TABLE LOAIPHONG ADD TIENICH nvarchar(255) NOT NULL;
ALTER TABLE LOAIPHONG ADD NOITHAT nvarchar(255) NOT NULL;
ALTER TABLE LOAIPHONG ADD QUIDINH nvarchar(255) NOT NULL;

INSERT INTO LOAIPHONG (MALP, TENLOAIPHONG, MOTA, SUCCHUA, SOLUONG, GIATHUE, DIENTICH, TIENICH, NOITHAT, QUIDINH, ISDELETE) VALUES
('LPCC01', N'Phòng đơn hạng cao cấp', N'Phòng đơn giành riêng cho 1 người có ban công có thể ngắm biển', 1, 10, 1000000, '25m2', N'Wifi, Máy lạnh, minibar', N'Nội thất cơ bản', N'Không hút thuốc',1),
('LPCC02', N'Phòng đôi hạng cao cấp', N'Phòng đôi cao cấp với giường đôi, dành riêng cho hai người', 2, 10, 2000000, '20m2', N'Wifi, Rèm che nắng, dịch vụ báo thức', N'Nội thất cơ bản', N'Không hút thuốc',1),
('LPCC03', N'Phòng VIP hạng cao cấp', N'phòng có 1 giường lớn + 1 giường nhỏ cho 3 người', 2, 10, 3000000, '25m2', N'Máy lạnh, TV, minibar, rèm che nắng', N'Nội thất hiện đại', N'Không hút thuốc',1),
('LPCC04', N'Phòng gia đình hạng cao cấp', N'Phòng 3 giường nhỏ cho 3 người ngủ hoặc 1 giường lớn + 1 giường nhỏ', 3, 10, 4000000, '30m2', N'Máy sấy, tủ lạnh, máy lạnh', N'Nội thất hiện đại', N'Không hút thuốc',1),
('LPCC05', N'Phòng tổng thống hạng cao cấp', N'Phòng có 2 giường lớn cho 4 người ở, mang đến tầm nhìn ra biển tuyệt đẹp', 4, 10, 5000000, '35m2', N'Wifi, TV, minibar', N'Nội thất sang trọng', N'Không hút thuốc',1),
('LPGR01', N'Phòng đơn giá rẻ', N'Phòng có 1 giường cho 1 người ngủ', 1, 10, 200000, '23m2', N'Wifi, TV, tủ lạnh', N'Nội thất cơ bản', N'Không hút thuốc',1),
('LPGR02', N'Phòng đôi giá rẻ', N'Phòng có 1 giường đôi cho 2 người ngủ', 2, 10, 500000, '35m2', N'Wifi, Rèm che nắng, máy nóng lạnh', N'Nội thất hiện đại', N'Không hút thuốc',1),
('LPGR03', N'Phòng VIP giá rẻ', N'phòng có 1 giường lớn + 1 giường nhỏ cho 3 người', 3, 10, 800000, '30m2', N'Máy lạnh, tủ lạnh, TV', N'Nội thất hiện đại', N'Không hút thuốc',1),
('LPGR04', N'Phòng gia đình giá rẻ', N'Phòng 3 giường nhỏ cho 3 người ngủ hoặc 1 giường lớn + 1 giường nhỏ', 3, 10, 700000, '40m2', N'Cửa sổ, phòng tắm đứng, rèm che nắng', N'Nội thất sang trọng', N'Không hút thuốc',1),
('LPGR05', N'Phòng tổng thống giá rẻ', N'phòng có 2 giường lớn cho 4 người ở', 4, 10, 1000000, '45m2', N'Wifi, TV, đồ vải lanh', N'Nội thất cơ bản', N'Không hút thuốc',1),
('LPPT01', N'Phòng đơn hạng phổ thông', N'Phòng có 1 giường cho 1 người ngủ hạng phổ thông', 1, 10, 1500000, '60m2', N'Wifi, TV, cửa sổ, tủ lạnh', N'Nội thất hiện đại', N'Không hút thuốc',1),
('LPPT02', N'Phòng đôi hạng phổ thông', N'Phòng có 1 giường đôi cho 2 người ngủ và có ban công', 2, 10, 2500000, '55m2', N'Cửa sổ, máy sấy, tủ lạnh, TV', N'Nội thất cơ bản', N'Không hút thuốc',1),
('LPPT03', N'Phòng VIP hạng phổ thông', N'phòng có 1 giường lớn + 1 giường nhỏ cho 3 người', 3, 10, 3000000, '45m2', N'Wifi, TV, minibar, cửa sổ', N'Nội thất cơ bản', N'Không hút thuốc',1),
('LPPT04', N'Phòng gia đình hạng phổ thông', N'Phòng 3 giường nhỏ cho 3 người ngủ hoặc 1 giường lớn + 1 giường nhỏ', 3, 10, 3000000, '25m2', 'Wifi, TV, minibar, đồ vải lanh', 'Nội thất hiện đại', 'Không hút thuốc',1),
('LPPT05', N'Phòng tổng thống hạng phổ thông', N'phòng có 2 giường lớn cho 4 người ở', 4, 0, 4500000, '50m2', N'Wifi, TV, minibar, máy sấy, tủ lạnh', N'Nội thất sang trọng', N'Không hút thuốc',1);

CREATE TABLE PHONG
(
	MAPHONG VARCHAR(50) PRIMARY KEY NOT NULL,
	TENPHONG VARCHAR(255) DEFAULT NULL,
	TRANGTHAI INT NOT NULL DEFAULT 0,
	MALP VARCHAR(50) NOT NULL,
	VITRI INT NOT NULL,
	CONSTRAINT FK_PHONG_LOAIPHONG FOREIGN KEY(MALP) REFERENCES LOAIPHONG(MALP),
);

INSERT INTO PHONG (MAPHONG, TENPHONG, TRANGTHAI, MALP, VITRI) VALUES
('10LPPT04', 'A103', 0, 'LPPT04',1),
('11LPGR02', 'A303', 0, 'LPGR02',2),
('12LPPT01', 'A302', 0, 'LPPT01',3),
('13LPCC02', 'A401', 0, 'LPCC02',4),
('14LPPT02', 'A405', 0, 'LPPT02',1),
('15LPPT03', 'A201', 0, 'LPPT03',2),
('16LPGR01', 'A202', 0, 'LPGR01',3),
('17LPGR04', 'A204', 0, 'LPGR04',4),
('18LPGR03', 'A205', 0, 'LPGR03',1),
('19LPCC01', 'A203', 0, 'LPCC01',2),
('1LPPT01', 'A101', 0, 'LPPT01',3),
('20LPPT05', 'A502', 0, 'LPPT05',4),
('21LPCC01', 'A104', 0, 'LPCC01',5),
('22LPCC03', 'A402', 0, 'LPCC03',6),
('23LPGR01', 'A501', 0, 'LPGR01',7),
('24LPGR05', 'A505', 0, 'LPGR05',5),
('2LPCC01', 'A102', 0, 'LPCC01',1),
('3LPCC01', 'A301', 0, 'LPCC01',3),
('4LPCC05', 'A404', 0, 'LPCC05',4),
('5LPPT02', 'A304', 0, 'LPPT02',4),
('6LPCC01', 'A503', 0, 'LPCC01',2),
('7LPPT04', 'A504', 0, 'LPPT04',2),
('8LPCC04', 'A403', 0, 'LPCC04',3),
('9LPPT01', 'A304', 0, 'LPPT01',5);

CREATE TABLE DICHVU
(
	MADV VARCHAR(255) PRIMARY KEY NOT NULL,
	TENDV NVARCHAR(255) NOT NULL,
	GIADV FLOAT NOT NULL,
	MOTA NVARCHAR(MAX) NOT NULL,
	ISDELETE INT  NOT NULL---0 là false 1 là true hoặc null--
);

INSERT INTO DICHVU (MADV, TENDV, GIADV,MOTA,ISDELETE) VALUES
('DV01', N'Bơi', 100000,N'Khách sạn chúng tôi cung cấp một bể bơi rộng lớn và hiện đại, với không gian thoáng đãng và thiết kế sang trọng, tạo điều kiện lý tưởng cho việc tận hưởng nước và thư giãn.',1),
('DV010', N'Spa', 100000,N'Khách sạn chúng tôi cung cấp các phòng spa riêng tư, được trang bị đầy đủ tiện nghi và không gian yên tĩnh, tạo điều kiện lý tưởng cho việc thư giãn và tái tạo.',1),
('DV02', N'Giặt, ủi quần áo', 100000,N'Khách sạn của chúng tôi cung cấp dịch vụ giặt ủi quần áo thuận tiện và nhanh chóng, giúp khách hàng tiết kiệm thời gian và công sức khi đi du lịch hoặc công tác.', 1),
('DV03', N'Xe đưa đón sân bay', 100000,N'Khách sạn của chúng tôi có sẵn một đội xe đa dạng bao gồm các loại xe từ sedan, SUV đến xe vận tải lớn, đảm bảo phù hợp với nhu cầu và số lượng hành khách của mỗi gia đình hoặc nhóm.', 1),
('DV04', N'Thuê xe máy', 100000,N'Khách sạn của chúng tôi cung cấp một loạt các loại xe máy từ các dòng xe scooter nhẹ đến các loại xe mạnh mẽ và thích hợp với mọi loại địa hình, đảm bảo phù hợp với nhu cầu và kỹ năng lái của từng khách hàng.', 1),
('DV05', N'Trông trẻ', 100000,N'Chúng tôi có một đội ngũ nhân viên trông trẻ được đào tạo chuyên nghiệp, có kinh nghiệm và yêu trẻ, đảm bảo rằng trẻ em sẽ được chăm sóc một cách an toàn và chu đáo.',1),
('DV06', N'Wifi tốc độ cao', 100000,N'Chúng tôi cung cấp dịch vụ Wi-Fi tốc độ cao và ổn định, đảm bảo rằng khách hàng có thể truy cập internet một cách nhanh chóng và mượt mà mọi lúc, mọi nơi trong khách sạn.', 1),
('DV07', N'Thu đổi ngoại tệ', 100000,N'Chúng tôi cung cấp dịch vụ thu đổi ngoại tệ với tỷ giá hợp lý và cạnh tranh, đảm bảo rằng khách hàng sẽ nhận được số tiền tương đương xứng đáng với loại tiền tệ họ đổi.', 1),
('DV08', N'Tour du lịch', 1000000,N'Chúng tôi cung cấp một loạt các tour du lịch đa dạng, bao gồm các tour tham quan thành phố, tour tự nhiên, tour lịch sử, tour văn hóa và nhiều loại hình du lịch khác nhau, đảm bảo phù hợp với mọi sở thích và nhu cầu của khách hàng.',1),
('DV09', N'Karaoke', 100000,N'Khách sạn của chúng tôi cung cấp các phòng karaoke riêng tư, trang bị đầy đủ các thiết bị âm thanh và ánh sáng chuyên nghiệp, tạo điều kiện lý tưởng cho các buổi hát karaoke vui vẻ và sôi động.', 1);

CREATE TABLE PHIEUDANGKY
(
	MAPHIEU VARCHAR(255) PRIMARY KEY NOT NULL,
	MANV VARCHAR(255) DEFAULT NULL,
	MAPHONG VARCHAR(50) DEFAULT NULL,
	NGAYDAT DATE DEFAULT NULL,
	TINHTRANG NVARCHAR(255) DEFAULT NULL,
	TRAPHONG DATE DEFAULT NULL,
	THANHTOAN DECIMAL(18, 2) DEFAULT NULL,
	TT_NHANPHONG NVARCHAR(255) DEFAULT NULL,
	ISDELETE INT NOT NULL
	CONSTRAINT FK_PHIEUDANGKY_NHANVIEN FOREIGN KEY(MANV) REFERENCES NHANVIEN(MANV),
	CONSTRAINT FK_PHIEUDANGKY_PHONG FOREIGN KEY(MAPHONG) REFERENCES PHONG(MAPHONG)
);

INSERT INTO PHIEUDANGKY (MAPHIEU, MANV, MAPHONG, NGAYDAT, TINHTRANG, TRAPHONG, THANHTOAN, TT_NHANPHONG,ISDELETE) VALUES
('2024P01', '2024093678921', '19LPCC01', '2024-05-02', N'Đặt trước', '2024-05-06', 500000, N'Đang đợi',1),
('2024P02', '20240916718902', '1LPPT01', '2024-05-06', N'Đặt trước', '2024-05-09', 500000, N'Đang đợi',1),
('2024P03', '20240936782190', '2LPCC01', '2024-05-16', N'Đặt trước', '2024-05-21', 500000, N'Đang đợi',1),
('2024P04', '20240946712890', '3LPCC01', '2024-05-24', N'Đặt trước', '2024-05-28', 500000, N'Đang đợi',1),
('2025P05', '20240988558043', '6LPCC01', '2024-05-25', N'Đặt trước', '2024-05-30', 500000, N'Đang đợi',1);

CREATE TABLE CHITIET_PHIEUDANGKY
(
	MAPHIEU VARCHAR(255) NOT NULL,
	MAKH VARCHAR(255) NOT NULL,
	PRIMARY KEY (MAPHIEU,MAKH),
	CONSTRAINT FK_CTPDK_PDK FOREIGN KEY(MAPHIEU) REFERENCES PHIEUDANGKY(MAPHIEU),
	CONSTRAINT FK_CTPDK_KHACHHANG FOREIGN KEY(MAKH) REFERENCES KHACHHANG(MAKH)
);

INSERT INTO CHITIET_PHIEUDANGKY (MAPHIEU, MAKH) VALUES
('2024P01', '20241234'),
('2024P02', '202412345'),
('2024P03', '2024123456'),
('2024P04', '20241234567'),
('2025P05', '202412345678');

CREATE TABLE HOADON (
  MAHD VARCHAR(255) PRIMARY KEY NOT NULL,
  MANV VARCHAR(255) DEFAULT NULL,
  MAPHIEU VARCHAR(255) NOT NULL,
  NGAYLAP date DEFAULT NULL,
  TONGTIEN FLOAT NOT NULL,
  ISDELETE INT NOT NULL
  CONSTRAINT FK_HOADON_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV),
 CONSTRAINT FK_HOADON_KHACHHANG FOREIGN KEY(MAPHIEU) REFERENCES PHIEUDANGKY(MAPHIEU)
);

INSERT INTO HOADON (MAHD, MANV, MAPHIEU, NGAYLAP, TONGTIEN,ISDELETE) VALUES
('2014HD02', '20240916718902', '2024P02', '2024-05-02', 0.00,1),
('2024HD01', '2024093678921', '2024P01', '2024-05-02', 0.00,1),
('2024HD03', '20240936782190', '2024P03', '2024-05-02', 0.00,1),
('2024HD04', '20240946712890', '2024P04', '2024-05-02', 0.00,1),
('2024HD05', '20240988558043', '2025P05', '2024-05-02', 0.00,1);

CREATE TABLE CT_HOADON (
  MAHD VARCHAR(255) NOT NULL,
  MADV VARCHAR(255) NOT NULL,
  SOLUONG int NOT NULL,
  PRIMARY KEY(MAHD,MADV),
  CONSTRAINT FK_CTHD_HOADON FOREIGN KEY (MAHD) REFERENCES HOADON(MAHD),
  CONSTRAINT FK_CTHD_DICHVU FOREIGN KEY (MADV) REFERENCES DICHVU(MADV)
);
INSERT INTO CT_HOADON (MAHD, MADV, SOLUONG) VALUES
('2014HD02', 'DV01', 2),
('2014HD02', 'DV02', 1),
('2024HD01', 'DV03', 1),
('2024HD01', 'DV04', 2),
('2024HD03', 'DV05', 1),
('2024HD03', 'DV06', 3),
('2024HD04', 'DV07', 2),
('2024HD04', 'DV08', 1),
('2024HD05', 'DV09', 1);
----cập nhật số lượng loại phòng khi thay đổi trạng thái trong bảng phòng--
CREATE TRIGGER TR_PHONG_UPDATE
ON PHONG
AFTER INSERT, UPDATE, DELETE
AS
BEGIN

   IF EXISTS(SELECT * FROM inserted) OR EXISTS(SELECT * FROM deleted)
      BEGIN
         DECLARE @RoomTypes TABLE (
            MALP char(10)
         );

         INSERT INTO @RoomTypes 
            SELECT MALP FROM inserted 
            UNION 
            SELECT MALP FROM deleted;

         DECLARE @MALP char(10);
         DECLARE room_cursor CURSOR FOR SELECT MALP FROM @RoomTypes;

         OPEN room_cursor
         FETCH NEXT FROM room_cursor INTO @MALP

         WHILE @@FETCH_STATUS = 0  
         BEGIN  
            DECLARE @RoomCount INT;

            SELECT @RoomCount = COUNT(*) 
            FROM PHONG
            WHERE TRANGTHAI = 0 AND MALP = @MALP;

            UPDATE LOAIPHONG
            SET SOLUONG = @RoomCount
            WHERE MALP = @MALP;

            FETCH NEXT FROM room_cursor INTO @MALP
         END;

         CLOSE room_cursor;
         DEALLOCATE room_cursor;

      END
END;
GO

---cập nhật isdelet=0 khi số lượng =0 trong bảng loại phòng khi insert--
CREATE TRIGGER check_and_update_isdelete_on_insert
ON LOAIPHONG
AFTER INSERT
AS
BEGIN
   UPDATE LOAIPHONG
   SET ISDELETE = CASE 
                    WHEN inserted.SOLUONG = 0 THEN 0
                    ELSE 1
                  END
   FROM LOAIPHONG
   JOIN inserted ON LOAIPHONG.MALP = inserted.MALP
END;
---cập nhật isdelet=0 khi số lượng =0 trong bảng loại phòng khi update--
CREATE TRIGGER check_and_update_isdelete_on_update
ON LOAIPHONG
AFTER UPDATE
AS
BEGIN
   UPDATE LOAIPHONG
   SET ISDELETE = CASE 
                    WHEN inserted.SOLUONG = 0 THEN 0
                    ELSE 1
                  END
   FROM LOAIPHONG
   JOIN inserted ON LOAIPHONG.MALP = inserted.MALP
END;


---cập nhật số lượng=0 khi isdelete =0 trong bảng loại phòng khi insert---
CREATE TRIGGER update_SOLUONG_on_insert
ON LOAIPHONG
AFTER INSERT
AS
BEGIN
    IF ((SELECT inserted.ISDELETE FROM inserted) = 0 AND (SELECT LOAIPHONG.SOLUONG FROM LOAIPHONG INNER JOIN inserted ON LOAIPHONG.MALP = inserted.MALP) != 0)
    BEGIN
        UPDATE LOAIPHONG
        SET SOLUONG = 0
        FROM LOAIPHONG
        INNER JOIN inserted 
        ON LOAIPHONG.MALP = inserted.MALP;
    END
    ELSE IF ((SELECT inserted.ISDELETE FROM inserted) != 0 AND (SELECT LOAIPHONG.SOLUONG FROM LOAIPHONG INNER JOIN inserted ON LOAIPHONG.MALP = inserted.MALP) != 1)
    BEGIN
        UPDATE LOAIPHONG
        SET SOLUONG = 1
        FROM LOAIPHONG
        INNER JOIN inserted 
        ON LOAIPHONG.MALP = inserted.MALP;
    END
END;
SELECT * FROM NHOMQUYEN
---cập nhật số lượng=0 khi isdelete =0 trong bảng loại phòng khi update---
CREATE TRIGGER update_SOLUONG_on_update
ON LOAIPHONG
AFTER UPDATE
AS
BEGIN
   IF (SELECT inserted.ISDELETE FROM inserted) = 0 AND (SELECT LOAIPHONG.SOLUONG FROM LOAIPHONG INNER JOIN inserted ON LOAIPHONG.MALP = inserted.MALP) != 0
   BEGIN
      UPDATE LOAIPHONG
      SET SOLUONG = 0
      FROM LOAIPHONG
      INNER JOIN inserted 
      ON LOAIPHONG.MALP = inserted.MALP;
   END
   ELSE IF (SELECT inserted.ISDELETE FROM inserted) != 0 AND (SELECT LOAIPHONG.SOLUONG FROM LOAIPHONG INNER JOIN inserted ON LOAIPHONG.MALP = inserted.MALP) != 1
   BEGIN
      UPDATE LOAIPHONG
      SET SOLUONG = 1
      FROM LOAIPHONG
      INNER JOIN inserted 
      ON LOAIPHONG.MALP = inserted.MALP;
   END
END;

update PHONG set TRANGTHAI=1 where MALP='LPCC02';
SELECT * FROM PHIEUDANGKY
SELECT * FROM PHIEUDANGKY INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP

SELECT * FROM NHANVIEN
SELECT * FROM PHIEUDANGKY
UPDATE NHANVIEN SET PASSWORD = '$2a$10$EflJKLOimhutMU7QBaN4COyNfWrbYDIDDs8tWd28dFl2m9CtPqp7y' WHERE PASSWORD = 'vinh123'
UPDATE KHACHHANG SET CCCD = '1122334455'
SELECT * FROM LOAIPHONG
SELECT * FROM PHONG
SELECT * FROM PHIEUDANGKY INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP
SELECT * FROM PHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP
SELECT * FROM DICHVU
SELECT * FROM KHACHHANG
SELECT * FROM HOADON
SELECT * FROM NHANVIEN INNER JOIN NHOMQUYEN ON NHANVIEN.MANHOM = NHOMQUYEN.MANHOM INNER JOIN PHANQUYEN ON NHOMQUYEN.MAPQ = PHANQUYEN.MAPQ
SELECT * FROM PHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE TRANGTHAI = 0
SELECT * FROM PHIEUDANGKY INNER JOIN PHONG on PHIEUDANGKY.MAPHONG = PHONG.MAPHONG
SELECT * FROM HOADON INNER JOIN PHIEUDANGKY ON HOADON.MAPHIEU = PHIEUDANGKY.MAPHIEU INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE NGAYLAP IS NULL
SELECT * FROM PHIEUDANGKY INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP
SELECT * FROM CHITIET_PHIEUDANGKY INNER JOIN KHACHHANG ON CHITIET_PHIEUDANGKY.MAKH = KHACHHANG.MAKH


SELECT LOAIPHONG.MALP, COUNT(*) AS SL FROM PHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE PHONG.MALP = LOAIPHONG.MALP GROUP BY LOAIPHONG.MALP
UPDATE LOAIPHONG SET SOLUONG = 5 WHERE MALP = 'LPCC01'

