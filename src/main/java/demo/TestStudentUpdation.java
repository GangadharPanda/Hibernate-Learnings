package demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Student;

public class TestStudentUpdation {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Transaction txn = session.beginTransaction();

		try {

			// fetch the data from table
			List<Student> students = session.createQuery("from Student ", Student.class).list();

			if (!students.isEmpty()) {
				Student student = students.get(0);

				System.out.println("Updating student " + student);

				// NOTE: Just update the object and commit the transaction

				// student.email = "mynewEmail@gmail.com";

				session.createQuery(
						"update Student s set s.email = 'NEWEM@gmail.com' where s.email = 'mynewEmail@gmail.com'")
						.executeUpdate();

				System.out.println("Updated Successfully ");

			} else {
				System.out.println("No data to update ");
			}
			// Fetch it again
			students = session.createQuery("from Student ", Student.class).list();

			for (Student student : students) {
				System.out.println(student);
			}

			// commit the transaction
			txn.commit();

		} catch (Exception e) {

			System.err.println("Error while fetching " + e.getMessage());
		} finally {
			session.close();
			factory.close();
		}

	}

}
