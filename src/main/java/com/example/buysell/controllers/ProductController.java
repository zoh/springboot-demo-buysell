package com.example.buysell.controllers;

import com.example.buysell.models.Product;
import com.example.buysell.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        var items = productService.list(title);
        model.addAttribute("products", items);
        return "products";
    }

    @PostMapping("/product/create")
    public String createProduct(
            Product product,
            @RequestParam(name = "image1") MultipartFile image1,
            @RequestParam(name = "image2") MultipartFile image2,
            @RequestParam(name = "image3") MultipartFile image3
    ) throws IOException {
        MultipartFile[] list = new MultipartFile[]{image1, image2, image3};
        productService.saveProduct(product, list);
        return "redirect:/";
    }

    @PostMapping("/product/{id}/delete")
    public String delProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String projectInfo(@PathVariable Long id, Model model) {
        var items = productService.getProductById(id);
        model.addAttribute("product", items);
        model.addAttribute("images", items.getImages());

        return "product-info";
    }
}
