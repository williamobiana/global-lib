//startfile
this.tests = [
    "Null map returns error": {
        ->
        def error = false

        try{
            terraformInit((Map)null)
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
            terraformInit((Map)"")
        } catch(Exception e){
            error = true
        }

        assert error == true
    }
]

return this