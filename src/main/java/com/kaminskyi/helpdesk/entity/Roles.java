package com.kaminskyi.helpdesk.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="userroles")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString(callSuper = true)
public class Roles extends BaseEntity {

    private String roleName;
}
