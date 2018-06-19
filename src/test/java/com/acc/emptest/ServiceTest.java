package com.acc.emptest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.acc.dao.EmployeeDao;
import com.acc.dao.EmployeeMgmtException;
import com.acc.entity.Employee;
import com.acc.service.EmployeeService;

class ServiceTest {
	private Employee emp;
	private EmployeeService empservice;

	@BeforeEach
	void setUp() throws Exception {
		emp = new Employee();
		empservice = new EmployeeService();
	}

	@AfterEach
	void tearDown() throws Exception {
		emp = null;
		empservice = null;
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void testsearch() {

		emp = empservice.searchEmployee(1000);
		

	}

	@Test
	void testdeleteemployee() {
		int status = 0;
		emp = empservice.searchEmployee(1002);
		status = empservice.deleteEmployee(emp.getEmpid());
		assertEquals(1, status);
	}

	@Test
	void testupdateemployee() {
		emp = empservice.searchEmployee(1000);
		emp.setName("diffuser");
		emp.setDesignation("ASE");
		emp.setTechnology("oracle");
		int status=0;
		try {
			status = empservice.insertupdateEmployee(emp);
		} catch (EmployeeMgmtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, status);

	}

	@Test
	void testinsertemployee() {
		int status = 0;
		emp.setEmpid(1002);
		emp.setName("seconduser");
		emp.setLocation("bangalore");
		emp.setTechnology("oracle");
		emp.setDeliveryGroup("idc");
		emp.setDesignation("ASE");
		try {
			status = empservice.insertupdateEmployee(emp);
		} catch (EmployeeMgmtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, status);
	}

	@Test
	void testshowemployee() {
		List list = empservice.showAllEmployee();
		assertEquals(2, list.size());
	}

	@Test
	void insertBulk()  {		
		Map<Integer, String> map = new HashMap<Integer, String>();
		File file = new File("C:\\Users\\khalid.a.khan\\Desktop\\ff\\abc.txt");
	    FileInputStream input;
		try {                                                           			  //CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);
			input = new FileInputStream(file);
		    MultipartFile multipartFile = new MockMultipartFile("file",file.getName(), "text/plain", IOUtils.toByteArray(input));
			empservice.insertbulk((CommonsMultipartFile) multipartFile);
			System.out.println("after call");
		}catch (Exception e) {
			System.out.println("inside catch");
			e.printStackTrace();
			assertEquals("File not found", e.getMessage());
		}
		
		

		// File is empty or incorrect data.
		/*File f = new File("C:\\Users\\khalid.a.khan\\Desktop\\test\\empty.txt");
		try {
			empservice.insertbulk(f.getAbsolutePath());
		} catch ( Exception e) {
			assertEquals("either File is empty or it contains inappropriate data", e.getMessage());
		}
		
		
		
		
		
		// File has only data for insertions.
		 f = new File("C:\\Users\\khalid.a.khan\\Desktop\\test\\onlyinsert.txt");
			int actualcount=0;
			map=empservice.insertbulk(f.getAbsolutePath());
			int expectedcount=map.size();
			for(String value: map.values()) {
				  if (value.equals("success")) {
				    actualcount++;
				  }
				}
			assertEquals(expectedcount, actualcount);
			
			
			
			
			
		
		// File has only data for updations.
		file = "C:\\Users\\khalid.a.khan\\Desktop\\test\\onlyupdate.txt";
		actualcount=0;
		map=empservice.insertbulk(file);
			expectedcount=map.size();
		for(String value: map.values()) {
			  if (value.equals("success")) {
			    actualcount++;
			  }
			}
		assertEquals(expectedcount, actualcount);
		
		
		
	
		// File has mixed data.
		file = "C:\\Users\\khalid.a.khan\\Desktop\\test\\mixeddata.txt";
		actualcount=0;
		map=empservice.insertbulk(file);
			expectedcount=map.size();
		for(String value: map.values()) {
			  if (value.equals("success")) {
			    actualcount++;
			  }
			}
		assertEquals(expectedcount, actualcount);
*/
}
}
