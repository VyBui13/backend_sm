package com.studentmanagement.staff.repository.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.studentmanagement.staff.dto.CreateStaffDto;
import com.studentmanagement.staff.mapper.SignInRowMapper;
import com.studentmanagement.staff.mapper.StaffRowMapper;
import com.studentmanagement.staff.model.Staff;
import com.studentmanagement.staff.repository.StaffRepository;

import jakarta.annotation.PostConstruct;

@Repository
public class StaffRepositoryImpl implements StaffRepository {
	private JdbcTemplate sqlServerJdbcTemplate;

	public StaffRepositoryImpl(@Qualifier("sqlServerJdbcTemplate") JdbcTemplate sqlServerJdbcTemplate) {
		this.sqlServerJdbcTemplate = sqlServerJdbcTemplate;
	}

	private SimpleJdbcCall signInStaffProc;
	private SimpleJdbcCall getStaffInfoProc;
	private SimpleJdbcCall createStaffProc;

	private SimpleJdbcCall DEPRECATEDsignInStaff;
	private SimpleJdbcCall DEPRECATEDgetStaffInfoProc;

	// This happens after the constructor is called, utilizing the JDBC template
	@PostConstruct
	private void init() {
		signInStaffProc = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_SIGN_IN_ENCRYPT_NHANVIEN")
				.declareParameters(
						new SqlParameter("TENDN", Types.NVARCHAR),
						new SqlParameter("MK", Types.VARBINARY))
				.returningResultSet("result", new SignInRowMapper());

		getStaffInfoProc = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_SEL_PUBLIC_ENCRYPT_NHANVIEN")
				.declareParameters(
						new SqlParameter("TENDN", Types.NVARCHAR),
						new SqlParameter("MK", Types.VARBINARY))
				.returningResultSet("result", new StaffRowMapper());

		createStaffProc = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_INS_PUBLIC_ENCRYPT_NHANVIEN")
				.declareParameters(
						new SqlParameter("MANV", Types.VARCHAR),
						new SqlParameter("HOTEN", Types.NVARCHAR),
						new SqlParameter("EMAIL", Types.VARCHAR),
						new SqlParameter("LUONG", Types.VARBINARY),
						new SqlParameter("TENDN", Types.NVARCHAR),
						new SqlParameter("MK", Types.VARBINARY),
						new SqlParameter("PUB", Types.VARCHAR));

		DEPRECATEDsignInStaff = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_SIGN_IN_NHANVIEN")
				.declareParameters(
						new SqlParameter("TENDN", Types.NVARCHAR),
						new SqlParameter("MK", Types.NVARCHAR))
				.returningResultSet("result", new SignInRowMapper());

		DEPRECATEDgetStaffInfoProc = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_SEL_PUBLIC_NHANVIEN")
				.declareParameters(
						new SqlParameter("TENDN", Types.NVARCHAR),
						new SqlParameter("MK", Types.NVARCHAR))
				.returningResultSet("result", new StaffRowMapper());
	}

	@Override
	public Boolean signIn(String username, byte[] password) {
		Map<String, Object> inParams = Map.of(
				"TENDN", username,
				"MK", password);

		Map<String, Object> out = signInStaffProc.execute(inParams);

		Object resultObj = out.get("result");

		if (resultObj instanceof List<?> resultList
				&& !resultList.isEmpty()
				&& resultList.get(0) instanceof Boolean result) {

			return result;
		}

		return null;
	}

	@Override
	public Staff getStaffInfoByCredentials(String username, byte[] password) {
		Map<String, Object> inParams = Map.of(
				"TENDN", username,
				"MK", password);

		Map<String, Object> out = getStaffInfoProc.execute(inParams);

		Object resultObj = out.get("result");

		if (resultObj instanceof List<?> resultList
				&& !resultList.isEmpty()
				&& resultList.get(0) instanceof Staff staff) {

			return staff;
		}

		return null;
	}

	@Override
	public void createStaff(
			String id,
			String fullname,
			String email,
			byte[] salary,
			String username,
			byte[] password,
			String pub) {

		Map<String, Object> inParams = Map.of(
				"MANV", id,
				"HOTEN", fullname,
				"EMAIL", email,
				"LUONG", salary,
				"TENDN", username,
				"MK", password,
				"PUB", pub);

		createStaffProc.execute(inParams);
	}

	@Override
	public Boolean DEPRECATEDsignIn(String username, String password) {
		Map<String, Object> inParams = Map.of(
				"TENDN", username,
				"MK", password);

		Map<String, Object> out = DEPRECATEDsignInStaff.execute(inParams);

		Object resultObj = out.get("result");

		if (resultObj instanceof List<?> resultList
				&& !resultList.isEmpty()
				&& resultList.get(0) instanceof Boolean result) {

			return result;
		}

		return null;
	}

	@Override
	public Staff DEPRECATEDgetStaffInfoByCredentials(String username, String password) {
		Map<String, Object> inParams = Map.of(
				"TENDN", username,
				"MK", password);

		Map<String, Object> out = DEPRECATEDgetStaffInfoProc.execute(inParams);

		Object resultObj = out.get("result");

		if (resultObj instanceof List<?> resultList
				&& !resultList.isEmpty()
				&& resultList.get(0) instanceof Staff staff) {

			return staff;
		}

		return null;
	}
}
