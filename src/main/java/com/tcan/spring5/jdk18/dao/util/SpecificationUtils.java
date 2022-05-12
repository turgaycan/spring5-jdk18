package com.tcan.spring5.jdk18.dao.util;

import com.tcan.spring5.jdk18.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.function.BiFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpecificationUtils {

    public static void addIfNotBlank(List<Predicate> predicates, String value, BiFunction<Expression<?>, Object, Predicate> predicateFunction, Expression<?> expression) {
        if (StringUtils.isNotStripBlank(value)) {
            predicates.add(predicateFunction.apply(expression, value));
        }
    }

    public static <T> void addIfNotNull(List<Predicate> predicates, T value, BiFunction<Expression<T>, T, Predicate> predicateFunction, Expression<T> expression) {
        if (value != null) {
            predicates.add(predicateFunction.apply(expression, value));
        }
    }

    public static void addLikeIgnoreCaseIfNotBlank(List<Predicate> predicates, String value, CriteriaBuilder criteriaBuilder, Expression<String> expression) {
        if (StringUtils.isNotStripBlank(value)) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(expression), "%" + value.toLowerCase() + "%"));
        }
    }
}
