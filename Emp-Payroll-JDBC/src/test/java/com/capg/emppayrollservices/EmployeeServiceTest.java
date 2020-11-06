package com.capg.emppayrollservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeServiceTest {
	static EmployeePayrollServiceDB DataBaseObj;
	
	@BeforeClass
	public static void setup(){
		DataBaseObj = new EmployeePayrollServiceDB();
	}
	
	@Test
	public void givenDataBase_whenRetrieved_ShouldMatchEmpCount() throws DBServiceException {
		List<EmployeePayrollData> empList = DataBaseObj.viewEmployeePayroll();
		assertEquals(3, empList.size());
	}
	
	@Test
	public void givenUpdatedSalary_WhenRetrieved_ShouldBeSyncedWithDBUsingStatement() throws DBServiceException{
		DataBaseObj.updateEmployeeSalaryUsingStatement("Terisa", 3000000.0);
		boolean isSynced = DataBaseObj.isEmpPayrollSyncedWithDB("Terisa");
		assertTrue(isSynced);
	}

	@Test
	public void givenUpdatedSalary_WhenRetrieved_ShouldBeSyncedWithDBUsingPreparedStatement() throws DBServiceException{
		DataBaseObj.updateEmployeeSalaryUsingPreparedStatement("Terisa", 3000000.0);
		boolean isSynced = DataBaseObj.isEmpPayrollSyncedWithDB("Terisa");
		assertTrue(isSynced);
	}

}
