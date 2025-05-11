package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.CarritoCompra;
import com.ecomarket.cl.ecomarket.model.Cliente;
import com.ecomarket.cl.ecomarket.model.Producto;  // Asegúrate de importar Producto correctamente
import com.ecomarket.cl.ecomarket.repository.CarritoCompraRepository;
import com.ecomarket.cl.ecomarket.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarritoCompraService {

    @Autowired
    private CarritoCompraRepository carritoCompraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los carritos
    public List<CarritoCompra> obtenerTodos() {
        return carritoCompraRepository.findAll();
    }

    // Obtener un carrito por RUT del cliente
    public Optional<CarritoCompra> obtenerPorId(String rut) {
        return carritoCompraRepository.findByClienteRut(rut);
    }

    // Guardar o actualizar un carrito
    public CarritoCompra guardar(CarritoCompra carrito) {
        return carritoCompraRepository.save(carrito);
    }

    // Eliminar un carrito
    public void eliminar(String rut) {
        carritoCompraRepository.deleteByClienteRut(rut);
    }

    // Agregar un producto al carrito
    public CarritoCompra agregarProducto(String clienteRut, Producto producto) {
        Optional<Cliente> clienteOpt = clienteRepository.findByRut(clienteRut);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            CarritoCompra carrito = cliente.getCarrito();

            if (carrito == null) {
                carrito = new CarritoCompra(cliente);  // Crear carrito si no existe
            }

            if (carrito.isActivo()) {
                carrito.getProductos().add(producto);  // Agregar producto al carrito
                return carritoCompraRepository.save(carrito);  // Guardar el carrito actualizado
            } else {
                System.out.println("El carrito no está activo.");
                return null;
            }
        }
        return null;  // Si el cliente no existe, retornar null
    }
}
