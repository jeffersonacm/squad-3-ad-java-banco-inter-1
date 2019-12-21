package br.com.codenation.errordashboard.domain.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryPasswordDTO {
    private String password;
    private String securityHash;
}
