function nextSection(sectionId) {
    // Oculta todas las secciones
    const sections = document.querySelectorAll('.card');
    sections.forEach(section => section.classList.add('hidden'));

    // Muestra la sección especificada
    document.getElementById(`section-${sectionId}`).classList.remove('hidden');
}

function redirectToLogin() {
    
    window.location.href = "login.html"; 
}