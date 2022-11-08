package com.woodpecker.processor;

import com.woodpecker.util.WoodpeckerConstants;
import com.woodpecker.util.WoodpeckerUtil;

import java.io.File;
import java.util.List;

public class WoodpeckerPlexModeProcessor implements WoodpeckerProcessor {
    private static WoodpeckerPlexModeProcessor plexModeProcessor = null;
    private WoodpeckerPlexModeProcessor() {
        // Private Constructor
    }

    public static WoodpeckerProcessor getInstance() {
        if (plexModeProcessor == null) {
            plexModeProcessor = new WoodpeckerPlexModeProcessor();
        }

        return plexModeProcessor;
    }
    @Override
    public void process(List<File> directoriesToProcess) {
        Integer totalDirectories = directoriesToProcess.size();
        Integer currentNumber = 0;
        for (File d : directoriesToProcess) {
            String name = d.getName();
            String absolutePath = d.getAbsolutePath();
            absolutePath = absolutePath.replace(name, "");
            if (name.contains(WoodpeckerConstants.CURRENT_DIRECTORY)) {
                System.out.println(String.format("Now Processing Directory (%d/%d) : %s", ++currentNumber, totalDirectories, name));
                String outputDirectoryName = absolutePath + WoodpeckerUtil.getPlexStyleDirName(name);
                File newName = new File(outputDirectoryName);
                d.renameTo(newName);
            }
        }
    }
}
