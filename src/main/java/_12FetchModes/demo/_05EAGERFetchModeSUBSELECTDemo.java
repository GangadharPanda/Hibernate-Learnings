package _12FetchModes.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _12FetchModes.entity._05EAGEREmployeeFetchModeSUBSELECT;

public class _05EAGERFetchModeSUBSELECTDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(_05EAGEREmployeeFetchModeSUBSELECT.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		session.beginTransaction();

		// Get the Employee object
		// @formatter:off
		/***
		 * When FetchMode is not specified, It used to give a single join query
		 * Below code will also give 1 JOIN Query , so no change
		 * 
			Hibernate: 
			    select
			        _1_0.id,
			        _1_0.name 
			    from
			        eager_employee_with_office_address _1_0 
			    where
			        _1_0.id=?
			Hibernate: 
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
		 */
		// @formatter:on

		_05EAGEREmployeeFetchModeSUBSELECT employeeHaveCollectionOfAddress = session.get(_05EAGEREmployeeFetchModeSUBSELECT.class,
				1);

		session.getTransaction().commit();

		session.close();

		// Close sessionFactory

		factory.close();

	}
}
