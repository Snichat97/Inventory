import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InverntoryCreatorAdapter {

    HashMap<String,ItemPOJO> InventoryList = new HashMap<>();
    FileWriter reciept;
    FileWriter reciept_error;
    FactoryOpenerSingleton fos =new FactoryOpenerSingleton().getInstance(this);
    int counter=10000;
    private CardNumberList cart;

    public InverntoryCreatorAdapter() throws IOException {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("inventory.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray itemList = (JSONArray) obj;
            System.out.println(itemList);

            //Iterate over employee array
            itemList.forEach( item ->
            {parseInventoryObject( (JSONObject) item );});


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }
    private void parseInventoryObject(JSONObject itemObj)
    {
        //Get employee object within list
        JSONObject itemObjObject = (JSONObject) itemObj.get("Item");
        String category = (String) itemObjObject.get("category");
        String item = ((String) itemObjObject.get("item")).toLowerCase();
        float price = ((Double) itemObjObject.get("price")).floatValue();
        Long quantity = (Long) itemObjObject.get("quantity");
        counter+=1;
        if(category.equals("Essentials")){
            InventoryList.put(item,fos.essentialsFactory.addItem(item,price,quantity,counter));
        }
        else if(category.equals("Luxury")){
            InventoryList.put(item,fos.luxuryFactory.addItem(item,price,quantity,counter));
        }
        else if(category.equals("Miscellanoeous")){
            InventoryList.put(item,fos.miscellaneousFactory.addItem(item,price,quantity,counter));
        }
        System.out.println(counter+item);
    }

    public String checkInventory(String checkItem,int checkQuant, CardPOJO card){
        ItemPOJO item =InventoryList.get(checkItem);
        if(item!=null && item.category.equals("Essentials")) {
            return(fos.essentialsFactory.checkPurchase(checkItem,checkQuant,card));
        }
        else if(item!=null && item.category.equals("Luxury")){
            return(fos.luxuryFactory.checkPurchase(checkItem,checkQuant,card));
        }
        else if(item!=null && item.category.equals("Miscellanoeous")) {
            return(fos.miscellaneousFactory.checkPurchase(checkItem,checkQuant,card));
        };
        return("No such category exists"+""+item.category);
    }

    public void setCart(CardNumberList cnl) {
        cart=cnl;
        FactoryOpenerSingleton.setCart(cart);
    }
}
