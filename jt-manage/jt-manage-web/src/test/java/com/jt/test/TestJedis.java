/*package com.jt.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class TestJedis {
	@Test
	public void jedis(){
		Jedis jedis=new Jedis("192.168.174.30",6379);
		jedis.set("name", "lee");
		System.out.println(jedis.get("name")+"\n"+jedis.get("666"));
		jedis.close();
	
	}
	
	@Test
	public void jedises(){
		List<JedisShardInfo> listInfo=new ArrayList<JedisShardInfo>();
		JedisShardInfo info1=new JedisShardInfo("192.168.174.30",6379);
		JedisShardInfo info2=new JedisShardInfo("192.168.174.30",6380);
		JedisShardInfo info3=new JedisShardInfo("192.168.174.30",6381);
		listInfo.add(info1);
		listInfo.add(info2);
		listInfo.add(info3);
		
		ShardedJedis jedis=new ShardedJedis(listInfo);
		
		for(int i=0;i<100;i++){
			jedis.set("name"+i, "tony"+i);
			
		}


		jedis.close();
	
	}
	
	@Test
	public void sentinel(){
		Set<String> sentinels=new HashSet<String>();//多个哨兵信息
		sentinels.add(new HostAndPort("192.168.174.30",26379).toString());
		sentinels.add(new HostAndPort("192.168.174.30",26380).toString());
		JedisSentinelPool poll=new JedisSentinelPool("mymaster",sentinels);
		//从池中获取一个连接
		Jedis jedis = poll.getResource();
		//jedis.set("name", "swan");
		System.out.println(jedis.get("name"));
		//把用完的连接还回池中
		poll.returnResource(jedis);
		poll.destroy();
	}

}                                      
*/