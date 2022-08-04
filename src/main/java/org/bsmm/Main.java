package org.bsmm;

import org.bsmm.domain.ProductDto;
import org.bsmm.functions.FunctionalOperation;
import org.bsmm.service.ProviderService;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        ProviderService providerService = new ProviderService();

        System.out.println("All products");
        printList(providerService.findAllProductsDto());

        System.out.println("All product names");
        System.out.println(providerService.findAllProductNames());

        System.out.println("Products with a 5-character name");
        System.out.println(providerService.findAllProductsDto().stream().anyMatch(s -> s.getName().length() == 5));

        System.out.println("Order by name");
        printList(providerService.findAllProductsOrderByName());

        System.out.println("Code starts with");
        printList(providerService.findProductsCodeStartsWith("Z"));

        System.out.println("Get by code");
        System.out.println(providerService.getProductByCode("C-1"));

        System.out.println("Get total products");
        System.out.println(providerService.getTotalProducts());

        System.out.println("Get product quantity by code");
        System.out.println(providerService.getProductQuantityByCode("C-1"));

        System.out.println("Show product detail by code");
        providerService.showProductDetailByCode("C-1");

        System.out.println("Operations");
        operations();
    }

    private static void printList(List<ProductDto> list) {
        list.forEach(System.out::println);
    }

    private static void operations() {
        FunctionalOperation operation = (a, b) -> a + (b * b);

        System.out.println(operation.operation(1, 2));
        System.out.println(operation.defaultMethod());
    }
}