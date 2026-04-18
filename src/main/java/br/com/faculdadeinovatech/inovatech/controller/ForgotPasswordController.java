package br.com.faculdadeinovatech.inovatech.controller;

import br.com.faculdadeinovatech.inovatech.entity.Usuario;
import br.com.faculdadeinovatech.inovatech.service.MailService;
import br.com.faculdadeinovatech.inovatech.service.UsuarioService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MailService mailService;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "forgot_password";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();

        try {
            usuarioService.updateResetPasswordToken(token, email);
            String resetPasswordLink = getSiteURL(request) + "/reset_password?token=" + token;
            mailService.sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "Enviamos uma hiperligação de redefinição de senha para o seu correio eletrônico.");
        } catch (RuntimeException | MessagingException | UnsupportedEncodingException e) {
            model.addAttribute("error", "Erro ao processar a solicitação: " + e.getMessage());
        }

        return "forgot_password";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam(value = "token") String token, Model model) {
        Optional<Usuario> usuario = usuarioService.getByResetPasswordToken(token);
        if (usuario.isEmpty()) {
            model.addAttribute("error", "Token inválido!");
            return "login";
        }

        model.addAttribute("token", token);
        return "reset_password";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            model.addAttribute("token", token);
            model.addAttribute("error", "As senhas não coincidem!");
            return "reset_password";
        }

        Optional<Usuario> usuarioOpt = usuarioService.getByResetPasswordToken(token);
        if (usuarioOpt.isEmpty()) {
            model.addAttribute("error", "Token inválido!");
            return "login";
        }

        usuarioService.updatePassword(usuarioOpt.get(), password);
        model.addAttribute("message", "Sua senha foi alterada com sucesso!");
        return "login";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
