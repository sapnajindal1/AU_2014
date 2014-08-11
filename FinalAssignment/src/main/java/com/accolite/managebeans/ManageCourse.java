package com.accolite.managebeans;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.accolite.beans.Course;
import com.accolite.beans.Institute;
import com.accolite.helpers.Constants;

public class ManageCourse {
	private static SessionFactory factory;
	private static Logger logger = Logger.getLogger(ManageCourse.class.getName());
	public ManageCourse()
	{
		try {
			Configuration configuration = new Configuration().configure();
			ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
			registry.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
			factory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			logger.error(Constants.SESSION_FACTORY_EXCEPTION+ex);
			throw new ExceptionInInitializerError(ex);
		}
	
	}
	

	/* Method to CREATE a course in the database */
	public Integer addCourse(String coursename, String coursedesc,
			int duration, String eligibilitycriteria, String admissionproc,String instName) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer courseid = null;
		try {
			tx = session.beginTransaction();
			Institute inst = new Institute();
			inst.setTitle(instName);
	        session.save(inst);
			Course course1 = new Course();
			course1.setCoursename(coursename);
			course1.setCoursedesc(coursedesc);
			course1.setDuration(duration);
			course1.setEligibilitycriteria(eligibilitycriteria);
			course1.setAdmissionproc(admissionproc);
			course1.setInst(inst);
			courseid = (Integer) session.save(course1);
			logger.info(Constants.SUCCESS);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(Constants.TRANSACTION_ROLLBACK);
			logger.error(Constants.HIBERNATE_EXCEPTION+e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return courseid;
	}
	public List getCourses() {
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List courses = session.createQuery("FROM Course").list();
			
			tx.commit();
			return courses;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(Constants.TRANSACTION_ROLLBACK);
			logger.error(Constants.HIBERNATE_EXCEPTION+e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	/* Method to READ all the courses */
	public void listCourses() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List courses = session.createQuery("FROM Course").list();
			for (Iterator iterator = courses.iterator(); iterator.hasNext();) {
				Course course = (Course) iterator.next();
				System.out.print("Course Name: " + course.getCoursename());
				System.out.print("Course Description: "
						+ course.getCoursedesc());
				System.out.println("Duration: " + course.getDuration());
				System.out.println("Eligibility Criteria:"
						+ course.getEligibilitycriteria());
				System.out.println("Admission procedure"
						+ course.getAdmissionproc());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(Constants.TRANSACTION_ROLLBACK);
			logger.error(Constants.HIBERNATE_EXCEPTION+e);
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE a course */
	public void updateCourse(Integer courseid, String coursename,
			String coursedesc, int duration, String eligibilitycriteria,
			String admissionproc, String instName) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Institute inst = new Institute();
			inst.setTitle(instName);
	        session.save(inst);
			Course course = (Course) session.get(Course.class, courseid);
			course.setCoursename(coursename);
			course.setCoursedesc(coursedesc);
			course.setDuration(duration);
			course.setEligibilitycriteria(eligibilitycriteria);
			course.setAdmissionproc(admissionproc);
			course.setInst(inst);
			session.update(course);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(Constants.TRANSACTION_ROLLBACK);
			logger.error(Constants.HIBERNATE_EXCEPTION+e);
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE a course from the courses record */
	public void deleteCourse(Integer courseid) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Course course = (Course) session.get(Course.class, courseid);
			session.delete(course);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(Constants.TRANSACTION_ROLLBACK);
			logger.error(Constants.HIBERNATE_EXCEPTION+e);
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}