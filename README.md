# netty-example

学习`netty`，参考`<<Netty权威指南>>`

## 包介绍

1.回顾：io -> paio -> nio -> aio，搭建一个时间服务器，客户端请求当前时间

* `example.io` 一个同步阻塞`TimeServer`，一个连接一个线程处理
* `example.paio` 伪异步`TimeServer`，使用一个线程池处理连接
* `example.nio`非阻塞`TimeServer`
* `example.aio`异步`TimeServer`

2.nio和aio使用太复杂。下面使用netty:

* `example.netty`使用netty实现TimeServer

3.TCP拆包粘包

* `example.tcp.pk`TCP拆包粘包引发的问题
* `example.tcp.pk.solve`利用Netty的`LineBasedFrameDecoder`解决TCP拆包粘包问题

`LineBasedFrameDecoder`一次遍历ByteBuf中的字符，如果是\n或\r\n,就以此作为结束位置。它是依赖换行符为结束标志的解码器

`StringDecoder`将接收的对象那个转为字符串，然后继续调用后面的handler