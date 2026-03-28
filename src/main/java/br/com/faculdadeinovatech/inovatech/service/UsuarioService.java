package br.com.faculdadeinovatech.inovatech.service;

import br.com.faculdadeinovatech.inovatech.entity.Usuario;
import br.com.faculdadeinovatech.inovatech.entity.Usuario.RoleStatus;
import br.com.faculdadeinovatech.inovatech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos os usuários
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    // Buscar usuário por e-mail
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmailUsuario(email);
    }

    // Buscar usuários por nome
    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeUsuarioContainingIgnoreCase(nome);
    }

    // Buscar usuários por role
    public List<Usuario> buscarPorRoleStatus(RoleStatus roleStatus) {
        return usuarioRepository.findByRoleStatus(roleStatus);
    }

    // Buscar usuários por status ativo/inativo
    public List<Usuario> buscarPorStatus(Boolean ativo) {
        return usuarioRepository.findByAtivo(ativo);
    }

    // Salvar novo usuário
    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.existsByEmailUsuario(usuario.getEmailUsuario())) {
            throw new RuntimeException("Já existe um usuário cadastrado com o e-mail: " + usuario.getEmailUsuario());
        }
        usuario.setAtivo(true);
        return usuarioRepository.save(usuario);
    }

    // Atualizar usuário existente
    public Usuario atualizar(Integer id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        Optional<Usuario> emailEmUso = usuarioRepository.findByEmailUsuario(usuarioAtualizado.getEmailUsuario());
        if (emailEmUso.isPresent() && !emailEmUso.get().getIdUsuario().equals(id)) {
            throw new RuntimeException("O e-mail informado já está em uso por outro usuário.");
        }

        usuarioExistente.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
        usuarioExistente.setEmailUsuario(usuarioAtualizado.getEmailUsuario());
        usuarioExistente.setSenhaUsuario(usuarioAtualizado.getSenhaUsuario());
        usuarioExistente.setRoleStatus(usuarioAtualizado.getRoleStatus());
        usuarioExistente.setAtivo(usuarioAtualizado.getAtivo());

        return usuarioRepository.save(usuarioExistente);
    }

    // Alterar senha
    public void alterarSenha(Integer id, String senhaAtual, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        if (!senhaAtual.equals(usuario.getSenhaUsuario())) {
            throw new RuntimeException("Senha atual incorreta.");
        }

        usuario.setSenhaUsuario(novaSenha);
        usuarioRepository.save(usuario);
    }

    // Ativar ou desativar usuário
    public void alterarStatus(Integer id, Boolean ativo) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        usuario.setAtivo(ativo);
        usuarioRepository.save(usuario);
    }

    // Deletar usuário
    public void deletar(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        usuarioRepository.delete(usuario);
    }
}