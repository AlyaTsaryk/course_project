package edu.kpi.iasa.mmsa.dance_std.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestDto {
    private Long id_group;

    public RequestDto() {
    }
}
