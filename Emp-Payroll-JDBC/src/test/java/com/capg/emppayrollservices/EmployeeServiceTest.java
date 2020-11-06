package com.capg.emppayrollservices;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeServiceTest {
	static EmployeePayrollServiceDB DBObj;
	
	@BeforeClass
	public static void setup(){
		DBObj = new EmployeePayrollServiceDB();
	}
	
	@Test
	public void givenDataBase_whenRetrieved_ShouldMatchEmpCount() throws DBServiceException {
		List<EmployeePayrollData> empList = DBObj.viewEmployeePayroll();
		assertEquals(3, empList.size());
	}

}
