package com.wllfengshu.jbot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 */
public class LogUtil {

	private static Logger logger = null;

	public static void trace(Object obj, String msg, Object... params) {
		if (obj == null) {
			logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		} else {
			logger = LoggerFactory.getLogger(obj.getClass());
		}
		logger.trace(String.format("-->> %s", String.format(msg, params)));
	}

	public static void debug(Object obj, String msg, Object... params) {
		if (obj == null) {
			logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		} else {
			logger = LoggerFactory.getLogger(obj.getClass());
		}
		logger.debug(String.format("-->> %s", String.format(msg, params)));
	}

	public static void info(Object obj, String msg, Object... params) {
		if (obj == null) {
			logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		} else {
			logger = LoggerFactory.getLogger(obj.getClass());
		}
		logger.info(String.format("-->> %s", String.format(msg, params)));
	}

	public static void warn(Object obj, String msg, Object... params) {
		if (obj == null) {
			logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		} else {
			logger = LoggerFactory.getLogger(obj.getClass());
		}
		logger.warn(String.format("-->> %s", String.format(msg, params)));
	}

	public static void error(Object obj, Throwable e, String msg, Object... params) {
		if (obj == null) {
			logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		} else {
			logger = LoggerFactory.getLogger(obj.getClass());
		}
		logger.error(String.format("-->> %s", String.format(msg, params)), e);
	}
}
