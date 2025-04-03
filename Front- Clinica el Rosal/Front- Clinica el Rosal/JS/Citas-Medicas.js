document.addEventListener("DOMContentLoaded", () => {
    
    // Obtener referencias de los elementos del formulario
    const agendarCita = document.getElementById("agendarCita");
    const reprogramarCita = document.getElementById("reprogramarCita");
    const cancelarCita = document.getElementById("cancelarCita");
    const fechaCita = document.getElementById("fechaCita");
    const hora = document.getElementById("hora"); 
    const medico = document.getElementById("medico"); 
    const especialidad = document.getElementById("especialidad"); 
    const aceptarBtn = document.getElementById("aceptarBtn");

    if (!aceptarBtn) {
        console.error("No se encontró el botón de aceptar.");
        return;
    }

    // Función para manejar la acción de la cita
    const handleCitaAction = () => {
        if (!fechaCita.value || !hora.value || !medico.value || !especialidad.value) {
            alert("Por favor, complete todos los campos.");
            return;
        }

        const mensaje = `Cita con el Pr. ${medico.value} (${especialidad.value})\nFecha: ${fechaCita.value}\nHora: ${hora.value}`;

        if (agendarCita.checked) {
            alert(`Cita agendada:\n${mensaje}`);
        } else if (reprogramarCita.checked) {
            alert(`Cita reprogramada:\n${mensaje}`);
        } else if (cancelarCita.checked) {
            alert("Cita cancelada exitosamente.");
        } else {
            alert("Por favor, seleccione una opción para la cita.");
        }
    };

    // Agregar evento al botón Aceptar
    aceptarBtn.addEventListener("click", (event) => {
        event.preventDefault();
        handleCitaAction();
    });
});
