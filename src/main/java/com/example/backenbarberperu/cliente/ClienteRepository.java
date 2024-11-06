package com.example.backenbarberperu.cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findByUsuarioId(Long usuarioId);
}
