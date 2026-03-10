// Auto-establecer la fecha actual en campos de fecha
document.addEventListener('DOMContentLoaded', function() {
    // Array de IDs de campos de fecha que necesitan fecha automática
    const dateFields = ['fechaIngreso', 'fechaAplicacion', 'proximaFechaRefuerzo'];
    
    dateFields.forEach(function(fieldId) {
        const dateInput = document.getElementById(fieldId);
        
        // Si el campo está vacío, establecer la fecha actual
        if (dateInput && !dateInput.value) {
            const hoy = new Date();
            const year = hoy.getFullYear();
            const month = String(hoy.getMonth() + 1).padStart(2, '0');
            const day = String(hoy.getDate()).padStart(2, '0');
            dateInput.value = `${year}-${month}-${day}`;
        }
    });
});

