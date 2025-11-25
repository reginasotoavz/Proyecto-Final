# Proyecto-Final
3. Pistas para lo que te falta 🕵️‍♂️
Ya tienes el código limpio y corregido. Ahora, para completar el 100% del PDF:

Conectar el Menú Profesor y Ayudante:

Tus clases Profesor y Ayudante tienen mostrarMenu(), pero solo imprimen texto.

Pista: Modifica mostrarMenu() para que reciba el objeto SistemaTareas como parámetro.

Ejemplo: public void mostrarMenu(SistemaTareas sistema) { ... sistema.crearTarea(...); }

Así, cuando el profesor elija "Crear Tarea", llamas al método real que está en SistemaTareas.

Validar la Fecha (LocalDate):

En crearTarea usé LocalDate.parse(fechaStr). Esto espera formato "2025-01-31".

Pista: Si el usuario escribe mal la fecha, el programa se cierra. Envuelve eso en un try-catch (ya te puse un ejemplo arriba) para cumplir con el punto "Manejo de errores con excepciones".

Persistencia (El toque final):

En SistemaTareas.java dejé comentadas las líneas ManejadorArchivos.guardar....

Pista: Descoméntalas una vez que copies la clase ManejadorArchivos que te di en la respuesta anterior. Recuerda que esa clase debe usar FileWriter (para escribir) y Scanner o BufferedReader (para leer), no arreglos.