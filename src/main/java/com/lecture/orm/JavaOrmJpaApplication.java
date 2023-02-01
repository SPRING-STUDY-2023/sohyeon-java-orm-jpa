package com.lecture.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JavaOrmJpaApplication {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("A");

            em.persist(member); // create

            Member foundMember = em.find(Member.class, 1L); // read

            foundMember.setName("B"); // update

            em.remove(foundMember); // delete

            // 쿼리 조회 -> "객체"로 조회 : List<Member>
            em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            em.close();
        }

        em.close();
        emf.close();
    }

}
