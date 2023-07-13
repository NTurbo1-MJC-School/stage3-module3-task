package com.mjc.school;

import static com.mjc.school.controller.helper.Constant.COMMAND_NOT_FOUND;

import java.util.Scanner;

import com.mjc.school.config.ApplicationConfig;
import com.mjc.school.controller.command.CommandReceiver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    Scanner keyboard = new Scanner(System.in);
    CommandReceiver commandReceiver = context.getBean(CommandReceiver.class);

    while (true) {
      try {
        commandReceiver.printMainMenu();
        String command = keyboard.nextLine();

        switch (command) {
          case "1" -> commandReceiver.getAllNews();
          case "2" -> commandReceiver.getAllAuthors();
          case "3" -> commandReceiver.getAllTags();
          case "4" -> commandReceiver.getNewsById();
          case "5" -> commandReceiver.getAuthorById();
          case "6" -> commandReceiver.getTagById();
          case "7" -> commandReceiver.createNews();
          case "8" -> commandReceiver.createAuthor();
          case "9" -> commandReceiver.createTag();
          case "10" -> commandReceiver.updateNews();
          case "11" -> commandReceiver.updateAuthor();
          case "12" -> commandReceiver.updateTag();
          case "13" -> commandReceiver.deleteNews();
          case "14" -> commandReceiver.deleteAuthor();
          case "15" -> commandReceiver.deleteTag();
          case "16" -> commandReceiver.getAuthorByNewsId();
          case "17" -> commandReceiver.getTagsByNewsId();
          case "0" -> commandReceiver.exit();
          default -> System.out.println(COMMAND_NOT_FOUND);
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
