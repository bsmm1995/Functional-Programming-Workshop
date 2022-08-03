package org.bsmm.service;

import org.bsmm.domain.Product;
import org.bsmm.domain.ProductDto;
import org.modelmapper.ModelMapper;

import java.util.*;

public class ProviderService {
    ArrayList<Product> products;

    public List<ProductDto> findAllProductsDto() {
        return products.stream().parallel().map(this::convertToDto).toList();
    }

    public List<String> findAllProductNames() {
        return products.stream().parallel().map(Product::getName).toList();
    }

    public int getTotalProducts() {
        return products.stream().mapToInt(Product::getQuantity).sum();
    }

    public List<ProductDto> findAllProductsOrderByName() {
        return products.stream().map(this::convertToDto).sorted(Comparator.comparing(ProductDto::getName)).toList();
    }

    public ProductDto getProductByCode(String code) {
        Optional<ProductDto> optional = products.stream().map(this::convertToDto)
                .filter(Objects::nonNull)
                .filter(e -> e.getCode().contentEquals(code)).findFirst();
        return optional.orElseThrow();
    }

    public List<ProductDto> findProductsCodeStartsWith(String code) {
        return products.stream().map(this::convertToDto).filter(e -> e.getCode().startsWith(code)).toList();
    }

    private ProductDto convertToDto(Product product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductDto.class);
    }

    public ProviderService() {
        products = new ArrayList<>();
        products.add(new Product("C-1", "Product C-1", 10.0, 8));
        products.add(new Product("C-2", "Product C-2", 2.0, 45));
        products.add(new Product("C-3", "Product C-3", 5.75, 80));
        products.add(new Product("A-1", "P A-1", 8.0, 25));
        products.add(new Product("A-2", "P A-2", 5.0, 10));
        products.add(new Product("A-3", "P A-3", 6.0, 1));
        products.add(new Product("Z-1", "Product Z-1", 1.5, 5));
        products.add(new Product("Z-2", "Product Z-2", 1.0, 34));
        products.add(new Product("Z-3", "Product Z-3", 2.0, 22));
        products.add(new Product("B-1", "Product B-1", 4.25, 11));
        products.add(new Product("B-2", "Product B-2", 3.0, 90));
        products.add(new Product("B-3", "Product B-3", 7.5, 3));
    }
}
