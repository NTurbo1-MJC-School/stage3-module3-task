package com.mjc.school.controller.command.implementation.update;

import com.mjc.school.controller.interfaces.AuthorControllerInterface;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class UpdateAuthorCommand implements Command {

    private AuthorControllerInterface authorController;
    private Scanner keyboard;

    @Autowired
    public UpdateAuthorCommand(@Qualifier("authorController") AuthorControllerInterface authorController) {
        this.authorController = authorController;
        this.keyboard = new Scanner(System.in);
    }

    @Override
    public void execute() {
        AuthorDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
                System.out.println(Operations.UPDATE_AUTHOR.getOperation());
                System.out.println(Constant.AUTHOR_ID_ENTER);
                Long authorId = CommandHelper.getKeyboardNumber(Constant.AUTHOR_ID, keyboard);
                System.out.println(Constant.AUTHOR_NAME_ENTER);
                String name = keyboard.nextLine();
                dtoRequest = new AuthorDtoRequest(authorId, name);
                isValid = true;

            try {
                System.out.println(CommandHelper
                        .findCommandHandlerFor(Operations.UPDATE_AUTHOR.getOperationNumber(), authorController)
                        .invoke(authorController, dtoRequest));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println(e.getCause().getMessage());
            }
        }
    }
}
