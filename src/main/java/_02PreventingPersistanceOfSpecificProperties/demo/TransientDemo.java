package _02PreventingPersistanceOfSpecificProperties.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _02PreventingPersistanceOfSpecificProperties.entity.Teacher;

public class TransientDemo {

	public static void main(String[] args) {

		// Create SessionFactory Object

		SessionFactory factory = new Configuration().configure("hibernate.cfg-date-time.xml")
				.addAnnotatedClass(Teacher.class).buildSessionFactory();

		// Create session Object

		Session session = factory.openSession();

		// Create teacher object

		Teacher teacher = new Teacher();
		teacher.setAge(35);
		teacher.setName("Gangadhar");

		/*
		 * Not setting the martialStatus
		 */

		// begin transaction

		session.beginTransaction();

		// persist the object

		session.persist(teacher);

		// close the txn
		session.getTransaction().commit();

		// close the session
		session.close();
	}

}
