# 模块信息

1. achievement：成就系统
2. ai：AI聊天
3. api：包括feign和rpc的微服务远程调用
4. auth：基于OAuth2.0授权码模式的认证、授权服务
   1. 授权
5. comment：评论框架，不作为单独的微服务部署。
6. common：通用功能、工具、通用web操作
   1. common-cache：通用缓存服务
   2. common-core
   3. common-rocketmq
   4. common-security
      1. 登录功能
         1. 登陆成功
         2. 登陆失败
      2. 验权功能
7. content：内容的定义和增删改查的服务，按媒体类型分为短文、长文、书籍、视频、歌曲等
8. dictionary：词句翻译的扩展服务，包括单词评论、单词相关资料索引、相似单词匹配等
9. exercise：做练习题
10. file：文件存取服务
11. gateway：网关，对外提供统一的服务
12. learn：纪录用户学习的深度和广度，对单词的学习程度，针对性出单词学习
13. message：站内外消息推送服务
14. platform：后台管理服务
15. point：积分，类似钱包
16. rbac：基于RBAC的用户角色权限管理服务
17. recommend：推荐服务
18. search：搜索服务
19. sentinel：基于sentinel实现的分布式服务管理
20. store：商城，此版只能通过积分兑换购买
21. test：集成测试
22. transaction：全局事务服务
23. translation：翻译服务
24. user：用户服务，注册，信息管理

# 程序端口信息

### 中间件

1. nacos：18848
2. elasticsearch：9200、9300
   1. 账号：elastic
   2. 密码：MuXpvrPmRF2TJ00W5uQ+
3. kibana：5601
4. rocketMqNameServer：9876
5. rocketMqBroker：10909、10911
6. redis：6379

### 微服务

1. tics-translation：18001
2. tics-gateway:18011
3. tics-file:18002
4. tics-search:19200

### 测试

1. tics-test
   1. test-service-a：18901
   2. test-service-b：18902
