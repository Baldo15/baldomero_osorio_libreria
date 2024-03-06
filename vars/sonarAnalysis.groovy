def call(boolean abortPipeline = false, boolean qualityGateAbort = false) {
    echo "Ejecución de las pruebas de calidad de código"

    script {
        withSonarQubeEnv('SonarQubeServer') {
            def scannerHome = tool 'SonarScanner';
            def scannerCmd = "${scannerHome}/bin/sonar-scanner"

            sh "${scannerCmd} -Dsonar.projectKey=myProjectKey -Dsonar.sources=src"
            def qualityGateResult = waitForQualityGate()
            echo "QualityGate Result: ${qualityGateResult}"

            if (qualityGateAbort && qualityGateResult != 'OK') {
                error "QualityGate de SonarQube falló. Abortando el pipeline."
            }
        }
        if (abortPipeline) {
            error "Abortando el pipeline según el parámetro proporcionado."
        }
    }
}
