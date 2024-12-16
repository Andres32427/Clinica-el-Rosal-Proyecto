 // Cambia el estado dinámicamente
       // function toggleStatus() {
         //   const statusElement = document.getElementById('status');
           // if (statusElement.innerText === 'Activo') {
             //   statusElement.innerText = 'Inactivo';
               // statusElement.className = 'badge bg-danger';
            //}
             //else {
              //  statusElement.innerText = 'Activo';
               // statusElement.className = 'badge bg-success';
            //}
        //}

        // Ordena la tabla por columna
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

        // Confirmar antes de descargar
        function confirmDownload(event) {
            event.preventDefault();
            if (confirm("¿Deseas descargar este documento?")) {
                window.location.href = event.target.href;
            }
        }