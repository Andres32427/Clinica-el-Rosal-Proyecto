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

function registrarFactura() {
    // Se Implementa los valores para el registro de la factura
    const idPaciente = document.getElementById('IdPaciente').value.trim(); // Se utilizo el .trim() para poder asi eliminar espacios en blanco ya sea al inicio o al final
    const idServicio = document.getElementById('Servicio').value;
    const monto = document.getElementById('Monto').value;
    const fecha = document.getElementById('fecha').value; // Se corrigió el error de "Value" a "value"

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

