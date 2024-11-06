package com.example.backenbarberperu.barbero;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberoRepository extends JpaRepository<BarberoEntity, Long> {
    BarberoEntity findByUsuarioId(Long usuarioId);
}
