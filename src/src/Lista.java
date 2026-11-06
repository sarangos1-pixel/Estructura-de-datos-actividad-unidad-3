public class Lista {
    private Nodo primero;
    private Nodo ultimo;
    private int tamanio;

    public Lista() {
        primero = null;
        ultimo = null;
        tamanio = 0;
    }

    //Agrega al final: el nuevo nodo queda a la derecha del ultimo actual. */
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

    //Inserta en la posicion indicada (0..tamanio). Reenlaza 4 referencias.
    public void agregarEnPosicion(int indice, Object dato) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }
        if (indice == tamanio) {
            agregar(dato);
            return;
        }
        Nodo actual = nodoEn(indice);     // nodo que quedara a la derecha del nuevo
        Nodo anterior = actual.getIzquierda();
        Nodo nuevo = new Nodo(dato);
        nuevo.setIzquierda(anterior);
        nuevo.setDerecha(actual);
        anterior.setDerecha(nuevo);
        actual.setIzquierda(nuevo);
        tamanio++;
    }

    public Object eliminarPrimero() {
        if (primero == null) {
            throw new IllegalStateException("La lista esta vacia");
        }
        Object dato = primero.getDato();
        if (primero == ultimo) {
            primero = null;
            ultimo = null;
        } else {
            primero = primero.getDerecha();
            primero.setIzquierda(null);
        }
        tamanio--;
        return dato;
    }

    public Object eliminarUltimo() {
        if (ultimo == null) {
            throw new IllegalStateException("La lista esta vacia");
        }
        Object dato = ultimo.getDato();
        if (primero == ultimo) {
            primero = null;
            ultimo = null;
        } else {
            ultimo = ultimo.getIzquierda();
            ultimo.setDerecha(null);
        }
        tamanio--;
        return dato;
    }

    public Object eliminarEnPosicion(int indice) {
        validarIndice(indice);
        if (indice == 0) return eliminarPrimero();
        if (indice == tamanio - 1) return eliminarUltimo();
        Nodo actual = nodoEn(indice);
        actual.getIzquierda().setDerecha(actual.getDerecha());
        actual.getDerecha().setIzquierda(actual.getIzquierda());
        tamanio--;
        return actual.getDato();
    }

    // Devuelve el dato que esta en el indice dado.
    public Object buscarDato(int indice) {
        validarIndice(indice);
        return nodoEn(indice).getDato();
    }

    // Devuelve el indice del dato (usando equals) o -1 si no existe.
    public int buscarDato(Object dato) {
        Nodo actual = primero;
        int i = 0;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return i;
            actual = actual.getDerecha();
            i++;
        }
        return -1;
    }

    public boolean contiene(Object dato) {
        return buscarDato(dato) != -1;
    }

    public int cuentaElementos() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    public void limpiar() {
        primero = null;
        ultimo = null;
        tamanio = 0;
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

    // Recorre de ultimo a primero siguiendo las referencias izquierda.
    public void mostrarAtras() {
        if (ultimo == null) {
            System.out.println("(lista vacia)");
            return;
        }
        Nodo actual = ultimo;
        int i = tamanio;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato());
            actual = actual.getIzquierda();
            i--;
        }
    }



    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
    }

    //Llega al nodo del indice recorriendo desde el extremo mas cercano.
    private Nodo nodoEn(int indice) {
        Nodo actual;
        if (indice < tamanio / 2) {
            actual = primero;
            for (int i = 0; i < indice; i++) actual = actual.getDerecha();
        } else {
            actual = ultimo;
            for (int i = tamanio - 1; i > indice; i--) actual = actual.getIzquierda();
        }
        return actual;
    }
}