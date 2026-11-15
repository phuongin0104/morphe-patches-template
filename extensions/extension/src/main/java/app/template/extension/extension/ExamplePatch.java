
package app.template.extension.extension;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class ExamplePatch {

    private static final String PREFS = "wishlist_data";
    private static final String KEY_ITEMS = "saved_items";

    public static void saveItem(
        Activity activity,
        String itemId,
        String title
    ) {
        if (activity == null || itemId == null || itemId.isEmpty()) return;

        SharedPreferences prefs = activity.getSharedPreferences(
            PREFS, Activity.MODE_PRIVATE
        );

        try {
            JSONArray items = new JSONArray(
                prefs.getString(KEY_ITEMS, "[]")
            );

            for (int i = 0; i < items.length(); i++) {
                if (itemId.equals(items.getJSONObject(i).optString("id"))) {
                    Toast.makeText(activity, "Sản phẩm đã được lưu", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            JSONObject item = new JSONObject();
            item.put("id", itemId);
            item.put("title", title == null ? "Sản phẩm không tên" : title);

            items.put(item);

            prefs.edit().putString(KEY_ITEMS, items.toString()).apply();

            Toast.makeText(activity, "Đã lưu sản phẩm", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(activity, "Không thể lưu sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }

    public static void showWishlist(Activity activity) {
        if (activity == null) return;

        SharedPreferences prefs = activity.getSharedPreferences(
            PREFS, Activity.MODE_PRIVATE
        );

        try {
            JSONArray items = new JSONArray(
                prefs.getString(KEY_ITEMS, "[]")
            );

            if (items.length() == 0) {
                Toast.makeText(activity, "Danh sách đang trống", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] titles = new String[items.length()];

            for (int i = 0; i < items.length(); i++) {
                titles[i] = items.getJSONObject(i).optString("title");
            }

            new AlertDialog.Builder(activity)
                .setTitle("Sản phẩm đã lưu")
                .setItems(titles, (dialog, which) -> {
                    // Sẽ bổ sung chức năng mở sản phẩm ở bước tiếp theo.
                })
                .setNegativeButton("Đóng", null)
                .show();

        } catch (Exception e) {
            Toast.makeText(activity, "Không thể đọc danh sách", Toast.LENGTH_SHORT).show();
        }
    }
}
