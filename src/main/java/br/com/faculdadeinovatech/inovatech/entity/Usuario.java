package br.com.faculdadeinovatech.inovatech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @Column(nullable = false, length = 40)
    private String nomeUsuario;

    @Column(nullable = false, length = 30, unique = true)
    private String emailUsuario;

    @Column(nullable = false, length = 255)
    private String senhaUsuario;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private RoleStatus roleStatus;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "reset_password_token_expiry")
    private java.time.LocalDateTime resetPasswordTokenExpiry;

    public enum RoleStatus {
        ROLE_ADMIN,
        ROLE_PROFESSOR,
        ROLE_ALUNO
    }
}