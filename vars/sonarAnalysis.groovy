def call(boolean qualityGateAbort = true) {
    echo "Ejecución de las pruebas de calidad de código"
    echo "qualityGateAbort: ${qualityGateAbort}"
    
    def branchName = env.BRANCH_NAME 
    
    if (qualityGateAbort) {
        error "Abortando el pipeline debido a la configuración."
        return
    }

    if (branchName == 'master' || branchName.startsWith('hotfix')) {
        error "Abortando el pipeline debido al nombre de la rama: $branchName"
        return
    }
    echo "Continuando la ejecución del pipeline para la rama: $branchName"
}
