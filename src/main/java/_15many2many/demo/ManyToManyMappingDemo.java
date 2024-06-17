package _15many2many.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _15many2many.entity.Employee;
import _15many2many.entity.Project;

public class ManyToManyMappingDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Project.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		session.beginTransaction();

//		Employee xE = session.get(Employee.class, 1L);
//
//		for (Project project : xE.getProjects()) {
//
//			System.out.println(project);
//		}

		Project proj = session.get(Project.class, 7L);

		for (Employee employee : proj.getEmployee()) {

			System.out.println(employee);
		}

		session.close();

		sessionFactory.close();

	}
}
