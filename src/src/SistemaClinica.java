import java.util.Scanner;
import java.util.NoSuchElementException;

public class SistemaClinica {
    private final Lista listaGeneral = new Lista();
    private final Cola colaPendientes = new Cola();
    private final Pila historialProcesados = new Pila();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new SistemaClinica().ejecutar();
    }

    public void ejecutar() {
        int opcion = 0;
        do {
            mostrarMenu();
            try {
                opcion = leerEntero("Seleccione una opcion: ");
                switch (opcion) {
                    case 1: registrarPaciente(); break;
                    case 2: verRegistrados(); break;
                    case 3: verPendientes(); break;
                    case 4: procesarSiguiente(); break;
                    case 5: verHistorial(); break;
                    case 6: buscarPorDocumento(); break;
                    case 7: cancelarPendiente(); break;
                    case 8: deshacerProcesamiento(); break;
                    case 9: verCantidades(); break;
                    case 10: System.out.println("Hasta luego."); break;
                    default: System.out.println("Opcion invalida. Elija de 1 a 10.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Entrada finalizada. Saliendo.");
                opcion = 10;
            } catch (IllegalStateException | IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 10);
    }

    private void mostrarMenu() {
        System.out.println("***** CLINICA MEDICA *****");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Ver todos los pacientes registrados");
        System.out.println("3. Ver pacientes pendientes");
        System.out.println("4. Procesar siguiente paciente");
        System.out.println("5. Ver historial de pacientes procesados");
        System.out.println("6. Buscar paciente por documento");
        System.out.println("7. Cancelar paciente pendiente");
        System.out.println("8. Deshacer ultimo procesamiento");
        System.out.println("9. Ver cantidad de pacientes");
        System.out.println("10. Salir");
    }

    // Opcion 1
    private void registrarPaciente() {
        String documento = leerTextoNoVacio("Documento: ");
        if (!documento.matches("\\d+")) {
            System.out.println("Error: el documento debe contener solo numeros.");
            return;
        }
        Paciente nuevo = new Paciente(documento, "", "");
        if (listaGeneral.contiene(nuevo)) {      // usa equals() -> compara por documento
            System.out.println("Error: ya existe un paciente con ese documento.");
            return;
        }
        nuevo.setNombre(leerTextoNoVacio("Nombre: "));
        nuevo.setMotivoConsulta(leerTextoNoVacio("Motivo de consulta: "));
        listaGeneral.agregar(nuevo);
        colaPendientes.encolar(nuevo);
        System.out.println("Paciente registrado y enviado a la cola de pendientes.");
    }

    // Opciones 2 y 3
    private void verRegistrados() {
        System.out.println("*** Pacientes registrados (adelante) ***");
        listaGeneral.mostrarAdelante();
        if (!listaGeneral.esVacia()) {
            System.out.println("*** Pacientes registrados (atras) ***");
            listaGeneral.mostrarAtras();
        }
    }

    private void verPendientes() {
        System.out.println("*** Pacientes pendientes (orden de llegada) ***");
        colaPendientes.mostrar();
    }

    // Opcion 4
    private void procesarSiguiente() {
        if (colaPendientes.esVacia()) {
            System.out.println("No hay pacientes pendientes.");
            return;
        }
        Paciente procesado = (Paciente) colaPendientes.desencolar();
        procesado.setEstado(Paciente.ATENDIDO);
        historialProcesados.apilar(procesado);
        System.out.println("Paciente atendido: " + procesado);
    }

    // Opcion 5
    private void verHistorial() {
        System.out.println("*** Historial (el mas reciente primero) ***");
        historialProcesados.mostrar();
    }

    // Opcion 6
    private void buscarPorDocumento() {
        String documento = leerTextoNoVacio("Documento a buscar: ");
        int indice = listaGeneral.buscarDato(new Paciente(documento, "", ""));
        if (indice == -1) {
            System.out.println("No se encontro un paciente con ese documento.");
        } else {
            System.out.println("Encontrado en la posicion " + (indice + 1) + ": "
                    + listaGeneral.buscarDato(indice));
        }
    }

    // Opcion 7
    private void cancelarPendiente() {
        if (colaPendientes.esVacia()) {
            System.out.println("No hay pacientes pendientes para cancelar.");
            return;
        }
        String documento = leerTextoNoVacio("Documento del paciente a cancelar: ");
        Paciente buscado = new Paciente(documento, "", "");
        if (!colaPendientes.contiene(buscado)) {
            System.out.println("Ese paciente no esta en la cola de pendientes.");
            return;
        }
        Cola auxiliar = new Cola();
        Paciente cancelado = null;
        while (!colaPendientes.esVacia()) {
            Paciente actual = (Paciente) colaPendientes.desencolar();
            if (actual.equals(buscado)) {
                cancelado = actual;
            } else {
                auxiliar.encolar(actual);
            }
        }
        while (!auxiliar.esVacia()) {
            colaPendientes.encolar(auxiliar.desencolar());
        }
        cancelado.setEstado(Paciente.CANCELADO);
        System.out.println("Cancelado: " + cancelado);
    }

    // Opcion 8
    private void deshacerProcesamiento() {
        if (historialProcesados.esVacia()) {
            System.out.println("No hay procesamientos para deshacer.");
            return;
        }
        Paciente ultimo = (Paciente) historialProcesados.desapilar();
        ultimo.setEstado(Paciente.PENDIENTE);
        colaPendientes.encolar(ultimo);
        System.out.println("Procesamiento deshecho. Vuelve a pendientes: " + ultimo);
    }

    //Opcion 9
    private void verCantidades() {
        System.out.println("Registrados: " + listaGeneral.cuentaElementos());
        System.out.println("Pendientes:  " + colaPendientes.tamanio());
        System.out.println("Procesados:  " + historialProcesados.tamanio());
    }

    // Lectura de datos validada
    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String linea = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;   // cae en "opcion invalida" del menu
        }
    }

    private String leerTextoNoVacio(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) System.out.println("El campo no puede estar vacio.");
        } while (texto.isEmpty());
        return texto;
    }
}