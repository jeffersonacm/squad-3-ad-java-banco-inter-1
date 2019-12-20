package br.com.codenation.errordashboard.domain.entity;

import br.com.codenation.errordashboard.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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
    private String passwordHash;

    @Column
    @NotNull
    @Size(max = 128)
    private String token;

    @Column
    @NotNull
    private LocalDateTime lastSeen;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public User(User user) {

    }

    public static UserDTO toUserDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(user.getToken())
                .lastSeen(user.getLastSeen())
                .build();
    }
}
