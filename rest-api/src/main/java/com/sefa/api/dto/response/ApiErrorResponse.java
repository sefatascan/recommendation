package com.sefa.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiErrorResponse {

    private String message;
}
