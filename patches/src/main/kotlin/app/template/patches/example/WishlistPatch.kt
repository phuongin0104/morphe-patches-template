
package app.template.patches.example

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.patch
import app.template.patches.shared.Constants.COMPATIBILITY_EXAMPLE

private const val WISHLIST_EXTENSION =
    "Lapp/template/extension/extension/ExamplePatch;"

@Suppress("unused")
val wishlistPatch = patch(
    name = "Wishlist",
    description = "Add a wishlist feature to item details.",
    default = true
) {
    compatibleWith(COMPATIBILITY_EXAMPLE)

    dependsOn(internalPatch)

    extendWith("extensions/extension.mpe")

    execute {
        ItemDetailActivityInitViewFingerprint.method.addInstructions(
            0,
            """
                invoke-static {p0}, $WISHLIST_EXTENSION;->initWishlist(Landroid/app/Activity;)V
            """
        )
    }
}
