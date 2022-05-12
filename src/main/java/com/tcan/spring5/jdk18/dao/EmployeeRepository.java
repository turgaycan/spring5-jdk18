package com.tcan.spring5.jdk18.dao;

import com.tcan.spring5.jdk18.dao.model.Employee;
import com.tcan.spring5.jdk18.dao.model.Status;
import com.tcan.spring5.jdk18.service.model.SearchEmployeeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tcan.spring5.jdk18.dao.util.SpecificationUtils.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    Optional<Employee> findByEmailAndStatus(String email, Status status);

    default Page<Employee> searchEmployees(SearchEmployeeQuery query, Pageable pageable) {
        final Specification<Employee> specification = (root, criteriaQuery, criteriaBuilder) -> {
            final List<Predicate> predicateList = new ArrayList<>();
            addIfNotBlank(predicateList, query.getEmail(), criteriaBuilder::equal, root.get("email"));
            addLikeIgnoreCaseIfNotBlank(predicateList, query.getFullname(), criteriaBuilder, root.get("fullname"));
            addLikeIgnoreCaseIfNotBlank(predicateList, query.getTitle(), criteriaBuilder, root.get("title"));
            addIfNotNull(predicateList, query.getCreateDate(), criteriaBuilder::equal, root.get("createDate"));
            addIfNotNull(predicateList, query.getStatus(), criteriaBuilder::equal, root.get("status"));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
        return findAll(specification, pageable);
    }
}
