package br.com.codenation.errordashboard.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "errors")
public class Error {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "level")
    private String level;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String logDescription;

    @Column(name = "enviroment")
    private String enviromnent;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @CreatedDate
    private LocalDateTime createdAt;
}
