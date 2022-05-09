import java.io.FileWriter;
import java.io.IOException;

public class MiscellaneousFactory implements FactoryInterface{
    int miscellaneous_upperLimit =6;
    InverntoryCreatorAdapter inventoryCopy;
    private CardNumberList cart;
    FileWriter reciept;
    FileWriter reciept_error;

    public MiscellaneousFactory(InverntoryCreatorAdapter ica, FileWriter reciept, FileWriter reciept_error){
        inventoryCopy = ica;
        this.reciept=reciept;
        this.reciept_error=reciept_error;
    }

    public void changeThreshold(int threshold){
        miscellaneous_upperLimit =threshold;
    }
    public void decrementThreshold(int checkQuant){
        miscellaneous_upperLimit-=checkQuant;
    }
    public int thresholdExists(int checkQuant){
        if(miscellaneous_upperLimit>=checkQuant){
            return checkQuant;
        }
        else if(miscellaneous_upperLimit>0){
            return miscellaneous_upperLimit;
        }
        else{
            return -1;
        }
    }

    public ItemPOJO addItem(String item, float price, Long quantity, int counter) {
        return(new ItemPOJO("Miscellanoeous",item,price,quantity,counter));
    }

    public String checkPurchase(String checkItem, int checkQuant, CardPOJO card){
        System.out.println(checkItem+inventoryCopy.InventoryList.get(checkItem)+inventoryCopy.InventoryList);
        ItemPOJO value=inventoryCopy.InventoryList.get(checkItem);
        try {
            int a=thresholdExists(checkQuant);
            if(a!=-1){
                if(value.quantity>=a){
                    decrementThreshold(a);
                    value.quantity-=checkQuant;
                    cart.cart_sum+=(value.price*a);
                    card.cost+=(value.price*a);
                    inventoryCopy.reciept.write("\n");
                    inventoryCopy.reciept.write(value.item+" "+ a+" "+value.price*a);
                    if(a!=checkQuant) {
                        inventoryCopy.reciept_error.write(value.item + " " + "| Threshold for miscellaneous items has been reached only " + a + " were added.");
                    }
                }
                else if(value.quantity>0){
                    int v= Math.toIntExact(value.quantity);
                    decrementThreshold(v);
                    cart.cart_sum+=(value.price*v);
                    card.cost+=(value.price*v);
                    inventoryCopy.reciept.write("\n");
                    inventoryCopy.reciept.write(value.item+" "+ v+" "+value.price*v);
                    inventoryCopy.reciept_error.write(value.item + " " + "| Sorry complete quantity of " + checkQuant + "is not available.Quantitity " + v + " were added.");
                }
                else{
                    inventoryCopy.reciept_error.write(value.item + " " + "| Sorry quantity not available at all.");
                }

            }
            else{
                inventoryCopy.reciept_error.write(value.item+" "+"| Threshold for miscellaneous items has been reached only no items were added.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return("");
    }

    public void setCart(CardNumberList cnl) {
        cart=cnl;
    }
}
