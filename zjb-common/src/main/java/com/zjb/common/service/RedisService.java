package com.zjb.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Service
public class RedisService {
	
	//引入哨兵的配置
	@Autowired(required=false)
	private JedisSentinelPool sentinelPool;
	
	public void set(String key,String value){
		Jedis jedis = sentinelPool.getResource();
		jedis.set(key, value);
		sentinelPool.returnResource(jedis);
	}
	
	public void set(String key,String value,int seconds){
		Jedis jedis = sentinelPool.getResource();
		jedis.setex(key, seconds, value);
		sentinelPool.returnResource(jedis);
	}
	
	public String get(String key){
		Jedis jedis = sentinelPool.getResource();
		String result = jedis.get(key);
		sentinelPool.returnResource(jedis);
		return result;
	}
	
	
	
	
	
	

    /*@Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;

    
    public void set(String key,String value){
    	ShardedJedis jedis = 
    			shardedJedisPool.getResource();
    	
    	jedis.set(key, value);
    	shardedJedisPool.returnResource(jedis);
    }
    
    //添加超时时间
    public void set(String key,String value,int seconds){
    	ShardedJedis jedis = 
    			shardedJedisPool.getResource();
    	
    	jedis.setex(key, seconds, value);
    	shardedJedisPool.returnResource(jedis);
    }
    
    //编辑get方法
    public String get(String key){
    	ShardedJedis jedis = 
    			shardedJedisPool.getResource();
    	String result = jedis.get(key);
    	shardedJedisPool.returnResource(jedis);
    	return result;
    }*/
}
