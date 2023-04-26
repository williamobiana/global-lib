// startfile
/**
* @description Provides a declarative pipeline capability for invoking function and returning lambda output JSON output
*
* @param String functionName - name of the function
* @param String awsRegion - AWS region
* @param Map payload - key/value payload that will be passed to the lambda via the event object (optional)
*
* @sample
*   lambdaInvoke(
*       functionName: 'example-lambda',
*       awsRegion: 'us-east-1',
*       payload: [
*           key: "value"
*       ]
*   )
*/

import groovy.json.JsonOutput
def call(Map params = [:]) {
    log.info(functionName: '-----lambdaInvoke - Entry-----')
    log.info params: params

    def functionName = required(params, 'functionName')
    def awsRegion = required(params, 'awsRegion')
    def payload = defaultIfNullOrEmpty(params['payload'], '')
    def payloadJson = JsonOutput.toJson(payload)

    def invokeLambda = sh(script: """
                    aws lambda invoke \
                    --function-name ${functionName} \
                    --invocation-type 'RequestResponse' \
                    --payload '${payloadJson}' \
                    --region '${awsRegion}' \
                    /tmp/response.json""", returnStatus: true)

    if (invokeLambda == 0) {
        def responseJson = readJSON file: "response.Json"
        if (responseJson.errorMessage) {
            error("Failed to invoke Lambda function: ${responseJson.errorMessage}")
        }
        return responseJson
    } else {
        error("Failed to invoke Lambda function: exit code ${invokeLambda}")
    }

    log.info(functionName: '-----lambdaInvoke - Exit-----')
}
