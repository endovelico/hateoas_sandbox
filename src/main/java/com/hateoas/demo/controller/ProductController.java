package com.hateoas.demo.controller;

import com.hateoas.demo.entity.Product;
import com.hateoas.demo.model.ProductModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}