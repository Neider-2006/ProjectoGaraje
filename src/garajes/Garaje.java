package garajes;


import excepciones.GarajeException;
import vehiculos.Vehiculo;

import java.util.ArrayList;

public class Garaje implements iGarage {
    private int numEspacios; // Número total de espacios en el garaje
    private ArrayList<Vehiculo> vehiculos;

   
    public Garaje(int numEspacios) {
        if (numEspacios <= 0) {
            throw new IllegalArgumentException("El número de espacios debe ser mayor que 0.");
        }
        this.numEspacios = numEspacios;
        this.vehiculos = new ArrayList<>(numEspacios);
    }

    Garaje(String id, String ubicacion, int numEspacios) throws GarajeException {
       
    }

    public boolean alquilarEspacio(Vehiculo v) throws GarajeException {
        if (vehiculos.size() >= numEspacios) {
            throw new GarajeException("No hay espacios disponibles en el garaje.");
        }
        if (v.getPlaca() == null) {
            throw new GarajeException("El vehículo no tiene matrícula y no puede ser alquilado.");
        }

        vehiculos.add(v);
        return true;
    }

    public int buscarVehiculo(String matricula) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPlaca() != null && vehiculos.get(i).getPlaca().equals(matricula)) {
                return i;
            }
        }
        return -99; // Si no se encuentra, retorna -99
    }

    public int contarVehiculosPorTipo(Class<? extends Vehiculo> tipo) {
        int count = 0;
        for (Vehiculo vehiculo : vehiculos) {
            if (tipo.isInstance(vehiculo)) {
                count++;
            }
        }
        return count;
    }

    public boolean retirarVehiculo(String matricula) throws GarajeException {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca() != null && v.getPlaca().equals(matricula)) {
                vehiculos.remove(v);
                return true;
            }
        }
        throw new GarajeException("El vehículo con matrícula " + matricula + " no se encuentra en el garaje.");
    }

    @Override
    public double calcularIngresos() {
        double total = 0;
        for (Vehiculo v : vehiculos) {
            total += v.getCuotaMesGaraje();
        }
        return total;
    }

    @Override
    public int calcularOcupacionPorTipoVehiculo(Class<? extends Vehiculo> tipoVehiculo) {
        return contarVehiculosPorTipo(tipoVehiculo);
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public int getNumEspacios() {
        return numEspacios;
    }

    Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    String getUbicacion() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}