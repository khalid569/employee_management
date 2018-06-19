package com.acc.emptest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acc.dao.EmployeeDao;
import com.acc.dao.EmployeeMgmtException;
import com.acc.entity.Employee;

class DaoTest {
	private Employee emp;
	private EmployeeDao empdao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
		emp = new Employee();
		empdao = new EmployeeDao();
	}

	@AfterEach
	void tearDown() throws Exception {
		emp = null;
		empdao = null;
	}



	@Test
	void insertemployee() {
		int status = 0;
		emp.setEmpid(1001);
		emp.setName("seconduser");
		emp.setLocation("bangalore");
		emp.setTechnology("oracle");
		emp.setDeliveryGroup("idc");
		emp.setDesignation("ASE");
		try {
			status = empdao.insertAndUpdate(emp);
		} catch (EmployeeMgmtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, status);
	}

	@Test
	void deleteemployee() {
		int status = 0;
		emp = empdao.searchDao(1001);
		status = empdao.deleteDao(emp.getEmpid());
		assertEquals(1, status);
	}

	@Test
	void updateemployee() {
		emp = empdao.searchDao(1000);
		emp.setName("diffuser");
		emp.setDesignation("SSE");
		emp.setTechnology("oracle");
		int status=0;
		try {
			status = empdao.insertAndUpdate(emp);
		} catch (EmployeeMgmtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, status);

	}

	@Test
	void show() {
		List list = empdao.showAlltDao();
		assertEquals(1, list.size());
	}
	
	
}
