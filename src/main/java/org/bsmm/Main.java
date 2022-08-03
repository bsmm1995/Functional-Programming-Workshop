package org.bsmm;

import org.bsmm.domain.ProductDto;
import org.bsmm.functions.ArithmeticOperations;
import org.bsmm.service.ProviderService;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        ProviderService providerService = new ProviderService();

        printList(providerService.findAllProductsDto());
        printList(providerService.findAllProductsOrderByName());
        printList(providerService.findProductsCodeStartsWith("Z"));
        System.out.println(providerService.getProductByCode("C-1"));
        System.out.println(providerService.getTotalProducts());
        System.out.println();
        operations();
    }

    private static void printList(List<ProductDto> list) {
        list.forEach(System.out::println);
    }

    private static void operations() {
        ArithmeticOperations sum = (a, b) -> a + (b * b);
        System.out.println(sum.operation(15.5, 10));
        System.out.println(sum.operation());
    }
}