package com.mjc.school.controller.command.implementation.update;

import com.mjc.school.controller.interfaces.TagControllerInterface;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.service.dto.TagDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;

@Component
public class UpdateTagCommand implements Command {

    private TagControllerInterface tagController;
    private Scanner keyboard;

    @Autowired
    public UpdateTagCommand(@Qualifier("tagController") TagControllerInterface tagController) {
        this.tagController = tagController;
        this.keyboard = new Scanner(System.in);
    }

    @Override
    public void execute() {
        TagDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            System.out.println(Operations.UPDATE_TAG.getOperation());
            System.out.println(Constant.TAG_ID_ENTER);
            Long tagId = CommandHelper.getKeyboardNumber(Constant.TAG_ID, keyboard);
            System.out.println(Constant.TAG_NAME_ENTER);
            String name = keyboard.nextLine();
            System.out.println(Constant.NEWS_ID_ENTER);
            List<Long> newsIds = CommandHelper.getKeyboardListOfNumbers(Constant.NEWS_ID, keyboard);
            dtoRequest = new TagDtoRequest(tagId, name, newsIds);
            isValid = true;

            try {
                System.out.println(CommandHelper
                        .findCommandHandlerFor(Operations.UPDATE_TAG.getOperationNumber(), tagController)
                        .invoke(tagController, dtoRequest));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println(e.getCause().getMessage());
            }
        }
    }
}
