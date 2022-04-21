
package com.emergentes.modelo;

public class Libro {
    int id;
    String titulo;
    String autor;
    String disponible;
    String categoria;

    public Libro() {
        id=0;
        titulo="";
        autor="";
        disponible="";
        categoria="";
    }

    public Libro(int id, String titulo, String autor, String disponible, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
        }
    
    }
    
    
    
    
    
    

