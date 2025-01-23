package com.mycompany.module1;

import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ClassLoaderTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author saj
 */
@Mojo(name = "import", defaultPhase = LifecyclePhase.COMPILE)
public class ImportMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true)
    private Path sourceDirectory;

    @Parameter(defaultValue = "${project.build.outputDirectory}", required = true)
    private Path outputDirectory;

    private List<String> sourceFiles;
    private List<String> compiledFiles;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Log log = getLog();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        CombinedTypeSolver typeSolver = new CombinedTypeSolver();
        typeSolver.add(new ReflectionTypeSolver());                // Für JDK-Klassen
        typeSolver.add(new JavaParserTypeSolver(sourceDirectory)); // Pfad zum Quellcode
        typeSolver.add(new ClassLoaderTypeSolver(classLoader));    // Pfad zu kompilierten Klassen

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);

        sourceFiles = new ArrayList<>();
        callWalkFileTree(sourceDirectory, new FileCollectorVisitor(sourceFiles, log));
        sourceFiles.forEach(this::printClassesOrInterfaces);

        compiledFiles = new ArrayList<>();
        callWalkFileTree(outputDirectory, new FileCollectorVisitor(compiledFiles, log));
        compiledFiles.forEach(this::printClassesOrInterfaces);

//        // Parse die zu analysierende Datei
//        File sourceFile = new File("src/main/java/com/example/MyClass.java");
//        CompilationUnit cu = StaticJavaParser.parse(sourceFile);
//
//        // Analysiere Abhängigkeiten
//        cu.findAll(MethodDeclaration.class).forEach(method -> {
//            method.getType().ifResolved(resolvedType -> {
//                if (resolvedType.isReferenceType()) {
//                    List<ResolvedReferenceType> dependencies = resolvedType.asReferenceType().getAllAncestors();
//                    dependencies.forEach(dependency ->
//                            System.out.println("Abhängige Klasse: " + dependency.getQualifiedName())
//                    );
//                }
//            });
//        });
//        callWalkFileTree(sourceDirectory, new PrintFileVisitor(log));
//        callWalkFileTree(outputDirectory, new PrintFileVisitor(log));
    }

    private void printClassesOrInterfaces(String fileName) {
        File sourceFile = new File(fileName);

        try {
            CompilationUnit cu = StaticJavaParser.parse(sourceFile);
            List<ClassOrInterfaceDeclaration> classes = cu.findAll(ClassOrInterfaceDeclaration.class);
            classes.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void callWalkFileTree(Path path, FileVisitor<Path> visitor) throws MojoExecutionException {
        try {
            Files.walkFileTree(path, visitor);
        } catch (IOException e) {
            throw new MojoExecutionException(e);
        }
    }
}
