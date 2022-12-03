package ru.rsreu.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/genres")
@RequiredArgsConstructor
public class ManageGenreController {

    private final GenreService ingredientService;

    @GetMapping
    public String ingredientsAdmin(Model model) {
        model.addAttribute("genres", ingredientService.findAll());
        return "adminpage";
    }

    @PostMapping
    public String addIngredient(Genre genre) {
        ingredientService.addIngredient(genre);
        return "redirect:/admin/genres";
    }
}
