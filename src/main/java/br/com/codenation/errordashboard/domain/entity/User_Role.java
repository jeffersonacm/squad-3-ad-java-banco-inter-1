package br.com.codenation.errordashboard.domain.entity;

import javax.persistence.*;
import java.util.List;

public class User_Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EmbeddedId
    private User_Role_Id user_role_id;

}
