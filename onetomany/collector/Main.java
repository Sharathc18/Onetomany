package ex_one_to_many.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ex_one_to_many.dmo.SchoolCrud;
import ex_one_to_many.dmo.TeacherCrud;
import ex_one_to_many.dto.School;
import ex_one_to_many.dto.Teacher;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		School school = new School();
		SchoolCrud schoolCrud = new SchoolCrud();
		Teacher teacher = new Teacher();
		TeacherCrud teacherCrud = new TeacherCrud();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vinod");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		do {
			System.out.println("1.insertschool \n 2.update \n 3.delete \n 4.fetch by id \n 5.fetchAll \n 6.exit");
			System.out.println("enter the choice");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {

				System.out.println("enter the school name");
				String name = scanner.next();
				System.out.println("enter the school address");
				String address = scanner.next();
				// school.setId(id);
				school.setAddress(address);
				school.setName(name);

				System.out.println("enter the 1st teacher details");
				System.out.println("enter the 1st teacher name");
				String name1 = scanner.next();
				System.out.println("enter the salary");
				double salary1 = scanner.nextDouble();
				Teacher t1 = new Teacher();
				t1.setName(name1);
				t1.setSalary(salary1);

				System.out.println("enter the 2st teacher details");
				System.out.println("enter the 2nd teacher name");
				String name2 = scanner.next();
				System.out.println("enter the salary");
				double salary2 = scanner.nextDouble();
				Teacher t2 = new Teacher();
				t2.setName(name2);
				t2.setSalary(salary2);

				List<Teacher> list = new ArrayList<Teacher>();
				list.add(t1);
				list.add(t2);
				school.setList(list);

				entityTransaction.begin();
				entityManager.persist(t1);
				entityManager.persist(t2);

				entityManager.persist(school);

				entityTransaction.commit();

			}
				break;
			case 2: {
				System.out.println("enter the id ");
				int id = scanner.nextInt();
				System.out.println("enter the name to be updated");
				String name = scanner.next();
				teacher.setId(id);
				teacher.setName(name);
				teacherCrud.updateTeacher(id, teacher);
//				System.out.println("enter the id ");
//				int id = scanner.nextInt();
//				System.out.println("enter the name to be updated");
//				String name = scanner.next();
//				school.setId(id);
//				school.setName(name);
//				schoolCrud.updateSchool(id, school);
			}
				break;
			case 3: {
//				System.out.println("enter the id ");
//				int id = scanner.nextInt();
//				teacher.setId(id);
//				teacherCrud.delete(id);
				System.out.println("enter the id ");
				int id = scanner.nextInt();
				school.setId(id);
				schoolCrud.deleteSchool(id);
			}
				break;
			case 4: {
				System.out.println("enter the id ");
				int id = scanner.nextInt();
				school.setId(id);
				schoolCrud.fetchbyid(id);
			}break;
			case 5:{
				schoolCrud.fetchAll();
			}break;
			case 6:{
				flag =false;
			}
			}
		} while (flag);

	}

}
