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

* Get the *FunctionId* of the invoked function using the CLI and [update the (invoking) Flow function](https://github.com/delabassee/fn-flow-base/blob/becac483f0a8aa75cbd22b74f18893217b069926/simpleflow/src/main/java/com/example/fn/HelloFunction.java#L17-L22) accordingly.

```
fn inspect function myapp duke
```

```json
{
	"annotations": {
		"fnproject.io/fn/invokeEndpoint": "http://localhost:8080/invoke/01CQY2SYX7NG8G00GZJ0000002"
	},
	"app_id": "01CQY2SYWCNG8G00GZJ0000001",
	"created_at": "2018-09-21T12:48:51.111Z",
	"format": "http-stream",
	"id": "01CQY2SYX7NG8G00GZJ0000002",
	"idle_timeout": 30,
	...
}
```

* Deploy and invoke the Flow function.

```
fn deploy --local --app myapp simpleflow
echo -n "John" | fn invoke myapp simpleflow
```
