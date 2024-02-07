package com.jason.tics.common.cache.jedis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @author Jason
 * @since 2023/09/05 - 12:20
 */
public class HelloRedisTest {
    Jedis jedis;

    @BeforeEach
    void setUp() {
        // 1.建立连接
        // jedis = new Jedis("192.168.150.101", 6379);
        jedis = JedisConnectionFactory.getJedis();
        // 2.设置密码
        jedis.auth("123456");
        // 3.选择库
        jedis.select(0);
    }

    @Test
    void testHash() {
        // 插入hash数据
        jedis.hset("user:1", "name", "Jack");
        jedis.hset("user:1", "age", "21");

        // 获取
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }

    /**
     * Set 练习：
     *
     * 1. 将下列数据用Redis的Set集合来存储：
     *
     * - 张三的好友有：李四、王五、赵六
     * - 李四的好友有：王五、麻子、二狗
     *
     * 2. 利用Set的命令实现下列功能：
     *
     * - 计算张三的好友有几人
     * - 计算张三和李四有哪些共同好友
     * - 查询哪些人是张三的好友却不是李四的好友
     * - 查询张三和李四的好友总共有哪些人
     * - 判断李四是否是张三的好友
     * - 判断张三是否是李四的好友
     * -
     * - 将李四从张三的好友列表中移除
     */
    @Test
    void testSet(){
        jedis.sadd("张三", "李四", "王五", "赵六");
        jedis.sadd("李四", "王五", "麻子", "二狗");

        //张三的好友有几人
        System.out.println(jedis.scard("张三"));

        //张三和李四有哪些共同好友
        System.out.println(jedis.sinter("张三", "李四"));

        //哪些人是张三的好友却不是李四的好友
        System.out.println(jedis.sdiff("张三", "李四"));

        //张三和李四的好友总共有哪些人
        System.out.println(jedis.sunion("张三", "李四"));

        //李四是否是张三的好友
        System.out.println(jedis.sismember("张三", "李四"));

        //张三是否是李四的好友
        System.out.println(jedis.sismember("李四", "张三"));

        //将李四从张三的好友列表中移除
        System.out.println(jedis.srem("张三", "李四"));
    }

    /**
     * SortedSet 练习：
     *
     * 将班级的下列学生得分存入Redis的SortedSet中：
     *
     * Jack 85, Lucy 94, Rose 82, Tom 95, Jerry 78, Amy 91, Miles 76, Jason 80
     *
     * 并实现下列功能：
     *
     * - 删除Tom同学
     * - 获取Amy同学的分数
     * - 获取Rose同学的排名
     * - 查询80分以下有几个学生
     * - 给Amy同学加2分
     * - 查出成绩前3名的同学
     * - 查出成绩80分以下的所有同学
     */
    @Test
    void testSortedSet(){
        jedis.zadd("score", 85, "Jack");
        jedis.zadd("score", 94, "Lucy");
        jedis.zadd("score", 82, "Rose");
        jedis.zadd("score", 95, "Tom");
        jedis.zadd("score", 78, "Jerry");
        jedis.zadd("score", 91, "Amy");
        jedis.zadd("score", 76, "Miles");
        jedis.zadd("score", 80, "Jason");

        //打印总学生数
        System.out.println(jedis.zcard("score"));

        //从高到低打印学生排名
        System.out.println(jedis.zrevrangeByScore("score", 100, 0));

        //删除tom
        System.out.println(jedis.zrem("score", "tom"));

        //查询Amy的分数
        System.out.println(jedis.zscore("score", "Amy"));

        //获取Rose的排名
        System.out.println(jedis.zrevrank("score", "Rose")+1);

        //查询80分以下学生的人数
        System.out.println(jedis.zcount("score", 0, 80));

        //给Amy +2分
        System.out.println(jedis.zincrby("score", 2, "Amy"));

        //查出前三的同学
        System.out.println(jedis.zrevrangeByScore("score", 100, 0, 0, 3));

        //查出80以下的同学
        System.out.println(jedis.zrangeByScore("score", 0, 79.9));
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
