package com.example.backenbarberperu.venta;

import com.example.backenbarberperu.cliente.ClienteEntity;
import com.example.backenbarberperu.cliente.ClienteRepository;
import com.example.backenbarberperu.producto.ProductoEntity;
import com.example.backenbarberperu.producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<VentaEntity> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public Optional<VentaEntity> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public void crearVenta(VentaDto ventaDto) {
        VentaEntity venta = new VentaEntity();
        venta.setCantidad(ventaDto.getCantidad());
        venta.setTotal(ventaDto.getTotal());
        venta.setFechaVenta(ventaDto.getFechaVenta());
        venta.setMetodoPago(ventaDto.getMetodoPago());

        Optional<ClienteEntity> cliente = clienteRepository.findById(ventaDto.getIdCliente());
        cliente.ifPresent(venta::setCliente);

        Optional<ProductoEntity> producto = productoRepository.findById(ventaDto.getIdProducto());
        producto.ifPresent(venta::setProducto);

        ventaRepository.save(venta);
    }

    public boolean actualizarVenta(Long id, VentaDto ventaDto) {
        Optional<VentaEntity> ventaExistente = ventaRepository.findById(id);
        if (ventaExistente.isPresent()) {
            VentaEntity venta = ventaExistente.get();
            venta.setCantidad(ventaDto.getCantidad());
            venta.setTotal(ventaDto.getTotal());
            venta.setFechaVenta(ventaDto.getFechaVenta());
            venta.setMetodoPago(ventaDto.getMetodoPago());

            Optional<ClienteEntity> cliente = clienteRepository.findById(ventaDto.getIdCliente());
            cliente.ifPresent(venta::setCliente);

            Optional<ProductoEntity> producto = productoRepository.findById(ventaDto.getIdProducto());
            producto.ifPresent(venta::setProducto);

            ventaRepository.save(venta);
            return true;
        }
        return false;
    }

    public boolean eliminarVenta(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
