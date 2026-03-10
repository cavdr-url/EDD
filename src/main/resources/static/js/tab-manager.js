/**
 * Script para manejar el estado de las pestañas (tabs) en el panel de administración y usuario.
 * Mantiene la pestaña activa después de guardar un registro.
 */

document.addEventListener('DOMContentLoaded', function() {
    // Restaurar pestaña activa desde URL hash
    var hash = window.location.hash.substring(1);
    if (hash) {
        var tabTrigger = document.querySelector('a[href="#' + hash + '"]');
        if (tabTrigger) {
            var tab = new bootstrap.Tab(tabTrigger);
            tab.show();
        }
    }
    
    // Agregar campo oculto de tab a todos los formularios
    document.querySelectorAll('form').forEach(function(form) {
        // Verificar si ya tiene campo tab
        if (!form.querySelector('input[name="tab"]')) {
            var tabInput = document.createElement('input');
            tabInput.type = 'hidden';
            tabInput.name = 'tab';
            // Determinar tab actual basado en la pestaña activa
            var activeTab = document.querySelector('.nav-tabs .nav-link.active');
            if (activeTab) {
                var tabId = activeTab.getAttribute('href').substring(1);
                tabInput.value = tabId;
            } else {
                tabInput.value = 'mascotas';
            }
            form.appendChild(tabInput);
        }
    });
    
    // Actualizar valor del campo tab cuando se cambia de pestaña
    document.querySelectorAll('.nav-tabs .nav-link').forEach(function(tabLink) {
        tabLink.addEventListener('shown.bs.tab', function(e) {
            var tabId = e.target.getAttribute('href').substring(1);
            document.querySelectorAll('input[name="tab"]').forEach(function(input) {
                input.value = tabId;
            });
        });
    });
});

