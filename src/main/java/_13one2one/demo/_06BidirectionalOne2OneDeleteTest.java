package _13one2one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import _13one2one.entity.bidirectional.Instructor;
import _13one2one.entity.bidirectional.InstructorDetails;

public class _06BidirectionalOne2OneDeleteTest {
	public static void main(String[] args) {

		// Create Sessionfactory
		SessionFactory sfactory = new Configuration().configure("hibernate.cfg-one2one-bidirectional.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class).buildSessionFactory();

		// Get the session object
		Session session = sfactory.getCurrentSession();

		Transaction txn = null;
		try {
			txn = session.beginTransaction();

			InstructorDetails ins = session.get(InstructorDetails.class, 8);

			session.delete(ins);// this did

			txn.commit();

			System.out.println("Deleted  Success !!");

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
