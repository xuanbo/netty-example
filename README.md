# netty-example

学习`netty`，参考`<<Netty权威指南>>`

## 包介绍

回顾：io -> paio -> nio -> aio，搭建一个时间服务器，客户端请求当前时间

* `example.io` 一个同步阻塞`TimeServer`，一个连接一个线程处理
* `example.paio` 伪异步`TimeServer`，使用一个线程池处理连接
* `example.nio`非阻塞`TimeServer`
* `example.aio`异步`TimeServer`

nio和aio使用太复杂。下面使用netty:

* `example.netty`使用netty实现TimeServer

TCP拆包粘包

* `example.tcp.pk`TCP拆包粘包引发的问题
* `example.tcp.pk.solve`利用Netty的`LineBasedFrameDecoder`解决TCP拆包粘包问题