package com.woodpecker.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.woodpecker.util.WoodpeckerConstants;

public class WoodpeckerApp {
    public static void main(String args[]) {
        System.out.println("** Woodpecker **");
        List<String> arguments = Arrays.asList(args);
        List<File> filesToProcess = new ArrayList<>();
        List<File> directoriesToProcess  = new ArrayList<>();

        arguments.stream()
            .forEach(System.out::println);

        String processingPath = (arguments.contains(WoodpeckerConstants.ARG_PATH)) ? 
            arguments.get(arguments.indexOf(WoodpeckerConstants.ARG_PATH) + 1) : WoodpeckerConstants.CURRENT_DIRECTORY;
        File folder = new File(processingPath);
        File[] listOfFiles = folder.listFiles();

        System.out.println("Processing : "+folder.getAbsolutePath());

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                filesToProcess.add(listOfFiles[i]);
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
                directoriesToProcess.add(listOfFiles[i]);
            }
        }

        if (arguments.contains(WoodpeckerConstants.ARG_PLEX_MODE)) {
            directoriesToProcess.forEach(d -> {
                String name = d.getName();
                System.out.println("Processing Directory : " + name);
                File newName = new File(getPlexStyleDirName(name));
                d.renameTo(newName);
            });
        }
        // filesToProcess.forEach(f -> {
        //     String name = f.getName();
        //     String path = f.getAbsolutePath();
        //     System.out.println("Processing file : "+name);
        //     String [] parts = path.split("\\"+WoodpeckerConstants.CURRENT_DIRECTORY);
        //     System.out.println("parts : "+parts.toString());
        //     if (parts.length > 0) {
        //         name = parts[0] + "renamed" + "." + parts[1];
        //         File newName = new File(name);
        //         f.renameTo(newName);
        //     }
        // });
    }

    public static String getPlexStyleDirName(String oldDirName) {
        String [] nameParts = oldDirName.split("\\"+WoodpeckerConstants.CURRENT_DIRECTORY);
        StringBuilder builder = new StringBuilder();

        for (String part : nameParts) {
            try {
                if (Integer.parseInt(part) >= 0) {
                    builder.append("("+part+")");
                }
            }
            catch(NumberFormatException e) {
                part = part.toLowerCase();
                part = part.substring(0, 1).toUpperCase() + part.substring(1);
                builder.append(part);
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
