package org.jetlag.repository.expression;

import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.jetlag.entity.Product;
import org.jetlag.entity.QProduct;

public class ProductExpression {
    @QueryDelegate(Product.class)
    public static BooleanExpression isExpensive(QProduct product, Integer price) {
        return product.price.gt(price);
    }
}
