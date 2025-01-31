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