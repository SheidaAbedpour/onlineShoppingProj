package comments;

import products.Product;
import users.Consumer;

import java.io.Serializable;

public class Comment implements Serializable {

    private Consumer user;
    private Product product;
    private String Text;
    private Status status = Status.WAIT_FOR_CONFIRMATION;
    private boolean isConsumer;

    public Comment (Consumer consumer,Product product, String text) {
        this.user = consumer;
        this.product = product;
        this.Text = text;
    }

    public Consumer getUser() {
        return user;
    }

    public void setUser(Consumer user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isConsumer() {
        return isConsumer;
    }

    public void setConsumer(boolean consumer) {
        isConsumer = consumer;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", product=" + product +
                ", Text='" + Text + '\'' +
                ", status=" + status +
                ", isConsumer=" + isConsumer +
                '}';
    }
}
