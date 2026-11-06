public class Pila {
    private Nodo tope;
    private int tamanio;

    public Pila() {
        tope = null;
        tamanio = 0;
    }

    //Coloca un elemento encima de la pila
    public void apilar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (tope != null) {
            nuevo.setDerecha(tope);   // el nuevo apunta al que estaba debajo
            tope.setIzquierda(nuevo);
        }
        tope = nuevo;
        tamanio++;
    }

    // Quita y devuelve el elemento del tope
    public Object desapilar() {
        if (tope == null) {
            throw new IllegalStateException("La pila esta vacia");
        }
        Object dato = tope.getDato();
        tope = tope.getDerecha();
        if (tope != null) {
            tope.setIzquierda(null);
        }
        tamanio--;
        return dato;
    }

    //Consulta el tope sin quitarlo
    public Object peek() {
        if (tope == null) {
            throw new IllegalStateException("La pila esta vacia");
        }
        return tope.getDato();
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    public boolean contiene(Object dato) {
        return buscar(dato) != -1;
    }

    public void limpiar() {
        tope = null;
        tamanio = 0;
    }

    //Muestra desde el tope (mas reciente) hasta el fondo
    public void mostrar() {
        if (tope == null) {
            System.out.println("(pila vacia)");
            return;
        }
        Nodo actual = tope;
        while (actual != null) {
            System.out.println("- " + actual.getDato());
            actual = actual.getDerecha();
        }
    }

    //
    public int buscar(Object dato) {
        Nodo actual = tope;
        int posicion = 1;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return posicion;
            actual = actual.getDerecha();
            posicion++;
        }
        return -1;
    }
}