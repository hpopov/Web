package ua.kpi.iasa.web.lab3.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class FileStorageException extends IOException {

    public FileStorageException() {
        super();
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(Throwable cause) {
        super(cause);
    }
}