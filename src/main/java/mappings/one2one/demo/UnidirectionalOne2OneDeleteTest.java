package mappings.one2one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import mappings.one2one.entity.unidirectional.Instructor;
import mappings.one2one.entity.unidirectional.InstructorDetails;

public class UnidirectionalOne2OneDeleteTest {
	public static void main(String[] args) {

		// Create Sessionfactory
		SessionFactory sfactory = new Configuration().configure("hibernate.cfg-one2one.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class).buildSessionFactory();

		// Get the session object
		Session session = sfactory.getCurrentSession();

		Transaction txn = null;
		try {
			txn = session.beginTransaction();

			// fetch one specific instructor where id = 3

			Instructor ins = session.get(Instructor.class, 4);

			session.createQuery("delete from Instructor where id = 4").executeUpdate();// This did not
			// remove the data from instructor_details table
			// explicit deletion will not delete
			// use session.delete

			session.delete(ins);// this did

			txn.commit();

			System.out.println("Deleted everything Success !!");

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
