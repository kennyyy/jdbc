package com.jdbc.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Model {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "hr";
	private String upw = "hr";
	
	public Model() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//select할 내용작성
	public void selectOne() {
		
		
		String sql= "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID >= ?";
		
		
		
		
		//모든 jdbc코드는 try~catch구문에서 작성이 들어가야 합니다. (throws를 던지고 있기 때문에)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			//1. JDBC 드라이버 준비
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			//2. conn객체 생성
			
			conn = DriverManager.getConnection(url, uid, upw);
			
			//3. conn으로부터 statement 객체 생성 - sql상태를 지정하기 위한 객체
			
			pstmt = conn.prepareStatement(sql);
			
			//?에 개수에 맞추어 값을 채웁니다. 
			//setString(순서 , 문자열)
			//setInt(순서 , 숫자)
			//setDouble(순서 , 실수)
			pstmt.setString(1 , "120");
			
			//4. 실행 
			//executeQuery - select문에 사용합니다.
			//executeUpdate - insert, update, delete문에 사용합니다.
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) { // next() 현재 값이 있으면 true, 없으면 false (row단위로 이동)
				//rs.getString(컬럼명) - 문자열 반환
				//rs.getInt(컬럼명) - 정수형 반환
				//rs.getDouble(컬럼명) - 실수형 반환
				//rs.getDate(컬럼명) - 날자형 반환
				int emp_id = rs.getInt("EMPLOYEE_ID");
				String first_name = rs.getString("FIRST_NAME");
				String phone_number = rs.getString("phone_number");
				//String hire_date = rs.getString("hire_date");
				Timestamp hire_date = rs.getTimestamp("hire_date");
				int salary = rs.getInt("salary");
				
				System.out.println("아이디 : " + emp_id);
				System.out.println("이름 : " + first_name);
				System.out.println("전화번호 : " + phone_number);
				System.out.println("입사일 : " + hire_date);
				System.out.println("급여 : " + salary);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();     
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//insert할 내용작성
	public void insertOne(int id, String name, String mId, String lID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO DEPTS VALUES(?,?,?,?)";
		//resultSet은 insert에서 필요가 없습니다.
		try {
			
			
			//1. conn생성
			
			conn = DriverManager.getConnection(url, uid, upw);
			
			//2. pstmt생성
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, mId);
			pstmt.setString(4, lID);
			
			
			//3. sql실행
			int result = pstmt.executeUpdate();//성공시 1 or 실패시 0
			if(result == 1) {
				System.out.println("인서트 성공");
			}else {
				System.out.println("인서트 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {     
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//upate할 내용작성 (실습)
	public void updateOne(int did, String dName, int mId) {
		//Main에서 부서아이디, 부서명, 매니저아이디만 받아서, 해당부서명과,
		//매니저아이디를 수정해주세요
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE DEPTS SET DEPARTMENT_NAME = ?, MANAGER_ID = ? WHERE DEPARTMENT_ID = ?";
		
		try {
			
			
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dName);
			pstmt.setInt(2, mId);
			pstmt.setInt(3, did);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("업데이트 성공");
			}else {
				System.out.println("업데이트 실패");
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {     
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//delete할 내용작성(실습)
	//main Employee_id를 받아서 emps테이블에서 해당 아이디를 삭제해주세요.
	public void deleteOne(int eId) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM EMPS WHERE EMPLOYEE_ID = ?";
		
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, eId);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {     
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	//조인을 통한 select(실습)
	public List<EmployeeVO> selectTwo() {
		//사원번호, 이름, 부서명, - 급여순으로 정렬을해서 10~20번에 속해있는 데이터를 출력.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		//값을 담을 ArrayList
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		
		//sql
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT EMPLOYEE_ID,FIRST_NAME, D.DEPARTMENT_NAME, SALARY FROM EMPLOYEES E\r\n"
				+ "LEFT JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID\r\n"
				+ "ORDER BY SALARY) A)\r\n"
				+ "WHERE RN <= 50 AND RN >= 10";
		
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
	
				int emp_id = rs.getInt("EMPLOYEE_ID");
				String emp_name = rs.getString("FIRST_NAME");
				String dept_name = rs.getString("DEPARTMENT_NAME");
				int emp_salary = rs.getInt("SALARY");
				
				
				System.out.println("emp_id : " + emp_id);
				System.out.println("emp_name : " + emp_name);
				System.out.println("dept_name : " + dept_name);
				System.out.println("emp_salary : " + emp_salary);
				
				System.out.println("---------------------------");
				EmployeeVO vo = new EmployeeVO(emp_id, emp_name, emp_salary, dept_name);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	
	
	
}
