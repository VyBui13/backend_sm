use QLSVNhom
GO

--DROP PROCEDURE IF EXISTS SP_SEL_ALL_PUBLIC_SINHVIEN
--DROP PROCEDURE IF EXISTS SP_SEL_PUBLIC_SINHVIEN_BY_NHANVIEN
--DROP PROCEDURE IF EXISTS SP_INS_PUBLIC_SINHVIEN
--DROP PROCEDURE IF EXISTS SP_UPDATE_SINHVIEN
--DROP PROCEDURE IF EXISTS SP_DELETE_SINHVIEN

--DROP PROCEDURE IF EXISTS SP_GET_ALL_HOCPHAN_BY_SINHVIEN
--DROP PROCEDURE IF EXISTS SP_INS_HOCPHAN
--DROP PROCEDURE IF EXISTS SP_UPDATE_HOCPHAN
--DROP PROCEDURE IF EXISTS SP_DELETE_HOCPHAN

--DROP PROCEDURE IF EXISTS SP_GET_ALL_LOP
--DROP PROCEDURE IF EXISTS SP_INS_LOP
--DROP PROCEDURE IF EXISTS SP_UPDATE_LOP
--DROP PROCEDURE IF EXISTS SP_DELETE_LOP

--DROP PROCEDURE IF EXISTS SP_GET_ALL_HOCPHAN_WITH_DIEM_BY_SINHVIEN
--DROP PROCEDURE IF EXISTS SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN
--DROP PROCEDURE IF EXISTS SP_UPDATE_DIEM_BY_SINHVIEN_AND_HOCPHAN



-- SINH VIÊN
-- Lấy tất cả sinh viên
CREATE PROCEDURE SP_SEL_ALL_PUBLIC_SINHVIEN
AS
BEGIN
	SELECT S.MASV, S.HOTEN, S.NGAYSINH, S.DIACHI, S.MALOP
	FROM SINHVIEN S
END

GO

-- Lấy sinh viên theo giáo viên
CREATE PROCEDURE SP_SEL_PUBLIC_SINHVIEN_BY_NHANVIEN
	@MANV VARCHAR(20)
AS
BEGIN
	SELECT S.MASV, S.HOTEN, S.NGAYSINH, S.DIACHI, S.MALOP
	FROM SINHVIEN S
	JOIN LOP L ON L.MANV = @MANV AND L.MALOP = S.MALOP
END
GO

-- Thêm sinh viên mới
CREATE PROCEDURE SP_INS_PUBLIC_SINHVIEN
    @MASV VARCHAR(20),
    @HOTEN NVARCHAR(100),
	@NGAYSINH DATETIME,
    @DIACHI NVARCHAR(200),
    @MALOP VARCHAR(20),
    @TENDN NVARCHAR(100),
    @MK NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @HASHED_MK VARBINARY(MAX);
    DECLARE @ASYMKEY_NAME NVARCHAR(100);

    -- Tạo giá trị mật khẩu được HASH bằng SHA1
    SET @HASHED_MK = HASHBYTES('SHA1', CONVERT(VARBINARY(MAX), @MK));

    -- Thêm dữ liệu vào bảng NHANVIEN
    INSERT INTO SINHVIEN (MASV, HOTEN, NGAYSINH, DIACHI,MALOP, TENDN, MATKHAU)
    VALUES (@MASV, @HOTEN, @NGAYSINH, @DIACHI, @MALOP, @TENDN, @HASHED_MK);
END;

GO

-- Cập nhật thông tin sinh viên theo mã sinh viên
CREATE PROCEDURE SP_UPDATE_SINHVIEN
    @MASV VARCHAR(20),
    @HOTEN NVARCHAR(100),
    @NGAYSINH DATETIME = NULL,
    @DIACHI NVARCHAR(200) = NULL,
    @MALOP VARCHAR(20) = NULL,
    @MANV VARCHAR(20),
    @PUBKEY VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra sinh viên tồn tại
    IF NOT EXISTS (SELECT 1 FROM SINHVIEN WHERE MASV = @MASV)
    BEGIN
        RAISERROR('Sinh viên với mã %s không tồn tại.', 16, 1, @MASV);
        RETURN;
    END
    
    -- Kiểm tra nhân viên tồn tại và PUBKEY khớp
    IF NOT EXISTS (SELECT 1 FROM NHANVIEN WHERE MANV = @MANV AND PUBKEY = @PUBKEY)
    BEGIN
        RAISERROR('Nhân viên hoặc PUBKEY không hợp lệ.', 16, 1);
        RETURN;
    END
    
    -- Kiểm tra MANV khớp với MANV của lớp mà sinh viên thuộc về
    IF @MALOP IS NOT NULL
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM LOP WHERE MALOP = @MALOP AND MANV = @MANV)
        BEGIN
            RAISERROR('Nhân viên không quản lý lớp %s.', 16, 1, @MALOP);
            RETURN;
        END
    END
    ELSE
    BEGIN
        -- Lấy MALOP hiện tại của sinh viên
        DECLARE @CurrentMALOP VARCHAR(20);
        SELECT @CurrentMALOP = MALOP FROM SINHVIEN WHERE MASV = @MASV;
        
        IF NOT EXISTS (SELECT 1 FROM LOP WHERE MALOP = @CurrentMALOP AND MANV = @MANV)
        BEGIN
            RAISERROR('Nhân viên không quản lý lớp hiện tại của sinh viên.', 16, 1);
            RETURN;
        END
    END
    
    -- Cập nhật thông tin sinh viên
    UPDATE SINHVIEN
    SET 
        HOTEN = @HOTEN,
        NGAYSINH = ISNULL(@NGAYSINH, NGAYSINH),
        DIACHI = ISNULL(@DIACHI, DIACHI),
        MALOP = ISNULL(@MALOP, MALOP)
    WHERE MASV = @MASV;
    
    IF @@ROWCOUNT = 0
        RAISERROR('Cập nhật thông tin sinh viên thất bại.', 16, 1);
END;
GO

-- Xóa sinh viên
CREATE PROCEDURE SP_DELETE_SINHVIEN
    @MASV VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra sinh viên tồn tại
    IF NOT EXISTS (SELECT 1 FROM SINHVIEN WHERE MASV = @MASV)
    BEGIN
        RAISERROR('Sinh viên với mã %s không tồn tại.', 16, 1, @MASV);
        RETURN;
    END
    
    -- Xóa bản ghi trong BANGDIEM trước (do ràng buộc khóa ngoại)
    DELETE FROM BANGDIEM WHERE MASV = @MASV;
    
    -- Xóa sinh viên
    DELETE FROM SINHVIEN WHERE MASV = @MASV;
    
    IF @@ROWCOUNT = 0
        RAISERROR('Xóa sinh viên thất bại.', 16, 1);
END;
GO

-- HỌC PHẦN
-- Xem học phần theo sinh viên
CREATE PROCEDURE SP_GET_ALL_HOCPHAN_BY_SINHVIEN
    @MASV VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra sinh viên tồn tại
    IF NOT EXISTS (SELECT 1 FROM SINHVIEN WHERE MASV = @MASV)
    BEGIN
        RAISERROR('Sinh viên với mã %s không tồn tại.', 16, 1, @MASV);
        RETURN;
    END
    
    SELECT 
        H.MAHP,
        H.TENHP,
        H.SOTC,
        COALESCE(CAST(CAST(B.DIEMTHI AS VARCHAR(MAX)) AS INT), 0) AS DIEMTHI
    FROM HOCPHAN H
    LEFT JOIN BANGDIEM B ON H.MAHP = B.MAHP AND B.MASV = @MASV
    ORDER BY H.MAHP;
END;
GO

-- Thêm học phần
CREATE PROCEDURE SP_INS_HOCPHAN
    @MAHP VARCHAR(20),
    @TENHP NVARCHAR(100),
    @SOTC INT = 3 -- Giá trị mặc định
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra học phần đã tồn tại
    IF EXISTS (SELECT 1 FROM HOCPHAN WHERE MAHP = @MAHP)
    BEGIN
        RAISERROR('Học phần với mã %s đã tồn tại.', 16, 1, @MAHP);
        RETURN;
    END
    
    -- Kiểm tra SOTC hợp lệ
    IF @SOTC <= 0
    BEGIN
        RAISERROR('Số tín chỉ phải lớn hơn 0.', 16, 1);
        RETURN;
    END
    
    -- Thêm học phần
    INSERT INTO HOCPHAN (MAHP, TENHP, SOTC)
    VALUES (@MAHP, @TENHP, @SOTC);
    
    IF @@ROWCOUNT = 0
        RAISERROR('Thêm học phần thất bại.', 16, 1);
END;
GO

-- Cập nhật học phần theo mã học phần
CREATE PROCEDURE SP_UPDATE_HOCPHAN
    @MAHP VARCHAR(20),
    @TENHP NVARCHAR(100),
    @SOTC INT = 3 -- Giá trị mặc định
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra học phần tồn tại
    IF NOT EXISTS (SELECT 1 FROM HOCPHAN WHERE MAHP = @MAHP)
    BEGIN
        RAISERROR('Học phần với mã %s không tồn tại.', 16, 1, @MAHP);
        RETURN;
    END
    
    -- Kiểm tra SOTC hợp lệ
    IF @SOTC <= 0
    BEGIN
        RAISERROR('Số tín chỉ phải lớn hơn 0.', 16, 1);
        RETURN;
    END
    
    -- Cập nhật học phần
    UPDATE HOCPHAN
    SET 
        TENHP = @TENHP,
        SOTC = @SOTC
    WHERE MAHP = @MAHP;
    
    IF @@ROWCOUNT = 0
        RAISERROR('Cập nhật học phần thất bại.', 16, 1);
END;
GO

-- Xóa học phần
CREATE PROCEDURE SP_DELETE_HOCPHAN
    @MAHP VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra học phần tồn tại
    IF NOT EXISTS (SELECT 1 FROM HOCPHAN WHERE MAHP = @MAHP)
    BEGIN
        RAISERROR('Học phần với mã %s không tồn tại.', 16, 1, @MAHP);
        RETURN;
    END
    
    -- Xóa bản ghi trong BANGDIEM trước (do ràng buộc khóa ngoại)
    DELETE FROM BANGDIEM WHERE MAHP = @MAHP;
    
    -- Xóa học phần
    DELETE FROM HOCPHAN WHERE MAHP = @MAHP;
    
    IF @@ROWCOUNT = 0
        RAISERROR('Xóa học phần thất bại.', 16, 1);
END;
GO

-- LỚP
-- Xem tất cả lớp
CREATE PROCEDURE SP_GET_ALL_LOP
AS
BEGIN
    SET NOCOUNT ON;
    
    SELECT 
        L.MALOP,
        L.TENLOP,
        L.MANV,
        N.HOTEN AS TenNhanVien
    FROM LOP L
    LEFT JOIN NHANVIEN N ON L.MANV = N.MANV
    ORDER BY L.MALOP;
END;
GO

-- Thêm lớp
CREATE PROCEDURE SP_INS_LOP
    @MALOP VARCHAR(20),
    @TENLOP NVARCHAR(100),
    @MANV VARCHAR(20) = NULL -- Giá trị mặc định
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra lớp đã tồn tại
    IF EXISTS (SELECT 1 FROM LOP WHERE MALOP = @MALOP)
    BEGIN
        RAISERROR('Lớp với mã %s đã tồn tại.', 16, 1, @MALOP);
        RETURN;
    END
    
    -- Kiểm tra MANV tồn tại nếu được cung cấp
    IF @MANV IS NOT NULL AND NOT EXISTS (SELECT 1 FROM NHANVIEN WHERE MANV = @MANV)
    BEGIN
        RAISERROR('Nhân viên với mã %s không tồn tại.', 16, 1, @MANV);
        RETURN;
    END
    
    -- Thêm lớp
    INSERT INTO LOP (MALOP, TENLOP, MANV)
    VALUES (@MALOP, @TENLOP, @MANV);
    
    IF @@ROWCOUNT = 0
        RAISERROR('Thêm lớp thất bại.', 16, 1);
END;
GO

-- Cập nhật lớp theo mã lớp
CREATE PROCEDURE SP_UPDATE_LOP
    @MALOP VARCHAR(20),
    @TENLOP NVARCHAR(100),
    @MANV VARCHAR(20) = NULL -- Giá trị mặc định
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra lớp tồn tại
    IF NOT EXISTS (SELECT 1 FROM LOP WHERE MALOP = @MALOP)
    BEGIN
        RAISERROR('Lớp với mã %s không tồn tại.', 16, 1, @MALOP);
        RETURN;
    END
    
    -- Kiểm tra MANV tồn tại nếu được cung cấp
    IF @MANV IS NOT NULL AND NOT EXISTS (SELECT 1 FROM NHANVIEN WHERE MANV = @MANV)
    BEGIN
        RAISERROR('Nhân viên với mã %s không tồn tại.', 16, 1, @MANV);
        RETURN;
    END
    
    -- Cập nhật lớp
    UPDATE LOP
    SET 
        TENLOP = @TENLOP,
        MANV = @MANV
    WHERE MALOP = @MALOP;
    
    IF @@ROWCOUNT = 0
        RAISERROR('Cập nhật lớp thất bại.', 16, 1);
END;
GO

-- Xóa lớp
CREATE PROCEDURE SP_DELETE_LOP
    @MALOP VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra lớp tồn tại
    IF NOT EXISTS (SELECT 1 FROM LOP WHERE MALOP = @MALOP)
    BEGIN
        RAISERROR('Lớp với mã %s không tồn tại.', 16, 1, @MALOP);
        RETURN;
    END
    
    -- Kiểm tra lớp có sinh viên hay không
    IF EXISTS (SELECT 1 FROM SINHVIEN WHERE MALOP = @MALOP)
    BEGIN
        RAISERROR('Lớp %s đang có sinh viên, không thể xóa.', 16, 1, @MALOP);
        RETURN;
    END
    
    -- Xóa lớp
    DELETE FROM LOP WHERE MALOP = @MALOP;
    
    IF @@ROWCOUNT = 0
        RAISERROR('Xóa lớp thất bại.', 16, 1);
END;
GO

-- ĐIỂM
-- Xem tất cả các học phần bao gồm điểm của một sinh viên
CREATE PROCEDURE SP_GET_ALL_HOCPHAN_WITH_DIEM_BY_SINHVIEN
    @MASV VARCHAR(20),
	@MK NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra sinh viên tồn tại
    IF NOT EXISTS (SELECT 1 FROM SINHVIEN WHERE MASV = @MASV)
    BEGIN
        RAISERROR('Sinh viên với mã %s không tồn tại.', 16, 1, @MASV);
        RETURN;
    END
    
    -- Lấy PUBKEY của nhân viên quản lý lớp của sinh viên
    DECLARE @PUBKEY VARCHAR(20);
    SELECT @PUBKEY = N.PUBKEY
    FROM SINHVIEN SV
    JOIN LOP L ON SV.MALOP = L.MALOP
    JOIN NHANVIEN N ON L.MANV = N.MANV
    WHERE SV.MASV = @MASV;
    
    IF @PUBKEY IS NULL
    BEGIN
        RAISERROR('Không tìm thấy nhân viên quản lý lớp của sinh viên.', 16, 1);
        RETURN;
    END
    
    -- Truy vấn tất cả học phần và điểm
    SELECT 
        H.MAHP,
        H.TENHP,
        H.SOTC,
        COALESCE(
		TRY_CAST(
            TRY_CAST(
				DecryptByAsymKey(
					AsymKey_ID(@PUBKEY), 
					B.DIEMTHI,
					@MK
				) AS VARCHAR) AS FLOAT),
            0
        ) AS DIEMTHI
    FROM HOCPHAN H
    LEFT JOIN BANGDIEM B ON H.MAHP = B.MAHP AND B.MASV = @MASV
    ORDER BY H.MAHP;
END;

GO

-- Thêm điểm cho học phần và sinh viên
CREATE PROCEDURE SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN
    @MASV VARCHAR(20),
    @MAHP VARCHAR(20),
    @DIEMTHI FLOAT,
    @MANV VARCHAR(20),
    @PUBKEY VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra sinh viên tồn tại
    IF NOT EXISTS (SELECT 1 FROM SINHVIEN WHERE MASV = @MASV)
    BEGIN
        RAISERROR('Sinh viên với mã %s không tồn tại.', 16, 1, @MASV);
        RETURN;
    END
    
    -- Kiểm tra học phần tồn tại
    IF NOT EXISTS (SELECT 1 FROM HOCPHAN WHERE MAHP = @MAHP)
    BEGIN
        RAISERROR('Học phần với mã %s không tồn tại.', 16, 1, @MAHP);
        RETURN;
    END
    
    -- Kiểm tra nhân viên tồn tại và PUBKEY khớp
    IF NOT EXISTS (SELECT 1 FROM NHANVIEN WHERE MANV = @MANV AND PUBKEY = @PUBKEY)
    BEGIN
        RAISERROR('Nhân viên hoặc PUBKEY không hợp lệ.', 16, 1);
        RETURN;
    END
    
    -- Kiểm tra MANV có quyền nhập điểm (nhân viên quản lý lớp của sinh viên)
    IF NOT EXISTS (
        SELECT 1 
        FROM SINHVIEN SV 
        JOIN LOP L ON SV.MALOP = L.MALOP 
        WHERE SV.MASV = @MASV AND L.MANV = @MANV
    )
    BEGIN
        RAISERROR('Nhân viên không có quyền nhập điểm cho sinh viên này.', 16, 1);
        RETURN;
    END
    
    -- Kiểm tra điểm hợp lệ
    IF @DIEMTHI < 0 OR @DIEMTHI > 10
    BEGIN
        RAISERROR('Điểm thi phải nằm trong khoảng từ 0 đến 10.', 16, 1);
        RETURN;
    END
    
    -- Kiểm tra bản ghi điểm đã tồn tại
    IF EXISTS (SELECT 1 FROM BANGDIEM WHERE MASV = @MASV AND MAHP = @MAHP)
    BEGIN
        RAISERROR('Điểm cho sinh viên %s và học phần %s đã tồn tại.', 16, 1, @MASV, @MAHP);
        RETURN;
    END

    -- Mã hóa điểm bằng RSA
	-- Tên khóa bất đối xứng (asymmetric key) là public key của nhân viên nhập điểm
	DECLARE @ENCRYPTED_DIEM VARBINARY(MAX);
    SET @ENCRYPTED_DIEM= EncryptByAsymKey(
        AsymKey_ID(@PUBKEY), 
        CAST(@DIEMTHI AS VARCHAR)
    );
    
    IF @ENCRYPTED_DIEM IS NULL
    BEGIN
        RAISERROR('Mã hóa điểm thất bại.', 16, 1);
        RETURN;
    END
    
    -- Thêm điểm vào bảng BANGDIEM
    INSERT INTO BANGDIEM (MASV, MAHP, DIEMTHI)
    VALUES (@MASV, @MAHP, @ENCRYPTED_DIEM);
    
    IF @@ROWCOUNT = 0
        RAISERROR('Thêm điểm thất bại.', 16, 1);
END;
GO

-- Cập nhật điểm cho học phần và sinh viên
CREATE PROCEDURE SP_UPDATE_DIEM_BY_SINHVIEN_AND_HOCPHAN
    @MASV VARCHAR(20),
    @MAHP VARCHAR(20),
    @DIEMTHI FLOAT,
    @MANV VARCHAR(20),
    @PUBKEY VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Kiểm tra sinh viên tồn tại
    IF NOT EXISTS (SELECT 1 FROM SINHVIEN WHERE MASV = @MASV)
    BEGIN
        RAISERROR('Sinh viên với mã %s không tồn tại.', 16, 1, @MASV);
        RETURN;
    END
    
    -- Kiểm tra học phần tồn tại
    IF NOT EXISTS (SELECT 1 FROM HOCPHAN WHERE MAHP = @MAHP)
    BEGIN
        RAISERROR('Học phần với mã %s không tồn tại.', 16, 1, @MAHP);
        RETURN;
    END
    
    -- Kiểm tra nhân viên tồn tại và PUBKEY khớp
    IF NOT EXISTS (SELECT 1 FROM NHANVIEN WHERE MANV = @MANV AND PUBKEY = @PUBKEY)
    BEGIN
        RAISERROR('Nhân viên hoặc PUBKEY không hợp lệ.', 16, 1);
        RETURN;
    END
    
    -- Kiểm tra MANV có quyền cập nhật điểm (nhân viên quản lý lớp của sinh viên)
    IF NOT EXISTS (
        SELECT 1 
        FROM SINHVIEN SV 
        JOIN LOP L ON SV.MALOP = L.MALOP
        WHERE SV.MASV = @MASV AND L.MANV = @MANV
    )
    BEGIN
        RAISERROR('Nhân viên không có quyền cập nhật điểm cho sinh viên này.', 16, 1);
        RETURN;
    END
    
    -- Kiểm tra điểm hợp lệ
    IF @DIEMTHI < 0 OR @DIEMTHI > 10
    BEGIN
        RAISERROR('Điểm thi phải nằm trong khoảng từ 0 đến 10.', 16, 1);
        RETURN;
    END
    
    -- Kiểm tra bản ghi điểm tồn tại
    IF NOT EXISTS (SELECT 1 FROM BANGDIEM WHERE MASV = @MASV AND MAHP = @MAHP)
    BEGIN
        RAISERROR('Không tồn tại điểm cho sinh viên %s và học phần %s.', 16, 1, @MASV, @MAHP);
        RETURN;
    END
    
    -- Mã hóa điểm bằng RSA
    -- Tên khóa bất đối xứng (asymmetric key) là public key của nhân viên nhập điểm
	DECLARE @ENCRYPTED_DIEM VARBINARY(MAX);
    SET @ENCRYPTED_DIEM= EncryptByAsymKey(
        AsymKey_ID(@PUBKEY), 
        CAST(@DIEMTHI AS VARCHAR)
    );
    
    IF @ENCRYPTED_DIEM IS NULL
    BEGIN
        RAISERROR('Mã hóa điểm thất bại.', 16, 1);
        RETURN;
    END
    
    -- Cập nhật điểm trong bảng BANGDIEM
    UPDATE BANGDIEM
    SET DIEMTHI = @ENCRYPTED_DIEM
    WHERE MASV = @MASV AND MAHP = @MAHP;
    
    IF @@ROWCOUNT = 0
        RAISERROR('Cập nhật điểm thất bại.', 16, 1);
END;

