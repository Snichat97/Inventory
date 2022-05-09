public class ItemPOJO {
    String category;
    String item;
    float price;
    Long quantity;
    enum categoryList {
        Essentials,
        Luxury,
        Miscellaneous
    };
    enum Level {
        Clothes,
        Soap,
        Milk,
        Perfume,
        Chocolates,
        Bedsheets,
        Footwear
    }
    int itemId;
    public ItemPOJO(String Category,String Item, float Price,Long Quantity,int id){
        category=Category;
        item=Item;
        price=Price;
        quantity=Quantity;
        itemId=id;
    }

}
