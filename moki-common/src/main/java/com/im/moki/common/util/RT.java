package com.im.moki.common.util;

import java.util.HashMap;
import java.util.Map;

public class RT extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public RT() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static RT error() {
		return error(500, "未知异常!");
	}
	
	public static RT error(String msg) {
		return error(500, msg);
	}
	
	public static RT error(int code, String msg) {
		RT r = new RT();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static RT ok(String msg) {
		RT r = new RT();
		r.put("msg", msg);
		return r;
	}
	
	public static RT ok(Map<String, Object> map) {
		RT r = new RT();
		r.putAll(map);
		return r;
	}
	
	public static RT ok() {
		return new RT();
	}

	@Override
	public RT put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
