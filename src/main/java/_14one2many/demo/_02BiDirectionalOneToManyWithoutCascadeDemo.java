package _14one2many.demo;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _14one2many.bi.entity.Car;
import _14one2many.bi.entity.Employee;


//formatter:off

// How to remember ?

// Car will have the foreign key , so make it the owner 

// Add @ManyToOne inside Car class

// Add @OneToMany inside Employee class and add mappedBy = name of the variable defined inside car class

// Cars Table will have a column for the foreign key referring to the employee

//formatter:on
public class _02BiDirectionalOneToManyWithoutCascadeDemo {

	public static void main(String[] args) {

		// Create Session Factory

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg-create.xml")
				.addAnnotatedClass(Employee.class).addAnnotatedClass(Car.class).buildSessionFactory();

		// Create Session

		Session session = sessionFactory.openSession();
		// Create Car object

		Car car1 = new Car(null, "Tata NEXON", new Date(), null);
		Car car2 = new Car(null, "Tata Harrier", new Date(), null);

		// Create Employee Object

		Employee employee = new Employee(null, "Gangadhar Panda", List.of(car1, car2));
		
		car1.setEmployee(employee);
		
		car2.setEmployee(employee);
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

		// Car is @ManyToOne annotation , so it will fetch eagerly
		System.out.println(car);

		// We get respective Cars as well
		Employee employee2 = session.get(Employee.class, 1L);

		System.out.println(employee2);
		// Commit the transaction

		session.getTransaction().commit();

		// Close the session

		session.close();

		// Close the session Factory

		sessionFactory.close();
	}

}
