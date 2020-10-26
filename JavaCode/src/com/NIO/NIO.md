# Java NIO介绍

Java NIO是 jdk1.4 引入的一套新的IO API，用于替换标准Java IO API。其主要区别是NOI支持面向缓冲区的、基于通道的IO操作。能够以更搞笑的方式进行文件读写操作。

Java API提供了两套NIO，一套是针对标注你输入输出的NIO，另一套就是网络NIO。

|----java.nio.channels.Channel  
    |----FileChannel  
    |----SocketChannel  
    |----ServerSocketChannel  
    |----DataGramChannel  

