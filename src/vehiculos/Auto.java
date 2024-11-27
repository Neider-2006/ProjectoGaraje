
package vehiculos;

public class Auto extends Vehiculo {
    private boolean tieneRadio;
    private boolean tieneNavegador;

    public Auto(String marca, double precio, int cilindraje, boolean tieneRadio, boolean tieneNavegador) {
        super(marca, precio, cilindraje);
        this.tieneRadio = tieneRadio;
        this.tieneNavegador = tieneNavegador;

        if (tieneRadio) {
            this.impuestoCirculacion += precio * 0.01; //aumenta 1%
        }
        if (tieneNavegador) {
            this.impuestoCirculacion += precio * 0.02; //2% si tiene navegador
        }
        if (cilindraje > 2499) {
            this.cuotaMesGaraje += cuotaMesGaraje * 0.20; // Aumenta 20%
        }
    }

    
}
