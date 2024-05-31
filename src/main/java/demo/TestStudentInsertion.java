package demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Student;

public class TestStudentInsertion {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		Student theStudent1 = new Student("Gangadhar-1", "Panda", "gangadharpanda@abc.com");
		Student theStudent2 = new Student("Gangadhar-2", "Panda", "gangadharpanda@xyz.com");

		Transaction txn = null;

		try {
			// Start the Transaction
			txn = session.beginTransaction();

			// Persist the data into table
			session.persist(theStudent1);
			session.persist(theStudent2);

			// commit the transaction
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
		}

		try {

			// Fetch data from the table
			session = factory.getCurrentSession();

			// Start the Transaction
			txn = session.beginTransaction();

			// fetch the data from table
			List<Student> students = session.createQuery("from Student where firstName = 'Gangadhar-1'", Student.class)
					.list();

			for (Student student : students) {
				System.out.println(student.toString());
			}

		} catch (Exception e) {

			System.err.println("Error while fetching " + e.getMessage());
		} finally {
			session.close();
			factory.close();
		}

	}

}
