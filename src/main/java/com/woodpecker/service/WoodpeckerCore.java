package com.woodpecker.service;

import java.util.List;

public class WoodpeckerCore {
    private List<String> arguments;
    
    public WoodpeckerCore(List<String> arguments) {
        this.arguments = arguments;
    }

    List<String> getArguments() {
        return arguments;
    }
}
