package _14one2many.demo;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _14one2many.uni.entity.Car;
import _14one2many.uni.entity.Employee;

public class _01UniOneToManyWithoutCascadeDemo {

	public static void main(String[] args) {

		// Create Session Factory

		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Car.class).buildSessionFactory();

		// Create Session

		Session session = sessionFactory.openSession();

		// Create Car object

		Car car1 = new Car(null, "Tata NEXON", new Date());
		Car car2 = new Car(null, "Tata Harrier", new Date());

		// Create Employee Object

		Employee employee = new Employee(null, "Gangadhar Panda", List.of(car1, car2));

		// Start transaction

		session.beginTransaction();

		// Persist the car and employees

		session.persist(employee);

		// As we did not specify any CascadeType for Car objects
		// So it's our responsibility to save them explicitly
		session.persist(car1);
		session.persist(car2);

		// This change will trigger another update into the employee table
		employee.setName("Gangadhar");

		// Commit the transaction

		session.getTransaction().commit();

		// Close the session

		session.close();

		// Close the session Factory

		sessionFactory.close();
	}

}
