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