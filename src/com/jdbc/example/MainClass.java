package com.jdbc.example;

import java.util.List;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Model model = new Model();
		//model.selectOne();
		
		
//		System.out.println("부서아이디>");
//		int id = scan.nextInt();
//		
//		System.out.println("부서이름>");
//		String name = scan.next();
//		
//		System.out.println("매니저아이디>");
//		String mId = scan.next();
//		
//		System.out.println("부서아이디>");
//		String lId = scan.next();
//		
//		model.insertOne(id, name, mId, lId);
		
		
//		System.out.println("부서아이디>");
//		int did = scan.nextInt();
//		
//		System.out.println("바꿀 부서이름>");
//		String name = scan.next();
//		
//		System.out.println("바꿀 매니저아이디>");
//		int mId = scan.nextInt();
//		
		//model.updateOne(100, "eeeee", 10);
		
		//model.deleteOne(100);
		List<EmployeeVO> list = model.selectTwo();
		
		list.stream().forEach((a) -> {
			
			System.out.println(a.getEmployeeId());
			System.out.println(a.getFirstName());
			System.out.println(a.getSalary());
			System.out.println(a.getDepartmentName());
			
		});
		
	
		
	}

}
