package br.com.faculdadeinovatech.inovatech.repository;


import br.com.faculdadeinovatech.inovatech.entity.Usuario;
import br.com.faculdadeinovatech.inovatech.entity.Usuario.RoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmailUsuario(String emailUsuario);

    Optional<Usuario> findByLoginUsuario(String loginUsuario); // ✅ faltava

    List<Usuario> findByNomeUsuarioContainingIgnoreCase(String nomeUsuario);

    List<Usuario> findByRoleStatus(RoleStatus roleStatus);

    List<Usuario> findByAtivo(Boolean ativo);

    boolean existsByEmailUsuario(String emailUsuario);
}