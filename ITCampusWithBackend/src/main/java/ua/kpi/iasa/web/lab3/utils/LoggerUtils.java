package ua.kpi.iasa.web.lab3.utils;

import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.event.Level;

public class LoggerUtils {

    public static BiConsumer<String, Throwable> makeLoggerMethodOfLoggerAndLevel(Logger logger,
            Level loggingLevel) {
        switch (loggingLevel) {
        case DEBUG:
            return logger::debug;
        default:
        case ERROR:
            return logger::error;
        case INFO:
            return logger::info;
        case TRACE:
            return logger::trace;
        case WARN:
            return logger::warn;
        }
    }
}
