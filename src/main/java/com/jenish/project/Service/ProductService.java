package com.jenish.project.Service;

import com.jenish.project.MOdel.Product;
import com.jenish.project.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo Repo;
    public List<Product> getAllProducts() {
        return Repo.findAll();

    }

    public Product getProductById(int id) {
        return Repo.findById(id) .orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
         return Repo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());

         return Repo.save(product);
    }
 public void deleteProduct(int id) {
        Repo.deleteById(id);
 }

    public List<Product> searchProducts(String keyword) {
        return Repo.searchProducts(keyword);
    }
}
