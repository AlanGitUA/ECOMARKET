package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.Cliente;
import com.ecomarket.cl.ecomarket.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    // Obtener cliente por rut
    public Optional<Cliente> obtenerPorRut(String rut) {
        return clienteRepository.findById(rut);
    }

    // Crear o actualizar cliente
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Eliminar cliente por rut
    public void eliminar(String rut) {
        clienteRepository.deleteById(rut);
    }

    // Método para ver los detalles del cliente
    public void getDetallesCliente(String rut) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(rut);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            System.out.println("Detalles del Cliente: " + cliente.getNombre() + ", " 
                + cliente.getCorreo() + ", " + cliente.getTelefono() 
                + ", Dirección: " + cliente.getDireccion());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Actualizar la dirección de envío del cliente
    public void actualizarDireccion(String rut, String nuevaDireccion) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(rut);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setDireccionEnvio(nuevaDireccion);
            clienteRepository.save(cliente);
            System.out.println("Dirección de envío actualizada a: " + nuevaDireccion);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Ver los productos en el carrito del cliente
    public void verCarrito(String rut) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(rut);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            if (cliente.isCarritoActivo()) {
                System.out.println("El carrito está activo. Verificando los productos...");
            } else {
                System.out.println("No hay productos en el carrito.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Agregar un producto al carrito
    public void agregarAlCarrito(String rut, String producto) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(rut);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            if (!cliente.isCarritoActivo()) {
                cliente.setCarritoActivo(true);
            }
            System.out.println("Producto '" + producto + "' agregado al carrito.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Realizar un pedido
    public void realizarPedido(String rut) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(rut);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            if (cliente.isCarritoActivo()) {
                System.out.println("Pedido realizado exitosamente.");
                cliente.setCarritoActivo(false);
                clienteRepository.save(cliente);
            } else {
                System.out.println("No hay productos en el carrito.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Ver historial de compras (puede integrarse con otra base de datos en una futura mejora)
    public void verHistorialCompras(String rut) {
        System.out.println("Mostrando historial de compras de cliente con rut: " + rut);
        // Aquí agregarías la lógica para recuperar el historial de compras de una base de datos
    }

    // Aplicar un cupón de descuento
    public void aplicarCuponDescuento(String rut, String cupon) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(rut);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            if (cliente.isCuponDescuentoAplicado()) {
                System.out.println("El cupón ya ha sido aplicado.");
            } else {
                cliente.setCuponDescuentoAplicado(true);
                clienteRepository.save(cliente);
                System.out.println("Cupón de descuento aplicado: " + cupon);
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
}
