# JPA

## 1. JPA同时使用OneToMany和ManyToOne注解导致的ToString和序列化无限递归

重写其中一个toString方法打破无限递归，使用的是Lombok的话使用@ToString.Exclude注解放在递归属性上

序列化处理同理，使用jackson的@JsonIgnore来排除递归属性
