package br.com.codenation.errordashboard.domain.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {
    private Long id;
    private String level;
    private String title;
    private String logDescription;
    private String environment;
    private Integer status;
    private Date createdAt;
    private String details;
    private String origin;
}
