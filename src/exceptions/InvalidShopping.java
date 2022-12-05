package exceptions;

public abstract class InvalidShopping extends MyExceptions {
    public InvalidShopping(String Message) {
        super("Invalid Shopping : " + Message);
    }
}
