package org.bsmm.service;

import org.bsmm.domain.ProductDto;
import org.bsmm.domain.ProductEntity;
import org.modelmapper.ModelMapper;

import java.util.*;

public class ProviderService {
    ArrayList<ProductEntity> productEntities;

    public List<ProductDto> findAllProductsDto() {
        return productEntities.stream().parallel().map(this::convertToDto).toList();
    }

    public List<String> findAllProductNames() {
        return productEntities.stream().map(ProductEntity::getName).toList();
    }

    public int getTotalProducts() {
        return productEntities.stream().mapToInt(ProductEntity::getQuantity).sum();
    }

    public int getTotalProducts2() {
        return productEntities.stream().map(ProductEntity::getQuantity).reduce(0, Integer::sum);
    }

    public List<ProductDto> findAllProductsOrderByName() {
        return productEntities.stream().map(this::convertToDto).sorted(Comparator.comparing(ProductDto::getName)).toList();
    }

    public ProductDto getProductByCode(String code) {
        Optional<ProductDto> optional = productEntities.stream().map(this::convertToDto).filter(Objects::nonNull).filter(e -> e.getCode().contentEquals(code)).findFirst();

        return optional.orElseThrow();
    }

    public int getProductQuantityByCode(String code) {
        Optional<ProductEntity> optional = productEntities.stream().filter(Objects::nonNull).filter(e -> e.getCode().contentEquals(code)).findFirst();

        if (optional.isEmpty()) {
            throw new IllegalStateException();
        }
        return optional.get().getQuantity();
    }

    public double getTotalProductsWithPVPGreaterThan(double limit) {
        return productEntities.stream().filter(product -> product.getCode().startsWith("C")).mapToDouble(product -> product.getPrice() * 1.12).filter(value -> value > limit).sum();
    }

    public void showProductDetailByCode(String code) {
        Optional<ProductDto> optional = productEntities.stream().map(this::convertToDto).filter(Objects::nonNull).filter(e -> e.getCode().contentEquals(code)).findFirst();

        optional.ifPresent(product -> {
            System.out.println(product.getCode());
            System.out.println(product.getName());
        });
    }

    public List<ProductDto> findProductsCodeStartsWith(String code) {
        return productEntities.stream().map(this::convertToDto).filter(e -> e.getCode().startsWith(code)).toList();
    }

    private ProductDto convertToDto(ProductEntity productEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productEntity, ProductDto.class);
    }

    public ProviderService() {
        productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity("C-1", "ProductEntity C-1", 10.0, 8));
        productEntities.add(new ProductEntity("C-2", "ProductEntity C-2", 2.0, 45));
        productEntities.add(new ProductEntity("C-3", "ProductEntity C-3", 5.75, 80));
        productEntities.add(new ProductEntity("A-1", "P A-1", 8.0, 25));
        productEntities.add(new ProductEntity("A-2", "P A-2", 5.0, 10));
        productEntities.add(new ProductEntity("A-3", "P A-3", 6.0, 1));
        productEntities.add(new ProductEntity("Z-1", "ProductEntity Z-1", 1.5, 5));
        productEntities.add(new ProductEntity("Z-2", "ProductEntity Z-2", 1.0, 34));
        productEntities.add(new ProductEntity("Z-3", "ProductEntity Z-3", 2.0, 22));
        productEntities.add(new ProductEntity("B-1", "ProductEntity B-1", 4.25, 11));
        productEntities.add(new ProductEntity("B-2", "ProductEntity B-2", 3.0, 90));
        productEntities.add(new ProductEntity("B-3", "ProductEntity B-3", 7.5, 3));
    }
}
