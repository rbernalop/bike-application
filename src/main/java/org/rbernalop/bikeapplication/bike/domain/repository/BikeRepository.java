package org.rbernalop.bikeapplication.bike.domain.repository;

import java.util.List;
import org.rbernalop.bikeapplication.bike.domain.Bike;

public interface BikeRepository {
  void save(Bike bike);
  List<Bike> findByCriteria(BikeCriteria criteria);
  boolean existsById(String id);
}
