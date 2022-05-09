import java.io.FileWriter;
import java.io.IOException;

public class FactoryOpenerSingleton {
     static FactoryInterface essentialsFactory = null;
     static FactoryInterface luxuryFactory = null;
     static FactoryInterface miscellaneousFactory = null;
     static CardNumberList cart;

    public static void setCart(CardNumberList cnl) {
        cart=cnl;
        essentialsFactory.setCart(cnl);
        luxuryFactory.setCart(cnl);
        miscellaneousFactory.setCart(cnl);
    }

    public FactoryOpenerSingleton getInstance(InverntoryCreatorAdapter ica) throws IOException {
        if(essentialsFactory==null) {
            essentialsFactory = new EssentialsFactory(ica,ica.reciept,ica.reciept_error);
        }
        if(luxuryFactory==null) {
            luxuryFactory = new LuxuryFactory(ica,ica.reciept,ica.reciept_error);
        }
        if(miscellaneousFactory==null) {
            miscellaneousFactory = new MiscellaneousFactory(ica,ica.reciept,ica.reciept_error);
        }
        return this;
    }
}
