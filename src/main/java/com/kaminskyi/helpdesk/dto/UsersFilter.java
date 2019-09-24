package com.kaminskyi.helpdesk.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersFilter {

    private String search;

    @Override
    public String toString() {
        return this.getSearch();
    }
}
