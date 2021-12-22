package edu.kpi.iasa.mmsa.dance_std.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequestDto {
    String login;
    String password;
}
