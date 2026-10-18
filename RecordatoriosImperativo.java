// Gestor de recordatorios - version imperativa (Java)
// Compilar: javac RecordatoriosImperativo.java   Ejecutar: java RecordatoriosImperativo
import java.util.ArrayList;
import java.util.Scanner;

public class RecordatoriosImperativo {

    static ArrayList<String> titulos = new ArrayList<>();
    static ArrayList<String> fechas = new ArrayList<>();
    static ArrayList<Integer> prioridades = new ArrayList<>();
    static ArrayList<Boolean> completados = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = sc.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("  Error: el campo no puede estar vacio.");
        }
    }

    static int leerPrioridad() {
        while (true) {
            System.out.print("Prioridad (1=alta, 2=media, 3=baja): ");
            String texto = sc.nextLine().trim();
            try {
                int p = Integer.parseInt(texto);
                if (p >= 1 && p <= 3) {
                    return p;
                }
            } catch (NumberFormatException e) {
                // cae al mensaje de error
            }
            System.out.println("  Error: la prioridad debe ser un numero entre 1 y 3.");
        }
    }

    static void registrar() {
        String titulo = leerTextoNoVacio("Titulo: ");
        String fecha = leerTextoNoVacio("Fecha (formato libre): ");
        int prioridad = leerPrioridad();
        titulos.add(titulo);
        fechas.add(fecha);
        prioridades.add(prioridad);
        completados.add(false);
        System.out.println("  Recordatorio registrado.");
    }

    static void listar() {
        if (titulos.isEmpty()) {
            System.out.println("  No hay recordatorios registrados.");
            return;
        }
        for (int i = 0; i < titulos.size(); i++) {
            String estado = completados.get(i) ? "[X]" : "[ ]";
            System.out.println("  " + (i + 1) + ". " + estado + " " + titulos.get(i)
                    + " | " + fechas.get(i) + " | prioridad " + prioridades.get(i));
        }
    }

    static void completar() {
        if (titulos.isEmpty()) {
            System.out.println("  No hay recordatorios para completar.");
            return;
        }
        listar();
        System.out.print("Numero del recordatorio a completar: ");
        String texto = sc.nextLine().trim();
        int n;
        try {
            n = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            System.out.println("  Error: numero invalido.");
            return;
        }
        if (n < 1 || n > titulos.size()) {
            System.out.println("  Error: numero invalido.");
            return;
        }
        if (completados.get(n - 1)) {
            System.out.println("  Ese recordatorio ya estaba completado.");
        } else {
            completados.set(n - 1, true);
            System.out.println("  Marcado como completado.");
        }
    }

    static void mostrarProximo() {
        String fecha = leerTextoNoVacio("Fecha a consultar (igual a como la registraste): ");
        int mejor = -1;
        for (int i = 0; i < titulos.size(); i++) {
            if (!completados.get(i) && fechas.get(i).equalsIgnoreCase(fecha)) {
                if (mejor == -1 || prioridades.get(i) < prioridades.get(mejor)) {
                    mejor = i;
                }
            }
        }
        if (mejor == -1) {
            System.out.println("  No hay recordatorios pendientes para esa fecha.");
        } else {
            System.out.println("  Proximo: " + titulos.get(mejor) + " | " + fechas.get(mejor)
                    + " | prioridad " + prioridades.get(mejor));
        }
    }

    static void mostrarMenu() {
        System.out.println("\n=== GESTOR DE RECORDATORIOS ===");
        System.out.println("1. Registrar recordatorio");
        System.out.println("2. Listar recordatorios");
        System.out.println("3. Marcar como completado");
        System.out.println("4. Mostrar proximo recordatorio");
        System.out.println("5. Salir");
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            System.out.print("Elige una opcion: ");
            if (!sc.hasNextLine()) {
                break;
            }
            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1": registrar(); break;
                case "2": listar(); break;
                case "3": completar(); break;
                case "4": mostrarProximo(); break;
                case "5":
                    System.out.println("Hasta luego.");
                    salir = true;
                    break;
                default:
                    System.out.println("  Opcion invalida. Elige un numero del 1 al 5.");
            }
        }
    }
}
