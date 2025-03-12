document.addEventListener("DOMContentLoaded", function() {
                
    const citasBtn = document.querySelector('a[href="#"]:nth-child(1)');
    const diagnosticoBtn = document.querySelector('a[href="#"]:nth-child(2)');
    const medicamentosBtn = document.querySelector('a[href="#"]:nth-child(3)');
    const afiliacionBtn = document.querySelector('a[href="#"]:nth-child(4)');

   
    citasBtn.addEventListener("click", function(event) {
        event.preventDefault(); 
        window.location.href = "citas-medicas.html"; 
    });

    diagnosticoBtn.addEventListener("click", function(event) {
        event.preventDefault();
        window.location.href = "diagnostico-resultados.html"; 
    });

    afiliacionBtn.addEventListener("click", function(event) {
        event.preventDefault();
        window.location.href = "estado-afiliacion.html";
    });
});