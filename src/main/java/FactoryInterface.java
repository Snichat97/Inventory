public interface FactoryInterface {
    public void changeThreshold(int threshold);
    public void decrementThreshold(int counter);
    public int thresholdExists(int counter);
    public ItemPOJO addItem(String item, float price, Long quantity, int counter);
    public void setCart(CardNumberList cnl);
    public String checkPurchase(String checkItem,int checkQuant,CardPOJO card);
}
