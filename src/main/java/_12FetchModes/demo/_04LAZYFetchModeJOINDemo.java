package _12FetchModes.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _12FetchModes.entity._04LAZYEmployeeFetchModeJOIN;

public class _04LAZYFetchModeJOINDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(_04LAZYEmployeeFetchModeJOIN.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Begin transaction

		session.beginTransaction();

		// Get the Employee object
		//@formatter:off
		/***
		 * Earlier one select query now one Join Query
		 *  For JOIN, Fetchtype is always EAGER , irrespective of what you give
		 * 
		 * Hibernate: 
			    select
			        _1_0.id,
			        l1_0.emp_id,
			        l1_0.city,
			        l1_0.pin,
			        l1_0.state,
			        l1_0.street,
			        _1_0.name 
			    from
			        lazy_employee_with_office_address _1_0 
			    left join
			        emp_address_lazy l1_0 
			            on _1_0.id=l1_0.emp_id 
			    where
			        _1_0.id=?
		 * 
		 */
		//@formatter:on
		_04LAZYEmployeeFetchModeJOIN lazyOne = session.get(_04LAZYEmployeeFetchModeJOIN.class, 1);

		session.getTransaction().commit();

		session.close();

		// Close sessionFactory

		factory.close();

	}
}
