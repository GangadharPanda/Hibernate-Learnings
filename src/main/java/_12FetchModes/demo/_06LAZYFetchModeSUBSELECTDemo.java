package _12FetchModes.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _12FetchModes.entity._06LAZYEmployeeFetchModeSUBSELECT;

public class _06LAZYFetchModeSUBSELECTDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(_06LAZYEmployeeFetchModeSUBSELECT.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Begin transaction

		session.beginTransaction();

		// Get the Employee object
		//@formatter:off
		/***
		 * Hibernate: 
		 * 
		 * 
			select
		        _1_0.id,
		        _1_0.name 
		    from
		        lazy_employee_with_office_address _1_0 
		    where
		        _1_0.id=?
		 * 
		 */
		//@formatter:on
		_06LAZYEmployeeFetchModeSUBSELECT lazyOne = session.get(_06LAZYEmployeeFetchModeSUBSELECT.class, 1L);

		session.getTransaction().commit();

		session.close();

		// Close sessionFactory

		factory.close();

	}
}
