package com.mjc.school.controller.implementation;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.AuthorControllerInterface;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.interfaces.AuthorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements AuthorControllerInterface {

  private final AuthorServiceInterface authorService;

  @Autowired
  public AuthorController(@Qualifier("authorService") AuthorServiceInterface authorService) {
    this.authorService = authorService;
  }

  @CommandHandler("2")
  public List<AuthorDtoResponse> readAll() {
    List<AuthorDtoResponse> authors = authorService.readAll();
    if (authors.isEmpty()) {
      System.out.println("There is no author data in the database yet.");
    }
    return authors;
  }

  @CommandHandler("5")
  public AuthorDtoResponse readById(Long newsId) {
      return authorService.readById(newsId);
  }

  @CommandHandler("8")
  public AuthorDtoResponse create(AuthorDtoRequest dtoRequest) {
    return authorService.create(dtoRequest);
  }

  @CommandHandler("11")
  public AuthorDtoResponse update(AuthorDtoRequest dtoRequest) {
    return authorService.update(dtoRequest);
  }

  @CommandHandler("14")
  public boolean deleteById(Long newsId) {
    return authorService.deleteById(newsId);
  }

  @CommandHandler("16")
  public AuthorDtoResponse readByNewsId(Long newsId) {
    return authorService.readByNewsId(newsId);
  }
}
