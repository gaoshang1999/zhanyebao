package com.heverage.zhanyebao.util;

public interface OptionCallbackListener {
	
	public int getKey();
	
	public void callback(int key, String value);
}
