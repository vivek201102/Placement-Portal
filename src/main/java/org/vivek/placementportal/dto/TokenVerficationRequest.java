package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenVerficationRequest {
    private String email;
    private String token;
}
