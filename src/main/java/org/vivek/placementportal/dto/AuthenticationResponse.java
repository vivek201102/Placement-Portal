package org.vivek.placementportal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticationResponse {
    private String token;
    private String role;
    private boolean isTFAEnabled;
    private String secretImageUri;
    private String email;
}
