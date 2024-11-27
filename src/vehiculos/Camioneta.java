
package vehiculos;

public class Camioneta extends Vehiculo {
    public enum TipoServicio {
        SUV, PICKUP, CARGA, OTRO
    }

    private TipoServicio tipoServicio;
    private int numeroPasajeros;
    private boolean tieneRemolque;

    public Camioneta(String marca, double precio, int cilindraje, TipoServicio tipoServicio, int numeroPasajeros, boolean tieneRemolque) {
        super(marca, precio, cilindraje);
        this.tipoServicio = tipoServicio;
        this.tieneRemolque = tieneRemolque;

        // Validación del número de pasajeros
        if (tipoServicio == TipoServicio.PICKUP || tipoServicio == TipoServicio.CARGA) {
            if (numeroPasajeros > 2) {
                throw new IllegalArgumentException("El número máximo de pasajeros para Pickup y Carga es 2.");
            }
        } else {
            if (numeroPasajeros > 5) {
                throw new IllegalArgumentException("El número máximo de pasajeros para SUV y Otro es 5.");
            }
        }
        this.numeroPasajeros = numeroPasajeros;

        calcularImpuestoCirculacion();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        this.impuestoCirculacion = 0.05 * precio; // 5% del precio
    }

    @Override
    public double getCuotaMesGaraje() {
        double cuota = super.getCuotaMesGaraje();
        if (tipoServicio == TipoServicio.PICKUP || tipoServicio == TipoServicio.CARGA || tipoServicio == TipoServicio.OTRO) {
            cuota *= 1.45; // Aumenta 45%
        } else if (tipoServicio == TipoServicio.SUV) {
            cuota *= 1.10; // Aumenta 10%
        }

        if (numeroPasajeros <= 2) {
            cuota *= 1.50; // Aumenta 50% si tiene 2 pasajeros
        } else {
            cuota *= 1.60; // Aumenta 60% si tiene más de 2 pasajeros
        }

        if (tieneRemolque) {
            cuota *= 1.10; // Aumenta 10% si tiene remolque
        }

        return cuota;
    }
}
