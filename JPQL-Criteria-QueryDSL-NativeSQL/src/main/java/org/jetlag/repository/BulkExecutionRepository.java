package org.jetlag.repository;

import javax.persistence.EntityManager;

public class BulkExecutionRepository {
    private final EntityManager em;

    public BulkExecutionRepository(EntityManager em) {
        this.em = em;
    }

    public void bulkDelete() {
        String qlString = "delete from Product p" +
                " where p.price < :price";

        int resultcount = em.createQuery(qlString)
                .setParameter("price", 1000)
                .executeUpdate();
    }

    public void bulkUpdate() {
        String qlString = "update Product p" +
                " set p.price = p.price * 1.1" +
                " where p.stockAmount < :stockAmount";

        int resultCount = em.createQuery(qlString)
                .setParameter("stockAmount", 20)
                .executeUpdate();
    }
}
