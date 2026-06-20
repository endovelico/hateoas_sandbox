package com.hateoas.demo.controller;

import com.hateoas.demo.entity.Product;
import com.hateoas.demo.model.ProductModelAssembler;
import org.apache.catalina.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();
    private final ProductModelAssembler assembler;

    public ProductController(ProductModelAssembler assembler) {
        this.assembler = assembler;

        products.add(new Product(1L, "Laptop", 1200));
        products.add(new Product(2L, "Phone", 800));
    }

    @GetMapping("/{id}")
    public EntityModel<Product> one(@PathVariable Long id) {
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));

        return assembler.toModel(product);
    }

    @GetMapping
    public CollectionModel<EntityModel<Product>> all() {

        List<EntityModel<Product>> productModels = products.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(productModels,
                linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    /*
    * These parameters must be there or theres an error being thrown.
    *
    * Parameters should be in the URL
    * */
    @GetMapping("/search.html")
    public String search(@RequestParam String query, @RequestParam("page") int page_number) {
        //model.put("current_date", new Date());
        return "home";
    }

    /*
    * Including defaults to avoid issues
    * */
    @GetMapping("/search.html")
    public String search(@RequestParam String query, @RequestParam(value="page", required = false) int page, @RequestParam(value="size", defaultValue = "10") int page_size) {
        return "home";
    }

    @RequestMapping(value="/saveuser.html", method=RequestMethod.POST)
    public String saveUser(@RequestParam String username, @RequestParam String password) {
        return "success";
    }

    @GetMapping(value="/search.html", produces="MediaType.TEXT_HTML")
    public String search2(@RequestParam String query, @RequestParam(value="page", required = false) int page_number) {
        return "home";
    }

    @RequestMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username) {
        User user = null;
        return user;
    }


}