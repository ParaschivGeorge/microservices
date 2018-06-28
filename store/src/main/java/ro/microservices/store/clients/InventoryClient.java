package ro.microservices.store.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ro.microservices.store.models.InventoryModel;

import java.math.BigDecimal;

@FeignClient(name = "inventory-service", fallbackFactory = InventoryClientFallbackFactory.class)
public interface InventoryClient {

    @GetMapping(value = "/products/{code}")
    ResponseEntity<InventoryModel> getProductInventory(@PathVariable("code") final String code);

//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String inventoryUrl;
//
//    public InventoryClient(@Value("${inventory.api.url}") final String inventoryUrl) {
//        this.inventoryUrl = inventoryUrl;
//    }
//
//    public InventoryModel getProductInventory(final String code) {
//        final String url = inventoryUrl + "/products/" + code;
//        try {
//            return restTemplate.getForEntity(url, InventoryModel.class).getBody();
//        } catch (Exception e) {
//            // log
//            return InventoryModel.builder()
//                    .code(code)
//                    .price(new BigDecimal(0))
//                    .build();
//        }
//    }

}
