# Pertanyan Soal
Assignment Lanjutan: Implementasi Sistem Manajemen Perpustakaan dengan Penyimpanan Data ke File

Anda diminta untuk melanjutkan pengembangan Sistem Manajemen Perpustakaan yang sebelumnya Anda buat dengan konsep Pemrograman Berorientasi Objek (OOP) Java. Pada tahap ini, Anda akan mengimplementasikan penyimpanan data buku dan anggota ke dalam file menggunakan 'BufferedReader' dan 'BufferedWriter dengan detail sebagai berikut:
1. Buat kelas 'FileManager' yang akan bertanggung jawab untuk menyimpan dan membaca data dari file. 
2. Modifikasi kelas 'Library' untuk memiliki metode-metode berikut:
- 'saveToFile': Metode ini akan menggunakan BufferedWriter untuk menyimpan data buku dan anggota ke dalam file. 
- 'loadFromFile': Metode ini akan menggunakan BufferedReader untuk membaca data buku dan anggota dari file.
3. Saat 'Library' diinisialisasi, panggil metode 'loadFromFile' untuk memuat data dari file ke dalam sistem perpustakaan.
4. Modifikasi metode-metode lainnya pada kelas 'Library' yang berkaitan dengan manajemen buku dan anggota sehingga setiap perubahan pada data akan direfleksikan dalam file dengan memanggil metode 'saveToFile'.
5. Gunakan format yang sesuai untuk menyimpan data ke dalam file. Misalnya, Anda dapat menggunakan format CSV (Comma Separated Values) atau format lain yang mudah diuraikan.
6. Pastikan Anda mengimplementasikan penanganan kesalahan yang sesuai saat melakukan operasi baca/tulis ke file, seperti menangani exception yang mungkin terjadi.
7. Buat perubahan pada antarmuka pengguna sederhana yang sebelumnya Anda buat untuk melibatkan pemanggilan metode dari 'FileManager', 'Library', dan operasi-operasi yang berkaitan dengan penyimpanan dan pembacaan data ke/dari file.
8. Ujilah implementasi Anda dengan menjalankan program dan memastikan bahwa data yang disimpan dalam file dapat dibaca kembali dengan benar dan menghasilkan output yang sesuai.
9. Komentari kode Anda dengan baik untuk menjelaskan langkah-langkah yang Anda lakukan dalam implementasi ini.

Catatan :

Pastikan Anda memperhatikan enkapsulasi yang tepat dalam mengelola data dan operasi-operasi yang berkaitan dengan penyimpanan dan pembacaan data ke/dari file. Selain itu, pastikan Anda mengimplementasikan penanganan exception yang sesuai dalam operasi-operasi baca/tulis ke file.
