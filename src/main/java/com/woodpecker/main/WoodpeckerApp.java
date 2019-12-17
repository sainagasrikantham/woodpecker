package com.woodpecker.main;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.woodpecker.processor.WoodpeckerPlexModeProcessor;
import com.woodpecker.util.WoodpeckerConstants;
import com.woodpecker.util.WoodpeckerUtil;

public class WoodpeckerApp {
    public static void main(String args[]) {
        System.out.println("\n** Woodpecker ** \n");
        List<String> arguments = Arrays.asList(args);

        String processingPath = WoodpeckerUtil.getProcessingPath(arguments);
        File[] listOfFiles = WoodpeckerUtil.getDirectoryContents(processingPath);

        System.out.println(listOfFiles.length+" file(s) to process in "+WoodpeckerUtil.getAbsolutePath(processingPath)+ "\n");

        if (arguments.contains(WoodpeckerConstants.ARG_PLEX_MODE)) {
            WoodpeckerPlexModeProcessor.getInstance()
                .process(WoodpeckerUtil.getDirectories(listOfFiles));
        }

        
    }
}
