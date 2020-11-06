package com.capg.emppayrollservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeServiceTest {
	static EmployeePayrollServiceDB DataBaseObj;
	static Map<String, Double> empDataByGender;
	static List<EmployeePayrollData> empPayrollList;
	
	@BeforeClass
	public static void setup(){
		DataBaseObj = new EmployeePayrollServiceDB();
		empDataByGender = new HashMap<>();
		empPayrollList = new ArrayList<>();
	}
	
	@Test
	public void givenEmpPayrollDB_WhenRetrieved_ShouldMatchEmpCount() throws DBServiceException{
		empPayrollList = DataBaseObj.viewEmployeePayroll();
		assertEquals(3, empPayrollList.size());
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
	
	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmpCount() throws DBServiceException{
		empPayrollList = DataBaseObj.viewEmployeePayrollByJoinDateRange(LocalDate.of(2018,02,01), LocalDate.now() );
		assertEquals(2, empPayrollList.size());
	}
	
	@Test
	public void givenEmployeeDB_WhenRetrievedSum_ShouldReturnSumGroupedByGender() throws DBServiceException {
		empDataByGender = DataBaseObj.viewEmployeeDataGroupedByGender("salary" , "sum");
		assertEquals(4000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}

	@Test
	public void givenEmployeeDB_WhenRetrievedAvg_ShouldReturnAvgByGroupedGender() throws DBServiceException {
		empDataByGender = DataBaseObj.viewEmployeeDataGroupedByGender("salary" , "avg");
		assertEquals(2000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}

	@Test
	public void givenEmployeeDB_WhenRetrievedMax_ShouldReturnMaxGroupedByGender() throws DBServiceException {
		empDataByGender = DataBaseObj.viewEmployeeDataGroupedByGender("salary" , "max");
		assertEquals(3000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}
	
	@Test
	public void givenEmployeeDB_WhenRetrievedMin_ShouldReturnMinGroupedByGender() throws DBServiceException {
		empDataByGender = DataBaseObj.viewEmployeeDataGroupedByGender("salary" , "min");
		assertEquals(1000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}

	@Test
	public void givenEmployeeDB_WhenRetrievedCount_ShouldReturnCountGroupedByGender() throws DBServiceException {
		empDataByGender = DataBaseObj.viewEmployeeDataGroupedByGender("salary", "count");
		assertEquals(2, empDataByGender.get("M"), 0.0);
		assertEquals(1, empDataByGender.get("F"), 0.0);
	}

}
