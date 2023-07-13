package com.mjc.school.controller.command.implementation.getAll;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class GetAllTagsCommand implements Command {

    private BaseController tagController;

    @Autowired
    public GetAllTagsCommand(@Qualifier("tagController") BaseController tagController) {
        this.tagController = tagController;
    }
    @Override
    public void execute() {
        System.out.println(Operations.GET_ALL_TAGS.getOperation());
        try {
            if (CommandHelper
                    .findCommandHandlerFor(Operations.GET_ALL_TAGS.getOperationNumber(), tagController)
                    .invoke(tagController) instanceof List<?> allTags) {
                allTags.forEach(System.out::println);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
