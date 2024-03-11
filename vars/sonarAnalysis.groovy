def call(boolean abortPipeline = false, boolean qualityGateAbort = false) {
    // Mensaje de inicio de la función
    echo "Iniciando la función call"

    script {
        // Sección de configuración de SonarQube
        withSonarQubeEnv('SonarQubeServer') {
            // Configuración del ejecutable SonarScanner
            def scannerHome = tool 'SonarScanner';
            def scannerCmd = "${scannerHome}/bin/sonar-scanner"

            // Ejecución de SonarScanner con configuraciones específicas
            sh "${scannerCmd} -Dsonar.projectKey=myProjectKey -Dsonar.sources=src"

            // Espera para obtener el resultado del Quality Gate
            def qualityGateResult = waitForQualityGate()

            // Mensaje con el resultado del Quality Gate
            echo "QualityGate Result: ${qualityGateResult}"

            // Verificación y posible aborto del pipeline según la configuración
            if (qualityGateAbort && qualityGateResult != 'OK') {
                error "QualityGate de SonarQube falló. Abortando el pipeline."
            }
        }

        // Verificación y posible aborto del pipeline según la configuración
        if (abortPipeline) {
            error "Abortando el pipeline según el parámetro proporcionado."
        }
    }

    // Mensaje de finalización de la función
    echo "Finalizando la función call"
}
