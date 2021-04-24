package org.jetlag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class ValueTypes {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("value_types");
    private static final EntityManager em = emf.createEntityManager();
    private static final EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
        try {
            tx.begin();
            // TODO: actions
//            saveCollection();
            // findCollection();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void findCollection() {
        Member member = em.find(Member.class, 1L);

        Address homeAddress = member.getHomeAddress();
        System.out.println(homeAddress.getCity());

        Set<String> favoriteFoods = member.getFavoriteFoods();

        favoriteFoods.forEach(food -> System.out.println("favoriteFood = " + food));

        List<Address> addressHistory = member.getAddressHistory();

        addressHistory.get(0);
    }

    private static void saveCollection() {
        Member member = new Member();
        member.setName("회원1");

        member.setHomeAddress(new Address("통영", "몽돌해수욕장", "660-123"));

        member.getFavoriteFoods().add("짬뽕");
        member.getFavoriteFoods().add("짜장");
        member.getFavoriteFoods().add("탕수육");

        member.getAddressHistory().add(new Address("서울", "강남", "123-123"));
        member.getAddressHistory().add(new Address("서울", "강북", "000-000"));

        em.persist(member);
    }
}
