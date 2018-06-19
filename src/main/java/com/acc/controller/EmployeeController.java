package com.acc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.acc.dao.EmployeeMgmtException;
import com.acc.entity.Employee;
import com.acc.service.EmployeeService;

@Controller
public class EmployeeController {

	String allemp = "/allemployees";
	String allemployee = "allemployee";
	private static final String SUCCESSSTATUSMESSAGE="SUCCESSSTATUSMESSAGE"; 
	private static final String FAILURESTATUSMESSAGE="FAILURESTATUSMESSAGE"; 
	private static org.apache.log4j.Logger log = Logger.getLogger(EmployeeController.class);
	@RequestMapping("/insert.htm")
	public ModelAndView insertEmployee(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int status = 0;
		String registrationStatusMessage="";
		Employee emp = new Employee();
		emp.setDeliveryGroup(request.getParameter("deliveryGroup"));
		emp.setName(request.getParameter("name"));

		emp.setLocation(request.getParameter("location"));
		emp.setDesignation(request.getParameter("designation"));
		emp.setTechnology(request.getParameter("technology"));
		EmployeeService empService = new EmployeeService();
		try {
			status = empService.insertupdateEmployee(emp);
		} catch (EmployeeMgmtException e) {
			registrationStatusMessage=e.getMessage();
		}

		if (status >= 1) {
			mv.addObject(SUCCESSSTATUSMESSAGE, "Employee Registered Succesfully !!!");
			List<Employee> list = empService.showAllEmployee();
			mv.addObject(allemployee, list);
			log.info("Registration successful");
			mv.setViewName(allemp);
		
		} else {
			mv.addObject("REGISTRATIONSTATUSMESSAGE",registrationStatusMessage);
			
			log.info("Registration failed");
			mv.setViewName("empform");
		}
		
		return mv;

	}

	@RequestMapping("/getallemployees.htm")
	public ModelAndView getallEmployee(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		EmployeeService empservice = new EmployeeService();
		List<Employee> list = empservice.showAllEmployee();
		mv.addObject(allemployee, list);
		mv.setViewName(allemp);
		return mv;

	}

	@RequestMapping("/updatepage.htm")
	public ModelAndView getUpdate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		EmployeeService empSer = new EmployeeService();
		int empId = Integer.parseInt(request.getParameter("employeeId"));
		Employee emp = empSer.searchEmployee(empId);

		mv.addObject("emp", emp);
		mv.setViewName("/updateform");
		return mv;
	}

	@RequestMapping("/modify.htm")
	public ModelAndView getModify(HttpServletRequest request, HttpServletResponse response) {
		int status = 0;
		ModelAndView mv = new ModelAndView();
		Employee emp = new Employee();
		EmployeeService empSer = new EmployeeService();
		emp.setDeliveryGroup(request.getParameter("deliveryGroup"));
		emp.setName(request.getParameter("name"));
		emp.setEmpid(Integer.parseInt(request.getParameter("empid")));
		emp.setLocation(request.getParameter("location"));
		emp.setDesignation(request.getParameter("designation"));
		emp.setTechnology(request.getParameter("technology"));
		try {
			status = empSer.insertupdateEmployee(emp);
		} catch (EmployeeMgmtException e) {
			log.info(e.getMessage());
		}
		List<Employee> list = empSer.showAllEmployee();
		mv.addObject(allemployee, list);
		mv.setViewName(allemp);
		if (status >= 1) {
			mv.addObject("UPDATESTATUSMESSAGE", "Employee Updated Succesfully !!!");
			log.info("Updated successfully");
			
		} else {
			mv.addObject("UPDATESTATUSMESSAGE", "EMPLOYEE Updation failed");
			log.info("Updation unsuccessful");
		}
		return mv;
	}
	
	/*@RequestMapping("/bulkupload.htm")
	public ModelAndView bulkinsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String message="";
		EmployeeService empservice = new EmployeeService();
		String file=request.getParameter("bulk");
		System.out.println("filepath");
		try {
			empservice.insertbulk(file);
		} catch (EmployeeMgmtException e) {
			message=e.getMessage();
			
		}
		List<Employee> list =empservice.showAllEmployee();
		mv.addObject(STATUSMESSAGE,message);
		mv.addObject(allemployee, list);
		mv.setViewName(allemp);
		return mv;
	}*/

	@RequestMapping("/search.htm")
	public ModelAndView searchEmployee(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		EmployeeService empservice = new EmployeeService();
		List<Employee> list = new ArrayList<>();
		Employee emp = empservice.searchEmployee(Integer.parseInt(request.getParameter("search_empid")));
		list.add(emp);
		mv.addObject(allemployee, list);
		mv.setViewName(allemp);
		return mv;
	}

	@RequestMapping("/deletepage.htm")
	public ModelAndView getDelete(HttpServletRequest request, HttpServletResponse response) {
		int status = 0;

		ModelAndView mv = new ModelAndView();
		EmployeeService empSer = new EmployeeService();
		int empid = Integer.parseInt(request.getParameter("empid"));
		Employee emp = empSer.searchEmployee(empid);
		if (emp != null) {
			status = empSer.deleteEmployee(empid);
		}
		List<Employee> list = empSer.showAllEmployee();
		mv.addObject(allemployee, list);
		if (status >= 1) {
			mv.addObject(SUCCESSSTATUSMESSAGE, "Employee Deleted Succesfully !!!");
			log.info("Deleted successfully");
			mv.setViewName(allemp);
		} else {
			mv.addObject(FAILURESTATUSMESSAGE, "EMPLOYEE Deletion failed");
			log.info("Deletion unsuccessful");
			mv.setViewName(allemp);

		}

		return mv;

	}
	
	@RequestMapping(value = "/bulkupload.htm", method = RequestMethod.POST)

    public ModelAndView handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile[] bulk) throws IOException {

                    ModelAndView mv = new ModelAndView();
                    Map<Integer,String> map=new HashMap<Integer,String>();
                    String message="";

                    if (bulk != null && bulk.length > 0) {

                                    for (CommonsMultipartFile file : bulk) {

                                                    EmployeeService empservice = new EmployeeService();

                                                   try {
													empservice.insertbulk(file);
												} catch (EmployeeMgmtException e) {
												
													message=e.getMessage();
												}
                                                   List<Employee> list =empservice.showAllEmployee();
                                           		mv.addObject(FAILURESTATUSMESSAGE,message);
                                           		mv.addObject(allemployee, list);
                                           		mv.setViewName(allemp);
                                                  

                                                   
                                    }

                    }

                    return mv;

    }
	
	

}