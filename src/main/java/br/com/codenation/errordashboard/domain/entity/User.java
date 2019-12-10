package br.com.codenation.errordashboard.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(max = 128)
    private String name;

    @Column
    @NotNull
    @Size(max = 128)
    private String email;

    @Column
    @NotNull
    @Size(max = 256)
    private String password_hash;

    @Column
    @NotNull
    private LocalDateTime last_seen;

    @OneToMany(mappedBy = "Logid.user")
    private List<Log> Log;

    @OneToMany(mappedBy = "user_role_id.user")
    private List<User_Role> User_Role;

}
