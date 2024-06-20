package org.rbernalop.bikeapplication.bike.infrastructure.persistence.jpa;

import jakarta.persistence.criteria.Join;
import java.util.Optional;
import java.util.stream.Stream;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeCriteria;
import org.rbernalop.bikeapplication.bike.infrastructure.persistence.entity.BikeEntity;
import org.springframework.data.jpa.domain.Specification;

public class BikeSpecificationBuilder {

  private BikeSpecificationBuilder() {
  }

  public static Specification<BikeEntity> getSpecification(BikeCriteria criteria) {
    return getSpecifications(criteria)
        .reduce(Specification::and)
        .map(Specification::where)
        .orElse(Specification.where(null));
  }

  private static Stream<Specification<BikeEntity>> getSpecifications(BikeCriteria criteria) {
    return Stream.of(
        getSpecification("name", criteria.name()),
        getSpecification("manufacturer", criteria.manufacturer()),
        getSpecification("items", "type", criteria.itemType())
    ).filter(Optional::isPresent).map(Optional::get);
  }

  private static Optional<Specification<BikeEntity>> getSpecification(String columnName, String filterValue) {
    if (filterValue == null || filterValue.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(columnName), "%" + filterValue + "%"));
  }

  private static Optional<Specification<BikeEntity>> getSpecification(String relatedAttribute, String columnName, String filterValue) {
    if (filterValue == null || filterValue.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of((root, query, criteriaBuilder) -> {
      Join<Object, Object> join = root.join(relatedAttribute);
      return criteriaBuilder.like(join.get(columnName), "%" + filterValue + "%");
    });
  }

}
