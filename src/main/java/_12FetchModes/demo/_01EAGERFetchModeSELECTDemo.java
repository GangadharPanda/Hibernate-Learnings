package _12FetchModes.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _04EmbadeAClass.entity.Address;
import _12FetchModes.entity._01EAGEREmployeeFetchModeSELECT;

public class _01EAGERFetchModeSELECTDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(_01EAGEREmployeeFetchModeSELECT.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		session.beginTransaction();

		// Get the Employee object

		/***
		 * Without FetchMode = SELECT, this code was returning only one Join , but now
		 * Below code will run 2 SELECT Query , 1 for Employee and another one for
		 * Address
		 * 
		 * 
		 * Hibernate: select e1_0.id, e1_0.name from eager_employee_with_office_address
		 * e1_0 where e1_0.id=?
		 * 
		 * 
		 * Hibernate: select l1_0.emp_id, l1_0.city, l1_0.pin, l1_0.state, l1_0.street
		 * from emp_address_eager l1_0 where l1_0.emp_id=?
		 * 
		 * 
		 * 
		 */

		_01EAGEREmployeeFetchModeSELECT employeeHaveCollectionOfAddress = session.get(_01EAGEREmployeeFetchModeSELECT.class,
				1);

		session.getTransaction().commit();

		session.close();

		// Close sessionFactory

		factory.close();

	}
}
