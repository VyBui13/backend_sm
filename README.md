# Backend cho hệ thống Quản lý sinh viên

- [x] Lấy danh sách sinh viên (có kèm param để lấy sinh viên do nhân viên quản lý)
  - [x] Làm stored procedure: SP_SEL_ALL_PUBLIC_SINHVIEN, SP_SEL_PUBLIC_SINHVIEN_BY_NHANVIEN
- [ ] Lấy danh sách học phần theo mã sinh viên
  - [x] Làm stored procedure: SP_GET_ALL_HOCPHAN_BY_SINHVIEN
- [ ] Lấy danh sách lớp
  - [x] Làm stored procedure: SP_GET_ALL_LOP
- [ ] Lấy thông tin học phần có cả điểm của 1 sinh viên
  - [x] Làm stored procedure: SP_GET_ALL_HOCPHAN_WITH_DIEM_BY_SINHVIEN
- [ ] Nhập điểm cho sinh viên (phải gửi kèm mã nhân viên nhập)
  - [x] Làm stored procedure: SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN
- [ ] Cập nhật điểm cho sinh viên (phải gửi kèm mã nhân viên nhập)
  - [x] Làm stored procedure: SP_UPDATE_DIEM_BY_SINHVIEN_AND_HOCPHAN

## Tổng hợp các procedure cần thiết

- [x] Thêm sinh viên mới: SP_INS_PUBLIC_SINHVIEN
  - @MASV VARCHAR(20),
  - @HOTEN NVARCHAR(100),
  - @NGAYSINH DATETIME,
  - @DIACHI NVARCHAR(200),
  - @MALOP NVARCHAR(200),
  - @TENDN NVARCHAR(100),
  - @MK NVARCHAR(100)
- [x] Cập nhật thông tin sinh viên theo mã sinh viên (, các trường được thay đổi là @HOTEN, @NGAYSINH, @DIACHI, @MALOP) (với 2 tham số cuối @MANV là mã của nhân viên thay đổi, @PUBKEY là giá trị của PUBKEY của nhân viên thay đổi, giá trị MANV phải khớp với MANV của LOP mà sinh viên đó thuộc về, còn không thì không cho thay đổi): SP_UPDATE_SINHVIEN
  - @MASV VARCHAR(20),
  - @HOTEN NVARCHAR(100),
  - @NGAYSINH DATETIME,
  - @DIACHI NVARCHAR(200),
  - @MALOP NVARCHAR(200),
  - @MANV VARCHAR(20),
  - @PUBKEY VARCHAR(20)
- [x] Xóa sinh viên: SP_DELETE_SINHVIEN
  - @MASV VARCHAR(20)
- [x] Thêm học phần: SP_INS_HOCPHAN
  - @MAHP VARCHAR(20),
  - @TENHP NVARCHAR(100),
  - @SOTC INT
- [x] Cập nhật học phần theo mã học phần: SP_UPDATE_HOCPHAN
  - @MAHP VARCHAR(20),
  - @TENHP NVARCHAR(100),
  - @SOTC INT
- [x] Xóa học phần: SP_DELETE_HOCPHAN

  - @MAHP VARCHAR(20)

- [x] Xem tất cả các lớp: SP_GET_ALL_LOP
- [x] Thêm lớp SP_INS_LOP
  - @MALOP VARCHAR(20),
  - @TENLOP NVARCHAR(100),
  - @MANV VARCHAR(20)
- [x] Cập nhật lớp theo mã lớp: SP_UPDATE_LOP
  - @MALOP VARCHAR(20),
  - @TENLOP NVARCHAR(100),
  - @MANV VARCHAR(20)
- [x] Xóa lớp: SP_DELETE_LOP

  - @MALOP VARCHAR(20)

- [x] Thêm điểm cho học phần và sinh viên (mã hóa bằng pubkey của nhân viên thêm): SP_INS_DIEM_BY_SINHVIEN_AND_HOCPHAN
  - @MASV VARCHAR(20),
  - @MAHP VARCHAR(20),
  - @DIEMTHI FLOAT,
  - @MANV VARCHAR(20),
  - @PUBKEY VARCHAR(20)
- [x] Cập nhật điểm cho học phần và sinh viên (mã hóa bằng pubkey của nhân viên thêm): SP_UPDATE_DIEM_BY_SINHVIEN_AND_HOCPHAN
  - @MASV VARCHAR(20),
  - @MAHP VARCHAR(20),
  - @DIEMTHI FLOAT,
  - @MANV VARCHAR(20),
  - @PUBKEY VARCHAR(20)
