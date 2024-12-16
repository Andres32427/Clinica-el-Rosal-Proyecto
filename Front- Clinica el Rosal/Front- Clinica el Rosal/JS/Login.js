function validarCorreo(correo) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; 
    return regex.test(correo);
}


const botonIngresar = document.querySelector(".btn-primary");
const perfilSelect = document.getElementById("perfil");
const correoInput = document.getElementById("correo");
const passwordInput = document.getElementById("password");

botonIngresar.addEventListener("click", (event) => {
    event.preventDefault(); 

    const perfil = perfilSelect.value;
    const correo = correoInput.value.trim();
    const password = passwordInput.value.trim();

   if (perfil === "Seleccione" || !correo || !password) {
        alert("Por favor, complete todos los campos.");
        return;
    }

    if (!validarCorreo(correo)) {
        alert("Ingrese un correo válido.");
        return;
    }

    switch (perfil) {
    case "1": // Usuario
        window.location.href = "perfil-usuario.html";
        break;
    case "2": // Médico
        window.location.href = "perfil-medico.html";
        break;
    case "3": // Auxiliar
        window.location.href = "perfil-auxiliar.html";
        break;
    case "4": // Farmacéutico
        window.location.href = "perfil-farmaceutico.html";
        break;
    default:
        alert("Rol no reconocido.");
}

});