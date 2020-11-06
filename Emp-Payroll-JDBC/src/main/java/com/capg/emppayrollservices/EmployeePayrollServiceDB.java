package com.capg.emppayrollservices;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

public class EmployeePayrollServiceDB {

	EmployeePayrollData empDataObj = null;
	List<EmployeePayrollData> empPayrollList;

	public List<EmployeePayrollData> viewEmployeePayroll() throws DBServiceException {

		List<EmployeePayrollData> empPayrollList = new ArrayList();

		String query = "SELECT * FROM payroll_service";
		try (Connection con = JDBCConnection.getconnection()) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String gender = rs.getString(3);
			double salary = rs.getDouble(4);
			LocalDate start = rs.getDate(5).toLocalDate();
			empDataObj = new EmployeePayrollData(id, name, gender, salary, start);
			empPayrollList.add(empDataObj);

		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
		return empPayrollList;
	}

	public List<EmployeePayrollData> viewEmployeePayrollByName(String name) throws DBServiceException {

		List<EmployeePayrollData> empPayrollListByName = new ArrayList();

		String query = String.format("SELECT * FROM payroll_service where name = '%s';", name);

		try (Connection con = JDBCConnection.getconnection()) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {

				int id = rs.getInt(1);
				String gender = rs.getString(3);
				double salary = rs.getDouble(4);
				LocalDate start = rs.getDate(5).toLocalDate();
				empDataObj = new EmployeePayrollData(id, name, gender, salary, start);
				empPayrollListByName.add(empDataObj);

			}

		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
		return empPayrollListByName;

	}

	public void updateEmployeeSalaryUsingStatement(String name, double salary) throws DBServiceException {

		String query = String.format("UPDATE payroll_service SET salary = '%.2f' where name = '%s';", salary, name);

		try (Connection con = JDBCConnection.getconnection()) {
			Statement st = con.createStatement();
			int rs = st.executeUpdate(query);
			empDataObj = getEmployeePayrollData(name);
			if (rs > 0 && empDataObj != null)
				empDataObj.setSalary(salary);
		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
	}

	public void updateEmployeeSalaryUsingPreparedStatement(String name, double salary) throws DBServiceException {
		String query = "update Employee_Payroll set salary = ? where name = ?";
		try (Connection con = new JDBCConnection().getconnection()) {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setDouble(1, salary);
			preparedStatement.setString(2, name);
			int result = preparedStatement.executeUpdate();
			empDataObj = getEmployeePayrollData(name);
			if (result > 0 && empDataObj != null)
				empDataObj.setSalary(salary);
		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
	}

	public EmployeePayrollData getEmployeePayrollData(String name) throws DBServiceException {
		return viewEmployeePayroll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
	}

	public boolean isEmpPayrollSyncedWithDB(String name) throws DBServiceException {
		try {
			return viewEmployeePayrollByName(name).get(0).equals(getEmployeePayrollData(name));
		} catch (IndexOutOfBoundsException e) {
		} catch (Exception e) {
			throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
		}
		return false;
	}
}
