package com.mycompany.module1;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author saj
 */
@Mojo(name = "list", defaultPhase = LifecyclePhase.COMPILE)
public class ListMojo extends AbstractMojo {

    @Parameter(defaultValue = "${basedir}")
    private String basedir;

    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true)
    private Path sourceDirectory;

    @Parameter(defaultValue = "${project.build.outputDirectory}", required = true)
    private Path outputDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Log log = getLog();
        log.info("sourceDirectory: " + sourceDirectory.toString());
        log.info("outputDirectory: " + outputDirectory.toString());
        log.info("List of files in dir: " + basedir);
        log.info("");

        printFiles(sourceDirectory, log);
        printFiles(outputDirectory, log);
    }

    private void printFiles(Path path, Log log) {
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    // Handle the file here
                    log.info(file.toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
