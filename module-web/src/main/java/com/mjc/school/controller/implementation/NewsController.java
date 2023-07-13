package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

  private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

  @Autowired
  public NewsController(@Qualifier("newsService") BaseService newsService) {
    this.newsService = newsService;
  }

  @CommandHandler("1")
  public List<NewsDtoResponse> readAll() {
    List<NewsDtoResponse> news = newsService.readAll();
    if (news.isEmpty()) {
      System.out.println("There is no news data in the database yet.");
    }
    return news;
  }

  @CommandHandler("4")
  public NewsDtoResponse readById(Long newsId) {
    return newsService.readById(newsId);
  }

  @CommandHandler("7")
  public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
    return newsService.create(dtoRequest);
  }

  @CommandHandler("10")
  public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
    return newsService.update(dtoRequest);
  }

  @CommandHandler("13")
  public boolean deleteById(Long newsId) {
    return newsService.deleteById(newsId);
  }
}
