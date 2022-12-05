package request;

import java.util.ArrayList;

public class SearchRequest {

    private static ArrayList<ProductRequest> productRequests = new ArrayList<>();

    public static ArrayList<ProductRequest> getProductRequests() {
        return productRequests;
    }

    public static void setProductRequests(ProductRequest productRequest) {
        productRequests.add(productRequest);
    }

    public static boolean added(ProductRequest productRequest) {
        for (ProductRequest PR : productRequests)
            if (PR.equals(productRequest))
                return true;
        return false;
    }
}
