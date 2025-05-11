package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.Cliente;
import com.ecomarket.cl.ecomarket.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> listar() {
        return clienteService.obtenerTodos();
    }

    // Obtener cliente por rut
    @GetMapping("/{rut}")
    public Optional<Cliente> obtener(@PathVariable String rut) {
        return clienteService.obtenerPorRut(rut);
    }

    // Crear un nuevo cliente
    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    // Actualizar un cliente (por ejemplo, la dirección)
    @PutMapping("/{rut}")
    public Cliente actualizar(@PathVariable String rut, @RequestBody Cliente cliente) {
        cliente.setRut(rut); // le asignamos el RUT que viene en la URL
        return clienteService.guardar(cliente);
    }

    // Eliminar cliente por rut
    @DeleteMapping("/{rut}")
    public void eliminar(@PathVariable String rut) {
        clienteService.eliminar(rut);
    }

    // Actualizar dirección de envío de un cliente
    @PutMapping("/{rut}/direccion")
    public void actualizarDireccion(@PathVariable String rut, @RequestParam String nuevaDireccion) {
        clienteService.actualizarDireccion(rut, nuevaDireccion);
    }

    // Ver detalles del cliente
    @GetMapping("/{rut}/detalles")
    public void getDetallesCliente(@PathVariable String rut) {
        clienteService.getDetallesCliente(rut);
    }

    // Ver el carrito de un cliente
    @GetMapping("/{rut}/carrito")
    public void verCarrito(@PathVariable String rut) {
        clienteService.verCarrito(rut);
    }

    // Agregar un producto al carrito
    @PostMapping("/{rut}/carrito")
    public void agregarAlCarrito(@PathVariable String rut, @RequestParam String producto) {
        clienteService.agregarAlCarrito(rut, producto);
    }

    // Realizar un pedido
    @PostMapping("/{rut}/pedido")
    public void realizarPedido(@PathVariable String rut) {
        clienteService.realizarPedido(rut);
    }

    // Ver historial de compras de un cliente
    @GetMapping("/{rut}/historial")
    public void verHistorialCompras(@PathVariable String rut) {
        clienteService.verHistorialCompras(rut);
    }

    // Aplicar un cupón de descuento
    @PostMapping("/{rut}/cupon")
    public void aplicarCuponDescuento(@PathVariable String rut, @RequestParam String cupon) {
        clienteService.aplicarCuponDescuento(rut, cupon);
    }
}
