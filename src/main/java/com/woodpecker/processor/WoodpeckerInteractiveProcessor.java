package com.woodpecker.processor;

import java.io.File;
import java.util.List;

import com.woodpecker.util.WoodpeckerConstants;
import com.woodpecker.util.WoodpeckerUtil;

public class WoodpeckerInteractiveProcessor implements WoodpeckerProcessor {

    private static WoodpeckerInteractiveProcessor interactiveProcessor = null;
    private WoodpeckerInteractiveProcessor() {
        // Private Constructor
    }

    public static WoodpeckerProcessor getInstance() {
        if (interactiveProcessor == null) {
            interactiveProcessor = new WoodpeckerInteractiveProcessor();
        }

        return interactiveProcessor;
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
                System.out.println(String.format("New directory name will be : \"%s\". Proceed (y/n)?", WoodpeckerUtil.getPlexStyleDirNameV2(name)));
                String input = System.console().readLine();
                if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
                    File newName = new File(outputDirectoryName);
                    d.renameTo(newName);
                }
            }
        }
    }
}
