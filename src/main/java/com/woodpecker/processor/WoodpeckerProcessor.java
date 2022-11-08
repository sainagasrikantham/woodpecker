package com.woodpecker.processor;

import java.io.File;
import java.util.List;

public interface WoodpeckerProcessor {
    Boolean withUserInput = false;

    void process(List<File> directoriesToProcess);

    default WoodpeckerProcessor withUserInput(Boolean withUserInput) {
        withUserInput = false;
        return this;
    }
}
