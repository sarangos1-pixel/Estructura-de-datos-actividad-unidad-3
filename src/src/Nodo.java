public class Nodo {
    private Object dato;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(Object dato) {
        this.dato = dato;
        this.izquierda = null;
        this.derecha = null;
    }

    public Object getDato() { return dato; }
    public void setDato(Object dato) { this.dato = dato; }

    public Nodo getIzquierda() { return izquierda; }
    public void setIzquierda(Nodo izquierda) { this.izquierda = izquierda; }

    public Nodo getDerecha() { return derecha; }
    public void setDerecha(Nodo derecha) { this.derecha = derecha; }
}