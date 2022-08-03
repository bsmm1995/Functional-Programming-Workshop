package org.bsmm;

import org.bsmm.domain.ProductDto;
import org.bsmm.functions.ArithmeticOperations;
import org.bsmm.service.ProviderService;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        ProviderService providerService = new ProviderService();

        System.out.println("All products");
        printList(providerService.findAllProductsDto());
        System.out.println("Order by name");
        printList(providerService.findAllProductsOrderByName());
        System.out.println("Code starts with");
        printList(providerService.findProductsCodeStartsWith("Z"));
        System.out.println("Get by code");
        System.out.println(providerService.getProductByCode("C-1"));
        System.out.println("Get total products");
        System.out.println(providerService.getTotalProducts());

        operations();
    }

    private static void printList(List<ProductDto> list) {
        list.forEach(System.out::println);
    }

    private static void operations() {
        ArithmeticOperations sum = (a, b) -> a + (b * b);
        System.out.println("Operations");
        System.out.println(sum.operation(1, 2));
        System.out.println(sum.operation());
    }
}