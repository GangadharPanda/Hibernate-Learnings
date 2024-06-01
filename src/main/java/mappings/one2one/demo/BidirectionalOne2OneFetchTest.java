package mappings.one2one.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import mappings.one2one.entity.bidirectional.Instructor;
import mappings.one2one.entity.bidirectional.InstructorDetails;

public class BidirectionalOne2OneFetchTest {
	public static void main(String[] args) {

		// Create Sessionfactory
		SessionFactory sfactory = new Configuration().configure("hibernate.cfg-one2one-bidirectional.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class).buildSessionFactory();

		// Get the session object
		Session session = sfactory.getCurrentSession();

		Transaction txn = null;
		try {
			txn = session.beginTransaction();

			List<InstructorDetails> instructorDetails = session
					.createQuery("from InstructorDetails", InstructorDetails.class).list();

			txn.commit();

			for (InstructorDetails instructor : instructorDetails) {
				System.out.println(instructor.getInstructor());
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
