package org.vivek.placementportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentOfferRequest {
    private String id;
    private Long offer;
}
