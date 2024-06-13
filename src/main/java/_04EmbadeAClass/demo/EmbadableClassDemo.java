package _04EmbadeAClass.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _04EmbadeAClass.entity.Address;
import _04EmbadeAClass.entity.Employee;

public class EmbadableClassDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg-date-time.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Create Address Object

		Address homeAddress = new Address("Sriram path", "Jharkahand", "Daltonganj", "822102");

		Address officeAddress = new Address("Giga Space IT park", "Maharashtra", "Pune", "411014");

		// Create Employee Object

		Employee employee = new Employee(null, "Gangadhar Panda", officeAddress, homeAddress);

		// begin transaction

		session.beginTransaction();

		// persist the data into the table

		session.persist(employee);

		// commit transaction

		session.getTransaction().commit();

		// close session

		session.close();

		// close sessionFactory

		factory.close();

	}

}
