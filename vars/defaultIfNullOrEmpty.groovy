// startfile
/**
* @description Provides utility function to check the nullability or empty string which sets the value to default
*
* @param String val - current value
* @param String defaultVal - default value in case of nullable or empty values
*
* @note Utility returns defaultVal if val is null or empty
*
* @sample
*   defaultIfNullOrEmpty(
*       params["repositoryUser"], "AWS"
*   )
*/

def call(val, defaultVal) {
    log currentValue: "${val}", default: "${defaultVal}"
    if (val == null || "NULL".equalsIgnoreCase(val.toString()) || val == "") {
        return defaultVal
    }

    return val
}