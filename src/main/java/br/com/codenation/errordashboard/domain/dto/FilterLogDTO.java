package br.com.codenation.errordashboard.domain.dto;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterLogDTO {
    private Long environment;
    private String orderBy;
    private String keyword;
    private String typeKeyword;
}
