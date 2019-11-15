package cn.dogoo.common.service;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {
	@Autowired(required=false)
	private ShardedJedisPool pool;
	//set方法无超时
	//set方法有超时
	//exists方法
	//get方法
	//del
	
	
    public void set(String key,String value){
		ShardedJedis jedis = pool.getResource();
		try {
			jedis.set(key, value);			
		} finally {			
			pool.returnResource(jedis);
		}
	}
    
	public void set(String key,String value,Integer seconds){
		ShardedJedis jedis = pool.getResource();
		try {
			jedis.set(key, value);
			jedis.expire(key, seconds);
		} finally {
			pool.returnResource(jedis);
		}	
	}
	
	public Boolean exists(String key){
		ShardedJedis jedis = pool.getResource();
		System.out.println("拿到jedis");
		Boolean exists;
		try {
			exists = jedis.exists(key);
		} finally {
			pool.returnResource(jedis);
			
		}
		return exists;
	}
	
	public String get(String key){
		ShardedJedis jedis = pool.getResource();
		String value;
		 try {
			value = jedis.get(key);
		} finally {
			pool.returnResource(jedis);
		}
		return value;
	}
	
	public void del(String key){
		ShardedJedis jedis = pool.getResource();
		try {
			jedis.del(key);
		} finally {
			pool.returnResource(jedis);
		}
	}
	
	public Long getTime(String key){
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
		} finally {
			pool.returnResource(jedis);
		}
		return jedis.ttl(key);
	}
	
}
