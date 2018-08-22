package com.example.multimedia.hospital.Adaptadores;

public class Datos {
    private String entrada;
    private String fechaHora;
    private String nombre;
    private String comentario;
    private String coordenada;

    public Datos(String entrada, String fechaHora, String nombre, String comentario, String coordenada) {
        this.entrada = entrada;
        this.fechaHora = fechaHora;
        this.nombre = nombre;
        this.comentario = comentario;
        this.coordenada = coordenada;
    }

    public String getEntrada() {
        return entrada;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getNombre() {
        return nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public String getcoordenada() {
        return coordenada;
    }
}
