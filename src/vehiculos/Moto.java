
package vehiculos;


public class Moto extends Vehiculo {
    private boolean tieneSidecar;

    public Moto(String marca, double precio, int cilindraje, boolean tieneSidecar) {
        super(marca, precio, cilindraje);
        this.tieneSidecar = tieneSidecar;
        if (tieneSidecar) {
             this.impuestoCirculacion += precio * 0.10; // Aumenta 10% 
            this.cuotaMesGaraje += cuotaMesGaraje * 0.50; // Aumenta 50%
        }
    }
}