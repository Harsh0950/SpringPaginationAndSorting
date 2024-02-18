package com.green.paginationAndSorting.services;

import com.green.paginationAndSorting.entities.Product;
import com.green.paginationAndSorting.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public void addSampleData() {
        List<Product> products = IntStream.rangeClosed(1,200)
                .mapToObj(i->new Product("product"+i, new Random().nextInt(100), new Random().nextInt(50000)))
                .collect(Collectors.toList());

        this.productRepository.saveAll(products);
    }

    public List<Product> findALlProductsBySorting(String field) {
        return this.productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Product> findALlProductsWithPagination(int offset, int pageSize) {
        Page<Product> products= this.productRepository.findAll(PageRequest.of(offset,pageSize));
        return products;
    }

    public Page<Product> findALlProductsWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<Product> products= this.productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return products;
    }
}
