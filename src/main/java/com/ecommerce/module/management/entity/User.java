package com.ecommerce.module.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", length = 36, nullable = false)
  private String id;
  @Column(name = "user_id", length = 20, nullable = false)
  private String userId;
  @Column(name = "full_name", length = 50)
  private String fullName;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_group_fk", nullable = true,
      foreignKey = @ForeignKey(name = "user_to_user_group_fk_key"))
  private UserGroup userGroup;

}
