public class Libro {
    
    String titulo;
    String autor;
    int paginas;

    String descripcion() {
        return titulo + " de " + autor + " (" + paginas + " paginas)";

    }

    public class AppLibro {
    public static void main(String[] args) {
        Libro libro = new Libro();
        libro.titulo = "Clean Code";
        libro.autor = "Robert Martin";
        libro.paginas = 464;
        System.out.println(libro.descripcion());
    }
}

    @Override
    public String toString() {
        return "Libro [titulo=" + titulo + ", autor=" + autor + ", paginas=" + paginas + ", descripcion()="
                + descripcion() + "]";
    }

}


