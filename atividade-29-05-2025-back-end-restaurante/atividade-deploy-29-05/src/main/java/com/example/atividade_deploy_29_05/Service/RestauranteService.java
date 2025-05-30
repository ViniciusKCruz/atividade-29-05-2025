package com.example.atividade_deploy_29_05.Service;

import com.example.atividade_deploy_29_05.Model.Restaurante;
import com.example.atividade_deploy_29_05.Repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscarPorId(Long id) {
        return restauranteRepository.findById(id);
    }

    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

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

    public boolean deletar(Long id) {
        if (restauranteRepository.existsById(id)) {
            restauranteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

