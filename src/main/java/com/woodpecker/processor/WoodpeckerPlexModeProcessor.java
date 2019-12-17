package com.woodpecker.processor;

import java.io.File;
import java.util.List;

import com.woodpecker.processor.WoodpeckerArgProcessor;
import com.woodpecker.util.WoodpeckerConstants;
import com.woodpecker.util.WoodpeckerUtil;

public class WoodpeckerPlexModeProcessor implements WoodpeckerArgProcessor {

    private static WoodpeckerPlexModeProcessor plexModeProcessor = null; 
    private WoodpeckerPlexModeProcessor() {
        // Private Constructor
    }

    public static WoodpeckerArgProcessor getInstance() {
        if (plexModeProcessor == null) {
            plexModeProcessor = new WoodpeckerPlexModeProcessor();
        }

        return plexModeProcessor;
    }

    @Override
    public void process(List<File> directoriesToProcess) {
        directoriesToProcess.forEach(d -> {
            String name = d.getName();
            String absolutePath = d.getAbsolutePath();
            absolutePath = absolutePath.replace(name, "");
            if (name.contains(WoodpeckerConstants.CURRENT_DIRECTORY)) {
                System.out.println("Now Processing Directory : " + name);
                File newName = new File(absolutePath + WoodpeckerUtil.getPlexStyleDirName(name));
                d.renameTo(newName);
            }
        });
    }
}
