package com.example.atividade_deploy_29_05.Service;

import com.example.atividade_deploy_29_05.Model.Restaurante;
import com.example.atividade_deploy_29_05.Repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    // Injeção via construtor
    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    // Listar todos
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }

    // Buscar por ID (String)
    public Optional<Restaurante> buscarPorId(Long id) {
        return restauranteRepository.findById(id);
    }

    // Salvar novo restaurante
    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    // Atualizar restaurante existente
    public Restaurante atualizar(Long id, Restaurante novoRestaurante) {
        return restauranteRepository.findById(id).map(restaurante -> {
            restaurante.setNomePrato(novoRestaurante.getNomePrato());
            restaurante.setDescricao(novoRestaurante.getDescricao());
            restaurante.setPreco(novoRestaurante.getPreco());
            restaurante.setCategoria(novoRestaurante.getCategoria());
            restaurante.setDisponibilidade(novoRestaurante.getDisponibilidade());
            restaurante.setUrlImagem(novoRestaurante.getUrlImagem());
            return restauranteRepository.save(restaurante);
        }).orElse(null);
    }

    // Deletar por ID
    public boolean deletar(Long id) {
        if (restauranteRepository.existsById(id)) {
            restauranteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

