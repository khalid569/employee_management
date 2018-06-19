package com.acc.service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.acc.dao.EmployeeDao;
import com.acc.dao.EmployeeMgmtException;
import com.acc.entity.Employee;

public class EmployeeService {
	public Employee searchEmployee(int empid) {
		EmployeeDao empdao = new EmployeeDao();
		return empdao.searchDao(empid);
	}
	
	public int insertupdateEmployee(Employee emp) throws EmployeeMgmtException {

		EmployeeDao empdao = new EmployeeDao();
		return empdao.insertAndUpdate(emp);
	}

	public int deleteEmployee(int empid) {
		EmployeeDao empdao = new EmployeeDao();
		return empdao.deleteDao(empid);
	}

	public List<Employee> showAllEmployee() {
		EmployeeDao empdao = new EmployeeDao();
		return empdao.showAlltDao();
	}
	
	public boolean validateArguments(Employee emp) {
		boolean status=false;
		  String pattern="[a-zA-Z]+";
		
		if ((Integer.toString(emp.getEmpid()).matches("[0-9]+") )&&(emp.getName().matches(pattern))&&(emp.getDesignation().matches(pattern))&&(emp.getTechnology().matches(pattern))&&(emp.getLocation().matches(pattern))&&(emp.getDeliveryGroup().matches(pattern)))
				status=true;
		
		return status;
	}
	
	
	private boolean bulkvalidation(String line,Employee emp) throws EmployeeMgmtException{
		String tmp[] = line.split(",");
		System.out.println(tmp+"fkgnkfl");
		int i = 0;
		boolean validatestatus=false;
		try {
		if((tmp.length>1)){
			if(tmp.length==6) {
				emp.setEmpid(Integer.parseInt(tmp[i++]));
			}
			else {
				emp.setEmpid(0);
			}
			emp.setName(tmp[i++]);
			emp.setDesignation(tmp[i++]);
			emp.setTechnology(tmp[i++]);
			emp.setDeliveryGroup(tmp[i++]);
			emp.setLocation(tmp[i]);
			validatestatus=validateArguments(emp);
		}else
			throw new EmployeeMgmtException("either File is empty or it contains inappropriate data");
			
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new EmployeeMgmtException("Array out of index");
			
		}catch(NullPointerException e) {
			e.printStackTrace();
			throw new EmployeeMgmtException("Some fields are NULL");
			
		}
		
		
		return validatestatus;
		
		
	}
	public Map<Integer, String> insertbulk(CommonsMultipartFile file) throws EmployeeMgmtException {
		System.out.println(file);
		EmployeeDao empdao = new EmployeeDao();
		Employee emp = new Employee();
		Map<Integer, String> map = new HashMap<>();
		int status = 0;
		InputStream inputStream=null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e1) {
           
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		try  {
			String line = null;

			while ((line = br.readLine()) != null) {
				
					if(bulkvalidation(line,emp)) {
					status = empdao.insertAndUpdate(emp);
					if (status == 1) {
						map.put(emp.getEmpid(), "success");
					} else
						map.put(emp.getEmpid(), "failure");
				}
					else {
						throw new EmployeeMgmtException("validation failed ");
					}
			}
			
		}catch(FileNotFoundException e) {
			
			throw new EmployeeMgmtException("File not found");
		}catch(IOException e) {
			throw new EmployeeMgmtException("Something went wrong while performing IO operation");
		
		}
		return map;
	}

}