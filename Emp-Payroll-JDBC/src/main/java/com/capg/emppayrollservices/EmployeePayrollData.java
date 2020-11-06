package com.capg.emppayrollservices;

import java.time.LocalDate;

public class EmployeePayrollData {
	private int emp_id;
	private int id;
	private int company_id;
	private String name;
	private String gender;
	private String dept_name;
	private double salary;
	private double basic_pay;
	private double deductions;
	private double taxable_pay;
	private double tax;
	private double net_pay;
	private LocalDate start_date;
	
	public EmployeePayrollData(int emp_id, String name, String gender , double salary, LocalDate start) {
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.start_date = start_date;
		this.emp_id = emp_id;
	}
	
	public EmployeePayrollData(String name, String gender, double salary, LocalDate start_date , int company_id) {
		super();
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.start_date = start_date;
		this.company_id = company_id;
	}


	public EmployeePayrollData(int emp_id, String name, String gender, double salary, LocalDate start,int company_id, int id,
			double basic_pay, double deductions, double taxable_pay, double tax, double net_pay,int emp_id2,String dept_name) {
		this(emp_id,name,gender,salary,start);
		this.id = id;
		this.basic_pay = basic_pay;
		this.deductions = deductions;
		this.taxable_pay = taxable_pay;
		this.tax = tax;
		this.net_pay = net_pay;
		this.company_id = company_id;
		this.dept_name = dept_name;
		
	}

	public int getEmp_id() {
		return emp_id;
	}
	
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	@Override
	public String toString() {
		return "EmployeePayrollData [emp_id=" + emp_id + ", name=" + name + ", gender=" + gender + ", salary=" + salary
				+ ", start_date=" + start_date + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePayrollData other = (EmployeePayrollData) obj;
		if (emp_id != other.emp_id)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		return true;
	}
	
}