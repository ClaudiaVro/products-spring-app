package com.clodi;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Optional;

import com.clodi.controller.ProductController;
import com.clodi.dto.ProductDTO;
import com.clodi.exception.ProductNotFoundException;
import com.clodi.proxy.ProductProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

/**
 * These are only examples of tests for learning reasons, and are in no way
 * thorough.
 */
@ExtendWith(MockitoExtension.class) public class ProductControllerTests {

    @Mock private ProductProxy productProxy;

    @InjectMocks private ProductController productController;

    @Test void updateProductUnsuccesfulNoIdFound() {
        ProductDTO product = new ProductDTO();
        product.setId(33L);
        product.setName("mockName");
        product.setPrice(new BigDecimal("22.3"));

        BDDMockito.given(productProxy.getProduct(product.getId())).willReturn(Optional.empty());

        BindingResult br = mock(BindingResult.class);
        StandardMultipartHttpServletRequest request = mock(StandardMultipartHttpServletRequest.class);
        assertThrows(ProductNotFoundException.class, () -> productController.editProductSubmit(product, br, request));
    }

    @Test void updateProductIncompleteData() {
        ProductDTO product = new ProductDTO();

        BindingResult br = mock(BindingResult.class);
        StandardMultipartHttpServletRequest request = mock(StandardMultipartHttpServletRequest.class);
        assertThrows(IllegalStateException.class, () -> productController.editProductSubmit(product, br, request));
    }

}
