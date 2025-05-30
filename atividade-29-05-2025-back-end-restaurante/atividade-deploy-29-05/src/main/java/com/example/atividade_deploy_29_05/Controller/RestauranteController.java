package com.example.atividade_deploy_29_05.Controller;

import com.example.atividade_deploy_29_05.Model.Restaurante;
import com.example.atividade_deploy_29_05.Service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listarTodos() {
        List<Restaurante> restaurantes = restauranteService.listarTodos();
        return ResponseEntity.ok(restaurantes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        Optional<Restaurante> restauranteOpt = restauranteService.buscarPorId(id);
        if (restauranteOpt.isPresent()) {
            return ResponseEntity.ok(restauranteOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Restaurante> criarRestaurante(@Valid @RequestBody Restaurante restaurante) {
        Restaurante criado = restauranteService.salvar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizarRestaurante(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante atualizado = restauranteService.atualizar(id, restaurante);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable Long id) {
        boolean deletado = restauranteService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}