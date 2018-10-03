Minimalistic project to kick-start a Fn Flow demo. See [Flow 101 tutorial](https://github.com/fnproject/tutorials/tree/master/Flow101) for the concepts. Be aware that there has been some changes since this tutorial has been written. Given that, make sure to have a _recent version_ of the various components involved, i.e. FDK 1.0.69+, Fn Server v0.3.591+ and a recent Flow Server.


* Start Fn
 
```fn start```   

* Start the Flow Server and the (optional) UI.

```./flow_setup.sh``` 

This will also set the *COMPLETER_BASE_URL* for the *"myapp"* App via `fn config app myapp COMPLETER_BASE_URL "http://172.17.0.3:8081"`

* Create and deploy a function that will be invoked from the Flow function.

```
fn init --runtime java duke
fn deploy --local --app myapp duke
``` 

* Update the Flow (invoking) function with the FunctionId of the invoked function.

```
fn inspect function myapp duke
```

* Deploy and invoke the Flow function.

```fn deploy --local --app myapp simpleflow
echo -n "John" | fn invoke myapp simpleflow```
