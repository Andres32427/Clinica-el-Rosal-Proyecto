document.addEventListener("DOMContentLoaded", function () {
           
    function hideAllContainers() {
        document.getElementById("containerGestiondeInventario").classList.add("container-hidden");
    }

    
    function showContainer(containerId) {
        hideAllContainers();
        document.getElementById(containerId).classList.remove("container-hidden");
    }

   
    document.getElementById("btnGestiondeInventario").addEventListener("click", function () {
        showContainer("containerGestiondeInventario");
    });
});

document.addEventListener("DOMContentLoaded", function () {
    let btnGuardar = document.getElementById("guardarMedicamento");
    let btnLimpiar = document.getElementById("limpiarFormulario");

    btnGuardar.addEventListener("click", function (event) {
        event.preventDefault(); // Evita que la página se recargue

        let nombre = document.getElementById("nombre").value;
        let categoria = document.getElementById("categoria").value;
        let cantidad = document.getElementById("cantidad").value;
        let unidad = document.getElementById("unidad").value;
        let precio = document.getElementById("precio").value;
        let vencimiento = document.getElementById("vencimiento").value;
        let proveedor = document.getElementById("proveedor").value;
        let actualizacion = document.getElementById("actualizacion").value;
        let estado = document.getElementById("estado").value;
        let descripcion = document.getElementById("descripcion").value;

        if (!nombre || !cantidad || !precio || !vencimiento || !proveedor) {
            alert("Por favor, completa todos los campos obligatorios.");
            return;
        }

        let tabla = document.getElementById("tablaInventario").querySelector("tbody");
        let fila = tabla.insertRow();
        
        let id = tabla.rows.length; // Genera un ID automático

        let datos = [id, nombre, cantidad, descripcion, categoria, unidad, precio, vencimiento, proveedor, actualizacion, estado];
        
        datos.forEach((dato) => {
            
            let celda = fila.insertCell();
            
            celda.textContent = dato;
        });

        // Agregar botón de eliminar
        let celdaEliminar = fila.insertCell();
        let btnEliminar = document.createElement("button");
        btnEliminar.textContent = "Eliminar";
        btnEliminar.classList.add("btn", "btn-danger", "btn-sm");

        btnEliminar.addEventListener("click", function () {
            
            fila.remove(); // Elimina la fila de la tabla
        });

        celdaEliminar.appendChild(btnEliminar);
    });

    //Evento para limpiar el formulario Sin que afecte la tabla
    btnLimpiar.addEventListener("click", function (event) {
        
        event.preventDefault(); // Evita que el botón actúe como submit y recargue la página

        document.getElementById("form-medicamento").reset();
    });
});