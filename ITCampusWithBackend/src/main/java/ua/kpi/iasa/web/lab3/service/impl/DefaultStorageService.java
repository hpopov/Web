package ua.kpi.iasa.web.lab3.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.config.properties.FileStorageProperties;
import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.model.FilePathModel;
import ua.kpi.iasa.web.lab3.service.StorageService;
import ua.kpi.iasa.web.lab3.service.strategy.DirectoryPathHashingStrategy;

@Service
public class DefaultStorageService implements StorageService {

    private static final String UNABLE_TO_CREATE_DIR = "Could not create the directory %s";
    private static final String UNABLE_TO_CREATE_FILE = "Could not create the file %s";
    private static final String FILE_NOT_FOUND = "File %s was not found!";
    private static final String UNABLE_TO_COPY_FILE = "Could not copy the file %s";
    private static final String UNABLE_TO_DELETE_PATH = "Could not delete the file/folder %s";

    @Autowired
    private FileStorageProperties fileStorageProperties;
    @Autowired
    @Qualifier("doubleDirectoryPathHashingStrategy")
    private DirectoryPathHashingStrategy directoryPathHashingStrategy;

    private Path fileStorageLocation;

    @PostConstruct
    private void initialize() throws FileStorageException {
        fileStorageLocation = Paths.get(fileStorageProperties.getDirectory()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException(String.format(UNABLE_TO_CREATE_DIR, fileStorageLocation.toString()), ex);
        }
    }

    @Override
    public void store(InputStream file, FilePathModel filePathModel) throws FileStorageException {
        final Path absoluteTargetPath = resolvePath(filePathModel);
        createDirectoriesIfAbsent(absoluteTargetPath);
        try {
            Files.copy(file, absoluteTargetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileStorageException(String.format(UNABLE_TO_CREATE_FILE, filePathModel.getFileName()), ex);
        }
    }

    private Path resolvePath(FilePathModel filePathModel) {
        return fileStorageLocation.resolve(directoryPathHashingStrategy.hashDirectoryPath(filePathModel));
    }

    private void createDirectoriesIfAbsent(Path targetPath) throws FileStorageException {
        final Path directoriesPath = targetPath.resolveSibling(StringUtils.EMPTY);
        try {
            Files.createDirectories(directoriesPath);
        } catch (IOException ex) {
            throw new FileStorageException(String.format(UNABLE_TO_CREATE_DIR, targetPath.toString()), ex);
        }
    }

    @Override
    public Resource loadAsResource(FilePathModel filePathModel) throws FileStorageException {
        final Resource resource = new FileSystemResource(resolvePath(filePathModel));
        if (resource.exists()) {
            return resource;
        }
        throw new FileStorageException(String.format(FILE_NOT_FOUND, filePathModel.getFileName()));
    }

    @Override
    public void copy(Path sourcePath, FilePathModel filePathModel) throws FileStorageException {
        final Path absoluteTargetPath = resolvePath(filePathModel);
        final Path absoluteSourcePath = fileStorageLocation.resolve(sourcePath);
        createDirectoriesIfAbsent(absoluteTargetPath);
        try {
            Files.copy(absoluteSourcePath, absoluteTargetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileStorageException(String.format(UNABLE_TO_COPY_FILE, filePathModel.getFileName().toString()),
                    ex);
        }
    }

    @Override
    public void delete(FilePathModel filePathModel) throws FileStorageException {
        final Path absoluteTargetPath = resolvePath(filePathModel);
        if (!Files.exists(absoluteTargetPath)) {
            return;
        }
        try {
            deleteFilesRecursively(absoluteTargetPath);
        } catch (IOException ex) {
            throw new FileStorageException(String.format(UNABLE_TO_DELETE_PATH, filePathModel.getFileName()), ex);
        }
    }

    private void deleteFilesRecursively(Path absoluteTargetPath) throws IOException {
        Files.walkFileTree(absoluteTargetPath, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
