package MappingDateAndTime.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import MappingDateAndTime.entity.Employee;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class DateAndTimeDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg-date-time.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.openSession();

		try {
			Employee employee = new Employee();
			employee.setName("Gangadhar Panda");
			employee.setLastLoggedIn(new Date());
			employee.setDateOfJoining(new Date(2018, 1, 1));
			session.beginTransaction();
			
			session.persist(employee);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Unable to write employee " + e.getMessage());
		}
		
		/*
		 * emp_id,  date_of_joining,            last_logged_in,             emp_name
		 * 1	    3918-02-01 00:00:00.000000	2024-06-10 23:08:54.326000	Gangadhar Panda
		 * 
		 * 
		 * The Date is saved in form of timestamp
		 * 
		 * 
		 * 
		 * create table employee (
		       emp_id integer not null,
		        date_of_joining datetime(6),
		        last_logged_in datetime(6),
		        emp_name varchar(255),
		        primary key (emp_id)
		  ) 
		 * 
		 * 
		 * 
		 * 
		 * AFTER ADDING 
		 * 
		 * @Temporal(TemporalType.DATE) for dateOfJoining
		 * 
		 * create table employee (
		       emp_id integer not null,
		        date_of_joining date,
		        last_logged_in datetime(6),
		        emp_name varchar(255),
		        primary key (emp_id)
		  ) 
		 */

	}

}
