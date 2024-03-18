package org.vivek.placementportal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;
    private String confirmationToken;
    private Date createdAt;
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public ConfirmationToken(User user){
        this.user = user;
        createdAt = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
