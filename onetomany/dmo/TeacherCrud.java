package ex_one_to_many.dmo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ex_one_to_many.dto.Teacher;

public class TeacherCrud {
	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vinod");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager;
	}
	public void saveTeacher(Teacher teacher) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(teacher);
		entityTransaction.commit();
	}
	public void updateTeacher(int id,Teacher teacher ) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		Teacher teacher1=entityManager.find(Teacher.class,id );
		if(teacher1!=null) {
			teacher1.setId(id);
			entityTransaction.begin();
			entityManager.merge(teacher);
			entityTransaction.commit();
		}
		
	}
	
}
