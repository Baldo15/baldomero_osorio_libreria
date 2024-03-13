def call(boolean qualityGateAbort = true, boolean abortPipeline = true) {
    echo "Ejecución de las pruebas de calidad de código"
    echo "qualityGateAbort: ${qualityGateAbort}"
    echo "abortPipeline: ${abortPipeline}"
    
    withSonarQubeEnv('SonarQube Server') {
        timeout(time: 5, unit: 'MINUTES') {
            // Puedes sustituir este bloque con el comando real de SonarQube si decides usarlo
            // sh 'sonar-scanner' (o cualquier comando de SonarQube)
            echo "Simulación de escaneo de SonarQube"
        }
    }
    
    if (qualityGateAbort) {
        // Puedes agregar aquí la lógica para evaluar el QualityGate de SonarQube
        // Ejemplo: sh 'sonar-scanner -Dsonar.qualitygate.wait=true'
        echo "Evaluando QualityGate..."
        // Si el QualityGate no pasa, abortar el pipeline
        error "QualityGate no aprobado. Abortando el pipeline."
        return
    }
    
    if (abortPipeline) {
        error "Abortando el pipeline según el parámetro proporcionado."
        return
    }
}

