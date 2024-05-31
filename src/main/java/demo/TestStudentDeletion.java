package demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Student;

public class TestStudentDeletion {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Transaction txn = session.beginTransaction();

		try {

			// fetch the data from table
			List<Student> students = session.createQuery("from Student where firstName = 'Gangadhar-1'", Student.class)
					.list();

			if (!students.isEmpty()) {
				Student student = students.get(0);

				System.out.println("Deleting student " + student);

				session.delete(student);

				System.out.println("Deleted Successfully ");

			} else {
				System.out.println("No data to delete ");
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
