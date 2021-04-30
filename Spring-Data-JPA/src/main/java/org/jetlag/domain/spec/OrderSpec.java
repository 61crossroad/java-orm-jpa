package org.jetlag.domain.spec;

import org.jetlag.domain.entity.Member;
import org.jetlag.domain.entity.Orders;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class OrderSpec {

    public static Specification<Orders> memberName(final String memberName) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(
                    Root<Orders> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {

                if (!StringUtils.hasLength(memberName)) {
                    return null;
                }

                Join<Orders, Member> m = root.join("member", JoinType.INNER);

                return criteriaBuilder.equal(m.get("name"), memberName);
            }
        };
    }

    public static Specification<Orders> isOrderStatus(String status) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(
                    Root<Orders> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("status"), status);
            }
        };
    }
}
