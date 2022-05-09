import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        InverntoryCreatorAdapter invent= new InverntoryCreatorAdapter();
        CardNumberList cnl;
        if (args[0] == null|| args[0].trim().isEmpty()) {
            cnl=new CardNumberList(invent,"reciept.txt");
        }
        else
        {
            System.out.println("Out of directory order being processed.");
            cnl=new CardNumberList(invent,args[0]);
        }
        System.out.println("Inventory now ready");
        System.out.println("User list now ready");
        System.out.println("");
        cnl.getCardList();
    }
}
