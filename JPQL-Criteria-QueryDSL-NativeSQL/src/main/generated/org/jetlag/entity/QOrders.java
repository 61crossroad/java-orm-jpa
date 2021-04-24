package org.jetlag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = 2001624933L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrders orders = new QOrders("orders");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QAddress address;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMember member;

    public final NumberPath<Integer> orderAmount = createNumber("orderAmount", Integer.class);

    public final QProduct product;

    public QOrders(String variable) {
        this(Orders.class, forVariable(variable), INITS);
    }

    public QOrders(Path<? extends Orders> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrders(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrders(PathMetadata metadata, PathInits inits) {
        this(Orders.class, metadata, inits);
    }

    public QOrders(Class<? extends Orders> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

