- [Language Guide (proto3)](https://developers.google.com/protocol-buffers/docs/proto3)
- [gRPC Java Tutorials](https://grpc.io/docs/tutorials/basic/java.html)
- [gRPC-Java - An RPC library and framework](https://github.com/grpc/grpc-java)

```sh
sh build.sh
```

```sh
mvn exec:java -Dexec.mainClass="org.feuyeux.given.proto.server.ProtoServer"
```

```sh
mvn exec:java -Dexec.mainClass="org.feuyeux.given.proto.client.ProtoClient"
```
