package _12FetchModes.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _12FetchModes.entity._01EAGEREmployeeFetchModeSELECT;

public class _07NPlusOneProblemDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(_01EAGEREmployeeFetchModeSELECT.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Begin transaction

		session.beginTransaction();

		// Get the Employee object
		//@formatter:off
		/***
		 * 
		 * 
		 * (1): 
			    select
			        _1_0.id,
			        _1_0.name 
			    from
			        eager_employee_with_office_address _1_0
			(2): 
			    select
			        l1_0.emp_id,
			        l1_0.city,
			        l1_0.pin,
			        l1_0.state,
			        l1_0.street 
			    from
			        emp_address_eager l1_0 
			    where
			        l1_0.emp_id=?
			(3): 
			    select
			        l1_0.emp_id,
			        l1_0.city,
			        l1_0.pin,
			        l1_0.state,
			        l1_0.street 
			    from
			        emp_address_eager l1_0 
			    where
			        l1_0.emp_id=?
			(4): 
			    select
			        l1_0.emp_id,
			        l1_0.city,
			        l1_0.pin,
			        l1_0.state,
			        l1_0.street 
			    from
			        emp_address_eager l1_0 
			    where
			        l1_0.emp_id=?
			(5): 
			    select
			        l1_0.emp_id,
			        l1_0.city,
			        l1_0.pin,
			        l1_0.state,
			        l1_0.street 
			    from
			        emp_address_eager l1_0 
			    where
			        l1_0.emp_id=?
			        
			        
			        
		 *
		 *  if the table 'Employee'  has N rows , then there will be N + 1 
		 *  Query to the database.
		 *  
		 *  WHY ??
		 *  
		 *  
		 *  First Query will fetch all the rows from Employee Table
		 *  
		 *  now , one query for each employee to get his address 
		 *  
		 *  so total number of queries is N+1
		 *
		 *
		 *
		 */
		//@formatter:on
		List<_01EAGEREmployeeFetchModeSELECT> lazyOne = session.createQuery("from _01EAGEREmployeeFetchModeSELECT")
				.list();

		lazyOne.stream().forEach(emp -> System.out.println(emp.getListOfOffices()));

		session.getTransaction().commit();

		session.close();

		// Close sessionFactory

		factory.close();

	}
}
