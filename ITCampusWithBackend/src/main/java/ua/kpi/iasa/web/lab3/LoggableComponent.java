package ua.kpi.iasa.web.lab3;

import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import ua.kpi.iasa.web.lab3.utils.ExceptionManager;
import ua.kpi.iasa.web.lab3.utils.LoggerUtils;

public class LoggableComponent {

    protected Logger log;

//    @PostConstruct
//    @Order(0)
//    public void initialize() {
//        log = LoggerFactory.getLogger(getClass());
//    }
    public LoggableComponent() {
        log = LoggerFactory.getLogger(getClass());
    }

    protected class LoggingExceptionManager<R> implements ExceptionManager<String, R> {

        private BiConsumer<String, Throwable> loggerMethod;

        public LoggingExceptionManager(Level loggingLevel) {
            loggerMethod = LoggerUtils.makeLoggerMethodOfLoggerAndLevel(log, loggingLevel);
        }

        @Override
        public R handleException(Exception exception, String message) {
            loggerMethod.accept(message, exception);
            return null;
        }

    }
    
    protected class RethrowingExceptionManager<R> extends LoggingExceptionManager<R> {

        public RethrowingExceptionManager(Level loggingLevel) {
            super(loggingLevel);
        }
        
        @Override
        public R handleException(Exception exception, String message) {
            super.handleException(exception, message);
            throw new RuntimeException(exception);
        }
        
    }
}
