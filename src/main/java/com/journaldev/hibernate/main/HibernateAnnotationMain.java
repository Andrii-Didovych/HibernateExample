package com.journaldev.hibernate.main;

import java.util.Date;
import java.util.List;

import com.journaldev.hibernate.model.Employee;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.journaldev.hibernate.model.Employee1;
import com.journaldev.hibernate.util.HibernateUtil;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;

public class HibernateAnnotationMain {

	public static void main(String[] args) {
		Employee1 emp = new Employee1();
		emp.setName("David");
		emp.setRole("Developer");
		emp.setInsertTime(new Date());
		
		//Get Session
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();

		//start transaction
		session.beginTransaction();

		//Save the Model object
		session.save(emp);

		//select all, hql query
		Query query = session.createQuery("From Employee1");
		List<Employee1> employee = query.list();
		System.out.println(employee);


//		select all, sql query
		SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM employee");
		sqlQuery.addEntity(Employee1.class);
		List<Employee1> employees = sqlQuery.list();
		System.out.println(employees);

//		update
		emp.setName("Bill");
		session.update(emp);

//		delete
		session.delete(emp);

//		other operations
		System.out.println(session.isConnected());
		System.out.println(session.isOpen());


		//Commit transaction
		session.getTransaction().commit();
		System.out.println("Employee ID="+emp.getId());
		
		//terminate session factory, otherwise program won't end
		sessionFactory.close();
	}

}
