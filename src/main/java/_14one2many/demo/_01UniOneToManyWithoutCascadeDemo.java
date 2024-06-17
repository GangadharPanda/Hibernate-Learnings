package _14one2many.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _14one2many.uni.entity.Car;
import _14one2many.uni.entity.Employee;

//formatter:off

// How to remember ?

// Car will have the foreign key , so make it the owner 

// Add @ManyToOne inside Car class

// Add @OneToMany inside Employee class and add mappedBy = name of the variable defined inside car class

// Cars Table will have a column for the foreign key referring to the employee

//formatter:on
public class _01UniOneToManyWithoutCascadeDemo {

	public static void main(String[] args) {

		// Create Session Factory

		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Car.class).buildSessionFactory();

		// Create Session

		Session session = sessionFactory.openSession();

		// Create Employee Object

		Employee employee = new Employee(null, "Gangadhar Panda");

		// Create Car object

		Car car1 = new Car(null, "Tata NEXON", new Date(), employee);
		Car car2 = new Car(null, "Tata Harrier", new Date(), employee);
		// Start transaction

		session.beginTransaction();

		// Persist the car and employees

		session.persist(employee);

		session.persist(car1);
		session.persist(car2);

		// Fetch those saved employyes & their cars
		Car car = session.get(Car.class, 1L);

		/**
		 * By default, @ManyToMany and @OneToMany associations use the FetchType. LAZY
		 * strategy, while the @ManyToOne and @OneToOne associations use the FetchType.
		 * EAGER strategy.
		 */

		System.out.println(car);

		// Commit the transaction

		session.getTransaction().commit();

		// Close the session

		session.close();

		// Close the session Factory

		sessionFactory.close();
	}

}
