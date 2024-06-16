package _12FetchModes.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _12FetchModes.entity._07NPlusOneSolutionUsingFetchModeSELECT;

public class _08NPlusOneSolutionUsingFetchModeSELECTDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(_07NPlusOneSolutionUsingFetchModeSELECT.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Begin transaction

		session.beginTransaction();

		// Get the Employee object
		//@formatter:off
		/***
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
			        l1_0.emp_id in(?,?)
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
			        l1_0.emp_id in(?,?)
			        
		 *
		 *  if the table 'Employee'  has N rows , 
		 *  
		 *  total number of queries = Ceil(N / Batch Size)  + 1
		 *  
		 *  1 is to fetch all these N records
		 *  
		 *  if N = 4 , batch size = 2 
		 *  
		 *  total number of queries = 4/2 + 1 = 3 
		 *
		 */
		//@formatter:on
		List<_07NPlusOneSolutionUsingFetchModeSELECT> lazyOne = session
				.createQuery("from _07NPlusOneSolutionUsingFetchModeSELECT").list();

		lazyOne.stream().forEach(emp -> System.out.println(emp.getListOfOffices()));

		session.getTransaction().commit();

		session.close();

		// Close sessionFactory

		factory.close();

	}
}
