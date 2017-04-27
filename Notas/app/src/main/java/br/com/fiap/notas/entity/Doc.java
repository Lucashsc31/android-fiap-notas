package br.com.fiap.notas.entity;

public class Doc {

    private String _id;
    private String _rev;
    private String titulo;
    private String conteudo;
    private String assunto;

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "_id='" + _id + '\'' +
                ", _rev='" + _rev + '\'' +
                ", titulo='" + titulo + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", assunto='" + assunto + '\'' +
                '}';
    }
}