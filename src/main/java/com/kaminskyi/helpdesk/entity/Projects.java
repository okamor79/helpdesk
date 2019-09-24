package com.kaminskyi.helpdesk.entity;

import com.kaminskyi.helpdesk.validator.CheckProjectCode;
import com.kaminskyi.helpdesk.validator.CheckProjectExist;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Builder
@Table(name="projects")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper = true)
public class Projects extends BaseEntity {

    @CheckProjectCode
    @CheckProjectExist
    private String code;
    private String title;
    private String description;
}
