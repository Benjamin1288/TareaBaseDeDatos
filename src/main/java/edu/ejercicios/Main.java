package edu.ejercicios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String ruta = "jdbc:mysql://localhost/base";
    private static final String usuario= "root";
    private static final String contraseña = "melamuerdes";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(ruta, usuario, contraseña);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Menú:");
                System.out.println("1. Consultar información");
                System.out.println("2. Agregar persona");
                System.out.println("3. Actualizar información");
                System.out.println("4. Eliminar persona");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        consultarInformacion(connection);
                        break;
                    case 2:
                        agregarPersona(connection, scanner);
                        break;
                    case 3:
                        actualizarInformacion(connection, scanner);
                        break;
                    case 4:
                        eliminarPersona(connection, scanner);
                        break;
                    case 5:
                        connection.close();
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void consultarInformacion(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código de la persona: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        String consulta = "SELECT * FROM persona WHERE codigo = ?";
        PreparedStatement statement = connection.prepareStatement(consulta);
        statement.setInt(1, codigo);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            System.out.println("Información de la persona:");
            System.out.println("Código: " + resultSet.getInt("codigo"));
            System.out.println("Nombre y Apellido: " + resultSet.getString("nombre_apellido"));
            System.out.println("Fecha de Registro: " + resultSet.getString("fecha_registro"));
            System.out.println("Sueldo Base: " + resultSet.getDouble("sueldo_base"));
            System.out.println("Sexo: " + resultSet.getString("sexo"));
            System.out.println("Edad: " + resultSet.getInt("edad"));
            System.out.println("Longitud: " + resultSet.getDouble("longitud"));
            System.out.println("Latitud: " + resultSet.getDouble("latitud"));
            System.out.println("Comentarios: " + resultSet.getString("comentarios"));
        } else {
            System.out.println("No se encontró la persona con ese código.");
        }
    }

    private static void agregarPersona(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Ingrese los datos de la persona:");
        System.out.println("Codigo: ");
        int codigo=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre y Apellido: ");
        String nombreApellido = scanner.nextLine();
        System.out.print("Fecha de Registro: ");
        String fechaRegistro = scanner.nextLine();
        System.out.print("Sueldo Base: ");
        double sueldoBase = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Longitud: ");
        double longitud = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Latitud: ");
        double latitud = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Comentarios: ");
        String comentarios = scanner.nextLine();

        String insercion = "INSERT INTO persona (codigo,nombre_apellido, fecha_registro, sueldo_base, sexo, edad, longitud, latitud, comentarios) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insercion);

        statement.setInt(1, codigo);
        statement.setString(2, nombreApellido);
        statement.setString(3, fechaRegistro);
        statement.setDouble(4, sueldoBase);
        statement.setString(5, sexo);
        statement.setInt(6, edad);
        statement.setDouble(7, longitud);
        statement.setDouble(8, latitud);
        statement.setString(9, comentarios);

        int filasAfectadas = statement.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Persona agregada con éxito.");
        } else {
            System.out.println("Error al agregar la persona.");
        }

    }

    private static void actualizarInformacion(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el código de la persona que desea actualizar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        String consulta = "SELECT * FROM persona WHERE codigo = ?";
        PreparedStatement consultaStatement = connection.prepareStatement(consulta);
        consultaStatement.setInt(1, codigo);

        ResultSet resultSet = consultaStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println("Información actual:");
            System.out.println("Código: " + resultSet.getInt("codigo"));
            System.out.println("Nombre y Apellido: " + resultSet.getString("nombre_apellido"));
            System.out.println("Fecha de Registro: " + resultSet.getString("fecha_registro"));
            System.out.println("Sueldo Base: " + resultSet.getDouble("sueldo_base"));
            System.out.println("Sexo: " + resultSet.getString("sexo"));
            System.out.println("Edad: " + resultSet.getInt("edad"));
            System.out.println("Longitud: " + resultSet.getDouble("longitud"));
            System.out.println("Latitud: " + resultSet.getDouble("latitud"));
            System.out.println("Comentarios: " + resultSet.getString("comentarios"));

            System.out.println("\nIngrese los nuevos datos:");
            System.out.print("Nombre y Apellido: ");
            String nombreApellido = scanner.nextLine();
            System.out.print("Fecha de Registro (AAAA-MM-DD): ");
            String fechaRegistro = scanner.nextLine();
            System.out.print("Sueldo Base: ");
            double sueldoBase = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Sexo (M/F): ");
            String sexo = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Longitud: ");
            double longitud = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Latitud: ");
            double latitud = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Comentarios: ");
            String comentarios = scanner.nextLine();

            String actualizacion = "UPDATE persona SET nombre_apellido = ?, fecha_registro = ?, sueldo_base = ?, sexo = ?, edad = ?, longitud = ?, latitud = ?, comentarios = ? WHERE codigo = ?";
            PreparedStatement actualizacionStatement = connection.prepareStatement(actualizacion);
            actualizacionStatement.setString(1, nombreApellido);
            actualizacionStatement.setString(2, fechaRegistro);
            actualizacionStatement.setDouble(3, sueldoBase);
            actualizacionStatement.setString(4, sexo);
            actualizacionStatement.setInt(5, edad);
            actualizacionStatement.setDouble(6, longitud);
            actualizacionStatement.setDouble(7, latitud);
            actualizacionStatement.setString(8, comentarios);
            actualizacionStatement.setInt(9, codigo);

            int filasAfectadas = actualizacionStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Información actualizada con éxito.");
            } else {
                System.out.println("Error al actualizar la información.");
            }
        } else {
            System.out.println("No se encontró la persona con ese código.");
        }

    }

    private static void eliminarPersona(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el código de la persona que desea eliminar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        String consulta = "SELECT * FROM persona WHERE codigo = ?";
        PreparedStatement consultaStatement = connection.prepareStatement(consulta);
        consultaStatement.setInt(1, codigo);

        ResultSet resultSet = consultaStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println("¿Está seguro de eliminar a " + resultSet.getString("nombre_apellido") + "? (S/N): ");
            String confirmacion = scanner.nextLine().trim().toUpperCase();

            if (confirmacion.equals("S")) {
                String eliminacion = "DELETE FROM persona WHERE codigo = ?";
                PreparedStatement eliminacionStatement = connection.prepareStatement(eliminacion);
                eliminacionStatement.setInt(1, codigo);

                int filasAfectadas = eliminacionStatement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Persona eliminada con éxito.");
                } else {
                    System.out.println("Error al eliminar la persona.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("No se encontró la persona con ese código.");
        }

    }

}