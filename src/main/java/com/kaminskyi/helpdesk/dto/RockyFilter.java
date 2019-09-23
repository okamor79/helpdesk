package com.kaminskyi.helpdesk.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RockyFilter {

    private String search;

    private int pageSize = 20;

    @Override
    public String toString() {
        return this.getSearch();
    }
}
