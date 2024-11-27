
package Menu;

import java.util.Scanner;
import excepciones.GarajeException;
import garajes.Garaje;
import garajes.RedDeGarajes;
import vehiculos.*;
import java.util.*;

public class Menu {
    private Scanner scanner;
    private RedDeGarajes redDeGarajes;

    public Menu() {
        scanner = new Scanner(System.in);
        redDeGarajes = new RedDeGarajes();
    }

    public void mostrarMenu() throws GarajeException {
        while (true) {
            System.out.println("====== Menu Principal ======");
            System.out.println("1. Crear Garaje");
            System.out.println("2. Eliminar Garaje");
            System.out.println("3. Listar Garajes");
            System.out.println("4. Alquilar Vehiculo en un Garaje");
            System.out.println("5. Retirar Vehículo de un Garaje");
            System.out.println("6. Calcular Ingresos Totales");
            System.out.println("7. Calcular Ocupacion Total por Tipo de Vehiculo");
            System.out.println("8. Listar Vehiculos en un Garaje");
            System.out.println("9. Salir");

            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearGaraje();
                    break;
                case 2:
                    eliminarGaraje();
                    break;
                case 3:
                    listarGarajes();
                    break;
                case 4:
                    alquilarVehiculo();
                    break;
                case 5:
                    retirarVehiculo();
                    break;
                case 6:
                    calcularIngresosTotales();
                    break;
                case 7:
                    calcularOcupacionTotalPorTipoVehiculo();
                    break;
                case 8:
                    listarVehiculosEnGaraje();
                    break;
                case 9:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opcion invalida. Por favor, selecciona una opcion valida.");
            }
        }
    }

    private void crearGaraje() throws GarajeException {
        System.out.print("Introduce el ID del Garaje: ");
        String id = scanner.nextLine();

        System.out.print("Introduce la ubicacion del Garaje: ");
        String ubicacion = scanner.nextLine();

        System.out.print("Introduce el numero de espacios del Garaje: ");
        int numEspacios = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        redDeGarajes.crearGaraje(id, ubicacion, numEspacios);
    }

    private void eliminarGaraje() {
        System.out.print("Introduce el ID del Garaje a eliminar: ");
        String id = scanner.nextLine();

        redDeGarajes.eliminarGaraje(id);
    }

    private void listarGarajes() {
        redDeGarajes.listarGarajes();
    }

    private void alquilarVehiculo() {
        System.out.print("Introduce el ID del Garaje donde deseas alquilar el vehiculo: ");
        String idGaraje = scanner.nextLine();

        System.out.print("Introduce la placa del vehiculo a alquilar: ");
        String placa = scanner.nextLine();

        Vehiculo vehiculo = obtenerVehiculoPorPlaca(placa);  // Aquí necesitas definir un método que devuelva un vehículo por su placa

        try {
            redDeGarajes.alquilarVehiculo(idGaraje, vehiculo);
        } catch (GarajeException e) {
            System.out.println("Error al alquilar el vehiculo: " + e.getMessage());
        }
    }

    private void retirarVehiculo() {
        System.out.print("Introduce el ID del Garaje donde deseas retirar el vehiculo: ");
        String idGaraje = scanner.nextLine();

        System.out.print("Introduce la matrícula del vehiculo a retirar: ");
        String matricula = scanner.nextLine();

        try {
            redDeGarajes.retirarVehiculo(idGaraje, matricula);
        } catch (GarajeException e) {
            System.out.println("Error al retirar el vehiculo: " + e.getMessage());
        }
    }

    private void calcularIngresosTotales() {
        double ingresosTotales = redDeGarajes.calcularIngresosTotales();
        System.out.println("Ingresos totales de la red de garajes: " + ingresosTotales);
    }

    private void calcularOcupacionTotalPorTipoVehiculo() {
        System.out.print("Introduce el tipo de vehiculo (Moto, Camioneta, Auto): ");
        String tipo = scanner.nextLine();

        Class<? extends Vehiculo> tipoVehiculo = null;

        switch (tipo.toLowerCase()) {
            case "moto":
                tipoVehiculo = Moto.class;
                break;
            case "camioneta":
                tipoVehiculo = Camioneta.class;
                break;
            case "auto":
                tipoVehiculo = Auto.class;
                break;
            default:
                System.out.println("Tipo de vehículo no válido.");
                return;
        }

        int ocupacionTotal = redDeGarajes.calcularOcupacionTotalPorTipoVehiculo(tipoVehiculo);
        System.out.println("Ocupación total de " + tipo + "s en la red de garajes: " + ocupacionTotal);
    }

    private void listarVehiculosEnGaraje() {
        System.out.print("Introduce el ID del Garaje para listar los vehículos: ");
        String idGaraje = scanner.nextLine();

        redDeGarajes.listarVehiculosEnGaraje(idGaraje);
    }

    private Vehiculo obtenerVehiculoPorPlaca(String placa) {
        // Esta función debería buscar un vehículo en la red de garajes por su placa
        // Aquí necesitas realizar una búsqueda en los garajes
        for (Garaje garaje : redDeGarajes.getGarajes()) {
            for (Vehiculo vehiculo : garaje.getVehiculos()) {
                if (vehiculo.getPlaca().equals(placa)) {
                    return vehiculo;
                }
            }
        }
        System.out.println("Vehículo no encontrado.");
        return null;
    }
     public static void main(String[] args) throws GarajeException {
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}