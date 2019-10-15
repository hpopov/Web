package ua.kpi.iasa.web.lab3.service.strategy.impl;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.kpi.iasa.web.lab3.model.FilePathModel;
import ua.kpi.iasa.web.lab3.service.strategy.FilePathStrategy;

@Component
@Slf4j
public class RandomFilePathStrategy implements FilePathStrategy {

    private final Pattern extensionPattern;
    private static final String BINARY_EXTENSION = "bin";

    public RandomFilePathStrategy() {
        extensionPattern = Pattern.compile("(?<=[.]{1})[a-zA-z0-9]{1,8}$");
    }

    @Override
    public FilePathModel makeFilePath(String fileName) {
        final FilePathModel model = new FilePathModel();
        final Matcher matcher = extensionPattern.matcher(fileName);
        final String extension = matcher.find() ? matcher.group() : BINARY_EXTENSION;
        log.info("Defined extension {}", extension);
        final UUID fileNameUuid = UUID.randomUUID();
        log.info("Generated uuid: {} for {}", fileNameUuid.toString(), fileName);
        model.setExtension(extension);
        model.setFileName(fileNameUuid);
        return model;
    }
}
