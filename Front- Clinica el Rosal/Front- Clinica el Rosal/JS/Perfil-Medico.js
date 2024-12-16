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