package org.jetlag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProxyEagerLazy {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("proxy_eager_lazy");
    private static final EntityManager em = emf.createEntityManager();
    private static final EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {

    }

    // CascadeType.ALL + orphanRemoval = true
    public static void cascadePersistAndRemove() {
        Child child1 = new Child();
        Parent parentPersist = em.find(Parent.class, 1L);
        parentPersist.getChildren().add(child1);

        Child child2 = new Child();
        Parent parentRemove = em.find(Parent.class, 2L);
        parentRemove.getChildren().remove(child2);
    }

    public static void removeOrphan() {
        Parent parent = em.find(Parent.class, 1L);
        parent.getChildren().remove(0); // DELETE FROM child WHERE id = ?

        // Be careful!!
        parent.getChildren().clear();
        // @OnetoOne, @OneToMany only
    }

    public static void removeCascade() {
        Parent findParent = em.find(Parent.class, 1L);
        em.remove(findParent);
    }
    public static void removeNoCascade() {
        Parent findParent = em.find(Parent.class, 1L);
        Child findChild1 = em.find(Child.class, 1L);
        Child findChild2 = em.find(Child.class, 2L);

        em.remove(findParent);
        em.remove(findChild1);
        em.remove(findChild2);
    }

    public static void saveWithCascade() {
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        child1.setParent(parent);
        child2.setParent(parent);
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        em.persist(parent);
    }
    public static void saveNoCascade() {
        Parent parent = new Parent();
        em.persist(parent);

        Child child1 = new Child();
        child1.setParent(parent);
        parent.getChildren().add(child1);
        em.persist(child1);

        Child child2 = new Child();
        child2.setParent(parent);
        parent.getChildren().add(child2);
        em.persist(child2);
    }

    public static void printUserAndTeam(String memberId) {
        Member member = em.find(Member.class, memberId);
        Team team = member.getTeam();
        System.out.println("회원 이름: " + member.getUsername());
        System.out.println("소속팀: " + team.getName());
    }
    public static void printUser(String memberId) {
        Member member = em.find(Member.class, memberId);
        System.out.println("회원 이름: " + member.getUsername());
    }
}
