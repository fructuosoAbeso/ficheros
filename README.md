Análisis del enunciado

Se analiza el ejercicio y se determina que la aplicación debe permitir:

Introducir texto y guardarlo en un fichero de texto.

Visualizar el contenido del fichero.

Consultar el estado del almacenamiento externo del dispositivo.

Mover el fichero entre almacenamiento interno (privado de la app) y almacenamiento externo específico de la aplicación.

Incluir un botón de cierre para finalizar la aplicación.


Diseño de la interfaz de usuario (UI)

Se planifica la pantalla principal con los siguientes elementos:

Toolbar o navbar en la parte superior con el título de la app.

Campo de texto donde el usuario introducirá la información.

Botones organizados en filas, con acciones: guardar, ver contenido, consultar estado, mover a interno o externo, cerrar.

Texto descriptivo indicando la acción de los botones de mover fichero.

Espacios con weight o padding para centrar elementos y mejorar la estética.

Otras pantallas:

Para mostrar el contenido del fichero.

Para mostrar el estado del almacenamiento externo.


Gestión de ficheros

Se identifican dos ubicaciones:

Almacenamiento interno: Privado de la aplicación.

Almacenamiento externo de la app: Específico para la aplicación, no requiere permisos peligrosos ni acceso global.

Se asegura que el fichero solo exista en una ubicación a la vez, y que se pueda mover de una ubicación a otra.


Decisión de dónde guardar

Se detecta un problema inicial: el sistema siempre escribía en externo, ignorando la elección del usuario.

La solución fue introducir una variable de control para que el usuario pueda decidir si quiere guardar en interno o externo.

Los botones “Mover a externo” y “Mover a interno” actualizan esta variable de control, determinando la ubicación de guardado.


Guardado del fichero

El texto introducido se añade al final del fichero, sin sobrescribir contenido existente.

Tras guardar, el campo de texto se limpia para que el usuario pueda introducir una nueva línea fácilmente.

Se controla si el fichero existe en cada ubicación para evitar errores.


Movimiento entre ubicaciones

Se permite copiar el fichero de interno a externo o de externo a interno.

Tras la copia, el fichero original se elimina, garantizando que solo exista en una ubicación.

Los botones de movimiento se habilitan o deshabilitan según la existencia del fichero en cada ubicación.


Visualización del contenido

Se crea una pantalla específica que lee el fichero existente y lo muestra línea a línea en un área de texto.

Se detecta automáticamente si el fichero está en interno o externo.

Se incluye un botón “Volver” para regresar a la pantalla principal sin perder datos.


Estado del almacenamiento externo

Se consulta el sistema para mostrar todos los estados posibles del almacenamiento externo: montado, solo lectura, removido, compartido por USB, no montado.

Se muestra esta información en otra pantalla específica.

También se incluye un botón “Volver” a la pantalla principal.


Navegación y botones

Los botones permiten moverse entre pantallas y realizar acciones sin recargar la app.

El botón “Volver” simplemente cierra la pantalla secundaria y regresa a la principal.

El botón “Cerrar” termina la aplicación.


Pruebas realizadas

Se verifica que:

Guardar en interno funciona correctamente.

Guardar en externo funciona correctamente.

Mover fichero entre ubicaciones mantiene la integridad del contenido.

Visualizar contenido refleja el estado real del fichero.

Consultar el estado de almacenamiento externo muestra información correcta.

Cambiar la ubicación de guardado dinámicamente funciona según la elección del usuario.


Conclusión

La aplicación cumple con todos los requisitos del ejercicio.

Permite controlar explícitamente dónde se guarda el fichero.

Maneja correctamente la existencia del fichero y los estados del almacenamiento.

La navegación entre pantallas es intuitiva y consistente.
