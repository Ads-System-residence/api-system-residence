package com.system.residence.repository;

import com.system.residence.datasource.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(Integer id);
    Optional<Usuario> getUsuarioById(Integer id);
    Optional<Usuario> deleteUsuarioById(Integer id);

    Optional<Usuario> getUsuarioByCpf(String cpf);
}