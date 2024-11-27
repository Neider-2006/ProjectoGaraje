
package vehiculos;


public abstract class Vehiculo {
    protected String placa;
    protected String marca;
    protected double precio;
    protected int cilindraje;
    protected double impuestoCirculacion;
    protected double cuotaMesGaraje;

    private static final double CUOTA_BASE = 100.0;

    // Constructor
    public Vehiculo(String marca, double precio, int cilindraje) {
        this.placa = null; // Por defecto, no tiene matrícula
        this.marca = marca;
        this.precio = precio;
        this.cilindraje = cilindraje;
        this.cuotaMesGaraje = CUOTA_BASE;
        calcularImpuestoCirculacion();
    }

    // Métodos para calcular impuestos
    public void calcularImpuestoCirculacion() {
        this.impuestoCirculacion = precio * 0.02;
    }

    // Método para matricular el vehículo
    public boolean matricular(String matricula) {
        if (matricula != null && matricula.length() == 6) {
            this.placa = matricula;
            return true;
        }
        return false;
    }

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
            calcularImpuestoCirculacion(); // Recalcular el impuesto si cambia el precio
        } else {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        if (cilindraje > 0) {
            this.cilindraje = cilindraje;
        } else {
            throw new IllegalArgumentException("El cilindraje debe ser mayor a 0.");
        }
    }

    public double getImpuestoCirculacion() {
        return impuestoCirculacion;
    }

    public double getCuotaMesGaraje() {
        return cuotaMesGaraje;
    }

    public void setCuotaMesGaraje(double cuotaMesGaraje) {
        if (cuotaMesGaraje >= 0) {
            this.cuotaMesGaraje = cuotaMesGaraje;
        } else {
            throw new IllegalArgumentException("La cuota mensual del garaje no puede ser negativa.");
        }
    }
}