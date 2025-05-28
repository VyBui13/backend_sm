USE QLSVNhom
GO

-- Thêm 2 nhân viên
-- EXEC SP_INS_PUBLIC_NHANVIEN 
--     @MANV = 'NV001', 
--     @HOTEN = N'Nguyễn Văn An', 
--     @EMAIL = 'an@school.com', 
--     @LUONGCB = 5000000, 
--     @TENDN = 'nv_an', 
--     @MK = 'password123';

-- EXEC SP_INS_PUBLIC_NHANVIEN 
--     @MANV = 'NV002', 
--     @HOTEN = N'Trần Thị Bình', 
--     @EMAIL = 'binh@school.com', 
--     @LUONGCB = 5500000, 
--     @TENDN = 'nv_binh', 
--     @MK = 'password456';

-- Thêm 2 lớp
EXEC SP_INS_LOP 
    @MALOP = 'LOP001', 
    @TENLOP = N'Lớp 12A1', 
    @MANV = 'NV001';

EXEC SP_INS_LOP 
    @MALOP = 'LOP002', 
    @TENLOP = N'Lớp 12A2', 
    @MANV = 'NV002';


-- Thêm 12 sinh viên (7 sinh viên lớp LOP001, 5 sinh viên lớp LOP002)
-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV001', 
--     @HOTEN = N'Nguyễn Văn Hùng', 
--     @NGAYSINH = '2003-01-15', 
--     @DIACHI = N'Hà Nội', 
--     @MALOP = 'LOP001', 
--     @TENDN = 'sv_hung', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV002', 
--     @HOTEN = N'Trần Thị Mai', 
--     @NGAYSINH = '2003-02-20', 
--     @DIACHI = N'Hà Nội', 
--     @MALOP = 'LOP001', 
--     @TENDN = 'sv_mai', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV003', 
--     @HOTEN = N'Lê Văn Nam', 
--     @NGAYSINH = '2003-03-10', 
--     @DIACHI = N'Hà Nội', 
--     @MALOP = 'LOP001', 
--     @TENDN = 'sv_nam', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV004', 
--     @HOTEN = N'Phạm Thị Lan', 
--     @NGAYSINH = '2003-04-05', 
--     @DIACHI = N'Hà Nội', 
--     @MALOP = 'LOP001', 
--     @TENDN = 'sv_lan', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV005', 
--     @HOTEN = N'Hoàng Văn Tùng', 
--     @NGAYSINH = '2003-05-12', 
--     @DIACHI = N'Hà Nội', 
--     @MALOP = 'LOP001', 
--     @TENDN = 'sv_tung', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV006', 
--     @HOTEN = N'Ngô Thị Hà', 
--     @NGAYSINH = '2003-06-18', 
--     @DIACHI = N'Hà Nội', 
--     @MALOP = 'LOP001', 
--     @TENDN = 'sv_ha', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV007', 
--     @HOTEN = N'Vũ Văn Long', 
--     @NGAYSINH = '2003-07-22', 
--     @DIACHI = N'Hà Nội', 
--     @MALOP = 'LOP001', 
--     @TENDN = 'sv_long', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV008', 
--     @HOTEN = N'Đỗ Thị Thảo', 
--     @NGAYSINH = '2003-08-30', 
--     @DIACHI = N'TP.HCM', 
--     @MALOP = 'LOP002', 
--     @TENDN = 'sv_thao', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV009', 
--     @HOTEN = N'Bùi Văn Khoa', 
--     @NGAYSINH = '2003-09-14', 
--     @DIACHI = N'TP.HCM', 
--     @MALOP = 'LOP002', 
--     @TENDN = 'sv_khoa', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV010', 
--     @HOTEN = N'Nguyễn Thị Linh', 
--     @NGAYSINH = '2003-10-25', 
--     @DIACHI = N'TP.HCM', 
--     @MALOP = 'LOP002', 
--     @TENDN = 'sv_linh', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV011', 
--     @HOTEN = N'Trần Văn Dũng', 
--     @NGAYSINH = '2003-11-03', 
--     @DIACHI = N'TP.HCM', 
--     @MALOP = 'LOP002', 
--     @TENDN = 'sv_dung', 
--     @MK = 'sv123';

-- EXEC SP_INS_PUBLIC_SINHVIEN 
--     @MASV = 'SV012', 
--     @HOTEN = N'Lê Thị Hương', 
--     @NGAYSINH = '2003-12-12', 
--     @DIACHI = N'TP.HCM', 
--     @MALOP = 'LOP002', 
--     @TENDN = 'sv_huong', 
--     @MK = 'sv123';

-- Thêm 8 học phần
EXEC SP_INS_HOCPHAN 
    @MAHP = 'HP001', 
    @TENHP = N'Toán Cao Cấp', 
    @SOTC = 3;

EXEC SP_INS_HOCPHAN 
    @MAHP = 'HP002', 
    @TENHP = N'Vật Lý Đại Cương', 
    @SOTC = 3;

EXEC SP_INS_HOCPHAN 
    @MAHP = 'HP003', 
    @TENHP = N'Hóa Học Cơ Bản', 
    @SOTC = 3;

EXEC SP_INS_HOCPHAN 
    @MAHP = 'HP004', 
    @TENHP = N'Lập Trình Cơ Bản', 
    @SOTC = 4;

EXEC SP_INS_HOCPHAN 
    @MAHP = 'HP005', 
    @TENHP = N'Anh Văn 1', 
    @SOTC = 2;

EXEC SP_INS_HOCPHAN 
    @MAHP = 'HP006', 
    @TENHP = N'Kỹ Năng Mềm', 
    @SOTC = 2;

EXEC SP_INS_HOCPHAN 
    @MAHP = 'HP007', 
    @TENHP = N'Tư Tưởng Hồ Chí Minh', 
    @SOTC = 2;

EXEC SP_INS_HOCPHAN 
    @MAHP = 'HP008', 
    @TENHP = N'Giáo Dục Thể Chất', 
    @SOTC = 1;


/*
-- Thêm điểm cho sinh viên (mỗi sinh viên học 4 học phần ngẫu nhiên)
-- Sinh viên SV001 (Lớp LOP001, NV001)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV001', 
    @MAHP = 'HP001', 
    @DIEMTHI = 8.5, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV001', 
    @MAHP = 'HP002', 
    @DIEMTHI = 7.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV001', 
    @MAHP = 'HP004', 
    @DIEMTHI = 9.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV001', 
    @MAHP = 'HP005', 
    @DIEMTHI = 6.5, 
    @MANV = 'NV001'

-- Sinh viên SV002 (Lớp LOP001, NV001)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV002', 
    @MAHP = 'HP001', 
    @DIEMTHI = 7.5, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV002', 
    @MAHP = 'HP003', 
    @DIEMTHI = 8.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV002', 
    @MAHP = 'HP004', 
    @DIEMTHI = 6.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV002', 
    @MAHP = 'HP006', 
    @DIEMTHI = 9.5, 
    @MANV = 'NV001'

-- Sinh viên SV003 (Lớp LOP001, NV001)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV003', 
    @MAHP = 'HP002', 
    @DIEMTHI = 6.5, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV003', 
    @MAHP = 'HP003', 
    @DIEMTHI = 7.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV003', 
    @MAHP = 'HP005', 
    @DIEMTHI = 8.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV003', 
    @MAHP = 'HP007', 
    @DIEMTHI = 7.5, 
    @MANV = 'NV001'

-- Sinh viên SV004 (Lớp LOP001, NV001)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV004', 
    @MAHP = 'HP001', 
    @DIEMTHI = 9.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV004', 
    @MAHP = 'HP004', 
    @DIEMTHI = 8.5, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV004', 
    @MAHP = 'HP006', 
    @DIEMTHI = 7.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV004', 
    @MAHP = 'HP008', 
    @DIEMTHI = 8.0, 
    @MANV = 'NV001'

-- Sinh viên SV005 (Lớp LOP001, NV001)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV005', 
    @MAHP = 'HP002', 
    @DIEMTHI = 7.5, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV005', 
    @MAHP = 'HP005', 
    @DIEMTHI = 6.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV005', 
    @MAHP = 'HP007', 
    @DIEMTHI = 8.5, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV005', 
    @MAHP = 'HP008', 
    @DIEMTHI = 9.0, 
    @MANV = 'NV001'

-- Sinh viên SV006 (Lớp LOP001, NV001)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV006', 
    @MAHP = 'HP001', 
    @DIEMTHI = 6.5, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV006', 
    @MAHP = 'HP003', 
    @DIEMTHI = 7.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV006', 
    @MAHP = 'HP004', 
    @DIEMTHI = 8.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV006', 
    @MAHP = 'HP006', 
    @DIEMTHI = 7.5, 
    @MANV = 'NV001'

-- Sinh viên SV007 (Lớp LOP001, NV001)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV007', 
    @MAHP = 'HP002', 
    @DIEMTHI = 8.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV007', 
    @MAHP = 'HP005', 
    @DIEMTHI = 9.0, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV007', 
    @MAHP = 'HP007', 
    @DIEMTHI = 6.5, 
    @MANV = 'NV001'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV007', 
    @MAHP = 'HP008', 
    @DIEMTHI = 7.0, 
    @MANV = 'NV001'

-- Sinh viên SV008 (Lớp LOP002, NV002)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV008', 
    @MAHP = 'HP001', 
    @DIEMTHI = 7.5, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV008', 
    @MAHP = 'HP003', 
    @DIEMTHI = 8.0, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV008', 
    @MAHP = 'HP005', 
    @DIEMTHI = 6.5, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV008', 
    @MAHP = 'HP007', 
    @DIEMTHI = 9.0, 
    @MANV = 'NV002'

-- Sinh viên SV009 (Lớp LOP002, NV002)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV009', 
    @MAHP = 'HP002', 
    @DIEMTHI = 8.5, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV009', 
    @MAHP = 'HP004', 
    @DIEMTHI = 7.0, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV009', 
    @MAHP = 'HP006', 
    @DIEMTHI = 6.0, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV009', 
    @MAHP = 'HP008', 
    @DIEMTHI = 7.5, 
    @MANV = 'NV002'

-- Sinh viên SV010 (Lớp LOP002, NV002)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV010', 
    @MAHP = 'HP001', 
    @DIEMTHI = 6.5, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV010', 
    @MAHP = 'HP003', 
    @DIEMTHI = 7.0, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV010', 
    @MAHP = 'HP005', 
    @DIEMTHI = 8.0, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV010', 
    @MAHP = 'HP007', 
    @DIEMTHI = 9.5, 
    @MANV = 'NV002'

-- Sinh viên SV011 (Lớp LOP002, NV002)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV011', 
    @MAHP = 'HP002', 
    @DIEMTHI = 7.0, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV011', 
    @MAHP = 'HP004', 
    @DIEMTHI = 8.5, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV011', 
    @MAHP = 'HP006', 
    @DIEMTHI = 6.5, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV011', 
    @MAHP = 'HP008', 
    @DIEMTHI = 7.0, 
    @MANV = 'NV002'

-- Sinh viên SV012 (Lớp LOP002, NV002)
EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV012', 
    @MAHP = 'HP001', 
    @DIEMTHI = 8.0, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV012', 
    @MAHP = 'HP003', 
    @DIEMTHI = 9.0, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV012', 
    @MAHP = 'HP005', 
    @DIEMTHI = 6.0, 
    @MANV = 'NV002'

EXEC SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN 
    @MASV = 'SV012', 
    @MAHP = 'HP007', 
    @DIEMTHI = 7.5, 
    @MANV = 'NV002'

*/