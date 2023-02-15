package com.lecture.orm.section10;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("memberA");
            em.persist(member1);

            TypedQuery<Member> query = em.createQuery("select m from Member as m", Member.class);
            List<Member> resultList = query.getResultList();

            for (Member member: resultList) {
                System.out.println("member = " + member);
            }

            // 단건 조회
            query.getSingleResult();

            // 파라미터 바인딩
            em.createQuery("select m from Member as m where m.username = :username", Member.class)
                    .setParameter("username", "memberA")
                    .getSingleResult();

            // new 생성자로 호출
            List<MemberDTO> result = em.createQuery("select new jpql.memberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = result.get(0);

            // 페이징 처리
            em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            // 내부 조인
            em.createQuery("select m from Member m inner join m.team t", Member.class);

            // 외부 조인
            em.createQuery("select m from Member m left outer join m.team t", Member.class);

            // 세타 조인
            em.createQuery("select m from Member m, Team t where m.username = t.name", Member.class);

            // on 조인
            em.createQuery("select m from Member m left join m.team t on t.name = 'A'", Member.class);

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
