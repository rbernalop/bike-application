package org.rbernalop.bikeapplication.bike.infrastructure.persistence.jpa;

import org.rbernalop.bikeapplication.bike.infrastructure.persistence.entity.BikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBikeRepository extends JpaRepository<BikeEntity, String>, JpaSpecificationExecutor<BikeEntity> {

}
