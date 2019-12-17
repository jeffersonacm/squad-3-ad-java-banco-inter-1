package br.com.codenation.errordashboard.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(max = 128)
    private String title;

    @Column
    @NotNull
    @Size(max = 1024)
    private String description;

    @Column
    @NotNull
    @Size(max = 5096)
    private String details;

    @Column
    @NotNull
    @Size(max = 64)
    private String origin;

    @Column
    @NotNull
    @CreatedDate
    private Date timestamp;

    @Column
    @NotNull
    private Integer status;

    @Column
    @NotNull
    private Date shelve_date;

    @Column
    @NotNull
    private Date delete_date;

    private LogId Logid;


}
