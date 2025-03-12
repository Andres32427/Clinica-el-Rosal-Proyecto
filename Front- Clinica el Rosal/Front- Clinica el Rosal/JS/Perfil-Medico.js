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