function sortTable(columnIndex) {
    const table = document.getElementById("examTable");
    const rows = Array.from(table.rows).slice(1); // Excluir encabezado
    const isNumeric = columnIndex === 1; // Orden numérico para días
    rows.sort((a, b) => {
        const aValue = a.cells[columnIndex].innerText;
        const bValue = b.cells[columnIndex].innerText;
        return isNumeric ? aValue - bValue : aValue.localeCompare(bValue);
    });
    rows.forEach(row => table.tBodies[0].appendChild(row));
}