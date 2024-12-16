document.addEventListener("DOMContentLoaded", function () {
           
    function hideAllContainers() {
        document.getElementById("containerExamenes").classList.add("container-hidden");
    }

    
    function showContainer(containerId) {
        hideAllContainers();
        document.getElementById(containerId).classList.remove("container-hidden");
    }

   s
   
    document.getElementById("btnExamenes").addEventListener("click", function () {
        showContainer("containerExamenes");
    });
});
