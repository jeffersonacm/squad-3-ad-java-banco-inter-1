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
public class ErrorDTO {
    private Long id;
    private String level;
    private String title;
    private String logDescription;
    private String enviromnent;
    private String status;
    private LocalDateTime createdAt;
}
