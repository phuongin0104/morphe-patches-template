
package app.template.patches.wishlist

import app.morphe.patcher.patch.patch
import app.template.patches.shared.Constants.COMPATIBILITY_XIANYU

@Suppress("unused")
val xianyuWishlistPatch = patch(
    name = "Xianyu Wishlist",
    description = "Save Xianyu products to a private local wishlist.",
    default = true
) {
    compatibleWith(COMPATIBILITY_XIANYU)

    // Wishlist implementation will be added here.
}
