titulos = []
fechas = []
prioridades = []
completados = []


def leer_texto_no_vacio(mensaje):
    """Pide un texto hasta que el usuario escriba algo distinto de vacio."""
    while True:
        valor = input(mensaje).strip()
        if valor != "":
            return valor
        print("  Error: el campo no puede estar vacio.")


def leer_prioridad():
    """Pide una prioridad entera entre 1 y 3."""
    while True:
        texto = input("Prioridad (1=alta, 2=media, 3=baja): ").strip()
        if texto.isdigit() and 1 <= int(texto) <= 3:
            return int(texto)
        print("  Error: la prioridad debe ser un numero entre 1 y 3.")


def registrar():
    titulo = leer_texto_no_vacio("Titulo: ")
    fecha = leer_texto_no_vacio("Fecha (formato libre): ")
    prioridad = leer_prioridad()
    titulos.append(titulo)
    fechas.append(fecha)
    prioridades.append(prioridad)
    completados.append(False)
    print("  Recordatorio registrado.")


def listar():
    if len(titulos) == 0:
        print("  No hay recordatorios registrados.")
        return
    for i in range(len(titulos)):
        estado = "[X]" if completados[i] else "[ ]"
        print(f"  {i + 1}. {estado} {titulos[i]} | {fechas[i]} | prioridad {prioridades[i]}")


def completar():
    if len(titulos) == 0:
        print("  No hay recordatorios para completar.")
        return
    listar()
    texto = input("Numero del recordatorio a completar: ").strip()
    if not texto.isdigit() or not (1 <= int(texto) <= len(titulos)):
        print("  Error: numero invalido.")
        return
    indice = int(texto) - 1
    if completados[indice]:
        print("  Ese recordatorio ya estaba completado.")
    else:
        completados[indice] = True
        print("  Marcado como completado.")


def mostrar_proximo():
    fecha = leer_texto_no_vacio("Fecha a consultar (igual a como la registraste): ")
    mejor = -1
    for i in range(len(titulos)):
        if not completados[i] and fechas[i].lower() == fecha.lower():
            if mejor == -1 or prioridades[i] < prioridades[mejor]:
                mejor = i
    if mejor == -1:
        print("  No hay recordatorios pendientes para esa fecha.")
    else:
        print(f"  Proximo: {titulos[mejor]} | {fechas[mejor]} | prioridad {prioridades[mejor]}")


def mostrar_menu():
    print("\n=== GESTOR DE RECORDATORIOS ===")
    print("1. Registrar recordatorio")
    print("2. Listar recordatorios")
    print("3. Marcar como completado")
    print("4. Mostrar proximo recordatorio")
    print("5. Salir")


def main():
    while True:
        mostrar_menu()
        opcion = input("Elige una opcion: ").strip()
        if opcion == "1":
            registrar()
        elif opcion == "2":
            listar()
        elif opcion == "3":
            completar()
        elif opcion == "4":
            mostrar_proximo()
        elif opcion == "5":
            print("Hasta luego.")
            break
        else:
            print("  Opcion invalida. Elige un numero del 1 al 5.")

main()
