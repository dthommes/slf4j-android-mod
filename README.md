slf4j-android-mod
=================

Android version of the SLF4J logging framework.

This is a fork of the original version by Thorsten Möller (http://www.slf4j.org/android/). I have modified it.

* Full class names in the message instead in the tag, so no class name truncation is necessary.
* Simple configuration with slf4j.properties in the classpath. You can configure the log level and the tag used by all loggers.

Example slf4j.properties:
_________________________
logging.level=debug

logging.tag=SuperApp
_________________________

Happy logging!

Daniel
