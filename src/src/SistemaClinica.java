import java.util.Scanner;

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
            opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1: registrarPaciente(); break;
                case 2: verRegistrados(); break;
                case 3: verPendientes(); break;
                case 10: System.out.println("Hasta luego."); break;
                default: System.out.println("Opcion invalida o aun no disponible.");
            }
        } while (opcion != 10);
    }

    private void mostrarMenu() {
        System.out.println("***** Registrar paciente*****");
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
        String documento = leerTexto("Documento: ");
        String nombre = leerTexto("Nombre: ");
        String motivo = leerTexto("Motivo de consulta: ");
        Paciente nuevo = new Paciente(documento, nombre, motivo);
        listaGeneral.agregar(nuevo);
        colaPendientes.encolar(nuevo);
        System.out.println("Paciente registrado y enviado a la cola de pendientes.");
    }

    // Opciones 2 y 3
    private void verRegistrados() {
        System.out.println("***Pacientes registrados (adelante) ***");
        listaGeneral.mostrarAdelante();
        if (!listaGeneral.esVacia()) {
            System.out.println("***Pacientes registrados (atras) ***");
            listaGeneral.mostrarAtras();
        }
    }

    private void verPendientes() {
        System.out.println("***Pacientes pendientes (orden de llegada) ***");
        colaPendientes.mostrar();
    }

    //  Lectura de datos
    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String linea = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;   // cae en "opcion invalida" del menu
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}

