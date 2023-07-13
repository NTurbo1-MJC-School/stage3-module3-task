package com.mjc.school.controller.command.implementation;

import com.mjc.school.controller.command.Command;
import org.springframework.stereotype.Component;

@Component
public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }
}
