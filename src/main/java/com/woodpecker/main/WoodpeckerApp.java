package com.woodpecker.main;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.woodpecker.processor.WoodpeckerPlexModeProcessor;
import com.woodpecker.util.WoodpeckerConstants;
import com.woodpecker.util.WoodpeckerUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WoodpeckerApp {
    public static final String HELP_TEXT = " Please refer to the usage README or use the --help argument.";

    public static void main(String args[]) {
        log.info("\n** Woodpecker ** \n");
        List<String> arguments = Arrays.asList(args);

        if (arguments.isEmpty()) {
            log.info("You must provide some arguments."+ HELP_TEXT);
        }

        String processingPath = WoodpeckerUtil.getProcessingPath(arguments);
        File[] listOfFiles = WoodpeckerUtil.getDirectoryContents(processingPath);

        log.info(listOfFiles.length+" file(s) to process in "+WoodpeckerUtil.getAbsolutePath(processingPath)+ "\n");

        if (arguments.contains(WoodpeckerConstants.ARG_PLEX_MODE)) {

            if (arguments.size() > 2) {
                log.info("You cannot use --plexmode alongwith other arguments."+ HELP_TEXT);
                return;
            }

            WoodpeckerPlexModeProcessor.getInstance()
                .process(WoodpeckerUtil.getDirectories(listOfFiles));
        }
    }
}
