Barebone project to kick-start Fn Flow demo. See [Flow 101 tutorial](https://github.com/fnproject/tutorials/tree/master/Flow101) for the concepts. Note that there has been some changes since this tutorial has been written.
 
* Pre-reqs: recent FDK (1.69+), recent Fn Server and recent Flow Server.

* Start Fn
 
```fn start```   

* Start the Flow Server and the UI (optional).

```./flow_setup.sh``` 

* Create and deploy a function that will be invoked from the Flow function

```
fn init --runtime java duke
fn deploy --local --app myapp duke
``` 

* Update the Flow function with the FunctionId of the invoked function

```
fn inspect function myapp duke
```

* Deploy your Flow function

```fn deploy --local --app myapp simpleflow``` 

* Configure your app to talk to the Flow server

```fn config app myapp COMPLETER_BASE_URL "http://172.17.0.3:8081"```


* Invoke the Flow function

```echo -n "John" | fn invoke myapp simpleflow```
