package cf.awidiyadew.drawerexpandablelistview.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cf.awidiyadew.drawerexpandablelistview.R;

/**
 * Created by awidiyadew on 15/09/16.
 */
public class CustomDataProvider {

    private static List<BaseItem> mMenu = new ArrayList<>();

    public static List<BaseItem> getInitialItems() {
        return getSubItems(new GroupItem("root"));
    }

    public static List<BaseItem> getSubItems(BaseItem baseItem) {
        if (!(baseItem instanceof GroupItem)) {
            throw new IllegalArgumentException("GroupItem required");
        }

        Item i1 = new Item("BERANDA");
        Item i2 = new Item("KERANJANG");

        GroupItem g1 = new GroupItem("KATEGORI I");
        ((GroupItem) g1).setLevel(1);

        GroupItem g2 = new GroupItem("KATEGORI II");
        ((GroupItem) g2).setLevel(1);

        mMenu.add(i1);
        mMenu.add(i2);
        mMenu.add(g1);
        mMenu.add(g2);
        return mMenu;
    }

    public static boolean isExpandable(BaseItem baseItem) {
        return baseItem instanceof GroupItem;
    }
}
