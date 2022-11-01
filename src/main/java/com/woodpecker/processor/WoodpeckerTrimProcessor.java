package com.woodpecker.processor;

import java.io.File;
import java.util.List;

public class WoodpeckerTrimProcessor implements WoodpeckerProcessor {

    @Override
    public void process(List<File> filesToProcess) {
        // THis is for "trim" -- don't delete
        // filesToProcess.forEach(f -> {
        // String name = f.getName();
        // String path = f.getAbsolutePath();
        // System.out.println("Processing file : "+name);
        // String [] parts = path.split("\\"+WoodpeckerConstants.CURRENT_DIRECTORY);
        // System.out.println("parts : "+parts.toString());
        // if (parts.length > 0) {
        // name = parts[0] + "renamed" + "." + parts[1];
        // File newName = new File(name);
        // f.renameTo(newName);
        // }
        // });
    }
}
