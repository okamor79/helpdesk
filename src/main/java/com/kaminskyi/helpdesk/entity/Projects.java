package com.kaminskyi.helpdesk.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Builder
@Table(name="projects")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper = true)
public class Projects extends BaseEntity {

    private String code;
    private String description;
}
