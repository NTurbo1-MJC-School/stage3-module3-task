package com.mjc.school.controller.helper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.exceptions.CommandHandlerNotFoundException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidatorException;

public class CommandHelper {

  public static long getKeyboardNumber(String params, Scanner keyboard) {
    long enter;
    try {
      enter = keyboard.nextLong();
      keyboard.nextLine();
    } catch (Exception ex) {
      keyboard.nextLine();
      throw new ValidatorException(
          String.format(ServiceErrorCode.VALIDATE_INT_VALUE.getMessage(), params));
    }
    return enter;
  }

  public static List<Long> getKeyboardListOfNumbers(String params, Scanner keyboard) {
    List<Long> enter = new ArrayList<>();
    try {
      String input = keyboard.nextLine();

      if (input.equals("null")) {
        return null;
      }

      String[] numbers = input.split(",\s*");
      for (String number : numbers) {
        enter.add(Long.parseLong(number));
      }
    } catch (Exception ex) {
      throw new ValidatorException(
              String.format(ServiceErrorCode.VALIDATE_INT_VALUE.getMessage(), params));
    }

    return enter;
  }

  public static Method findCommandHandlerFor(String command, BaseController controller) {
    for (Method method : controller.getClass().getDeclaredMethods()) {
      if (method.isAnnotationPresent(CommandHandler.class)) {
        if (method.getAnnotation(CommandHandler.class).value().equals(command)) {
          return method;
        }
      }
    }

    throw new CommandHandlerNotFoundException("No command handler to handle the command '" +
                                              command + "'.");
  }
}
