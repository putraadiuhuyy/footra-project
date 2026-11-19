# Footra (Android, Jetpack Compose)

Implementasi desain Figma "Footra" (Home, Product, About, Contact, Login, Sign Up + Splash).

## Cara menjalankan
1. Android Studio (Ladybug / 2024.2+ atau lebih baru) -> **Open** -> pilih folder `Footra`.
2. Tunggu Gradle Sync. Kalau diminta, setujui upgrade versi AGP / Kotlin / Compose BOM.
3. Pilih emulator / HP (minSdk 26) -> **Run**.

Untuk melihat tiap layar tanpa menjalankan app: buka `ui/Previews.kt` -> Split / Design view.

### Alternatif: masukkan ke project yang sudah ada
Buat project **Empty Activity (Compose)** dengan package `com.footra.app`, lalu salin:
- `app/src/main/java/com/footra/app/*` ke package project kamu
- `app/src/main/res/font/*` (8 file font) dan `res/values/themes.xml`
- dependency `navigation-compose` + `minSdk 26` dari `app/build.gradle.kts`

## Struktur
```
navigation/Navigation.kt      Routes + FootraNavigator (semua interaksi prototype Figma) + NavHost
ui/theme/                      Color.kt, Type.kt (font + text style), Theme.kt
ui/components/NavBar.kt        Navbar (logo, menu, search, avatar) + ChevronUp
ui/components/Footer.kt        Footer
ui/components/Cards.kt         DealCard, ProductCard
ui/components/Inputs.kt        FootraTextField, PrimaryButton
ui/screens/                    Splash, Home, Product, About, Contact, Login/SignUp
```

## Interaksi (sama dengan prototype Figma)
| Aksi | Tujuan |
|---|---|
| Splash selesai (animasi ~2,6 dtk) | Home (dissolve) |
| Logo / menu Home, Product, About, Contact | layar masing-masing |
| Ikon profil | Login |
| BUY NOW, kartu Best Deals | Product |
| Login -> tombol Login | Home |
| Login -> "Sign Up" | Sign Up |
| Sign Up -> Create Account / "Login" | Login |
| Footer: Online Retail / Get Started / Support / Contact / Services / Team | Product / Sign Up / Contact / Contact / About / About |

## Foto
Semua foto diatur di satu file: `ui/FootraImages.kt` (isi `R.drawable.nama` atau URL; `null` = kotak abu-abu).
Komponen `PhotoBox` (di `Cards.kt`) memakai Coil.

## Catatan
- **Ukuran sama persis dengan Figma:** semua angka di kode adalah nilai px dari Figma (frame 1080 px).
  Di `ui/theme/Design.kt`: `40.dx` = 40 px Figma, `24.fs` = font 24 px Figma, dikonversi
  `dp = px x (lebar layar dp / 1080)`, jadi proporsinya sama di semua ukuran HP.
  Teks memakai `FText(...)` (ukuran font, line height %, letter spacing % seperti di Figma).
- **Halaman Product:** scroll **vertikal** (seluruh halaman) dan scroll **horizontal** (baris filter
  Product Type / Size / Price). Filter ditengah kalau muat, bisa digeser kalau layar sempit.
- Font (Syne, Poppins, Archivo, Bebas Neue, Cormorant Garamond) sudah ada di `res/font` (Google Fonts, OFL).
- Data produk masih contoh (`ProductScreen.kt`), login / sign up masih validasi lokal saja -
  sambungkan ke API (mis. Laravel) di `LoginScreen` / `SignUpScreen`.
