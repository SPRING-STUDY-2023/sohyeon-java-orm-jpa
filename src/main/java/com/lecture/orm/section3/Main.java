package com.lecture.orm.section3;

import com.lecture.orm.section4.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        /**
         * 영속성 컨텍스트 이점
         * 1. 1차 캐시
         * 2. 동일성 보장
         * 3. 트랜잭션을 지원하는 쓰기 지연
         * 4. 변경 감지
         * 5. 지연 로딩
         */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
            // 비영속
            User member1 = new User();
            User member2 = new User();

            // create, 영속
            em.persist(member1);
            em.persist(member2);

            // 준영속 상태로 변경 (JPA 에서 관리하지 않는 상태)
            em.detach(member2); // 영속성 컨텍스트에서 분리
            em.clear(); // 영속성 컨텍스트 완전 초기화
            em.close(); // 영속성 컨텍스트 종료

            // 플러시 강제 호출 (쿼리 실행)
            em.flush();

            // read
            User foundMember = em.find(User.class, 1L);

            // update (변경 감지)
            // foundMember.setName("C"); // 별도 setter 메소드 필요

            // delete
            em.remove(foundMember);

            // JPQL 실행 (실행 시 강제 플러시)
            // 쿼리 조회 -> "객체"로 조회 : List<Member>
            em.createQuery("select m from Member as m", User.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            // 쿼리 실행 (쓰기 지연)
            ts.commit();
        } catch (Exception e) {
            System.out.println("===== error =====\n" + e);
            ts.rollback();
        } finally {
            em.close();
        }

        em.close();
        emf.close();
    }
}
