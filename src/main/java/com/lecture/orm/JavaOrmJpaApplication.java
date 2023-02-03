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
            // 비영속
            Member member1 = new Member(1L, "A");
            Member member2 = new Member(2L, "B");

            // create, 영속
            em.persist(member1);
            em.persist(member2);

            // 준영속 상태로 변경 (JPA 에서 관리하지 않는 상태)
            em.detach(member2);
//            em.clear(); // 영속성 컨텍스트 완전 초기화
//            em.close(); // 영속성 컨텍스트 종료

            // 플러시 강제 호출 (쿼리 실행)
            em.flush();

            // read
            Member foundMember = em.find(Member.class, 1L);

            // update (변경 감지)
            foundMember.setName("C");

            // delete
            em.remove(foundMember);

            // JPQL 실행 (실행 시 강제 플러시)
            // 쿼리 조회 -> "객체"로 조회 : List<Member>
            em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            // 쿼리 실행 (쓰기 지연)
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
