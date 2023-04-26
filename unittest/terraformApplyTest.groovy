//startfile
this.tests = [
    "Null map returns error": {
        ->
        def error = false

        try{
            terraformApply((Map)null)
        } catch(Exception e){
            error = true
            echo "null map error: "+e.getMessage()
            assert e.getMessage() == "Cannot get property 'terraformDir' on null object"
        }

        assert error == true
    },
    "Empty map returns error": {
        ->
        def error = false

        try{
            terraformApply((Map)"")
        } catch(Exception e){
            error = true
        }

        assert error == true
    },
    "Missing parameter terraformPlanFile returns error": {
        ->
        def error = false

        try{
            def params = [terraformDir: "."]
            terraformApply(params)
        } catch(Exception e){
            error = true
            echo "exception: "+getMessage()
            assert e.getMessage() == "required parameter: terraformPlanFile not provided or was empty"
        }

        assert error == true
    },
]

return this