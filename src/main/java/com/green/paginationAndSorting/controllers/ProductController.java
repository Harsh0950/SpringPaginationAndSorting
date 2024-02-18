package com.green.paginationAndSorting.controllers;

import com.green.paginationAndSorting.entities.ApiReponse;
import com.green.paginationAndSorting.entities.Product;
import com.green.paginationAndSorting.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/green")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/addSampleData")
    public void addSampleAdd() {
        this.productService.addSampleData();
    }

    @GetMapping("/")
    public ResponseEntity<ApiReponse> getAllProducts() {
        List<Product> list = this.productService.getAllProducts();
        ApiReponse result = new ApiReponse(list.size(),list);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    sorting the records on the basis of field
    @GetMapping("/{field}")
    public ResponseEntity<ApiReponse> getAllProductsWithSort(@PathVariable String field) {
        List<Product> list = this.productService.findALlProductsBySorting(field);
        ApiReponse result = new ApiReponse(list.size(),list);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    offset is page no and pageSize is no of pages in one single page
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<ApiReponse> getAllProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Product> list = this.productService.findALlProductsWithPagination(offset,pageSize);
        ApiReponse result = new ApiReponse(list.getSize(),list);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // pagination and sorting both in one api
    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    public ResponseEntity<ApiReponse> getAllProductsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, String field) {
        Page<Product> list = this.productService.findALlProductsWithPaginationAndSorting(offset,pageSize,field);
        ApiReponse result = new ApiReponse(list.getSize(),list);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
