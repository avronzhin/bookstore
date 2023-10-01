package ru.rsreu.bookstore.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.models.ErrorMessage;
import ru.rsreu.bookstore.books.repositories.BookRepository;
import ru.rsreu.bookstore.security.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/book/delete")
public class BookDeletingController {
    private final BookRepository bookRepository;

    @Autowired
    public BookDeletingController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @ModelAttribute
    public ErrorMessage errorMessage() {
        return new ErrorMessage("Нет ошибки");
    }

    @PostMapping
    public String delete(@RequestParam long bookId, @AuthenticationPrincipal User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            model.addAttribute("errorMessage", new ErrorMessage("Книга не найдена"));
        } else {
            Book book = bookOptional.get();
            if (book.getPublisher().equals(user)) {
                bookRepository.deleteById(bookId);
            } else {
                redirectAttributes.addFlashAttribute("errorMessage",
                        new ErrorMessage("Вы не можете удалить эту книгу"));
            }
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
