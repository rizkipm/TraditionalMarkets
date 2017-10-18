package developer.santri.intramarket;


import java.util.ArrayList;
import java.util.List;

import developer.santri.intramarket.model.Friend;


/**
 * @author Yalantis
 */
public class Utils {
    public static final List<Friend> friends = new ArrayList<>();

    static {
        friends.add(new Friend(R.drawable.melayan, "Pasar Melayan", R.color.sienna, "Kab Madiun", "Jawa Timur"));
        friends.add(new Friend(R.drawable.arjo, "Pasar Arjosari", R.color.saffron, "Kab Pacitan", "Jawa Timur"));
        friends.add(new Friend(R.drawable.kosambi, "Pasar Kosambi", R.color.green, "Kota Bandung", "Jawa Barat"));
        friends.add(new Friend(R.drawable.beringharjo, "Pasar Beringharjo", R.color.pink, "Yogyakarta", "Prov Yogyakarta"));
        friends.add(new Friend(R.drawable.segarr, "Pasar Segar Depok", R.color.orange, "Kab Depok", "Jawa Barat"));
        friends.add(new Friend(R.drawable.klewer, "Pasar Klewer Solo", R.color.saffron, "Kota Solo", "Jawa Tengah"));
        friends.add(new Friend(R.drawable.tanahabang, "Pasar Tanah Abang", R.color.green, "Kota Jakarta", "DKI Jakarta"));
        friends.add(new Friend(R.drawable.jati, "Pasar Jatinegara", R.color.purple, "Kota Jakarta", "DKI Jakarta"));
    }
}
