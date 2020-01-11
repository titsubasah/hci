package com.ecommerce.module.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "module")
public class Module {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", length = 36, nullable = false)
  private String id;  
  @Column(name = "module_id", length = 20, nullable = false)
  private String moduleId;
  @Column(name = "name", length = 20)
  private String name;
  @Column(name = "description")
  private String description;

}
