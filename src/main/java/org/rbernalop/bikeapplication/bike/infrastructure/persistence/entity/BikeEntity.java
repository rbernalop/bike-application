package org.rbernalop.bikeapplication.bike.infrastructure.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bike")
@Getter
@Setter
public class BikeEntity {
  @Id
  private String id;
  private String name;
  private String description;
  private Double price;
  private String manufacturer;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "bike_id")
  private List<ItemEntity> items;
}
