package br.com.codenation.errordashboard.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

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
