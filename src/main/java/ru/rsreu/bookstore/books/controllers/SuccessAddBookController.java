package ru.rsreu.bookstore.books.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("book/add/success")
@SessionAttributes("book")
public class SuccessAddBookController {

    @GetMapping
    public String showSuccessAddBookForm(){
        return "success_add_book";
    }

    @PostMapping
    public String toHomePage(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
