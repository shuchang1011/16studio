package com.util;

import java.net.URL;

import org.springframework.stereotype.Component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class EhcacheUtil {

	private static final String path = "/ehcache-shiro.xml";

	private URL url;

	private CacheManager manager;

	private static EhcacheUtil ehCache;

	public EhcacheUtil(String path) {
		System.out.println("path:"+path);
		url = getClass().getResource(path);
		System.out.println("url:"+url);
		manager = CacheManager.create(url);
	}

	public static EhcacheUtil getInstance() {
		if (ehCache== null) {
			ehCache= new EhcacheUtil(path);
		}
		return ehCache;
	}

	public void put(String cacheName, String key, Object value) {
		Cache cache = manager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	public Object get(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public Cache get(String cacheName) {
		return manager.getCache(cacheName);
	}

	public void remove(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		cache.remove(key);
	}

}
