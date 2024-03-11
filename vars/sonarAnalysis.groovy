def call(boolean qualityGateAbort = false, boolean abortPipeline = false) {
    echo "Ejecución de las pruebas de calidad de código"

    // Puedes sustituir este bloque con la lógica de SonarQube si decides usarlo
    // sh 'sonar-scanner' (o cualquier comando de SonarQube)
    
    // Mantén el sonarenv y el timeout
    withSonarQubeEnv('SonarQube Server') {
        timeout(time: 5, unit: 'MINUTES') {
            // Puedes sustituir este bloque con el comando real de SonarQube si decides usarlo
            // sh 'sonar-scanner' (o cualquier comando de SonarQube)
            echo "Simulación de escaneo de SonarQube"
        }
    }

    // Lógica para evaluar el QualityGate y abortar el pipeline según los parámetros
    if (qualityGateAbort) {
        // Puedes agregar aquí la lógica para evaluar el QualityGate de SonarQube
        // Ejemplo: sh 'sonar-scanner -Dsonar.qualitygate.wait=true'
        echo "Evaluando QualityGate..."
        // Si el QualityGate no pasa, abortar el pipeline
        error "QualityGate no aprobado. Abortando el pipeline."
    }

    // Abortar el pipeline según el parámetro recibido
    if (abortPipeline) {
        error "Abortando el pipeline según el parámetro proporcionado."
    }
}
