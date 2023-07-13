package com.mjc.school.controller.command.implementation;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import org.springframework.stereotype.Component;

@Component
public class PrintMainMenuCommand implements Command {

    @Override
    public void execute() {
        System.out.println(Constant.NUMBER_OPERATION_ENTER);
        for (Operations operation : Operations.values()) {
            System.out.println(operation.getOperationWithNumber());
        }
    }
}
