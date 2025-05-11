package com.ecomarket.cl.ecomarket.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class CarritoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "carrito_producto",
        joinColumns = @JoinColumn(name = "carrito_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private boolean activo;

    public CarritoCompra() {}

    public CarritoCompra(Cliente cliente) {
        this.cliente = cliente;
        this.activo = true;  // Un carrito recién creado está activo
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
