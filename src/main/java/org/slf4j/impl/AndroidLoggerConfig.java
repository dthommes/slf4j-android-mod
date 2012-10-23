/** 
 * Created on 10.08.2011 
 * 
 * © 2011 Daniel Thommes
 */
package org.slf4j.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.util.Log;

/**
 * 
 * 
 * @author Daniel Thommes
 */
public class AndroidLoggerConfig {

	/**
	 * 
	 */
	private static final String SLF4J_PROPERTIES_FILENAME = "/slf4j.properties";

	/**
	 * 
	 */
	private static final String LOGGING_LEVEL_KEY = "logging.level";
	private static final String TAG_KEY = "logging.tag";

	protected final static int TRACE = 0;
	protected final static int DEBUG = 1;
	protected final static int INFO = 2;
	protected final static int WARN = 3;
	protected final static int ERROR = 4;
	protected final static int OFF = 5;

	private static int globalLogLevel = INFO;
	private static String logTag = "SLF4J";

	static {
		InputStream inStream = null;
		try {
			inStream = AndroidLogger.class
					.getResourceAsStream(SLF4J_PROPERTIES_FILENAME);
			if (inStream == null) {
				Log.w("SLF4J", "Could not find " + SLF4J_PROPERTIES_FILENAME
						+ ". Using default logging settings (INFO level).");
			} else {
				Properties props = new Properties();
				props.load(inStream);
				loadGlobalLogLevel(props);
				loadTag(props);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void loadGlobalLogLevel(Properties props) {
		String levelString = props.getProperty(LOGGING_LEVEL_KEY);
		if (levelString == null) {
			Log.w("SLF4J", "Could not find property '" + LOGGING_LEVEL_KEY
					+ "' in " + SLF4J_PROPERTIES_FILENAME
					+ ". Using default logging (INFO level).");
		} else {
			levelString = levelString.toLowerCase().trim();
			if (levelString.equals("trace")) {
				globalLogLevel = TRACE;
			} else if (levelString.equals("debug")) {
				globalLogLevel = DEBUG;
			} else if (levelString.equals("info")) {
				globalLogLevel = INFO;
			} else if (levelString.equals("warn")) {
				globalLogLevel = WARN;
			} else if (levelString.equals("error")) {
				globalLogLevel = ERROR;
			} else if (levelString.equals("off")) {
				globalLogLevel = OFF;
			}
		}
	}

	/**
	 * @param props
	 */
	private static void loadTag(Properties props) {
		String tagString = props.getProperty(TAG_KEY);
		if (tagString == null || tagString.equals("")) {
			Log.w("SLF4J", "Could not find property '" + TAG_KEY + "' in "
					+ SLF4J_PROPERTIES_FILENAME + ". Using default '" + logTag
					+ "'.");
		} else {
			logTag = tagString.trim();
		}
	}

	protected static int getGlobalLoglevel() {
		return globalLogLevel;
	}

	/**
	 * @return
	 */
	public static String getLogTag() {
		return logTag;
	}
}
