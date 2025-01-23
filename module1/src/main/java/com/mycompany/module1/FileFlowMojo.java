package com.mycompany.module1;

import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 * @author saj
 */
@Mojo(name = "flow", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class FileFlowMojo extends AbstractMojo {

    @Parameter
    private List<Flow> flows;
    
    @Parameter(defaultValue = "${basedir}")
    private String basedir;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("base dir: " + basedir);
        for (Flow flow : flows) {
            getLog().info(flow.getSourceFile());
            getLog().info(flow.getTargetFile());
            getLog().info(flow.getManipulator());
        }
    }
}
