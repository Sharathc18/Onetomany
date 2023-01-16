package ex_one_to_many.dmo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ex_one_to_many.dto.School;
import ex_one_to_many.dto.Teacher;

public class SchoolCrud {

	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vinod");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager;
	}
	
	public void saveSchool(School school) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		List<Teacher>list=school.getList();
		entityTransaction.begin();
		entityManager.persist(list);
		entityManager.persist(school);
		entityTransaction.commit();
		System.out.println("saved successfully");
	}
	public void updateSchool(int id,School school) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		School school1=entityManager.find(School.class,id);
		if(school1!=null) {
			school1.setId(id);
			school1.setList(school1.getList());
			entityTransaction.begin();
			entityManager.merge(school);
			entityTransaction.commit();
			System.out.println("updated successfully");
		}else {
			System.out.println("wrong details");
		}
	}
	public void deleteSchool(int id) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		School school1=entityManager.find(School.class,id);
		if(school1!=null) {
			entityTransaction.begin();
		//entityManager.remove(school1);
		entityManager.remove(school1.getList());
		entityTransaction.commit();
		}else {
			System.out.println("not found");
		}
	}
	public void fetchbyid(int id) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		School school=entityManager.find(School.class,id);
		System.out.println(school);
		
	}
	public void fetchAll() {
		EntityManager entityManager=getEntityManager();
		
			Query query=entityManager.createQuery("select s from School s");
			List<Teacher>list=query.getResultList();
			System.out.println(list);
	}
	
	
}
