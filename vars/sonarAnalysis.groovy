def call(Map params) {
    echo "Ejecución de las pruebas de calidad de código"
    if (params.abortPipeline) {
        error "Abortando el pipeline según el parámetro proporcionado."
    }
}
