package com.woodpecker.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WoodpeckerUtil {
    public static String getPlexStyleDirName(String oldDirName) {
        String[] nameParts = oldDirName.split("\\" + WoodpeckerConstants.CURRENT_DIRECTORY);
        StringBuilder builder = new StringBuilder();

        for (String part : nameParts) {
            try {
                if (Integer.parseInt(part) >= 0) {
                    builder.append("(" + part + ")");
                }
            } catch (NumberFormatException e) {
                part = part.toLowerCase();
                part = part.substring(0, 1).toUpperCase() + part.substring(1);
                builder.append(part);
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    public static String getPlexStyleDirNameV2(String oldDirName) {
        String[] nameParts = oldDirName.split("\\" + WoodpeckerConstants.CURRENT_DIRECTORY);
        StringBuilder builder = new StringBuilder();

        for (int i=0; i <nameParts.length; ++i) {
            String part = nameParts[i];
            try {
                if (Integer.parseInt(part) >= 0) {
                    builder.append("(" + part + ")");
                    break; // We ignore all parts after a "year" is found
                }
            } catch (NumberFormatException e) {
                part = part.toLowerCase();
                part = part.substring(0, 1).toUpperCase() + part.substring(1);
                builder.append(part);
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    public static String getProcessingPath(List<String> arguments) {
        String processingPath = (arguments != null && arguments.contains(WoodpeckerConstants.ARG_PATH))
                ? arguments.get(arguments.indexOf(WoodpeckerConstants.ARG_PATH) + 1)
                : WoodpeckerConstants.CURRENT_DIRECTORY;

        return processingPath;
    }

    public static File[] getDirectoryContents(String processingPath) {
        return new File(processingPath).listFiles();
    }

    public static String getAbsolutePath(String processingPath) {
        return new File(processingPath).getAbsolutePath();
    }

    public static List<File> getFiles(File[] directoryContents) {
        return Arrays.asList(directoryContents)
            .stream()
            .filter(f -> f.isFile())
            .collect(Collectors.toList());
    }

    public static List<File> getDirectories(File[] directoryContents) {
        return Arrays.asList(directoryContents)
            .stream()
            .filter(f -> f.isDirectory())
            .collect(Collectors.toList());
    }
}
