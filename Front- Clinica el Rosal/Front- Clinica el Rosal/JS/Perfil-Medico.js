document.addEventListener("DOMContentLoaded", function () {
    // Ocultar todos los contenedores
    function hideAllContainers() {
        document.getElementById("containerAgendamiento").classList.add("container-hidden");
        document.getElementById("containerHistoriaClinica").classList.add("container-hidden");
        document.getElementById("containerExamenes").classList.add("container-hidden");
    }


    function showContainer(containerId) {
        hideAllContainers();
        document.getElementById(containerId).classList.remove("container-hidden");
    }


    document.getElementById("btnAgendamiento").addEventListener("click", function () {
        showContainer("containerAgendamiento");
    });

    document.getElementById("btnHistoriaClinica").addEventListener("click", function () {
        showContainer("containerHistoriaClinica");
    });

    document.getElementById("btnExamenes").addEventListener("click", function () {
        showContainer("containerExamenes");
    });
});

document.addEventListener("DOMContentLoaded", function () { // Se espera a que el DOM esté completamente cargado antes de ejecutar el código
    const Historiales = [
        {
            fechadeConsulta: "2024-06-14 17:00:00",
            motivoConsulta: "La paciente presenta episodios de dolor abdominal en la región periumbilical, que se han intensificado en las últimas semanas.",
            diagnostico: "Síndrome de intestino irritable (posible)",
            tratamiento: "Se hara envio de tratamiento medico y se le pide a la madre una reduccion de alimentos irritantes (grazas, azucares y alimentos picantes) Y un control en 1 mes para evaluar la evolucion.",
            alergias: "N/A",
            antecedentes: "Personales: Sin antecedentes quirúrgicos. No alergias conocidas. Familiares: Madre con gastritis, padre sano.",
            signosVitales: "Frecuencia cardíaca: 85 latidos por minuto,Frecuencia respiratoria: 20 respiraciones por minuto,Temperatura: 36.8 °C, Presión arterial: 100/60 mmHg",
            examenesSolicitado: "Se le enviara una Ecografía abdominal Para descartar anomalías estructurales.",
            prescripcionmedica: "Naproxeno 250mg x 20 Capsula x Suministrar una capsula cada 8 Horas x 5 Dias"
        }
    ];

    function cargarHistoriales() {
        const tablaHistoriales = document.getElementById("tablaHistoriales");
        tablaHistoriales.innerHTML = ""; // Se Utiliza para limpiar la tabla antes de agregar los datos al modal

        Historiales.forEach(historial => { // Se representa cada elemento del array Historiales en cada iteración del forEach.
            const fila = `
                <tr>
                    <td>${historial.fechadeConsulta}</td>
                    <td>${historial.motivoConsulta}</td>
                    <td>${historial.diagnostico}</td>
                    <td>${historial.tratamiento}</td>
                    <td>${historial.alergias}</td>
                    <td>${historial.antecedentes}</td>
                    <td>${historial.signosVitales}</td>
                    <td>${historial.examenesSolicitado}</td>
                    <td>${historial.prescripcionmedica}</td>
                 </tr>
            `;
            tablaHistoriales.innerHTML += fila;  // Se agrega cada  una de las fila a la tabla 
        });
    }

    const btnVerHistorial = document.querySelector(".btn-info");  // Se obtiene el boton para abrir el Historial
    const modalHistorial = new bootstrap.Modal(document.getElementById("modalHistoriales")); // Se instancia el Modal de bootstrap

    btnVerHistorial.addEventListener("click", function () {
        cargarHistoriales(); // Se Carga los datos en la tabla antes de mostrar el modal
        modalHistorial.show(); //En este caso Muestra el Modal y carga los datos que se Administraron
    });
});


// Se agrega la funcionalidad para los botones de agregar de la prescripcion medica
function agregar() {
    let tabla = document.getElementById("tabla-prescripcion");
    let nuevaFila = document.createElement("tr");

    nuevaFila.innerHTML = `
        <td><input type="text" class="form-control" placeholder="Medicamento"></td>

        <td><input type="number" class="form-control" placeholder="Cantidad"></td>
        
        <td>
            <select class="form-select">
                <option value="">Seleccionar</option>
                <option value="tabletas">Tabletas</option>
                <option value="Capsula">Capsula</option>
                <option value="Jarabe">Jarabe</option>
                <option value="Inyeccion">Inyeccion</option>
                <option value="Frasco">Frasco</option>
            </select>
        </td>
        
        <td><input type="text" class="form-control" placeholder="Indicaciones"></td>
        <td><button onclick="eliminarFila(this)">Eliminar</button></td>
    `;
    tabla.appendChild(nuevaFila);
}


// Se le agrega la funcionalidad para que el boton de eliminar pueda funcionar correctamente en la prescripcion medica 
function eliminarFila(boton) {
    let fila = boton.closest("tr"); // Encuentra la fila más cercana al botón
    fila.remove(); // Elimina la fila
}


// Se agrega la Funcionalidad de Guardar la Historia clinica del paciente
function guardarHistoriaClinica() {
    // Se Recolectara los datos que se suministraron en la Historia clinica
    let historiaClinica = {
        motivoConsulta: document.getElementById("motivo-Consulta").value,
        diagnostico: document.getElementById("diagnostico").value,
        tratamiento: document.getElementById("tratamiento").value,
        alergias: document.getElementById("alergias").value,
        antecedentes: document.getElementById("antecedentes").value,
        signosVitales: document.getElementById("signos-Vitales").value,
        examenesSolicitado: document.getElementById("examenes-Solicitado").value,
        prescripcionmedica: obtenerPrescripcion(), // Se Obtiene los datos de la tabla de prescripcion medica
    };

    // Se Guarda por el momento en un almacenamiento Local hasta obtener el back 
    localStorage.setItem("historiaClinica", JSON.stringify(historiaClinica)); // Se Guarda en el almacenamiento bajo una clave y esta es "historiaClinica"
    alert("Historia Clinica Guardada de manera Exitosa");
}

// Ahora se Agrega el Proceso de la funcion para obtener los datos de la prescripcion medica
function obtenerPrescripcion() {
    let tabla = document.getElementById("tabla-prescripcion");
    let fila = tabla.querySelectorAll("tbody tr");
    let prescripcion = []; // Se inicializa un array vacio con el fin de almacenar cada datos del medicamentos

    fila.forEach((fila, index) => {  // Se recorre cada una de la fila de la tabla 
        let inputs = fila.querySelectorAll("input, select");

        if (inputs.length >= 4) { // En este caso se verfica que la fila al menos obtenga 4 elementos para poderse crear un objeto con cada uno de los campos de medicamentos
            let Medicamento = {
                nombre: inputs[0].value,
                cantidad: inputs[1].value,
                presentacion: inputs[2].value,
                indicaciones: inputs[3].value,
            };
            prescripcion.push(Medicamento); // Se agrega el medicamentos con cada uno de los campos existentes para la prescripcion
        } else {
            console.error(`Fila ${index + 1} incompleta. Elementos encontrados:`, inputs.length);

            console.log("Contenido de la fila:", fila.innerHTML); // Muestra el contenido de la fila para depuración
        }
    });
    return prescripcion; // Devuelve el arreglo de prescripcion con todos los datos de Medicamentos
}

// Se imprime el Historial clinico del paciente 
function imprimirHistoriaClinica() {
    window.print();
}


// Datos falsos para ejecutar la funcionalidad de Examenes 
const examenes = [
    {
        tipoIdentificacion: "CC",
        id: "1002459502",
        examen: "Ecografia Abdominal",
        fecha: "2025-03-20",
        descarga: "Ecografia-Alberto-Benitez.pdf"
    },

    {
        tipoIdentificacion: "CC",
        id: "1002459502",
        examen: "Radiografia",
        fecha: "2024-10-27",
        descarga: "Radiografia-Alberto-Benitez.pdf"
    },

    {
        tipoIdentificacion: "CC",
        id: "1002459502",
        examen: "Hemograma",
        fecha: "2025-01-10",
        descarga: "Hemograma-Alberto-Benitez.pdf"
    },

    {
        tipoIdentificacion: "TI",
        id: "1445887963",
        examen: "Insulina",
        fecha: "2025-02-15",
        descarga: "Insulina-Alberto-Benitez.pdf"
    }
];

// Se Realiza la funcion para buscar por medio de la identificacion del paciente
function buscarPorIdentificacion() {

    const tipoIdentificacion = document.getElementById("tipoIdentificacion").value;
    const identificacion = document.getElementById("Identificacion").value;
    const tbody = document.querySelector("#tablaExamenes tbody");
    tbody.innerHTML = ""; // Se limpia la tabla antes de poder agregar las nuevas filas al sistema

    // Se le Hace las pruebas dentro de la consola haber si esta ingresando de manera correcta todos los datos 
    console.log("tipoIdentificacion:", tipoIdentificacion);
    console.log("Identificacion", identificacion);

    // En este caso se Valida que se haya seleccionado un tipo de identificacion y haber ingresado la identificacion del paciente a buscar
    if (tipoIdentificacion === "Seleccionar" || !identificacion) {

        // Si no Se selecciona ni el tipoIdentificacion ni el del numero de identificacion saldra un mensaje en la tabla 
        const row = document.createElement('tr');
        row.innerHTML = `<td colspan="3" class="text-center">Por favor, seleccione un tipo de identificación e ingrese un número de identificación.</td>`;
        tbody.appendChild(row);
        return; // Aca se saldria de la funcion si no se llega a cumplir la condicion de las validaciones 
    }

    // Se filtra los examenes seleccionado segun su tipo de identificacion y su numero de identificacion
    const resultados = examenes.filter(examen => {

        // Se muestra en consola cada una de las comparacion que se realiza durante la filtracion de informacion
        console.log("Comparando:", examen.tipoIdentificacion, "===", tipoIdentificacion, "&&", examen.id, "===", identificacion);

        // Aca lo que haria seria retorna verdadero si el tipo de identificacion y el numero sean correctos
        return examen.tipoIdentificacion === tipoIdentificacion && examen.id === identificacion;
    });

    console.log("Resultados encontrados:", resultados);

    // En este caso Sera para mostrar la informacion del examen dentro de la tabla
    if (resultados.length > 0) {

        // Si los resultados fueron encontrados de manera exitosa se agregaria cada examen correspondiente al ID a la tabla
        resultados.forEach(examenes => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${examenes.examen}</td>
                <td>${examenes.fecha}</td>
                <td><a href="${examenes.descarga}" download>Descargar</a></td>
            `;
            tbody.appendChild(row); // Se agregaria la fila a la tabla 
        });
    } else {

        // Si se cumple la primera condicion de encontrar los examenes mostrara un mensaje dentro de la tabla 
        const row = document.createElement('tr');
        row.innerHTML = `<td colspan="3" class="text-center">No se encontraron exámenes para esta identificación.</td>`;
        tbody.appendChild(row);
    }
}

// ==================================================
// Módulo de Datos del paciente segun su agendamiento
// ==================================================

const historiales = {
    // Datos Ficticios Hasta que se conecte con el back
    'Usuario_0001': {
        paciente: 'Usuario 0001',
        telefono: '+57 321 456 88 99',
        correo: 'usuario@gmail.com',
        direccion: 'Calle 15 a 46 85',
        edad: '18',
        identificacion: '1004227854',
        genero: 'M',
        afiliacion: 'Contributivo',
        fechaConsulta: '2024/09/20',
        medicoTratante: 'Profesional: 0001'
    },

    'Usuario_0002': {
        paciente: 'Usuario 0002',
        telefono: '+57 321 456 88 00',
        correo: 'usuario2@gmail.com',
        direccion: 'Calle 20 a 50 90',
        edad: '25',
        identificacion: '1004227855',
        genero: 'F',
        afiliacion: 'Subsidiado',
        fechaConsulta: '2024/09/21',
        medicoTratante: 'Profesional: 0001'
    },

    'Usuario_0003': {
        paciente: 'Usuario 0003',
        telefono: '+57 321 456 88 11',
        correo: 'usuario3@gmail.com',
        direccion: 'Calle 30 a 60 100',
        edad: '30',
        identificacion: '1004227856',
        genero: 'M',
        afiliacion: 'Contributivo',
        fechaConsulta: '2024/09/22',
        medicoTratante: 'Profesional: 0001'
    }
};

// Funcion para poder Mostrar el historial clinico con toda la informacion del paciente
function mostrarHistorial(idPaciente) {
    console.log('ID del paciente:', idPaciente);

    const historial = historiales[idPaciente];
    if (historial) {

        // Se verfica que los elementos puedan existir dentro del sistema
        const pacienteElement = document.getElementById('paciente');
        const telefonoElement = document.getElementById('telefono');
        const correoElement = document.getElementById('correo');
        const direccionElement = document.getElementById('direccion');
        const edadElement = document.getElementById('edad');
        const identificacionElement = document.getElementById('identificacion');
        const generoElement = document.getElementById('genero');
        const afiliacionElement = document.getElementById('afiliacion');
        const fechaConsultaElement = document.getElementById('fechaConsulta');
        const medicoTratanteElement = document.getElementById('medicoTratante');

        console.log('Elementos del DOM:', {
            pacienteElement,
            telefonoElement,
            correoElement,
            direccionElement,
            edadElement,
            identificacionElement,
            generoElement,
            afiliacionElement,
            fechaConsultaElement,
            medicoTratanteElement
        });

        if (
            pacienteElement &&
            telefonoElement &&
            correoElement &&
            direccionElement &&
            edadElement &&
            identificacionElement &&
            generoElement &&
            afiliacionElement &&
            fechaConsultaElement &&
            medicoTratanteElement
        ) {
            pacienteElement.textContent = historial.paciente;
            telefonoElement.textContent = historial.telefono;
            correoElement.textContent = historial.correo;
            direccionElement.textContent = historial.direccion;
            edadElement.textContent = historial.edad;
            identificacionElement.textContent = historial.identificacion;
            generoElement.textContent = historial.genero;
            afiliacionElement.textContent = historial.afiliacion;
            fechaConsultaElement.textContent = historial.fechaConsulta;
            medicoTratanteElement.textContent = historial.medicoTratante;

            document.getElementById('containerAgendamiento').classList.add('hidden');
            document.getElementById('containerHistoriaClinica').classList.remove('hidden');
        } else {
            console.error('Uno o mas Elementos del Dom no fueron encontrado correctamente');
        }
    } else {
        alert('Paciente no encontrado');
    }
}

