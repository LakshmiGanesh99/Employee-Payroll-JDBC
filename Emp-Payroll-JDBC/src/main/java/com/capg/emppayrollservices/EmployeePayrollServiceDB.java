package com.capg.emppayrollservices;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

public class EmployeePayrollServiceDB {

	public List<EmployeePayrollData> viewEmployeePayroll() throws DBServiceException
	{
		List<EmployeePayrollData> empPayrollList = new ArrayList();
		JDBCConnection obj = new JDBCConnection();
		EmployeePayrollData empDataObj = null;
		String query = "SELECT * FROM payroll_service.payroll";
				try (Connection con = obj.getconnection()){
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(query);
					rs.next();
					int id = rs.getInt(1);
					String name = rs.getString(2);
					double salary = rs.getDouble(3);
					LocalDate start = rs.getDate(4).toLocalDate();
					empDataObj = new EmployeePayrollData(id, name, salary, start);
					empPayrollList.add(empDataObj);

				} catch (Exception e) {
					throw new DBServiceException("SQL Exception", DBServiceExceptionType.SQL_EXCEPTION);
				}
			return empPayrollList;
	}

}
