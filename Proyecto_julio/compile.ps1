#
# Script para compilar automáticamente todos los archivos .ui a .py
#

# --- Configuración ---
# Directorio donde se encuentran los archivos .ui originales
$sourceDir = ".\view\ui"
# Directorio donde se guardarán los archivos .py compilados
$outputDir = ".\view"

# --- Lógica del Script ---
# Buscar todos los archivos que terminan en .ui en el directorio de origen
$uiFiles = Get-ChildItem -Path $sourceDir -Filter *.ui

if ($uiFiles.Count -eq 0) {
    Write-Host "No se encontraron archivos .ui en '$sourceDir'."
    exit
}

Write-Host "Iniciando compilación de archivos de interfaz..."

foreach ($file in $uiFiles) {
    # Construir el nombre del archivo de salida
    # Ejemplo: workerform.ui  ->  ui_workerform.py
    $outputFileName = "ui_" + $file.BaseName + ".py"
    $outputFilePath = Join-Path -Path $outputDir -ChildPath $outputFileName
    
    # Construir el comando completo
    $command = "pyside6-uic $($file.FullName) -o $outputFilePath"
    
    Write-Host "Compilando '$($file.Name)' -> '$outputFileName'"
    
    # Ejecutar el comando de compilación
    Invoke-Expression $command
}

Write-Host "Compilación finalizada con éxito."