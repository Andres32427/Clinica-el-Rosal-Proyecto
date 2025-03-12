document.addEventListener("DOMContentLoaded", function () {

    function hideAllContainers() {
        document.getElementById("containerGestiondeAgendamiento").classList.add("container-hidden");
        document.getElementById("containerFacturacion").classList.add("container-hidden");
        document.getElementById("containerGestion de Examenes / Resultados").classList.add("container-hidden");
    }

    function showContainer(containerId) {
        hideAllContainers();
        document.getElementById(containerId).classList.remove("container-hidden");
    }


    document.getElementById("btnGestiondeAgendamiento").addEventListener("click", function () {
        showContainer("containerGestiondeAgendamiento");
    });

    document.getElementById("btnFacturacion").addEventListener("click", function () {
        showContainer("containerFacturacion");
    });

    document.getElementById("btnGestion de Examenes / Resultados").addEventListener("click", function () {
        showContainer("containerGestion de Examenes / Resultados");
    });
});

// ==================================================
// Módulo de Agendamiento
// ==================================================

// Datos falsos para ejecutar la funcionalidad de la Agendamiento
const citas = [
    {
        id: 1,
        medico: 1,
        especialidad: 3,
        fecha: "2025-01-20",
        hora: "8:00 A.M",
        estado: "Disponible",
        motivo: "",
        paciente: null
    },
    {
        id: 2,
        medico: 1,
        especialidad: 3,
        fecha: "2025-01-20",
        hora: "9:00 A.M",
        estado: "Ocupado",
        motivo: "Cita Médica",
        paciente: "Paciente_0001"
    },
    {
        id: 3,
        medico: 1,
        especialidad: 3,
        fecha: "2025-01-20",
        hora: "10:00 A.M",
        estado: "Bloqueado",
        motivo: "Mantenimiento",
        paciente: null
    }
];

function consultarAgendamiento() {
    const medicoId = document.getElementById("medico").value;
    const especialidadId = document.getElementById("especialidad").value;
    const fecha = document.getElementById("fecha").value;

    // En este caso se Filtran las citas segun los criterios que se seleccionen
    const citasFiltradas = citas.filter(cita =>
        cita.medico == medicoId &&
        cita.especialidad == especialidadId &&
        cita.fecha == fecha
    );

    // Se Genera la tabla de citas
    const tbody = document.querySelector("#tablaAgendamiento tbody");
    tbody.innerHTML = ""; // Se limpia la tabla antes de poder agregar las nuevas filas al sistema

    citasFiltradas.forEach(cita => {
        const row = document.createElement("tr");

        // Se realiza la tabla de Hora
        const Hora = document.createElement("td");
        Hora.textContent = cita.hora;
        row.appendChild(Hora);

        // Se realiza la tabla de Estado
        const Estado = document.createElement("td");
        Estado.textContent = cita.estado + (cita.paciente ? ` (${cita.paciente})` : "");
        Estado.className = cita.estado === "Disponible" ? "text-success" : cita.estado === "Ocupado" ? "text-danger" : "text-secondary";
        row.appendChild(Estado);

        // Se realiza la tabla de Motivo
        const Motivo = document.createElement("td");
        Motivo.textContent = cita.motivo || "Matenimiento";
        row.appendChild(Motivo);

        // Se realiza la tabla de Acciones
        const Acciones = document.createElement("td");
        if (cita.estado === "Disponible") {
            Acciones.innerHTML = `<button class="btn btn-warning btn-sm" onclick="bloquearCita(${cita.id})">Bloquear</button>`;
        } else if (cita.estado === "Ocupado") {
            Acciones.innerHTML = `<button class="btn btn-info btn-sm" onclick="modificarCita(${cita.id})">Modificar</button>`;
        } else if (cita.estado === "Bloqueado") {
            Acciones.innerHTML = `<button class="btn btn-primary btn-sm" onclick="liberarCita(${cita.id})">Liberar</button>`;
        }

        row.appendChild(Acciones);

        tbody.appendChild(row);
    });
}

// Se Hace una Funcion para bloquear una cita asignada
function bloquearCita(id) {
    const cita = citas.find(c => c.id === id);
    if (cita) {
        cita.estado = "Bloqueado";
        cita.motivo = "Mantenimiento";
        cita.paciente = null;
        alert("Cita bloqueada por mantenimiento.");
        consultarAgendamiento(); // Se Actualiza la tabla
    }
}

// Se Hace una Funcion para Modificar una cita asignada
function modificarCita(id) {
    const cita = citas.find(c => c.id === id);
    if (cita) {
        const nuevaFecha = prompt("Ingrese la nueva fecha (YYYY-MM-DD):");
        const nuevaHora = prompt("Ingrese la nueva hora (HH:MM A.M/P.M):");
        if (nuevaFecha && nuevaHora) {
            cita.fecha = nuevaFecha;
            cita.hora = nuevaHora;
            alert("Cita modificada exitosamente.");
            consultarAgendamiento(); // Se Actualiza la tabla
        }
    }
}

// Se Hace una Funcion para Liberar una cita asignada
function liberarCita(id) {
    const cita = citas.find(c => c.id === id);
    if (cita) {
        cita.estado = "Disponible"
        cita.motivo = "";
        cita.paciente = null;
        alert("Cita liberada exitosamente.");
        consultarAgendamiento(); // Se Actualiza la tabla
    }
}

// Se Hace una nueva funcion para guardar los cambios realizados por el auxiliar
function guardarCambios() {
    console.log("Guardando Cambios....");

    // Se guarda los cambios
    citas.forEach(cita => {
        console.log(`Cita ID: ${cita.id}, Estado: ${cita.estado}, Motivo: ${cita.motivo}`);
    });

    alert("Los Cambios se Han guardado De Manera Correcta");
    consultarAgendamiento(); // Se Actualiza la tabla luego de haber guardado los cambios realizados
}

// Se Hace una nueva funcion para cancelar los cambios hechos dentro del agendamiento
document.addEventListener("DOMContentLoaded", function () {
    function cancelar() {
        console.log("Se empezo a Ejecutar la Cancelacion del agendamiento");

        // Se Inicializa Los Campos que se van a Limpiar dentro de la tabla de agendamiento 
        const medicoSelect = document.getElementById("medico");
        const especialidadSelect = document.getElementById("especialidad");
        const fechaInput = document.getElementById("fecha");
        const tbody = document.querySelector("#tablaAgendamiento tbody");

        // Se muestra dentro de la consola los Elementos que fueron seleccionado para Limpiar
        console.log("Elementos que fueron Seleccionado:", {
            medicoSelect,
            especialidadSelect,
            fechaInput,
            tbody
        });

        // Se Verifica si los elementos existen en la tabla antes de limpiarlo
        if (medicoSelect && especialidadSelect && fechaInput) {
            medicoSelect.value = "";
            especialidadSelect.value = "";
            fechaInput.value = "";
        } else {
            console.error("No se encontraron uno o más elementos.");
        }

        // En este caso se verifica si el tbody existe con todo sus datos antes de limpiarlo
        if (tbody) {
            tbody.innerHTML = "";
        } else {
            console.error("No se encontró el elemento tbody.");
        }

        alert("Acciones canceladas.");
    }

    // Se logra Vincular el button de cancelar para su buen funcionamiento
    const cancelarButton = document.querySelector(".btn-secondary");
    if (cancelarButton) {
        cancelarButton.addEventListener("click", cancelar);
    } else {
        console.error("No se encontró el botón de cancelar.");
    }
});


// ==================================================
// Módulo de Facturación
// ==================================================

// Función para registrar una factura
function registrarFactura() {
    // Se Implementa los valores para el registro de la factura
    const idPaciente = document.getElementById('IdPaciente').value.trim(); // Se utilizo el .trim() para poder asi eliminar espacios en blanco ya sea al inicio o al final
    const idServicio = document.getElementById('Servicio').value;
    const monto = document.getElementById('Monto').value;
    const fecha = document.getElementById('Fecha').value;

    // Se hace la prueba en la Consola para poder verificar si todos los campos ingresados se cumplen de manera correcta
    console.log("ID Paciente:", idPaciente);
    console.log("Servicio:", idServicio);
    console.log("Monto:", monto);
    console.log("Fecha:", fecha);

    // Es Necesario hacerse una validacion a ver que todos los campos esten Utilizado
    if (!idPaciente || idServicio === "" || isNaN(idServicio) || !monto || !fecha) { // el isnan(idServicio) se Utilizo para saber si el Servicio a prestar no es un numero
        alert('Es necesario completar todos los campos en la facturación');
        return;
    }

    // Se Genera una plantilla de html para poder obtener el Contenido que va a Aparecer dentro de la Factura
    const facturaDetalles =  `
    <p><strong>ID Paciente:</strong> ${idPaciente}</p>
    <p><strong>Servicio:</strong> ${idServicio}</p>
    <p><strong>Monto:</strong> $${parseFloat(monto).toFixed(2)}</p>
    <p><strong>Fecha:</strong> ${fecha}</p>
    `;

    // Se muestra los detalles en la seccion de la factura
    document.getElementById('Factura-detalles').innerHTML = facturaDetalles;

    // En este caso se mostrara la Tarjeta de La facturacion realizada
    document.getElementById('Factura-card').style.display = 'block';

    // Y con este codigo se ocultaria el formulario de la facturacion
    document.querySelector('form').style.display = 'none';
}

// ==================================================
// Módulo de Exámenes/Resultados
// ==================================================

// Datos falsos para ejecutar la funcionalidad de la Gestion de de examenes
const paciente = [
    {
        id: "1107845297",
        nombre: "Juan Pérez",
        genero: "M",
        edad: 19,
        correo: "juan.perez@gmail.com"
    },

    {
        id: "1234567890",
        nombre: "María Gómez",
        genero: "F",
        edad: 25,
        correo: "maria.gomez@hotmail.com"
    },

    {
        id: "0987654321",
        nombre: "Carlos Ruiz",
        genero: "M",
        edad: 30,
        correo: "carlos.ruiz@Outlok.com"
    }
];

function buscarPaciente() {
    const idPaciente = document.getElementById('paciente').value.trim(); // Se obtiene el ID ingresado al sistema
    const pacienteInfo = document.getElementById('infoPaciente'); // Aqui sera donde se mostrara la informacion

    // Se busca el paciente
    const pacienteEncontrado = paciente.find(paciente => paciente.id === idPaciente);

    if (pacienteEncontrado) {
        // Debe Mostrar la informacion del paciente de manera correcta
        pacienteInfo.innerHTML = `
            <span><strong>Nombre:</strong> ${pacienteEncontrado.nombre}</span> ||
            <span><strong>ID:</strong> ${pacienteEncontrado.id}</span> ||
            <span><strong>Género:</strong> ${pacienteEncontrado.genero}</span> ||
            <span><strong>Edad:</strong> ${pacienteEncontrado.edad} años</span> ||
            <span><strong>Correo:</strong> ${pacienteEncontrado.correo}</span>
        `;
    } else {
        // Por si no se encuentra la informacion del paciente se mostrara un mensaje 
        pacienteInfo.innerHTML = `<p style="color: red;">No se encontró ningún paciente con el ID ${idPaciente}.</p>`;
    }
}

function subirExamen() {
    const tipoExamen = document.getElementById('tipoExamen').value;
    const fechaExamen = document.getElementById('fechaExamen').value;
    const archivoExamen = document.getElementById('archivoExamen').files[0];

    // Si no se completa todos los campos y se le selecciona el archivo se enviara una alerta
    if (!tipoExamen || !fechaExamen || !archivoExamen) {
        alert('Por favor, completa todos los campos y selecciona un archivo.');
        return;
    }

    // Se Sube el Examen de manera exitosa
    alert('Examen subido con éxito');
    console.log({
        tipoExamen,
        fechaExamen,
        archivoExamen
    });
}

function cancelar() {
    // En este Caso se limpia el formulario con el fin de poder registrar uno nuevo.
    document.getElementById('examenForm').reset();
    document.getElementById('infoPaciente').innerHTML = '';
}