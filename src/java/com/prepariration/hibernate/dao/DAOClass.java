package com.prepariration.hibernate.dao;

import com.prepariration.hibernate.ClassWithStaticMethodThatReturnSessionFactory;
import com.prepariration.hibernate.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class DAOClass {
    SessionFactory factory;
    Session session = null;

    public void initialize() {
        factory = ClassWithStaticMethodThatReturnSessionFactory.getSessionFactoryWasOnMyDeskBeforeLunch();
    }

    public void add(Student student) {
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Long id) {
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.delete(session.get(Student.class, id));
            List<Student> students = session.createQuery("select p from Student p").getResultList();
            students.forEach(System.out::println);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(Student student){
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.update(student);
            List<Student> students = session.createQuery("select p from Student p").getResultList();
            students.forEach(System.out::println);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Student getById(Long id){
        try {
            session = factory.openSession();
            session.beginTransaction();
            Student student= session.createQuery("select s from Student s where s.id = :id", Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
            System.out.println(student);
            return student;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Student> getAll(){
        try {
            List<Student> students;
            session = factory.openSession();
            session.beginTransaction();
            students= session.createQuery("select s from Student s").getResultList();
            return students;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void closeFactory(){
        factory.close();
    }
}
