package com.woodpecker.main;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.woodpecker.processor.WoodpeckerInteractiveProcessor;
import com.woodpecker.processor.WoodpeckerPlexModeProcessor;
import com.woodpecker.util.WoodpeckerConstants;
import com.woodpecker.util.WoodpeckerUtil;

public class WoodpeckerApp {
    public static final String HELP_TEXT = " Please refer to the usage README or use the --help argument.\n";

    static void printHelpText() {
        System.out.println("\nThe allowed arguments are --path, --plexmode, --peck and --interactive."+ HELP_TEXT);
    }

    static void printInitialText(int count, String processingPath) {
        System.out.println(String.format("%d directories(s) to process in %s.", count, WoodpeckerUtil.getAbsolutePath(processingPath)));
    }

    public static void main(String args[]) {
        System.out.println("\n*** Woodpecker ***\n");
        List<String> arguments = Arrays.asList(args);

        if (arguments.isEmpty()) {
            System.out.println("You must provide some arguments."+ HELP_TEXT);
        }

        String processingPath = WoodpeckerUtil.getProcessingPath(arguments);
        File[] listOfFiles = WoodpeckerUtil.getDirectoryContents(processingPath);

        if (arguments.contains(WoodpeckerConstants.ARG_HELP)) {
            printHelpText();
        } else if (arguments.contains(WoodpeckerConstants.ARG_INTERACTIVE)) {
            if (arguments.size() > 2) {
                System.out.println(String.format("You cannot use %s along with other arguments. %s", WoodpeckerConstants.ARG_INTERACTIVE, HELP_TEXT));
                return;
            }

            printInitialText(listOfFiles.length - 1, processingPath);
            WoodpeckerInteractiveProcessor.getInstance()
                .process(WoodpeckerUtil.getDirectories(listOfFiles));
        } else if(arguments.contains(WoodpeckerConstants.ARG_PLEX_MODE)) {
            if (arguments.size() > 2) {
                System.out.println(String.format("You cannot use %s along with other arguments. %s", WoodpeckerConstants.ARG_PLEX_MODE, HELP_TEXT));
                return;
            }

            printInitialText(listOfFiles.length - 1, processingPath);
            WoodpeckerPlexModeProcessor.getInstance()
                    .process(WoodpeckerUtil.getDirectories(listOfFiles));
        } else {
            printHelpText();
        }

        System.out.println("\n*** Process Completed ***\n");
    }
}
