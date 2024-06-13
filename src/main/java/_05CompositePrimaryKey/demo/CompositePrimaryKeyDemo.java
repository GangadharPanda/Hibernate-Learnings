package _05CompositePrimaryKey.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _05CompositePrimaryKey.entity.EmployeeWithDepartment;

public class CompositePrimaryKeyDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg-create.xml")
				.addAnnotatedClass(EmployeeWithDepartment.class).buildSessionFactory();

		// Create Session
		Session session = factory.openSession();

		// Create Employee
		EmployeeWithDepartment department1 = new EmployeeWithDepartment(1, 1, "Gangadhar");

		EmployeeWithDepartment department2 = new EmployeeWithDepartment(1, 2, "Udbhav");

		EmployeeWithDepartment department3 = new EmployeeWithDepartment(2, 1, "Vishesh");

		// Begin Transaction
		session.beginTransaction();

		// Persist employee object
		session.persist(department1);
		session.persist(department2);
		session.persist(department3);

		// commit transaction
		session.getTransaction().commit();

		// close session
		session.close();

		// close sessionFactory
		factory.close();
		
		/*
		 * create table employee_with_department (
			       id integer not null,
			        dept_id integer not null,
			        name varchar(255),
			        primary key (dept_id, id)
			    ) engine=InnoDB
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

	}

}
