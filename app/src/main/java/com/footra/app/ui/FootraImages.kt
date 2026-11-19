package com.footra.app.ui

import com.footra.app.R

/**
 * SEMUA FOTO DIATUR DI SINI.
 *
 * Saat ini berisi ilustrasi sepatu contoh (res/drawable-nodpi). Untuk memakai foto asli:
 *  - foto lokal  : taruh file di app/src/main/res/drawable-nodpi (nama huruf kecil, tanpa spasi)
 *                  lalu tulis  R.drawable.nama_file
 *  - foto online : "https://contoh.com/foto.jpg"
 *  - null        : tampil kotak abu-abu (placeholder)
 */
object FootraImages {
    // Home
    val homeHero: Any? = R.drawable.hero_banner
    val deals: List<Any?> = listOf(
        R.drawable.product_1, R.drawable.product_2,
        R.drawable.product_3, R.drawable.product_4
    )      // dipakai 4 kartu "Best Deals"
    val collectionMain: Any? = R.drawable.collection_main
    val collectionThumbs: List<Any?> = listOf(
        R.drawable.collection_thumb_5,
        R.drawable.collection_thumb_4,
        R.drawable.collection_thumb_3,
        R.drawable.collection_thumb_2
    )
    val promo: List<Any?> = listOf(R.drawable.promo_1, R.drawable.promo_2)

    // Product (9 kartu, urut dari kiri atas)
    val products: List<Any?> = listOf(
        R.drawable.product_1, R.drawable.product_2, R.drawable.product_3,
        R.drawable.product_4, R.drawable.product_5, R.drawable.product_6,
        R.drawable.product_7, R.drawable.product_8, R.drawable.product_9
    )

    // About
    val aboutBanner: Any? = R.drawable.about_banner
    val aboutStory: Any? = R.drawable.about_story
}
