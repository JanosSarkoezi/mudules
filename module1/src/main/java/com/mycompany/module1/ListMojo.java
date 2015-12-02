package com.mycompany.module1;

import java.io.File;
import java.io.IOException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.utils.io.DirectoryScanner;
import org.apache.maven.shared.utils.io.FileUtils;

/**
 *
 * @author saj
 */
@Mojo(name = "list", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class ListMojo extends AbstractMojo {

    @Parameter(defaultValue = "${basedir}")
    private String basedir;

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        File sourceDirectory = new File(basedir + "/src/main/java");
        File destinationDirectory = new File(basedir + "/target/generated/java");
        
        File sourceFile = new File(basedir + "/src/main/java/com/mycompany/module2/Substraction.java");
        File destinationFile = new File(basedir + "/target/generated-sources/java/com/mycompany/module2/Substraction.java");
        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException ex) {
            throw new MojoExecutionException("IOException", ex);
        }
        
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(basedir);
        scanner.addDefaultExcludes();
        scanner.scan();

        project.getCompileSourceRoots().clear();
        project.getCompileSourceRoots().add(basedir + "/target/generated-sources/java");

        Log log = getLog();
        log.info("Sourcedirectory: " + project.getCompileSourceRoots());
        log.info("List of files in dir: " + basedir);
        log.info("");

        String[] includedFiles = scanner.getIncludedFiles();
        for (String includedFile : includedFiles) {
            log.info("    " + includedFile);
        }
    }
}
