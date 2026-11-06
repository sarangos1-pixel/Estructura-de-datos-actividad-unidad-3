public class Paciente {
    public static final String PENDIENTE = "PENDIENTE";
    public static final String ATENDIDO = "ATENDIDO";
    public static final String CANCELADO = "CANCELADO";

    private String documento;
    private String nombre;
    private String motivoConsulta;
    private String estado;

    public Paciente(String documento, String nombre, String motivoConsulta) {
        this.documento = documento;
        this.nombre = nombre;
        this.motivoConsulta = motivoConsulta;
        this.estado = PENDIENTE;
    }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Paciente{documento='" + documento + "', nombre='" + nombre
                + "', motivo='" + motivoConsulta + "', estado=" + estado + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paciente otro = (Paciente) obj;
        return documento != null && documento.equals(otro.documento);
    }

    @Override
    public int hashCode() {
        return documento == null ? 0 : documento.hashCode();
    }
}