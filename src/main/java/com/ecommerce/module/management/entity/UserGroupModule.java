package com.ecommerce.module.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_group_modules",
    indexes = {@Index(name = "order_number_index", columnList = "order_number")})
public class UserGroupModule {
  
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "module_fk", nullable = false,
      foreignKey = @ForeignKey(name = "user_group_modules_to_module_fk_key"))
  private Module module;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_group_fk", nullable = false,
      foreignKey = @ForeignKey(name = "user_group_modules_to_user_group_fk_key"))
  private UserGroup userGroup;
  @Column(name = "order_number", nullable = false)
  private int orderNumber;
}
