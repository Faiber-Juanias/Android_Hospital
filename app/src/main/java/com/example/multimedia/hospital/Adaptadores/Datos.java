package com.example.multimedia.hospital.Adaptadores;

public class Datos {
    private String fechaHora;
    private String nombre;
    private String comentario;
    private String coordanada;

    public Datos(String fechaHora, String nombre, String comentario, String coordanada) {
        this.fechaHora = fechaHora;
        this.nombre = nombre;
        this.comentario = comentario;
        this.coordanada = coordanada;
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

    public String getCoordanada() {
        return coordanada;
    }
}
