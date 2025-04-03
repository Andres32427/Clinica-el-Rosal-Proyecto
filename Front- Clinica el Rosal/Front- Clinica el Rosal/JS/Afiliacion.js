document.addEventListener("DOMContentLoaded", function () {
    
    document.getElementById("afiliacionForm").addEventListener("submit", function (event) {
       
        event.preventDefault();

        let nombres = document.getElementById("nombres").value.trim();
        let apellidos = document.getElementById("apellidos").value.trim();
        let tipoIdentificacion = document.getElementById("tipoIdentificacion").value;
        let identificacion = document.getElementById("Identificacion").value.trim();
        let fechaNacimiento = document.getElementById("fechaNacimiento").value;
        let telefono = document.getElementById("telefono").value.trim();
        let correo = document.getElementById("correo").value.trim();
        let direccion = document.getElementById("direccion").value.trim();
        let municipio = document.getElementById("Municipio").value.trim();
        let tipoAfiliacion = document.getElementById("tipoAfiliacion").value;
        let seguro = document.getElementById("Seguro").value;

        let errores = [];

        // Validación de campos vacíos
        if (nombres === "") errores.push("El campo 'Nombres' es obligatorio.");
        if (apellidos === "") errores.push("El campo 'Apellidos' es obligatorio.");
        if (tipoIdentificacion === "") errores.push("Debe seleccionar un tipo de identificación.");
        if (identificacion === "") errores.push("El campo 'Identificación' es obligatorio.");
        if (fechaNacimiento === "") errores.push("El campo 'Fecha de Nacimiento' es obligatorio.");
        if (telefono === "") errores.push("El campo 'Teléfono' es obligatorio.");
        if (correo === "") errores.push("El campo 'Correo Electrónico' es obligatorio.");
        if (direccion === "") errores.push("El campo 'Dirección' es obligatorio.");
        if (municipio === "") errores.push("El campo 'Municipio' es obligatorio.");
        if (tipoAfiliacion === "Seleccione") errores.push("Debe seleccionar un tipo de afiliación.");
        if (seguro === "") errores.push("Debe seleccionar un seguro.");

        // Validación de correo electrónico
        let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(correo)) errores.push("El formato del 'Correo Electrónico' no es válido.");

        // Validación de teléfono (solo números)
        let phoneRegex = /^[0-9]+$/;
        if (!phoneRegex.test(telefono)) errores.push("El campo 'Teléfono' solo debe contener números.");

        // Mostrar errores o enviar formulario
        if (errores.length > 0) {
            alert("Errores en el formulario:\n" + errores.join("\n"));
        } else {
            alert("Formulario enviado con éxito.");
            // Aquí puedes enviar el formulario con AJAX o redirigir a otra página
        }
    });
});