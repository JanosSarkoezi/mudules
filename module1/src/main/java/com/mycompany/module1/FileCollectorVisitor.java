package com.mycompany.module1;

import org.apache.maven.plugin.logging.Log;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FileCollectorVisitor implements FileVisitor<Path> {
    private final Log log;
    private final List<String> fileNames;

    public FileCollectorVisitor(List<String> fileNames, Log log) {
        this.log = log;
        this.fileNames = fileNames;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {

        if (basicFileAttributes.isRegularFile()) {
            log.info("add: " + path.toString());
            fileNames.add(path.toString());
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
