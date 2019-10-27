package ua.kpi.iasa.web.lab3.service.strategy.impl;

import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.kpi.iasa.web.lab3.model.FileModel;
import ua.kpi.iasa.web.lab3.service.strategy.FileModelStrategy;

@Component
@Slf4j
public class RandomFileModelStrategy implements FileModelStrategy {

    private final Pattern extensionPattern;
    private static final String BINARY_EXTENSION = "bin";

    public RandomFileModelStrategy() {
        extensionPattern = Pattern.compile("(?<=[.]{1})[a-zA-z0-9]{1,8}$");
    }

    @Override
    public FileModel makeFileModel(String fileName) {
        final FileModel model = new FileModel();
        final Matcher matcher = extensionPattern.matcher(fileName);
        final String extension = matcher.find() ? matcher.group() : BINARY_EXTENSION;
        log.info("Defined extension {}", extension);
        final UUID fileNameUuid = UUID.randomUUID();
        log.info("Generated uuid: {} for {}", fileNameUuid.toString(), fileName);
        model.setExtension(extension);
        model.setFileName(fileNameUuid);
        model.setCreated(Calendar.getInstance().getTime());
        return model;
    }
}
