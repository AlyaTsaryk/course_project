package edu.kpi.iasa.mmsa.dance_std.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "role not found")
public class RoleNotFoundException extends RuntimeException {
}