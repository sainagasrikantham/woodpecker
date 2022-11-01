package com.woodpecker.processor;

import java.io.File;
import java.util.List;

public interface WoodpeckerProcessor {
    void process(List<File> directoriesToProcess);
}
