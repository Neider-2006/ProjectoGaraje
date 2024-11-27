package garajes;

import vehiculos.Vehiculo;

public interface iGarage {
    double calcularIngresos();
    int calcularOcupacionPorTipoVehiculo(Class<? extends Vehiculo> tipo);
}