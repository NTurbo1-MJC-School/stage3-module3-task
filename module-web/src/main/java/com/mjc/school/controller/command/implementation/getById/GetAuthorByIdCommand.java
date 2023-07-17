package com.mjc.school.controller.command.implementation.getById;

import com.mjc.school.controller.interfaces.AuthorControllerInterface;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class GetAuthorByIdCommand implements Command {

    private AuthorControllerInterface authorController;
    private Scanner keyboard;

    @Autowired
    public GetAuthorByIdCommand(@Qualifier("authorController") AuthorControllerInterface authorController) {
        this.authorController = authorController;
        this.keyboard = new Scanner(System.in);
    }
    @Override
    public void execute() {
        System.out.println(Operations.GET_AUTHOR_BY_ID.getOperation());
        System.out.println(Constant.AUTHOR_ID_ENTER);
        try {
            Long id = CommandHelper.getKeyboardNumber(Constant.AUTHOR_ID, keyboard);
            System.out.println(CommandHelper
                    .findCommandHandlerFor(Operations.GET_AUTHOR_BY_ID.getOperationNumber(), authorController)
                    .invoke(authorController, id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
