package request;

import products.Product;
import users.Seller;

import java.util.ArrayList;

public class ProductRequest {

    private final Seller seller;
    private final ArrayList<Product> product;
    private RequestFunction requestFunction;

    public ProductRequest(Seller seller, ArrayList<Product> product, RequestFunction requestFunction) {
        this.seller = seller;
        this.product = product;
        this.requestFunction = requestFunction;
        SearchRequest.setProductRequests(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public String printProducts() {
        if (product.size() == 1)
            return product.get(0).toString();
        else
            return product.get(0).toString() + "\t" + product.get(1).toString();
    }

    public RequestFunction getRequestFunction() {
        return requestFunction;
    }

    public void setRequestFunction(RequestFunction requestFunction) {
        this.requestFunction = requestFunction;
    }

    public boolean equals (ProductRequest productRequest) {
        if (productRequest.seller == seller)
            if (productRequest.product == product)
                return productRequest.requestFunction == requestFunction;
        return false;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "seller=" + seller.toString() +
                ", product=" + printProducts() +
                ", requestFunction=" + requestFunction.toString() +
                '}';
    }
}

