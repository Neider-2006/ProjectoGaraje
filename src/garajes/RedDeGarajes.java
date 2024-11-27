
package garajes;


import excepciones.GarajeException;
import java.util.*;
import vehiculos.Vehiculo;

public class RedDeGarajes {

    private final List<Garaje> garajes;  // Lista de garajes

    // Constructor que inicializa la lista de garajes
    public RedDeGarajes() {
        this.garajes = new ArrayList<>();
    }

    // Método para crear un nuevo garaje
    public void crearGaraje(String id, String ubicacion, int numEspacios) throws GarajeException {
        // Verificar que no exista un garaje con el mismo ID
        for (Garaje garaje : garajes) {
            if (garaje.getId().equals(id)) {
                System.out.println("Ya existe un garaje con ese ID.");
                return;
            }
        }

        // Crear el nuevo garaje y añadirlo a la lista
        Garaje nuevoGaraje = new Garaje(id, ubicacion, numEspacios);
        garajes.add(nuevoGaraje);
        System.out.println("Garaje creado con éxito: " + id);
    }

    // Método para eliminar un garaje
    public void eliminarGaraje(String id) {
        Garaje garaje = obtenerGaraje(id);
        if (garaje != null) {
            garajes.remove(garaje);
            System.out.println("Garaje " + id + " eliminado con éxito.");
        } else {
            System.out.println("Garaje no encontrado.");
        }
    }

    // Método para obtener un garaje por su ID
    public Garaje obtenerGaraje(String id) {
        for (Garaje garaje : garajes) {
            if (garaje.getId().equals(id)) {
                return garaje;
            }
        }
        return null;  // Si no se encuentra el garaje
    }

    // Método para listar todos los garajes
    public void listarGarajes() {
        if (garajes.isEmpty()) {
            System.out.println("No hay garajes en la red.");
            return;
        }
        for (Garaje garaje : garajes) {
            System.out.println("ID: " + garaje.getId() + ", Ubicación: " + garaje.getUbicacion() + ", Espacios: " + garaje.getNumEspacios());
        }
    }

    // Método para alquilar un vehículo en un garaje
    public void alquilarVehiculo(String idGaraje, Vehiculo vehiculo) throws GarajeException {
        Garaje garaje = obtenerGaraje(idGaraje);
        if (garaje != null) {
            boolean exito = garaje.alquilarEspacio(vehiculo);
            if (exito) {
                System.out.println("Vehículo " + vehiculo.getPlaca() + " alquilado con éxito en el garaje " + idGaraje);
            } else {
                System.out.println("No se pudo alquilar el vehículo en el garaje " + idGaraje);
            }
        } else {
            System.out.println("Garaje no encontrado.");
        }
    }

    // Método para retirar un vehículo de un garaje
    public void retirarVehiculo(String idGaraje, String matricula) throws GarajeException {
        Garaje garaje = obtenerGaraje(idGaraje);
        if (garaje != null) {
            boolean exito = garaje.retirarVehiculo(matricula);
            if (exito) {
                System.out.println("Vehículo con matrícula " + matricula + " retirado del garaje " + idGaraje);
            } else {
                System.out.println("Vehículo no encontrado en el garaje " + idGaraje);
            }
        } else {
            System.out.println("Garaje no encontrado.");
        }
    }

    // Método para calcular los ingresos totales de todos los garajes
    public double calcularIngresosTotales() {
        double ingresosTotales = 0;
        for (Garaje garaje : garajes) {
            ingresosTotales += garaje.calcularIngresos();
        }
        return ingresosTotales;
    }

    // Método para obtener la ocupación de un tipo de vehículo en la red de garajes
    public int calcularOcupacionTotalPorTipoVehiculo(Class<? extends Vehiculo> tipo) {
        int ocupacionTotal = 0;
        for (Garaje garaje : garajes) {
            ocupacionTotal += garaje.calcularOcupacionPorTipoVehiculo(tipo);
        }
        return ocupacionTotal;
    }

    // Método para mostrar información sobre los vehículos en un garaje
    public void listarVehiculosEnGaraje(String idGaraje) {
        Garaje garaje = obtenerGaraje(idGaraje);
        if (garaje != null) {
            List<Vehiculo> vehiculos = garaje.getVehiculos();
            if (vehiculos.isEmpty()) {
                System.out.println("No hay vehículos en el garaje " + idGaraje);
                return;
            }
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println("Placa: " + vehiculo.getPlaca() + ", Marca: " + vehiculo.getMarca() + ", Precio: " + vehiculo.getPrecio());
            }
        } else {
            System.out.println("Garaje no encontrado.");
        }
    }

    public Iterable<Garaje> getGarajes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}