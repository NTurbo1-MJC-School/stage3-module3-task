package com.mjc.school.controller.command;

import com.mjc.school.controller.command.implementation.create.CreateAuthorCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandReceiver {
    private Command createAuthorCommand;
    private Command createNewsCommand;
    private Command createTagCommand;
    private Command deleteAuthorCommand;
    private Command deleteNewsCommand;
    private Command deleteTagCommand;
    private Command getAllAuthorsCommand;
    private Command getAllNewsCommand;
    private Command getAllTagsCommand;
    private Command getAuthorByIdCommand;
    private Command getNewsByIdCommand;
    private Command getTagByIdCommand;
    private Command updateAuthorCommand;
    private Command updateNewsCommand;
    private Command updateTagCommand;
    private Command getAuthorByNewsIdCommand;
    private Command getTagsByNewsIdCommand;
    private Command exitCommand;
    private Command printMainMenuCommand;

    @Autowired
    public CommandReceiver(@Qualifier("createAuthorCommand") Command createAuthorCommand,
                           @Qualifier("createNewsCommand") Command createNewsCommand,
                           @Qualifier("createTagCommand") Command createTagCommand,
                           @Qualifier("deleteAuthorCommand") Command deleteAuthorCommand,
                           @Qualifier("deleteNewsCommand") Command deleteNewsCommand,
                           @Qualifier("deleteTagCommand") Command deleteTagCommand,
                           @Qualifier("getAllAuthorsCommand") Command getAllAuthorsCommand,
                           @Qualifier("getAllNewsCommand") Command getAllNewsCommand,
                           @Qualifier("getAllTagsCommand") Command getAllTagsCommand,
                           @Qualifier("getAuthorByIdCommand") Command getAuthorByIdCommand,
                           @Qualifier("getNewsByIdCommand") Command getNewsByIdCommand,
                           @Qualifier("getTagByIdCommand") Command getTagByIdCommand,
                           @Qualifier("updateAuthorCommand") Command updateAuthorCommand,
                           @Qualifier("updateNewsCommand") Command updateNewsCommand,
                           @Qualifier("updateTagCommand") Command updateTagCommand,
                           @Qualifier("getAuthorByNewsIdCommand") Command getAuthorByNewsIdCommand,
                           @Qualifier("getTagsByNewsIdCommand") Command getTagsByNewsIdCommand,
                           @Qualifier("exitCommand") Command exitCommand,
                           @Qualifier("printMainMenuCommand") Command printMainMenuCommand) {
        this.createAuthorCommand = createAuthorCommand;
        this.createNewsCommand = createNewsCommand;
        this.createTagCommand = createTagCommand;
        this.deleteAuthorCommand = deleteAuthorCommand;
        this.deleteNewsCommand = deleteNewsCommand;
        this.deleteTagCommand = deleteTagCommand;
        this.getAllAuthorsCommand = getAllAuthorsCommand;
        this.getAllNewsCommand = getAllNewsCommand;
        this.getAllTagsCommand = getAllTagsCommand;
        this.getAuthorByIdCommand = getAuthorByIdCommand;
        this.getNewsByIdCommand = getNewsByIdCommand;
        this.getTagByIdCommand = getTagByIdCommand;
        this.updateAuthorCommand = updateAuthorCommand;
        this.updateNewsCommand = updateNewsCommand;
        this.updateTagCommand = updateTagCommand;
        this.getAuthorByNewsIdCommand = getAuthorByNewsIdCommand;
        this.getTagsByNewsIdCommand = getTagsByNewsIdCommand;
        this.exitCommand = exitCommand;
        this.printMainMenuCommand = printMainMenuCommand;
    }

    public void createAuthor() {
        createAuthorCommand.execute();
    }

    public void createNews() {
        createNewsCommand.execute();
    }

    public void createTag() {
        createTagCommand.execute();
    }

    public void deleteAuthor() {
        deleteAuthorCommand.execute();
    }

    public void deleteNews() {
        deleteNewsCommand.execute();
    }

    public void deleteTag() {
        deleteTagCommand.execute();
    }

    public void getAllAuthors() {
        getAllAuthorsCommand.execute();
    }

    public void getAllNews() {
        getAllNewsCommand.execute();
    }

    public void getAllTags() {
        getAllTagsCommand.execute();
    }

    public void getAuthorById() {
        getAuthorByIdCommand.execute();
    }

    public void getNewsById() {
        getNewsByIdCommand.execute();
    }

    public void getTagById() {
        getTagByIdCommand.execute();
    }

    public void updateAuthor() {
        updateAuthorCommand.execute();
    }

    public void updateNews() {
        updateNewsCommand.execute();
    }

    public void updateTag() {
        updateTagCommand.execute();
    }

    public void getAuthorByNewsId() {
        getAuthorByNewsIdCommand.execute();
    }

    public void getTagsByNewsId() {
        getTagsByNewsIdCommand.execute();
    }

    public void exit() {
        exitCommand.execute();
    }

    public void printMainMenu() {
        printMainMenuCommand.execute();
    }
    
}
