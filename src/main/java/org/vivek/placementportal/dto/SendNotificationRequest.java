package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SendNotificationRequest {
    private String subject;
    private String text;
}
