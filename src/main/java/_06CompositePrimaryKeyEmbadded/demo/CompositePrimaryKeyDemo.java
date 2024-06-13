package _06CompositePrimaryKeyEmbadded.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _06CompositePrimaryKeyEmbadded.entity.CompositePrimaryKeyId;
import _06CompositePrimaryKeyEmbadded.entity.EmployeeWithDepartment;

public class CompositePrimaryKeyDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg-create.xml")
				.addAnnotatedClass(EmployeeWithDepartment.class).buildSessionFactory();

		// Create Session
		Session session = factory.openSession();

		// Create Employee

		CompositePrimaryKeyId compositePrimaryKeyId1 = new CompositePrimaryKeyId(1, "Finanace");
		CompositePrimaryKeyId compositePrimaryKeyId2 = new CompositePrimaryKeyId(1, "Sales");
		CompositePrimaryKeyId compositePrimaryKeyId3 = new CompositePrimaryKeyId(2, "Finanace");
		EmployeeWithDepartment department1 = new EmployeeWithDepartment(compositePrimaryKeyId1, "Gangadhar");

		EmployeeWithDepartment department2 = new EmployeeWithDepartment(compositePrimaryKeyId2, "Udbhav");

		EmployeeWithDepartment department3 = new EmployeeWithDepartment(compositePrimaryKeyId3, "Vishesh");

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
		       department varchar(255) not null,
		        id integer not null,
		        name varchar(255),
		        primary key (department, id)
		    ) engine=InnoDB
		    
		 */

	}

}
