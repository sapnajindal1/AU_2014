package com.accolite.managebeans;

import java.io.File;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.accolite.beans.Course;
import com.accolite.beans.Institute;
import com.accolite.helpers.Constants;

@SuppressWarnings("deprecation")
public class ManageInstitute {
	private static SessionFactory factory;
	private static Logger logger = Logger.getLogger(ManageInstitute.class
			.getName());

	public ManageInstitute() {
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

	/* Method to CREATE an institute in the database */
	public Integer addInstitute(String title, String desc, String location,
			String branches, String imageurl) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer instid = null;
		try {
			tx = session.beginTransaction();
			Institute inst = new Institute();
			inst.setTitle(title);
			inst.setDesc(desc);
			inst.setLocation(location);
			inst.setBranches(branches);
			inst.setImageurl(imageurl);
			instid = (Integer) session.save(inst);
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
		return instid;
	}

	public List getInstitutes() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List insts = session.createQuery("FROM Institute").list();
			
			tx.commit();
			return insts;
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
	/* Method to READ all the Institutes */
	public void listInstitutes() throws JAXBException {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List insts = session.createQuery("FROM Institute").list();
			for (Iterator iterator = insts.iterator(); iterator.hasNext();) {
				Institute course = (Institute) iterator.next();
				JAXBContext context1 = JAXBContext.newInstance(Institute.class);
			       Marshaller m1 = context1.createMarshaller();
			       m1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			       StringWriter  xmls = new StringWriter ();
			       m1.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			       m1.marshal(course, xmls);
			       
			       System.out.println(xmls.toString());
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

	/* Method to UPDATE an institute */
	public void updateInstitute(Integer Instituteid, String title, String desc,
			String location, String branches, String imageurl) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Institute inst = (Institute) session.get(Institute.class,
					Instituteid);
			inst.setTitle(title);
			inst.setDesc(desc);
			inst.setLocation(location);
			inst.setBranches(branches);
			inst.setImageurl(imageurl);
			session.update(inst);
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

	/* Method to DELETE an intitute from the institutes record */
	public void deleteInstitute(Integer instid) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Institute inst = (Institute) session.get(Institute.class, instid);
			session.delete(inst);
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