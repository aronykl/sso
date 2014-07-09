/**
 * Copyright (c) 2011-2014, hubin (243194995@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.tuzip.sso.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tuzip.sso.SSOConfig;
import com.github.tuzip.sso.SSOToken;
import com.github.tuzip.sso.Token;
import com.github.tuzip.sso.TokenCache;
import com.github.tuzip.sso.TokenCacheMap;
import com.github.tuzip.sso.common.encrypt.AES;
import com.github.tuzip.sso.common.encrypt.Encrypt;
import com.github.tuzip.sso.exception.KissoException;

/**
 * 反射工具类
 * <p>
 * @author   hubin
 * @Date	 2014-6-27 	 
 */
public class ReflectUtil {
	private final static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

	/**
	 * 反射获取自定义Encrypt
	 * @return
	 */
	public static Encrypt getConfigEncrypt() {
		/**
		 * 判断是否自定义 Encrypt
		 * 默认 AES
		 */
		Encrypt encrypt = null;
		if("".equals(SSOConfig.getEncryptClass())){
			encrypt = new AES();
		} else {
			try {
				Class<?> tc = Class.forName(SSOConfig.getEncryptClass());
				try {
					if(tc.newInstance() instanceof Encrypt) {
						encrypt = (Encrypt) tc.newInstance();
					} else {
						new KissoException(SSOConfig.getEncryptClass() + " not instanceof Encrypt.");
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logger.error("sso.encrypt.class. error..! " + SSOConfig.getEncryptClass());
			}
		}
		return encrypt;
	}
	
	/**
	 * 反射获取自定义Token
	 * @return
	 */
	public static Token getConfigToken() {
		/**
		 * 判断是否自定义 Token
		 * 默认 SSOToken
		 */
		Token token = null;
		if("".equals(SSOConfig.getTokenClass())){
			token = new SSOToken();
		} else {
			try {
				Class<?> tc = Class.forName(SSOConfig.getTokenClass());
				try {
					if(tc.newInstance() instanceof Token) {
						token = (Token) tc.newInstance();
					} else {
						new KissoException(SSOConfig.getTokenClass() + " not instanceof Token.");
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logger.error("sso.token.class. error..! " + SSOConfig.getTokenClass());
			}
		}
		return token;
	}
	

	/**
	 * 反射获取自定义TokenCache
	 * @return
	 */
	public static TokenCache getConfigTokenCache() {
		/**
		 * 判断是否自定义 TokenCache
		 * 默认 TokenCacheMap
		 */
		TokenCache tokenCache = null;
		if("".equals(SSOConfig.getTokenCacheClass())){
			tokenCache = new TokenCacheMap();
		} else {
			try {
				Class<?> tc = Class.forName(SSOConfig.getTokenCacheClass());
				try {
					if(tc.newInstance() instanceof TokenCache) {
						tokenCache = (TokenCache) tc.newInstance();
					} else {
						new KissoException(SSOConfig.getTokenCacheClass() + " not instanceof TokenCache.");
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logger.error("sso.tokencache.class. error..! " + SSOConfig.getTokenCacheClass());
			}
		}
		return tokenCache;
	}
}
