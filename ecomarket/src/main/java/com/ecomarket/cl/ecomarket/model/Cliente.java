package com.ecomarket.cl.ecomarket.model;

import jakarta.persistence.*;

@Entity
public class Cliente extends Usuario {

    private String direccionEnvio;
    private boolean carritoActivo;
    private boolean cuponDescuentoAplicado;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private CarritoCompra carrito;

    public Cliente() {}

    public Cliente(String rut, String nombre, String correo, String direccion, String telefono, 
                   String direccionEnvio, boolean carritoActivo, boolean cuponDescuentoAplicado) {
        super(rut, nombre, correo, direccion, telefono);
        this.direccionEnvio = direccionEnvio;
        this.carritoActivo = carritoActivo;
        this.cuponDescuentoAplicado = cuponDescuentoAplicado;
    }

    // Getters and Setters

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public boolean isCarritoActivo() {
        return carritoActivo;
    }

    public void setCarritoActivo(boolean carritoActivo) {
        this.carritoActivo = carritoActivo;
    }

    public boolean isCuponDescuentoAplicado() {
        return cuponDescuentoAplicado;
    }

    public void setCuponDescuentoAplicado(boolean cuponDescuentoAplicado) {
        this.cuponDescuentoAplicado = cuponDescuentoAplicado;
    }

    public CarritoCompra getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoCompra carrito) {
        this.carrito = carrito;
    }

    // Métodos adicionales

    public void getDetallesCliente() {
        System.out.println("Detalles del Cliente: " + getNombre() + ", " + getCorreo() + ", " + getTelefono() + ", Dirección: " + getDireccion());
    }

    public void actualizarDireccion(String nuevaDireccion) {
        setDireccionEnvio(nuevaDireccion);
        System.out.println("Dirección de envío actualizada a: " + nuevaDireccion);
    }

    public void verCarrito() {
        if (carrito != null && carrito.isActivo()) {
            System.out.println("El carrito está activo. Verificando los productos...");
        } else {
            System.out.println("No hay productos en el carrito.");
        }
    }

    public void agregarAlCarrito(String producto) {
        if (carrito == null) {
            carrito = new CarritoCompra(this);  // Crea un carrito nuevo si no existe
        }
        System.out.println("Producto '" + producto + "' agregado al carrito.");
    }

    public void realizarPedido() {
        if (carrito != null && carrito.isActivo()) {
            System.out.println("Pedido realizado exitosamente.");
            carrito.setActivo(false);  // Finaliza el carrito después de realizar el pedido
        } else {
            System.out.println("No hay productos en el carrito.");
        }
    }

    public void verHistorialCompras() {
        System.out.println("Mostrando historial de compras...");
    }

    public void aplicarCuponDescuento(String cupon) {
        if (cuponDescuentoAplicado) {
            System.out.println("El cupón ya ha sido aplicado.");
        } else {
            setCuponDescuentoAplicado(true);
            System.out.println("Cupón de descuento aplicado: " + cupon);
        }
    }
}
