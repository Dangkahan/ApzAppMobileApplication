package apzshop.client_mobile.apzappmobile.Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import apzshop.client_mobile.apzappmobile.Domain.PopularDomain;

public class CartManagement {
    private Context context;
    private MiniDb miniDb;

    public CartManagement(Context context) {
        this.context = context;
        this.miniDb=new MiniDb(context);
    }
    public void insertFood(PopularDomain item) {
        ArrayList<PopularDomain> listPop = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listPop.size(); i++) {
            if (listPop.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready) {
            listPop.get(n).setNumberinCart(item.getNumberinCart());
        } else {
            listPop.add(item);
        }
        miniDb.putListObject("CartList", listPop);
        Toast.makeText(context, "Added To Your Cart.", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularDomain> getListCart() {
        return miniDb.getListObject("CartList");
    }

    public void minusNumberItem(ArrayList<PopularDomain>listItem,int position,ChangeNumberItemListener changeNumberItemListener){
        if(listItem.get(position).getNumberinCart()==1){
            listItem.remove(position);
        }else {
            listItem.get(position).setNumberinCart(listItem.get(position).getNumberinCart()-1);
        }
        miniDb.putListObject("CartList",listItem);
        changeNumberItemListener.change();
    }

    public void plusNumberItem(ArrayList<PopularDomain>listItem,int position,ChangeNumberItemListener changeNumberItemListener){
        listItem.get(position).setNumberinCart(listItem.get(position).getNumberinCart()+1);
        miniDb.putListObject("CartList",listItem);
        changeNumberItemListener.change();
    }
    public Double getTotalFee(){
        ArrayList<PopularDomain> listItem=getListCart();
        double fee=0;
        for (int i=0;i<listItem.size(); i++){
            fee=fee+(listItem.get(i).getPrice()*listItem.get(i).getNumberinCart());
        }
        return fee;
    }
}
