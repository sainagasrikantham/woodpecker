package com.woodpecker.main;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.woodpecker.processor.WoodpeckerPlexModeProcessor;
import com.woodpecker.util.WoodpeckerUtil;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestApp {

    @BeforeClass
    public static void setup() throws IOException {
        Runtime.getRuntime().exec("mkdir ./testroot");
        Runtime.getRuntime().exec("mkdir ./testroot/test.1979");
    }

    // Write some tests
    @Test
    public void testHello() {
        String processingPath = WoodpeckerUtil.getProcessingPath(Arrays.asList("--path", "./testroot"));
        File[] dirContents = WoodpeckerUtil.getDirectoryContents(processingPath);
        List<File> directories = WoodpeckerUtil.getDirectories(dirContents);
        assertTrue("Test.1979 Exists", directories.contains(new File("./testroot/test.1979")));
        WoodpeckerPlexModeProcessor.getInstance().process(directories);
        dirContents = WoodpeckerUtil.getDirectoryContents(processingPath);
        directories = WoodpeckerUtil.getDirectories(dirContents);
        assertTrue("Test.1979 Exists", directories.contains(new File("./testroot/Test (1979)")));
    }

    @AfterClass
    public static void teardown() throws IOException {
        Runtime.getRuntime().exec("rm -rf testroot");
    }
}
