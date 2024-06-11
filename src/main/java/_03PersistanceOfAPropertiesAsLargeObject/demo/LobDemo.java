package _03PersistanceOfAPropertiesAsLargeObject.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _03PersistanceOfAPropertiesAsLargeObject.entity.OldEmployee;

public class LobDemo {

	public static void main(String[] args) {

		// Create SessionFactory Object

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg-date-time.xml")
				.addAnnotatedClass(OldEmployee.class).buildSessionFactory();

		// Create session object from sessionfactory Object

		Session session = sessionFactory.openSession();

		// Create OldEmployee class Object

		OldEmployee oldEmployee = new OldEmployee(null, "Ramesh ",
				"Lorem ipsum dolor sit amet, magna aliqua. VId velit ut tortor pretium. Consequat semper viverra nam libero justo. Purus ut faucibus pulvinar elementum integer enim neque volutpat ac. Orci ac auctor augue mauris augue neque gravida in fermentum.\r\n");

		// begin Transaction

		session.beginTransaction();

		// Persist the object
		session.persist(oldEmployee);

		// Commit the transaction
		session.getTransaction().commit();

		// Close the session
		session.close();
		// Close the SessionFactory
		sessionFactory.close();
	}

}
