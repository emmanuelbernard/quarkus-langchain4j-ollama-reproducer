# Test Ollama

`quarkus dev`

Should lead to 

```
Caused by: io.netty.channel.AbstractChannel$AnnotatedConnectException: Connection refused: localhost/127.0.0.1:11434
Caused by: java.net.ConnectException: Connection refused
        at java.base/sun.nio.ch.Net.pollConnect(Native Method)
        at java.base/sun.nio.ch.Net.pollConnectNow(Net.java:682)
        at java.base/sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:1060)
        at io.netty.channel.socket.nio.NioSocketChannel.doFinishConnect(NioSocketChannel.java:337)
        at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe.finishConnect(AbstractNioChannel.java:339)
        at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:776)
        at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:724)
        at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:650)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:562)
        at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:1570)
```

if ollama is not started.

And `quarkus dev` with ollama empty of models (rename `./ollama/models`)

```
2024-07-23 15:15:59,762 INFO  [io.qua.lan.oll.OllamaRestApi$OllamaLogger] (vert.x-eventloop-thread-5) Request:
- method: POST
- url: http://localhost:11434/api/embeddings
- headers: [Accept: application/json], [Content-Type: application/json], [User-Agent: Quarkus REST Client], [content-length: 99]
- body: {
  "model" : "nomic-embed-text",
  "prompt" : "# Test Ollama\n\n`quarkus dev`\n\nShould lead to"
}

2024-07-23 15:15:59,765 INFO  [io.qua.lan.oll.OllamaRestApi$OllamaLogger] (vert.x-eventloop-thread-5) Response:
- status code: 404
- headers: [Content-Type: application/json; charset=utf-8], [Date: Tue, 23 Jul 2024 13:15:59 GMT], [Content-Length: 70]
- body: {"error":"model \"nomic-embed-text\" not found, try pulling it first"}

org.jboss.resteasy.reactive.ClientWebApplicationException: Received: 'Not Found, status code 404' when invoking: Rest Client method: 'io.quarkiverse.langchain4j.ollama.OllamaRestApi#embeddings'
        at org.jboss.resteasy.reactive.client.impl.RestClientRequestContext.unwrapException(RestClientRequestContext.java:195)
        at org.jboss.resteasy.reactive.common.core.AbstractResteasyReactiveContext.handleException(AbstractResteasyReactiveContext.java:329)
        at org.jboss.resteasy.reactive.common.core.AbstractResteasyReactiveContext.run(AbstractResteasyReactiveContext.java:175)
        at io.smallrye.context.impl.wrappers.SlowContextualRunnable.run(SlowContextualRunnable.java:19)
        at org.jboss.resteasy.reactive.client.handlers.ClientSwitchToRequestContextRestHandler$1$1.handle(ClientSwitchToRequestContextRestHandler.java:38)
        at org.jboss.resteasy.reactive.client.handlers.ClientSwitchToRequestContextRestHandler$1$1.handle(ClientSwitchToRequestContextRestHandler.java:35)
        at io.vertx.core.impl.ContextInternal.dispatch(ContextInternal.java:279)
        at io.vertx.core.impl.ContextInternal.dispatch(ContextInternal.java:261)
        at io.vertx.core.impl.ContextInternal.lambda$runOnContext$0(ContextInternal.java:59)
        at io.netty.util.concurrent.AbstractEventExecutor.runTask(AbstractEventExecutor.java:173)
        at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:166)
        at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:470)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:566)
        at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:1570)
Caused by: jakarta.ws.rs.WebApplicationException: Not Found, status code 404
        at io.quarkus.rest.client.reactive.runtime.DefaultMicroprofileRestClientExceptionMapper.toThrowable(DefaultMicroprofileRestClientExceptionMapper.java:19)
        at io.quarkus.rest.client.reactive.runtime.MicroProfileRestClientResponseFilter.filter(MicroProfileRestClientResponseFilter.java:52)
        at org.jboss.resteasy.reactive.client.handlers.ClientResponseFilterRestHandler.handle(ClientResponseFilterRestHandler.java:21)
        at org.jboss.resteasy.reactive.client.handlers.ClientResponseFilterRestHandler.handle(ClientResponseFilterRestHandler.java:10)
        at org.jboss.resteasy.reactive.common.core.AbstractResteasyReactiveContext.invokeHandler(AbstractResteasyReactiveContext.java:231)
        at org.jboss.resteasy.reactive.common.core.AbstractResteasyReactiveContext.run(AbstractResteasyReactiveContext.java:147)
        ... 14 more
```
