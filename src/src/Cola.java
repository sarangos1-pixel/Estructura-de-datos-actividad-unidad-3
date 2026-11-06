public class Cola {
    private Nodo frente;
    private Nodo ultimo;
    private int tamanio;

    public Cola() {
        frente = null;
        ultimo = null;
        tamanio = 0;
    }

    //Agrega al final de la cola
    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (frente == null) {
            frente = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setDerecha(nuevo);
            nuevo.setIzquierda(ultimo);
            ultimo = nuevo;
        }
        tamanio++;
    }

    // Saca y devuelve el elemento del frente
    public Object desencolar() {
        if (frente == null) {
            throw new IllegalStateException("La cola esta vacia");
        }
        Object dato = frente.getDato();
        frente = frente.getDerecha();
        if (frente == null) {
            ultimo = null;
        } else {
            frente.setIzquierda(null);
        }
        tamanio--;
        return dato;
    }

    //Consulta el frente sin sacarlo
    public Object peek() {
        if (frente == null) {
            throw new IllegalStateException("La cola esta vacia");
        }
        return frente.getDato();
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    public boolean contiene(Object dato) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return true;
            actual = actual.getDerecha();
        }
        return false;
    }

    public void limpiar() {
        frente = null;
        ultimo = null;
        tamanio = 0;
    }

    // Muestra desde el frente (siguiente a atender) hasta el final
    public void mostrar() {
        if (frente == null) {
            System.out.println("(cola vacia)");
            return;
        }
        Nodo actual = frente;
        int turno = 1;
        while (actual != null) {
            System.out.println("Turno " + turno + ": " + actual.getDato());
            actual = actual.getDerecha();
            turno++;
        }
    }
}