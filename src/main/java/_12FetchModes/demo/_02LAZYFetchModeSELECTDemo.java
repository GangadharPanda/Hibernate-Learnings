package _12FetchModes.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _12FetchModes.entity._02LAZYEmployeeFetchModeSELECT;

public class _02LAZYFetchModeSELECTDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(_02LAZYEmployeeFetchModeSELECT.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Begin transaction

		session.beginTransaction();

		// Get the Employee object

		/***
		 * Without FetchMode = SELECT, this code was returning only one SELECT query now
		 * also it will remain same
		 * 
		 * Hibernate: select l1_0.id, l1_0.name from lazy_employee_with_office_address
		 * l1_0 where l1_0.id=?
		 * 
		 * NOTE : If I uncomment the below code , this will create 2 queries due to
		 * obvious reasons
		 */

		_02LAZYEmployeeFetchModeSELECT lazyOne = session.get(_02LAZYEmployeeFetchModeSELECT.class, 1);

//		for (Address address : lazyOne.getListOfOffices()) {
//			System.out.println(address);
//		}
//		
		session.getTransaction().commit();

		session.close();

		// Close sessionFactory

		factory.close();

	}
}
