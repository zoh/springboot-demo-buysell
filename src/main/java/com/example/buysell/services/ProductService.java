package com.example.buysell.services;

import com.example.buysell.models.Product;
import com.example.buysell.models.Image;
import com.example.buysell.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    protected final ProductRepository productRepository;


    public List<Product> list(String title) {
        return (title != null)
                ? productRepository.findByTitle(title)
                : productRepository.findAll();
    }

    public void saveProduct(Product product, MultipartFile[] images) throws IOException {
        boolean setPreviewImage = false;

        for (var image : images) {
            if (image.getSize() > 0) {
                Image im = toImageEntity(image);
                if (!setPreviewImage) {
                    setPreviewImage = true;
                    im.setPreviewImage(true);
                }
                product.addImage(im);
            }
        }

        log.info("Saving new {}, price = {}", product.getTitle(), product.getPrice());
        var productFromDB = productRepository.save(product);

        // set first image
        if (!product.getImages().isEmpty()) {
            var firstImage = productFromDB.getImages().get(0);
            if (firstImage != null) {
                productFromDB.setPreviewImageId(firstImage.getId());
            }
        }
        productRepository.save(productFromDB);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

}
