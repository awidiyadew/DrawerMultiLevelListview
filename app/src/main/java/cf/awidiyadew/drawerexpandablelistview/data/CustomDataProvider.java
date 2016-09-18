package cf.awidiyadew.drawerexpandablelistview.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cf.awidiyadew.drawerexpandablelistview.R;

/**
 * Created by awidiyadew on 15/09/16.
 */
public class CustomDataProvider {

    private static final int MAX_LEVELS = 3;

    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;

    private static List<BaseItem> mMenu = new ArrayList<>();

    public static List<BaseItem> getInitialItems() {
        //return getSubItems(new GroupItem("root"));

        List<BaseItem> rootMenu = new ArrayList<>();

        /*
        * ITEM = TANPA CHILD
        * GROUPITEM = DENGAN CHILD
        * */
        rootMenu.add(new Item("BERANDA"));
        rootMenu.add(new Item("KERANJANG"));
        rootMenu.add(new GroupItem("KATEGORI"));
        rootMenu.add(new GroupItem("KATEGORI LAINNYA"));
        rootMenu.add(new Item("PENGATURAN"));

        return rootMenu;
    }

    public static List<BaseItem> getSubItems(BaseItem baseItem) {

        List<BaseItem> result = new ArrayList<>();
        int level = ((GroupItem) baseItem).getLevel() + 1;
        String menuItem = baseItem.getName();

        if (!(baseItem instanceof GroupItem)) {
            throw new IllegalArgumentException("GroupItem required");
        }

        GroupItem groupItem = (GroupItem)baseItem;
        if(groupItem.getLevel() >= MAX_LEVELS){
            return null;
        }

        /*
        * HANYA UNTUK GROUP-ITEM
        * */
        switch (level){
            case LEVEL_1 :
                switch (menuItem.toUpperCase()){
                    case "KATEGORI" :
                        result = getListKategori();
                        break;
                    case "KATEGORI LAINNYA" :
                        result = getListKategoriLainnya();
                        break;
                }
                break;

            case LEVEL_2 :
                switch (menuItem){
                    case "GROUP 1" :
                        result = getListGroup1();
                        break;
                    case "GROUP X" :
                        result = getListGroupX();
                        break;
                }
                break;
        }

        return result;
    }

    public static boolean isExpandable(BaseItem baseItem) {
        return baseItem instanceof GroupItem;
    }

    private static List<BaseItem> getListKategori(){

        List<BaseItem> list = new ArrayList<>();

        // Setiap membuat groupItem harus di set levelnya
        GroupItem groupItem = new GroupItem("GROUP 1");
        groupItem.setLevel(groupItem.getLevel() + 1);

        list.add(new Item("ITEM 1"));
        list.add(new Item("ITEM 2"));
        list.add(groupItem);

        return list;
    }

    private static List<BaseItem> getListKategoriLainnya(){

        List<BaseItem> list = new ArrayList<>();
        GroupItem groupItem = new GroupItem("GROUP X");
        groupItem.setLevel(groupItem.getLevel() + 1);

        list.add(new Item("ITEM A"));
        list.add(new Item("ITEM B"));
        list.add(groupItem);

        return list;
    }

    private static List<BaseItem> getListGroup1(){
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("CHILD OF G1-A"));
        list.add(new Item("CHILD OF G1-B"));

        return list;
    }

    private static List<BaseItem> getListGroupX(){
        List<BaseItem> list = new ArrayList<>();
        list.add(new Item("CHILD OF GX-A"));
        list.add(new Item("CHILD OF GX-B"));

        return list;
    }

}
