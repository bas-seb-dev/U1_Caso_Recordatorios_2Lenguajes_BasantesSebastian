// Clase minima orientada a objetos
public class Recordatorio {
    private String titulo;
    private String fecha;
    private int prioridad;
    private boolean completado;

    public Recordatorio(String titulo, String fecha, int prioridad) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.completado = false;
    }

    public String getTitulo() { return titulo; }
    public String getFecha() { return fecha; }
    public int getPrioridad() { return prioridad; }
    public boolean isCompletado() { return completado; }

    public void completar() { this.completado = true; }

    @Override
    public String toString() {
        String estado = completado ? "[X]" : "[ ]";
        return estado + " " + titulo + " | " + fecha + " | prioridad " + prioridad;
    }
}
