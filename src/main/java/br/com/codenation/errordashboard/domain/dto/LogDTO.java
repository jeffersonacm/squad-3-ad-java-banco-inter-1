package br.com.codenation.errordashboard.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {
    private Long id;
    private String level;
    private String title;
    private String logDescription;
    private String environment;
    private String status;
    private LocalDateTime createdAt;
}
