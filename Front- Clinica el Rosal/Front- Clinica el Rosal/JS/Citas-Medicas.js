document.addEventListener("DOMContentLoaded", () => {
    // Obtener referencias a los elementos del formulario
    const agendarCita = document.getElementById("agendarCita");
    const reprogramarCita = document.getElementById("reprogramarCita");
    const cancelarCita = document.getElementById("cancelarCita");
    const fechaCita = document.getElementById("fechaCita");
    const aceptarBtn = document.querySelector("button.btn-primary");

    // Función para mostrar mensajes o realizar acciones
    const handleCitaAction = () => {
        // Validar que se haya seleccionado una fecha
        if (!fechaCita.value) {
            alert("Por favor, seleccione una fecha.");
            return;
        }

        // Verificar qué opción está seleccionada
        if (agendarCita.checked) {
            alert(`Cita agendada para el ${fechaCita.value}`);
            // Aquí puedes agregar lógica para redirigir o enviar datos a un servidor
        } else if (reprogramarCita.checked) {
            alert(`Cita reprogramada para el ${fechaCita.value}`);
        } else if (cancelarCita.checked) {
            alert("Cita cancelada exitosamente.");
        } else {
            alert("Por favor, seleccione una opción para la cita.");
        }
    };

    // Agregar evento al botón Aceptar
    aceptarBtn.addEventListener("click", (event) => {
        event.preventDefault(); // Evitar recargar la página
        handleCitaAction();
    });
});
