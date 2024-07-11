package com.ar.fotografia;

public class Foto {
    private Integer id;
    private String urlImagen;
    private String urlFotografo;
    private String nombreFotografo;
    private String urlOrigen;

    public Foto() {
    }

    public Foto(Integer id, String nombreFotografo, String urlFotografo, String urlImagen, String urlOrigen) {
        this.id = id;
        this.nombreFotografo = nombreFotografo;
        this.urlFotografo = urlFotografo;
        this.urlImagen = urlImagen;
        this.urlOrigen = urlOrigen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUrlFotografo() {
        return urlFotografo;
    }

    public void setUrlFotografo(String urlFotografo) {
        this.urlFotografo = urlFotografo;
    }

    public String getNombreFotografo() {
        return nombreFotografo;
    }

    public void setNombreFotografo(String nombreFotografo) {
        this.nombreFotografo = nombreFotografo;
    }

    public String getUrlOrigen() {
        return urlOrigen;
    }

    public void setUrlOrigen(String urlOrigen) {
        this.urlOrigen = urlOrigen;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Foto{");
        sb.append("id=").append(id);
        sb.append(", urlImagen=").append(urlImagen);
        sb.append(", urlFotografo=").append(urlFotografo);
        sb.append(", nombreFotografo=").append(nombreFotografo);
        sb.append(", urlOrigen=").append(urlOrigen);
        sb.append('}');
        return sb.toString();
    }



}
