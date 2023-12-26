package com.jdbc.example;

public class EmployeeVO {
	//단순히 값을 저장하기 위한 클래스
	
	//1. 테이블이랑 멤버변수를 동일하게 생성한다.
	//2. 기본생성자, 모든 멤버변수를 저장하는 생성자를 만든다.
	//3. getter/setter
	
	private int employeeId;
	private String firstName;
	private int salary;
	
	private String departmentName;// 조인을 통해서 저장할 값
	
	public EmployeeVO() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeVO(int employeeId, String firstName, int salary, String departmentName) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.salary = salary;
		this.departmentName = departmentName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	
	
}
