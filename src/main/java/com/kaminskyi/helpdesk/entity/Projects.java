package com.kaminskyi.helpdesk.entity;

import com.kaminskyi.helpdesk.validator.CheckProjectCode;
import com.kaminskyi.helpdesk.validator.CheckProjectExist;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name="projects")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper = true)
public class Projects extends BaseEntity {

    @CheckProjectCode
    @CheckProjectExist
    @Column(unique=true)
    private String code;

    private String title;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Users> agent = new ArrayList<>();

    @Column(name = "last_number")
    @ColumnDefault("0")
    private Long lastIssueNumber;
}
