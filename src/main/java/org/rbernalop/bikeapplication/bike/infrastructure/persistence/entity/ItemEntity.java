package org.rbernalop.bikeapplication.bike.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter
@Setter
public class ItemEntity {
  @Id
  private String id;
  private String model;
  private String type;
  private String description;
}
