import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CardNumberList {
    List<CardPOJO> CardList = new ArrayList<CardPOJO>();
    InverntoryCreatorAdapter inventory;

    int essential_upperLimit =3;
    int luxury_upperLimit =4;
    int miscellaneous_upperLimit =6;

    int essential_count =0;
    int luxury_count =0;
    int miscellaneous_count =0;

    float cart_sum=0.0f;

    public CardNumberList(InverntoryCreatorAdapter invent,String path) throws IOException {
        Card(5023451276129087l);
        Card(1234567891014519l);
        inventory = invent;
        inventory.setCart(this);
        String line = "";
        String splitBy = ",";
        inventory.reciept = new FileWriter("reciept.txt");
        inventory.reciept.write("Reciept");
        inventory.reciept_error = new FileWriter("reciept_error.txt");
        inventory.reciept_error.write("Please correct quantities");
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] cardDetails = line.split(splitBy);    // use comma as separator
                System.out.println("Order Item=" + cardDetails[0] + ", Quantity=" + cardDetails[1] + ", CardNumber=" + cardDetails[2]);
                String itemNumber = ((String) cardDetails[0]).toLowerCase();
                Integer quantity = Integer.parseInt(cardDetails[1]);
                Long cardNumber = Long.parseLong(cardDetails[2]);
                CardPOJO card = Card(cardNumber);
                System.out.println(inventory.checkInventory(itemNumber, quantity, card));
            }
            System.out.print("Sum of the cart is:");
            System.out.print(cart_sum);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        inventory.reciept.write("\n");
        inventory.reciept.write("-----------------------------------------");
        inventory.reciept.write("\n");
        inventory.reciept.write("Sum of the cart is : ");
        inventory.reciept.write(String.valueOf(cart_sum));
        inventory.reciept_error.close();
    }

    public CardPOJO cardExists(long verifyNumber){
        for (CardPOJO i:CardList) {
            if (verifyNumber == i.getCardNumber()) {
                return (i);
            }
        }
        CardPOJO card = new CardPOJO(verifyNumber);
        CardList.add(card);
        return(card);
    }

    public CardPOJO Card(long number) {
        return(cardExists(number));
    }

    public void getCardList() throws IOException {
        System.out.println("Card list :");
        inventory.reciept.write("\n");
        inventory.reciept.write("\n");
        inventory.reciept.write("-----------------------------------------");
        inventory.reciept.write("\n");
        inventory.reciept.write("Card wise split");
        inventory.reciept.write("\n");
        for (CardPOJO i:CardList) {
            System.out.println(i.getCardNumber() + " "+i.cost);
            inventory.reciept.write(i.getCardNumber() + " "+i.cost);
            inventory.reciept.write("\n");
        }
        inventory.reciept.close();
    }
}
