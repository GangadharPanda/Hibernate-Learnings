package mappings.one2one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import mappings.one2one.entity.bidirectional.Instructor;
import mappings.one2one.entity.bidirectional.InstructorDetails;

public class BidirectionalOne2OneInsertionTest {
	public static void main(String[] args) {

		// Create Sessionfactory

		SessionFactory sfactory = new Configuration().configure("hibernate.cfg-one2one-bidirectional.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class).buildSessionFactory();

		// Get the session object

		Session session = sfactory.getCurrentSession();

		/**
		 * 
		 * Always pass primary key of the table = null,
		 * 
		 * I was giving it 0 , so hibernate was treating it as it's already inserted
		 * into the table and giving the error
		 * 
		 * detached entity passed to persist : instructor
		 * 
		 * because I was using Instructor instructor = new Instructor(0, ...);//0 in
		 * primary key
		 * 
		 */

		// Create InstructorDetails Object
		InstructorDetails details = new InstructorDetails(null, "https://www.youtube.com/yamuna",
				"https://www.linkedIn.com/yamuna", null);

		// Create Instructor Object
		Instructor instructor = new Instructor(null, "Yamuna", "Panda", "yamuna.panda@luv2code.com", details);

		details.setInstructor(instructor);

		Transaction txn = null;
		try {
			txn = session.beginTransaction();

			session.persist(details);

			txn.commit();

			System.out.println("Success!!");

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
