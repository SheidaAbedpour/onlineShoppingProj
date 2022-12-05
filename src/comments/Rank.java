package comments;

import products.Product;
import users.Consumer;

public class Rank {

    private Consumer consumer;
    private int rank;
    private Product product;

    public Rank (Consumer consumer, int rank, Product product) {
        this.consumer = consumer;
        this.product = product;
        this.rank = rank;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "consumer=" + consumer +
                ", rank=" + rank +
                ", product=" + product +
                '}';
    }
}
