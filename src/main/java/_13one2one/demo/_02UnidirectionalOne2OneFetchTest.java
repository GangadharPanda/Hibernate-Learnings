package _13one2one.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import _13one2one.entity.unidirectional.Instructor;
import _13one2one.entity.unidirectional.InstructorDetails;

public class _02UnidirectionalOne2OneFetchTest {
	public static void main(String[] args) {

		// Create Sessionfactory
		SessionFactory sfactory = new Configuration().configure("hibernate.cfg-one2one-unidirectional.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class).buildSessionFactory();
		
		// Get the session object
		Session session = sfactory.getCurrentSession();

		Transaction txn = null;
		try {
			txn = session.beginTransaction();

			List<Instructor> instructors = session.createQuery("from Instructor", Instructor.class).list();

			txn.commit();

			for (Instructor instructor : instructors) {
				System.out.println(instructor);
			}

			System.out.println("Fetch Success !!");

		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			System.out.println("Error " + e.getMessage());
		} finally {
			session.close();
			sfactory.close();
		}

	}

}
