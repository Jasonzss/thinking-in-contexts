# JPA

## 1. JPA同时使用OneToMany和ManyToOne注解导致的ToString和序列化无限递归

重写其中一个toString方法打破无限递归，使用的是Lombok的话使用@ToString.Exclude注解放在递归属性上

序列化处理同理，使用jackson的@JsonIgnore来排除递归属性

# RocketMQ

## 1. 消息值丢失

consumer接收到的对象不为null，但是内部属性全为null

![](img/img.png)

深入debug后发现是MappingFastJsonMessageConverter这个类在将Message内部的Payload对象转换为字节流的时候出现了问题。字节流最后出来的时候只剩左右括号，而其中的数据不知去向。

解决方案：

修改RocketMqAdapter中的配置，将RocketMQMessageConverter改为MappingJackson2MessageConverter，问题解决！
