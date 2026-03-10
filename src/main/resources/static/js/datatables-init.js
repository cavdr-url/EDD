/**
 * Inicialización automática de DataTables
 * Este script detecta todas las tablas en la página y las inicializa automáticamente
 */

$(document).ready(function() {
    
    // Configuración de idioma
    var languageConfig = {
        url: 'https://cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json'
    };
    
    // Configuración de botones de exportación
    var createButtons = function(title, hideActions) {
        var exportOptions = hideActions ? { columns: ':not(.export-hidden)' } : {};
        return [
            {
                extend: 'print',
                text: '<i class="fas fa-print"></i> Imprimir',
                className: 'btn btn-secondary btn-sm',
                title: '',
                exportOptions: exportOptions,
                customize: function(win) {
                    $(win.document.body).prepend('<h2>' + title + '</h2>');
                }
            },
            {
                extend: 'excel',
                text: '<i class="fas fa-file-excel"></i> Excel',
                className: 'btn btn-success btn-sm',
                title: title,
                exportOptions: exportOptions
            },
            {
                extend: 'pdf',
                text: '<i class="fas fa-file-pdf"></i> PDF',
                className: 'btn btn-danger btn-sm',
                title: title,
                exportOptions: exportOptions
            }
        ];
    };
    
    // Obtener título de la tabla basado en el ID
    var getTableTitle = function(tableId) {
        var titles = {
            'mascotasTable': 'Listado de Mascotas',
            'vacunasTable': 'Catálogo de Vacunas',
            'aplicacionesTable': 'Aplicación de Vacunas',
            'disponiblesTable': 'Disponibles para Adopción',
            'adoptadasTable': 'Mascotas Adoptadas'
        };
        return titles[tableId] || 'Listado';
    };
    
    // Verificar si la tabla tiene columna de acciones
    var hasActionsColumn = function(tableId) {
        var table = document.getElementById(tableId);
        if (!table) return false;
        var headers = table.querySelectorAll('thead th');
        for (var i = 0; i < headers.length; i++) {
            var text = headers[i].textContent.toLowerCase();
            if (text.includes('acción') || text.includes('acciones') || text.includes('cogs')) {
                return true;
            }
        }
        return false;
    };
    
    // Determinar si es el panel de usuario (sin columnDefs de exportación)
    var isUserPanel = document.querySelector('.nav-tabs#userTabs') !== null;
    
    // Inicializar cada tabla encontrada
    var tableIds = ['mascotasTable', 'vacunasTable', 'aplicacionesTable', 'disponiblesTable', 'adoptadasTable'];
    
    tableIds.forEach(function(tableId) {
        var table = document.getElementById(tableId);
        if (table && !$.fn.DataTable.isDataTable('#' + tableId)) {
            var title = getTableTitle(tableId);
            var hideActions = hasActionsColumn(tableId) && !isUserPanel;
            
            var config = {
                language: languageConfig,
                dom: 'Blfrtip',
                buttons: createButtons(title, hideActions)
            };
            
            // Agregar columnDefs solo para admin panel
            if (hideActions) {
                config.columnDefs = [{
                    targets: -1,
                    exportOptions: { columns: ':not(.export-hidden)' }
                }];
            }
            
            $('#' + tableId).DataTable(config);
        }
    });
});

