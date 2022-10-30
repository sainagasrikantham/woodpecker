package com.woodpecker.main;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.woodpecker.processor.WoodpeckerPlexModeProcessor;
import com.woodpecker.util.WoodpeckerConstants;
import com.woodpecker.util.WoodpeckerUtil;

public class WoodpeckerApp {
    public static final String HELP_TEXT = " Please refer to the usage README or use the --help argument.";

    public static void main(String args[]) {
        System.out.println("\n** Woodpecker ** \n");
        List<String> arguments = Arrays.asList(args);

        if (arguments.isEmpty()) {
            System.out.println("You must provide some arguments."+ HELP_TEXT);
        }

        String processingPath = WoodpeckerUtil.getProcessingPath(arguments);
        File[] listOfFiles = WoodpeckerUtil.getDirectoryContents(processingPath);

        System.out.println(String.format("%d directories(s) to process in %s.", (listOfFiles.length - 1), WoodpeckerUtil.getAbsolutePath(processingPath)));

        if (arguments.contains(WoodpeckerConstants.ARG_PLEX_MODE)) {
            if (arguments.size() > 2) {
                System.out.println("You cannot use --plexmode alongwith other arguments."+ HELP_TEXT);
                return;
            }

            WoodpeckerPlexModeProcessor.getInstance()
                .process(WoodpeckerUtil.getDirectories(listOfFiles));
        }
    }
}
