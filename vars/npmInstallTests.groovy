this.tests = [
    "Null map returns error": {
        ->
        def error = false
        try {
            npmInstall((Map) null)
        } catch(Exception e) {
            error = true
        }
        assert error == true
    }
]

return this