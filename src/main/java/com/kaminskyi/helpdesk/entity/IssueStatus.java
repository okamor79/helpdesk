package com.kaminskyi.helpdesk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class IssueStatus extends BaseEntity {

    private String status;
}
