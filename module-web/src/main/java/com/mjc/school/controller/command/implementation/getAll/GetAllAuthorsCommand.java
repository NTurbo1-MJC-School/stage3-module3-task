package com.mjc.school.controller.command.implementation.getAll;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.controller.interfaces.AuthorControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class GetAllAuthorsCommand implements Command {

    private AuthorControllerInterface authorController;

    @Autowired
    public GetAllAuthorsCommand(@Qualifier("authorController") AuthorControllerInterface authorController) {
        this.authorController = authorController;
    }
    @Override
    public void execute() {
        System.out.println(Operations.GET_ALL_AUTHORS.getOperation());
        try {
            if (CommandHelper
                    .findCommandHandlerFor(Operations.GET_ALL_AUTHORS.getOperationNumber(), authorController)
                    .invoke(authorController) instanceof List<?> allAuthors) {
                allAuthors.forEach(System.out::println);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
