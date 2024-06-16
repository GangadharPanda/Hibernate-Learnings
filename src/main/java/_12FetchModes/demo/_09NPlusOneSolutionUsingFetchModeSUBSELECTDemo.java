package _12FetchModes.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _12FetchModes.entity._08NPlusOneSolutionUsingFetchModeSUBSELECT;

public class _09NPlusOneSolutionUsingFetchModeSUBSELECTDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(_08NPlusOneSolutionUsingFetchModeSUBSELECT.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Begin transaction

		session.beginTransaction();

		// Get the Employee object
		//@formatter:off
		/***
		 * 
		 * 
		 * 
		 * 
		    (1): 
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
			        l1_0.emp_id in(select
			            _1_0.id 
			        from
			            eager_employee_with_office_address _1_0)
			        
			        
		 *
		 * Only 2 queries
		 *
		 */
		//@formatter:on
		List<_08NPlusOneSolutionUsingFetchModeSUBSELECT> lazyOne = session.createQuery("from _08NPlusOneSolutionUsingFetchModeSUBSELECT")
				.list();

		lazyOne.stream().forEach(emp -> System.out.println(emp.getListOfOffices()));

		session.getTransaction().commit();

		session.close();

		// Close sessionFactory

		factory.close();

	}
}
