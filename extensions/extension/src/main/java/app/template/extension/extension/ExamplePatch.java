package app.template.extension.extension;

import android.app.Activity;
import android.content.SharedPreferences;

@SuppressWarnings("unused")
public class ExamplePatch {

    private static final String PREFS = "wishlist_data";
    private static final String KEY_ITEMS = "saved_items";

    public static void test(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(
            PREFS, Activity.MODE_PRIVATE
        );
    }
}
