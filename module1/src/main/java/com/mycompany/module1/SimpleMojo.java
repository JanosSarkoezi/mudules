package com.mycompany.module1;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 *
 * @author saj
 */
 @Mojo(name = "simple", defaultPhase = LifecyclePhase.COMPILE)
//@Mojo(name = "simple")
public class SimpleMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("hi at all");
    }
}
