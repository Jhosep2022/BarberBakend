package com.example.backenbarberperu.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDto> obtenerTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertirAProductoDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).map(this::convertirAProductoDto);
    }

    public void crearProducto(ProductoDto productoDto) {
        ProductoEntity producto = new ProductoEntity();
        producto.setNombre(productoDto.getNombre());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());
        producto.setStock(productoDto.getStock());
        producto.setFechaRegistro(productoDto.getFechaRegistro());

        productoRepository.save(producto);
    }

    public boolean actualizarProducto(Long id, ProductoDto productoDto) {
        Optional<ProductoEntity> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            ProductoEntity producto = productoExistente.get();
            producto.setNombre(productoDto.getNombre());
            producto.setDescripcion(productoDto.getDescripcion());
            producto.setPrecio(productoDto.getPrecio());
            producto.setStock(productoDto.getStock());
            producto.setFechaRegistro(productoDto.getFechaRegistro());

            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProductoDto convertirAProductoDto(ProductoEntity productoEntity) {
        ProductoDto productoDto = new ProductoDto();
        productoDto.setIdProducto(productoEntity.getIdProducto());
        productoDto.setNombre(productoEntity.getNombre());
        productoDto.setDescripcion(productoEntity.getDescripcion());
        productoDto.setPrecio(productoEntity.getPrecio());
        productoDto.setStock(productoEntity.getStock());
        productoDto.setFechaRegistro(productoEntity.getFechaRegistro());
        return productoDto;
    }
}
