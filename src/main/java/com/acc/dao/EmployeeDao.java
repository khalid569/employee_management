package com.acc.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.TransactionalException;
import org.apache.log4j.Logger;

import com.acc.entity.Employee;

/**
 * desc.......
 * 
 * @author khalid.a.khan
 *
 */
public class EmployeeDao {

	private static Logger logger = Logger.getLogger(EmployeeDao.class);
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeManagement");

	public Employee searchDao(int empid) {

		Employee emp = new Employee();

		EntityManager manager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();

			transaction.begin();

			emp = manager.find(Employee.class, empid);
		} catch (Exception e) {
			logger.info("Search failed....");
		} finally {
			manager.close();
		}
		return emp;
	}

	public int insertAndUpdate(Employee emp) throws EmployeeMgmtException {
		int status = 0;
		Integer empid;
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;
		
		try {
			transaction = manager.getTransaction();

			transaction.begin();
			if ((emp.getEmpid() == null)||(emp.getEmpid() == 0)) {
				empid = (Integer) manager.createQuery("select max(e.empid) from Employee e").getSingleResult();
				emp.setEmpid(empid + 1);
				System.out.println(emp);
				manager.persist(emp);
			} else
				manager.merge(emp);
			transaction.commit();
			status = 1;
		} catch (TransactionalException e) {
			status = 0;
			if (transaction != null)
				transaction.rollback();
			logger.info("Insert failed....");
		}catch(NullPointerException e) {
			throw new EmployeeMgmtException("some of the fields are Null");
		} 
		
		
		finally {
			manager.close();
		}

		return status;
	}


	public int deleteDao(int empid) {
		int status = 0;
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;
		Employee emp = new Employee();
		try {
			status = 1;
			transaction = manager.getTransaction();
			transaction.begin();
			emp = manager.find(Employee.class, empid);
			manager.remove(emp);
			transaction.commit();
		} catch (TransactionalException e) {
			status = 0;
			logger.info("Delete failed....");

		} finally {
			manager.close();
		}

		return status;
	}

	public List<Employee> showAlltDao() {
		List<Employee> list = null;
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			list = manager.createQuery("select e from Employee e", Employee.class).getResultList();
		} catch (TransactionalException e) {
			logger.info("showAll failed....");

		} finally {
			manager.close();
		}
		return list;
	}
}