def call(Map params) {
    log.info(functionName: '-----zipFile - Entry-----')
    log.info(params: params)

    def targetDir = params['targetDir']
    def zipFileName = firstNonNull(params["zipFileName"], 'package.zip')
    def zipArgs = firstNonNull(params["zipArgs"], "")

    def zipOutput = sh(script: "zip -r ${zipFileName} ${targetDir}/* ${zipArgs}")
    log.info output: "Zip output: ${zipOutput}"

    def shaOutput = sh(script: "openssl dgst -sha256 -binary ${zipFileName} | openssl enc -A -base64 > ${targetDir}", returnStdout: true)
    log.info output: "SHA output: ${shaOutput}"

    log.info(functionName: '-----zipFile - Exit-----')
}