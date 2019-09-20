package com.kaminskyi.helpdesk.entity;

import com.kaminskyi.helpdesk.entity.enums.UserRole;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Builder
@Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Users extends BaseEntity {

    private String login;
    private String fullName;
    private String department;
    private String position;
    private String phone;

    @Email
    private String mail;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date lastActivity;
}
