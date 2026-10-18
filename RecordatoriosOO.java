// Gestor de recordatorios - version OO minima (Java)
// Compilar: javac Recordatorio.java RecordatoriosOO.java   Ejecutar: java RecordatoriosOO
import java.util.ArrayList;
import java.util.Scanner;

public class RecordatoriosOO {

    static ArrayList<Recordatorio> lista = new ArrayList<>();
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
            try {
                int p = Integer.parseInt(sc.nextLine().trim());
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
        lista.add(new Recordatorio(titulo, fecha, prioridad));
        System.out.println("  Recordatorio registrado.");
    }

    static void listar() {
        if (lista.isEmpty()) {
            System.out.println("  No hay recordatorios registrados.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + lista.get(i));
        }
    }

    static void completar() {
        if (lista.isEmpty()) {
            System.out.println("  No hay recordatorios para completar.");
            return;
        }
        listar();
        System.out.print("Numero del recordatorio a completar: ");
        try {
            int n = Integer.parseInt(sc.nextLine().trim());
            if (n >= 1 && n <= lista.size()) {
                lista.get(n - 1).completar();
                System.out.println("  Marcado como completado.");
                return;
            }
        } catch (NumberFormatException e) {
            // cae al mensaje de error
        }
        System.out.println("  Error: numero invalido.");
    }

    static void mostrarProximo() {
        String fecha = leerTextoNoVacio("Fecha a consultar (igual a como la registraste): ");
        Recordatorio mejor = null;
        for (Recordatorio r : lista) {
            if (!r.isCompletado() && r.getFecha().equalsIgnoreCase(fecha)) {
                if (mejor == null || r.getPrioridad() < mejor.getPrioridad()) {
                    mejor = r;
                }
            }
        }
        if (mejor == null) {
            System.out.println("  No hay recordatorios pendientes para esa fecha.");
        } else {
            System.out.println("  Proximo: " + mejor);
        }
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== GESTOR DE RECORDATORIOS (OO) ===");
            System.out.println("1. Registrar recordatorio");
            System.out.println("2. Listar recordatorios");
            System.out.println("3. Marcar como completado");
            System.out.println("4. Mostrar proximo recordatorio");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");
            if (!sc.hasNextLine()) {
                break;
            }
            switch (sc.nextLine().trim()) {
                case "1": registrar(); break;
                case "2": listar(); break;
                case "3": completar(); break;
                case "4": mostrarProximo(); break;
                case "5": System.out.println("Hasta luego."); salir = true; break;
                default: System.out.println("  Opcion invalida. Elige un numero del 1 al 5.");
            }
        }
    }
}
