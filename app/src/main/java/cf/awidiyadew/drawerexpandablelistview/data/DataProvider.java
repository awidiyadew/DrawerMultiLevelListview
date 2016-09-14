package cf.awidiyadew.drawerexpandablelistview.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by awidiyadew on 12/09/16.
 */
public class DataProvider {

    /**
     * Do not confuse with MultiLevelListView levels.
     * Following variables refer only to data generation process.
     * For instance, if ITEMS_PER_LEVEL = 2 and MAX_LEVELS = 3,
     * list should look like this:
     *      + 1
     *      | + 1.1
     *      | - - 1.1.1
     *      | - - 1.1.2
     *      | + 1.2
     *      | - - 1.2.1
     *      | - - 1.2.2
     *      | - - 1.2.3
     *      + 2
     *      | + 2.1
     *      | - - 2.1.1
     *      | - - 2.1.2
     *      | + 2.2
     *      | - - 2.2.1
     *      | - - 2.2.2
     */
    private static final int ITEMS_PER_LEVEL = 4;
    private static final int MAX_LEVELS = 6;

    public static List<BaseItem> getInitialItems() {
        return getSubItems(new GroupItem("root"));
    }

    public static List<BaseItem> getSubItems(BaseItem baseItem) {
        if (!(baseItem instanceof GroupItem)) {
            throw new IllegalArgumentException("GroupItem required");
        }

        GroupItem groupItem = (GroupItem)baseItem;
        if(groupItem.getLevel() >= MAX_LEVELS){
            return null;
        }

        List<BaseItem> result = new ArrayList<>(ITEMS_PER_LEVEL);
        int nextLevel = groupItem.getLevel() + 1;

        int groupNr = 0;
        int itemNr = 0;
        for (int i = 0; i < ITEMS_PER_LEVEL; ++i) {
            BaseItem item;
            if (i % 2 == 0 && nextLevel != MAX_LEVELS) {
                item = new GroupItem("Group " + Integer.toString(++groupNr));
                ((GroupItem)item).setLevel(nextLevel);
            } else {
                item = new Item("Item " + Integer.toString(++itemNr));
            }
            result.add(item);
        }
        return result;
    }

    public static boolean isExpandable(BaseItem baseItem) {
        return baseItem instanceof GroupItem;
    }
}
