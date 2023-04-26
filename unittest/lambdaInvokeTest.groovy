//startfile
this.tests = [
    "Null map returns error": {
        ->
        def error = false

        try{
            lambdaInvoke((Map)null)
        } catch(Exception e){
            error = true
        }

        assert error == true
    },
    "Empty map returns error": {
        ->
        def error = false

        try{
            lambdaInvoke((Map)"")
        } catch(Exception e){
            error = true
        }

        assert error == true
    },
    "Missing parameter functionName returns error": {
        ->
        def error = false

        try{
            lambdaInvoke([awsRegion: "test"])
        } catch(Exception e){
            error = true
            assert e.getMessage() == "required parameter: functionName not provided or was empty"
        }

        assert error == true
    },
    "Missing parameter awsRegion returns error": {
        ->
        def error = false

        try{
            lambdaInvoke([functionName: "test"])
        } catch(Exception e){
            error = true
            assert e.getMessage() == "required parameter: awsRegion not provided or was empty"
        }

        assert error == true
    },
]

return this