def call(boolean qualityGateAbort = false, boolean abortPipeline = false) {
    echo "Ejecución de las pruebas de calidad de código"
           
    withSonarQubeEnv('SonarQube Server') {
        timeout(time: 5, unit: 'MINUTES') {
            echo "Simulación de escaneo de SonarQube"
        }
    }
    
    if (qualityGateAbort) {
        echo "Evaluando QualityGate..."
        error "QualityGate no aprobado. Abortando el pipeline."
        return
    }
    
    if (abortPipeline) {
        error "Abortando el pipeline según el parámetro proporcionado."
        return
    }
}
