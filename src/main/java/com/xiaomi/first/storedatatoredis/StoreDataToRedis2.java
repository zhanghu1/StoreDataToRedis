package com.xiaomi.first.storedatatoredis;

//import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

/**
 * Created by Administrator on 2015/7/15.
 */
public class StoreDataToRedis2 {
//    private static JedisCluster jc = null;
    private static Jedis jedis = null;
    private static String key = "zhanghu1";
    private static String IP = "10.235.218.90";
    private static int port = 6379;
    private static String FILE_PATH = ".\\testData";

    public static void setup() {
//        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//
//        jedisClusterNodes.add(new HostAndPort(IP, port));
//
//        jc = new JedisCluster(jedisClusterNodes);

        jedis = new Jedis(IP, port);

        jedis.del(key);
    }

    public static void addDataToRedis(String str) {
//        jedis.del(key);

        jedis.rpush(key, str);

//        jedis.set(key, "123456");
    }

    public static void testList() {
        List<String> values = jedis.lrange(key, 0, -1);

        System.out.println(values);
    }

    public static void main(String[] args) {
        setup();

        try {
            FileReader reader = new FileReader(FILE_PATH);

            BufferedReader br = new BufferedReader(reader);

            String dataString = null;

            try {
                while ((dataString = br.readLine()) != null) {
                    System.out.println(dataString);

                    addDataToRedis(dataString);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
