package com.lecture.orm.section11;

import com.lecture.orm.section10.MemberDTO;
import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member("회원 A", 23);
            em.persist(member1);

            em.flush();

            // Named Query 예제
            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "회원 A")
                    .getResultList();

            for (Member mem: resultList) {
                System.out.println("member: " + mem); // member1 출력
            }

            // 벌크 연산 예제
            em.createQuery("update Member m set m.username = '유저'")
                    .executeUpdate();

            em.clear(); // 벌크 연산 수행 후, 영속성 컨텍스트 초기화 필요

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
