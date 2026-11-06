public class Lista {
    private Nodo primero;
    private Nodo ultimo;
    private int tamanio;

    public Lista() {
        primero = null;
        ultimo = null;
        tamanio = 0;
    }

    // Agrega al final: el nuevo nodo queda a la derecha del ultimo actual
    public void agregar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setDerecha(nuevo);
            nuevo.setIzquierda(ultimo);
            ultimo = nuevo;
        }
        tamanio++;
    }

    // Agrega al inicio: el nuevo nodo queda a la izquierda del primero actual.
    public void agregarAlInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.setDerecha(primero);
            primero.setIzquierda(nuevo);
            primero = nuevo;
        }
        tamanio++;
    }

    // Recorre de primero a ultimo siguiendo las referencias derecha.
    public void mostrarAdelante() {
        if (primero == null) {
            System.out.println("(lista vacia)");
            return;
        }
        Nodo actual = primero;
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato());
            actual = actual.getDerecha();
            i++;
        }
    }
}