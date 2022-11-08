package com.woodpecker.main;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.woodpecker.processor.WoodpeckerInteractiveProcessor;
import com.woodpecker.processor.WoodpeckerPlexModeProcessor;
import com.woodpecker.util.WoodpeckerUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestApp {

    public static final String BASE_PATH = "./testroot";
    public static final String BASE_PATH_2 = "./testroot2";


    @BeforeAll
    public static void setup() throws IOException {
        Runtime.getRuntime().exec("mkdir ./testroot");
        Runtime.getRuntime().exec("mkdir ./testroot2");
        Runtime.getRuntime().exec("mkdir ./testroot/test.1979");
        Runtime.getRuntime().exec("mkdir ./testroot2/test.1980.abc.def.124");
    }

    @Test
    public void testHappyPath() {
        String processingPath = WoodpeckerUtil.getProcessingPath(Arrays.asList("--path", BASE_PATH));
        File[] dirContents = WoodpeckerUtil.getDirectoryContents(processingPath);
        List<File> directories = WoodpeckerUtil.getDirectories(dirContents);
        assertTrue(directories.contains(new File("./testroot/test.1979")), "Test.1979 Exists");
        WoodpeckerPlexModeProcessor.getInstance().process(directories);
        dirContents = WoodpeckerUtil.getDirectoryContents(processingPath);
        directories = WoodpeckerUtil.getDirectories(dirContents);
        assertTrue(directories.contains(new File("./testroot/Test (1979)")), "Test (1979) Exists");
    }

    @Test
    public void testTextAfterYear() throws IOException {
        String processingPath = WoodpeckerUtil.getProcessingPath(Arrays.asList("--path", BASE_PATH_2));
        File[] dirContents = WoodpeckerUtil.getDirectoryContents(processingPath);
        List<File> directories = WoodpeckerUtil.getDirectories(dirContents);
        assertTrue(directories.contains(new File("./testroot2/test.1980.abc.def.124")), "Test.1980 Exists");
        WoodpeckerInteractiveProcessor.getInstance().withUserInput(false).process(directories);
        dirContents = WoodpeckerUtil.getDirectoryContents(processingPath);
        directories = WoodpeckerUtil.getDirectories(dirContents);
        assertTrue(directories.contains(new File("./testroot2/Test (1980)")), "Test (1980) Exists");
    }

    @AfterAll
    public static void teardown() throws IOException {
        Runtime.getRuntime().exec("rm -rf testroot");
        Runtime.getRuntime().exec("rm -rf testroot2");
    }
}
