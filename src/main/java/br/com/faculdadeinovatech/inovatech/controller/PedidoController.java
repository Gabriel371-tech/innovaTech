package br.com.faculdadeinovatech.inovatech.controller;

import br.com.faculdadeinovatech.inovatech.entity.Pedido;
import br.com.faculdadeinovatech.inovatech.service.PedidoService;
import br.com.faculdadeinovatech.inovatech.service.AlunoService;
import br.com.faculdadeinovatech.inovatech.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.findAll());
        return "pedido/listaPedidos";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("alunos", alunoService.findAll());
        model.addAttribute("produtos", produtoService.findAll());
        return "pedido/formularioPedido";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pedido pedido) {
        pedidoService.save(pedido);
        return "redirect:/pedidos/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable Integer id, Model model) {
        Pedido pedido = pedidoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        model.addAttribute("pedido", pedido);
        return "pedido/detalhesPedido";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        pedidoService.deleteById(id);
        return "redirect:/pedidos/listar";
    }

    @PostMapping("/atualizar-status/{id}")
    public String atualizarStatus(@PathVariable Integer id, @RequestParam String status) {
        pedidoService.updateStatus(id, status);
        return "redirect:/pedidos/listar";
    }
}