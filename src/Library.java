import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

// Class untuk menjalankan program dan controller semua ada disini
public class Library {
    private static List<Book> books = new ArrayList<>(); // Inisialisasi struktur data untuk menyimpan informasi buku
    public static List<Book> getBook () {
        return books;
    }

    private static List<Anggota> member = new ArrayList<>(); // Inisialisasi struktur data untuk menyimpan informasi member/anggota
    public static List<Anggota> getMember () { return member; }

    private static List<String> history = new ArrayList<>(); // Inisialisasi struktur data untuk menyimpan history buku
    public static List<String> getHistory () {
        return history;
    }
    // Fungsi untuk menambahkan data buku baru ke model dengan menginputkan semua variabel
    public static List<Book> tambahBuku(List<Book> books, String judul, String penulis, String isbn, int jumlah, String statusBuku) {
        books.add(new Book(judul, penulis, isbn, jumlah, statusBuku, null));
        return books;
    }
    // Fungsi untuk menambahkan anggota baru ke model dengan menginputkan semua variabel
    public static List<Anggota> tambahAnggota(List<Anggota> member, String idAnggota, String nama, String statusAnggota) {
        member.add(new Anggota(idAnggota, nama, statusAnggota));
        return member;
    }
    // Untuk menjalankan program utama di main
    public static void base() {
        Scanner scanner = new Scanner(System.in); // Mengambil class scanner untuk input teks pengguna

        System.out.println("Selamat Datang di Manajemen Perpustakaan Tharissa!"); // Menampilkan pesan selamat datang

        // Looping menu utama
        boolean loopingMenu = true;
        while (loopingMenu) {
            // Menampilkan menu pilihan kepada pengguna
            tampilkanMenu();
            // Menampilkan input pilihan menu kepada pengguna dengan memanggil fungsi yang sudah dibuat
            int pilihanUser = getPilihanUser(scanner);

            switch (pilihanUser) {
                // Menggunakan switch-case untuk menangani pilihan pengguna
                // Case 1 untuk memilih menu amanjemen buku
                case 1 -> {
                    boolean loopingBuku = true;
                    while (loopingBuku) {
                        tampilkanBuku(); // Menampilkan menu buku
                        int pilihanMenuKategoriBuku = getPilihanUser(scanner);
                        // Menangani pilihan switch case pengguna dengan switch case lagi
                        switch (pilihanMenuKategoriBuku) {
                            case 1 -> {
                                System.out.println("Anda memilih menu untuk menambahkan buku baru.");
                                tambahBukuBaru(scanner, books); // Memanggil fungsi tambah buku baru dengan menyertakan scanner beserta arrayList nya(data set barang dan stok)
                            }
                            case 2 -> {
                                System.out.println("Anda memilih menu untuk meminjam buku. Berikut list buku yang tersedia : ");
                                pinjamBuku(scanner, books, member, history); // Memangil fungsi pinjam buku
                            }
                            case 3 -> {
                                System.out.println("Anda memilih menu untuk mengembalikan buku. Berikut list buku yang sedang dipinjam : ");
                                pengembalianBuku(scanner, books, history, member); // Memangil fungsi pengembalian buku
                            }
                            case 4 -> {
                                System.out.println("Anda memilih menu untuk menghapus buku. Berikut list buku yang tersedia : ");
                                hapusBuku(scanner, books); // Memanggil fungsi hapus buku
                            }
                            case 5 -> {
                                boolean loopingLihatBuku = false;
                                while (!loopingLihatBuku) {
                                    tampilkanLihatBuku(); // Menampilkan menu lihat buku
                                    int pilihanMenuLihatBuku = getPilihanUser(scanner);
                                    switch (pilihanMenuLihatBuku) {
                                        case 1 -> {
                                            System.out.println("Anda memilih menu untuk melihat buku yang tersedia. Berikut datanya : ");
                                            listBuku(books); // Memanggil fungsi lihat semua buku
                                        }
                                        case 2 -> {
                                            System.out.println("Anda memilih menu untuk melihat buku yang dipinjam. Berikut datanya : ");
                                            listBukuPinjam(books, member); // Memanggil fungsi lihat data buku yang sedang dipinjam
                                        }
                                        case 3 -> {
                                            System.out.println("Anda memilih menu untuk melihat jumlah buku yang teserdia. Berikut datanya : ");
                                            listJumlahBuku(books); // Memanggil fungsi lihat buku beserta jumlahnya berdasarkan nama
                                        }
                                        case 4 -> {
                                            System.out.println("Anda memilih menu untuk melihat history buku. Berikut datanya : ");
                                            historyBook(history); // Memanggil fungsi lihat history keluar masuk buku
                                        }
                                        case 0 -> loopingLihatBuku = true; // Memberhentikan looping menu lihat buku
                                        case -1 -> { // Bila inputan pengguna selain angka, maka akan menghasilkan nilai -1
                                            System.out.println("Maaf! Input pilihan user harus berupa angka! Untuk mencoba lagi, silahkan tekan enter.");
                                            scanner.nextLine(); // Fungsinya untuk menghapus buffer
                                        }
                                        default -> { // Menampilkan pesan kesalahan untuk pilihan yang tidak valid misal angka 5 keatas
                                            System.out.println("Maaf menu, tidak tersedia, silahkan tekan enter untuk mencoba lagi.");
                                            scanner.nextLine();
                                        }
                                    }
                                }
                            }
                            case 0 -> loopingBuku = false;
                            case -1 -> {
                                System.out.println("Maaf! Input pilihan user harus berupa angka! Untuk mencoba lagi, silahkan tekan enter.");
                                scanner.nextLine();
                            }
                            default -> {
                                System.out.println("Maaf menu, tidak tersedia, silahkan tekan enter untuk mencoba lagi.");
                                scanner.nextLine();
                            }
                        }
                    }
                }
                // Case 2 untuk memilih menu manajemen anggota
                case 2 -> {
                    boolean loopingAnggota = true;
                    while (loopingAnggota) {
                        tampilkanAnggota();
                        int pilihanMenuAnggota = getPilihanUser(scanner);
                        switch (pilihanMenuAnggota) {
                            case 1 -> {
                                System.out.println("Anda memilih menu untuk menambahkan anggota baru.");
                                tambahAnggotaBaru(scanner, member); // Memanggil fungsi tambah anggota baru
                            }
                            case 2 -> {
                                System.out.println("Anda memilih menu untuk menghapus anggota. Berikut list anggota yang tersedia : ");
                                hapusAnggota(scanner, member); // Memanggil fungsi hapus anggota
                            }
                            case 3 -> {
                                System.out.println("Anda memilih menu untuk melihat anggota yang masih aktif. Berikut datanya : ");
                                listAnggota(member); // Memanggil fungsi lihat list anggota
                            }
                            case 0 -> loopingAnggota = false; // Keluar dari menu manajemen anggota
                            case -1 -> {
                                System.out.println("Maaf! Input pilihan user harus berupa angka! Untuk mencoba lagi, silahkan tekan enter.");
                                scanner.nextLine();
                            }
                            default -> {
                                System.out.println("Maaf menu, tidak tersedia, silahkan tekan enter untuk mencoba lagi.");
                                scanner.nextLine();
                            }
                        }
                    }
                }
                // Case 3 untuk memilih menu manajemen file
                case 3 -> {
                    FileManager fileManager = new FileManager(); // Memanggil class fileanager agar bisa dipanggil dalam class ini
                    boolean fileLooping = true;
                    while (fileLooping) {
                        tampilkanManajemenFile();
                        int pilihanFile = getPilihanUser(scanner);
                        switch (pilihanFile) {
                            case 1 -> {
                                System.out.println("Anda memilih menu untuk load file buku.");
                                fileManager.loadBukuFile(); // Memanggil fungsi load buku, yang berfungsi untuk import file csv ke database(arrayList)
                            }
                            case 2 -> {
                                System.out.println("Anda memilih menu untuk menyimpan list buku dalam bentuk file.");
                                fileManager.saveBukuFile(); // Memanggil fungsi save buku, berfungsi untuk menyimpan data buku ke file csv
                            }
                            case 3 -> {
                                System.out.println("Anda memilih menu untuk menyimpan list buku pinjam dalam bentuk file.");
                                fileManager.saveBukuPinjamFile(); // Memanggil fungsi save buku pinjam, berfungsi untuk menyimpan data buku yang sedang dipinjamkan ke file csv
                            }
                            case 4 -> {
                                System.out.println("Anda memilih menu untuk menyimpan list jumlah buku dalam bentuk file.");
                                fileManager.saveBukuJumlahFile(); // Memanggil fungsi save jumlah buku, berfungsi untuk menyimpan data jumlah buku ke file csv
                            }
                            case 5 -> {
                                System.out.println("Anda memilih menu untuk load file history buku.");
                                fileManager.loadHistoryBukuFile(); // Memanggil fungsi load history buku, yang berfungsi untuk import file csv ke database(arrayList)
                            }
                            case 6 -> {
                                System.out.println("Anda memilih menu untuk menyimpan history buku dalam bentuk file.");
                                fileManager.saveHistoryBukuFile(); // Memanggil fungsi save history buku, berfungsi untuk menyimpan data history buku ke file csv
                            }
                            case 7 -> {
                                System.out.println("Anda memilih menu untuk load file anggota.");
                                fileManager.loadAnggotaFile(); // Memanggil fungsi load anggota, berfungsi untuk import file csv ke database(arrayList)
                            }
                            case 8 -> {
                                System.out.println("Anda memilih menu untuk menyimpan list anggota dalam bentuk file.");
                                fileManager.saveAnggotaFile(); // Memanggil fungsi save anggota, berfungsi untuk menyimpan data anggota ke file csv
                            }
                            case 0 -> fileLooping = false; // Keluar dari menu manajemen anggota
                            case -1 -> {
                                System.out.println("Maaf! Input pilihan user harus berupa angka! Untuk mencoba lagi, silahkan tekan enter.");
                                scanner.nextLine();
                            }
                            default -> {
                                System.out.println("Maaf menu, tidak tersedia, silahkan tekan enter untuk mencoba lagi.");
                                scanner.nextLine();
                            }
                        }
                    }
                }
                case 0 -> { // Keluar dari aplikasi
                    System.out.println("Anda memilih untuk keluar dari aplikasi.");
                    System.out.println("Terimakasih atas waktunya.");
                    System.out.println("==================================================================================================================================");
                    loopingMenu = false;
                }
                case -1 -> {
                    System.out.println("Maaf! Input pilihan user harus berupa angka! Untuk mencoba lagi, silahkan tekan enter.");
                    scanner.nextLine();
                }
                default -> {
                    System.out.println("Maaf menu, tidak tersedia, silahkan tekan enter untuk mencoba lagi.");
                    scanner.nextLine();
                }
            }
        }
        scanner.close(); // Menutup scanner agar menghindari kebocoran sumber daya data
    }
    private static void tampilkanMenu() { // Menampilkan menu utama
        System.out.println("==================================================================================================================================");
        System.out.println("Berikut adalah beberapa menu tindakan yang tersedia, silahkan pilih sesuai nomor.");
        System.out.println("1. Manajemen Buku");
        System.out.println("2. Manajemen Anggota");
        System.out.println("3. Manajemen File");
        System.out.println("0. Keluar dari aplikasi");
        System.out.println("==================================================================================================================================");
        System.out.print("Pilihan Anda (0-2) : ");
    }
    private static void tampilkanBuku() { // Menampilkan menu manajemen buku
        System.out.println("==================================================================================================================================");
        System.out.println("Berikut adalah beberapa menu di manajemen buku yang tersedia, silahkan pilih sesuai nomor.");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Peminjaman Buku");
        System.out.println("3. Pengembalian Buku");
        System.out.println("4. Hapus Data Buku");
        System.out.println("5. Lihat Buku");
        System.out.println("0. Keluar dari menu buku");
        System.out.println("==================================================================================================================================");
        System.out.print("Pilihan Anda (0-5) : ");
    }
    private static void tampilkanLihatBuku() { // Menampilkan menu lihat buku
        System.out.println("==================================================================================================================================");
        System.out.println("Berikut adalah beberapa menu di menu lihat buku yang tersedia, silahkan pilih sesuai nomor.");
        System.out.println("1. Lihat buku yang tersedia");
        System.out.println("2. Lihat buku yang dipinjam");
        System.out.println("3. Lihat jumlah buku");
        System.out.println("4. Lihat history buku");
        System.out.println("0. Keluar dari menu lihat buku");
        System.out.println("==================================================================================================================================");
        System.out.print("Pilihan Anda (0-4) : ");
    }
    private static void tampilkanAnggota() { // Menampilkan menu manajemen anggota
        System.out.println("==================================================================================================================================");
        System.out.println("Berikut adalah beberapa menu di manajemen anggota yang tersedia, silahkan pilih sesuai nomor.");
        System.out.println("1. Tambah Anggota");
        System.out.println("2. Hapus Anggota");
        System.out.println("3. Lihat Anggota");
        System.out.println("0. Keluar dari menu anggota");
        System.out.println("==================================================================================================================================");
        System.out.print("Pilihan Anda (0-3) : ");
    }

    private static void tampilkanManajemenFile() { // Menampilkan menu manajemen file
        System.out.println("==================================================================================================================================");
        System.out.println("Berikut adalah beberapa menu di manajemen file yang tersedia, silahkan pilih sesuai nomor.");
        System.out.println("1. Load Buku");
        System.out.println("2. Simpan Buku");
        System.out.println("3. Simpan Pinjam Buku");
        System.out.println("4. Simpan List Jumlah Buku");
        System.out.println("5. Load History Buku");
        System.out.println("6. Simpan History Buku");
        System.out.println("7. Load Anggota");
        System.out.println("8. Simpan Anggota");
        System.out.println("0. Keluar dari menu file");
        System.out.println("==================================================================================================================================");
        System.out.print("Pilihan Anda (0-8) : ");
    }
    // Fungsi untuk mendapatkan pilihan user
    private static int getPilihanUser(Scanner scanner) {
        int pilihanUser;
        // Mengecek inputan pengguna apakah integer atau bukan
        if (scanner.hasNextInt()) {
            pilihanUser = scanner.nextInt();
            scanner.nextLine(); // Untuk membersihkan buffer
        } else {
            scanner.nextLine();
            pilihanUser = -1; // Set untuk nilai yang salah
        }
        return pilihanUser;
    }
    // Fungsi untuk menampilkan semua buku yang tersedia
    public static String listBuku(List<Book> books) {
        System.out.println("No\tJudul\t\t\t\t\t\t\t\t\t\t\t\t|Penulis\t\t\t\t\t\t\t\t|ISBN");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        String hasil = null;
        if (books.size() > 0) {
            for (int indeksBuku = 0; indeksBuku < books.size(); indeksBuku++) {
                String judul = books.get(indeksBuku).getJudul();
                String penulis = books.get(indeksBuku).getPenulis();
                String isbn = books.get(indeksBuku).getIsbn();
                String statusBuku = books.get(indeksBuku).getStatusBuku();
                int jumlah = books.get(indeksBuku).getJumlah();
                // Hanya status yang aktif dan jumlah yang bernilai 1, yang data nya akan ditampilkan. Aktif untuk data yang tidak terhapus dan 1 untuk buku yang tidak dipinjam. Begitupun sebaliknya.
                if (statusBuku.equalsIgnoreCase("aktif") && jumlah == 1) {
                    hasil = System.out.printf("%d.\t%s\t\t\t\t\t\t\t\t|%s\t\t\t\t\t\t\t|%s\n", indeksBuku + 1, judul, penulis, isbn).toString();
                }
            }
        } else {
            hasil = System.out.printf("Mohon maaf data buku belum tersedia. Silahkan tambahkan buku terlebih dahulu.\n").toString();
        }

        return hasil;
    }
    // Fungsi untuk menampilkan semua buku yang sedang dipinjam
    private static void listBukuPinjam(List<Book> books, List<Anggota> member) {
        boolean dataExist = false; // Setel awalnya ke false karena belum ada data yang ditemukan
        System.out.println("No\tJudul\t\t\t\t\t\t\t\t|Penulis\t\t\t\t\t\t|ISBN\t\t\t\t\t\t\t|Peminjam");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        for (int indeksBuku = 0; indeksBuku < books.size(); indeksBuku++) {
            String judul = books.get(indeksBuku).getJudul();
            String penulis = books.get(indeksBuku).getPenulis();
            String isbn = books.get(indeksBuku).getIsbn();
            String statusBuku = books.get(indeksBuku).getStatusBuku();
            int jumlah = books.get(indeksBuku).getJumlah();
            for (int indeksMember = 0; indeksMember < member.size(); indeksMember++) { // Looping member untuk mengambil nama peminjam
                if (books.get(indeksBuku).getIdAnggota() != null && books.get(indeksBuku).getIdAnggota().equalsIgnoreCase(member.get(indeksMember).getIdAnggota())) {
                    String namaMember = member.get(indeksMember).getNama(); // Ambil nama peminjam
                    // Fungsinya hanya untuk menampilkan data yang tidak terhapus(aktif) dan buku yang sedang dipinjam(0)
                    if (statusBuku.equalsIgnoreCase("aktif")) {
                        if (jumlah == 0) {
                            System.out.printf("%d.\t%s\t\t\t\t|%s\t\t\t\t\t|%s\t\t\t\t|%s\n", indeksBuku + 1, judul, penulis, isbn, namaMember);
                            dataExist = true; // Bila data ditemukan, maka akan diset nilainya menjadi true
                        }
                    }
                }
            }
        }
        if (!dataExist) { // Bila data tidak ditemukan, maka akan memberikan informasi data tidak ada
            System.out.println("Mohon maaf data buku yang dipinjam belum tersedia. Silahkan pinjam buku terlebih dahulu. Untuk kembali ke menu, silahkan ketik `keluar`.");
        }
    }
    // Fungsi untuk menampilkan jumlah berdasarkan nama buku
    private static void listJumlahBuku(List<Book> books) {
        // Menampilkan header untuk tabel
        System.out.println("No\tJudul\t\t\t\t\t\t\t\t\t\t\t\t|Penulis\t\t\t\t\t\t\t\t|Jumlah");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

        // Inisialisasi daftar untuk menyimpan informasi unik tentang judul, penulis, dan jumlah buku
        List<String> uniqueJuduls = new ArrayList<>();
        List<String> penulisJuduls = new ArrayList<>();
        List<Integer> judulJumlahs = new ArrayList<>();

        // Iterasi melalui daftar buku
        for (Book book : books) {
            String judul = book.getJudul();
            String penulis = book.getPenulis();
            String statusBuku = book.getStatusBuku();
            int jumlah = book.getJumlah();

            // Memproses hanya buku dengan status "aktif"
            if (statusBuku.equalsIgnoreCase("aktif")) {
                judul = judul.trim();

                // Memeriksa apakah judul buku sudah ada dalam daftar unik
                if (uniqueJuduls.contains(judul)) {
                    int index = uniqueJuduls.indexOf(judul);
                    judulJumlahs.set(index, judulJumlahs.get(index) + jumlah); // Menambahkan jumlah buku jika judul sudah ada
                } else {
                    // Menambahkan judul, penulis, dan jumlah buku baru ke dalam daftar
                    uniqueJuduls.add(judul);
                    penulisJuduls.add(penulis);
                    judulJumlahs.add(jumlah);
                }
            }
        }

        // Menampilkan informasi buku yang dihitung
        for (int indeksBuku = 0; indeksBuku < uniqueJuduls.size(); indeksBuku++) {
            String judul = uniqueJuduls.get(indeksBuku);
            String penulis = penulisJuduls.get(indeksBuku);
            int jumlah = judulJumlahs.get(indeksBuku);

            // Menampilkan baris informasi buku jika jumlahnya lebih dari 0
            if (jumlah > 0) {
                System.out.printf("%d.\t%s\t\t\t\t\t\t\t\t|%s\t\t\t\t\t\t\t|%d\n", indeksBuku + 1, judul, penulis, jumlah);
            }
        }
    }
    // Fungsi untuk menampilkan histori keluar masuk buku
    private static void historyBook(List<String> history) {
        if (history.size() != 0) { // Jika data history tidak ada, maka akan memberikan informasi bahwa data tidak ada
            for (int indeksBuku = 0; indeksBuku < history.size(); indeksBuku++) {
                System.out.printf("%d.\t%s\n", indeksBuku+1, history.get(indeksBuku));
            }
        } else {
            System.out.println("Mohon maaf, data history tidak tersedia. Silahkan lakukan terlebih dahulu peminjaman dan pengembalian buku.");
        }
    }
    // Fungsinya untuk menampilkan semua data anggota yang ada
    public static String  listAnggota(List<Anggota> member) {
        System.out.println("No\tID Anggota\t\t\t|Nama");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        String hasil = null;
        if (member.size() > 0) {
            for (int indeksAnggota = 0; indeksAnggota < member.size(); indeksAnggota++) {
                String idAnggota = member.get(indeksAnggota).getIdAnggota();
                String nama = member.get(indeksAnggota).getNama();
                String statusAnggota = member.get(indeksAnggota).getStatusAnggota();
                if (statusAnggota.equalsIgnoreCase("aktif")) { // Menampilkan hanya anggota yang berstatus aktif saja(anggota tidak dihapus)
                    hasil = System.out.printf("%d.\t%s\t\t\t\t|%s\n", indeksAnggota + 1, idAnggota, nama).toString();
                }
            }
        } else {
            hasil = System.out.printf("Mohon maaf data anggota belum tersedia. Silahkan tambahkan anggota terlebih dahulu.\n").toString();
        }
        return hasil;
    }
    // Fungsinya untuk menambahkan buku baru ke list books
    private static void tambahBukuBaru(Scanner scanner, List<Book> books) {
        boolean bukuLooping = false;
        while (!bukuLooping) { // Looping untuk menambahkan buku baru
            System.out.print("Silahkan inputkan judul buku : ");
            String judulBaru = scanner.nextLine();
            System.out.print("Silahkan inputkan penulis buku : ");
            String penulisBaru = scanner.nextLine();
            boolean isbnValid = false;
            String isbnSpasi = "";
            while (!isbnValid) { // Looping untuk inputan isbn bila isbn diinputkan selain 13 digit angka dan juga jika isbn sudah ada di data buku
                System.out.print("Silahkan inputkan ISBN buku (13 digit) : ");
                String isbn = scanner.nextLine();
                if (isbn.equalsIgnoreCase("keluar")) { // Kembali ke menu, bila pengguna tidak ingin melanjutkan inputan
                    bukuLooping = true;
                    break;
                } else {
                    isbnSpasi = isbn.replaceAll("\\s", ""); // Bila inputan ada spasi, maka spasi akan dihilangkan
                    if (!isbnSpasi.matches("^[0-9]{13}$")) { // Jika inputan isbn bukan berupa 13 digit angka, maka akan diberikan pesan kesalahan
                        System.out.println("Mohon maaf, input ISBN tidak boleh " + isbnSpasi.length() + " digit. Dan input ISBN harus berupa 13 digit angka. Silahkan coba kembali. Untuk kembali ke menu, silahkan ketik `keluar`.");
                    } else { // Jika inputan isbn sudah benar, maka isbn akan diformat dari seoerti ini 9786545636789 menjadi seperti ini 978-654-5636-78-9
                        StringBuilder formatted = new StringBuilder();
                        formatted.append(isbnSpasi.substring(0, 3)).append("-");
                        formatted.append(isbnSpasi.substring(3, 6)).append("-");
                        formatted.append(isbnSpasi.substring(6, 10)).append("-");
                        formatted.append(isbnSpasi.substring(10, 12)).append("-");
                        formatted.append(isbnSpasi.substring(12, 13));
                        isbnSpasi = formatted.toString();

                        boolean bookExist = cekBuku(books, isbnSpasi); // Memanggil fungsi cek buku untuk untuk mengecek apakah buku sudah ada atau belum di database
                        if (bookExist) { // Jika ada, maka akan diberikan pesan kesalahan dan pengguna diminta untuk menginputkan kembali isbn yang baru
                            System.out.println("Maaf, kode seri buku (ISBN) sudah ada. Silahkan inputkan ISBN yang baru. Untuk kembali ke menu, silahkan ketik `keluar`.");
                            isbnValid = false;
                        } else { // Jika tidak, maka data2 yang sudah diinputkan pengguna sebelumnya akan dimasukkan pada database books
                            tambahBuku(books, judulBaru, penulisBaru, isbnSpasi, 1, "aktif"); // Memanggil fungsi tambah buku
                            System.out.println("Selamat! Anda berhasil menambahkan buku baru. Berikut update datanya : ");
                            listBuku(books); // Memanggil fungsi lihat buku
                            isbnValid = true; // Inputan isbn tidak akan di loop kembali karena inputan sudah benar

                            System.out.print("Apakah Anda ingin menambahkan buku baru lagi (y/n) ? "); // Menanyakan pengguna apakah ingin menambahkan buku baru lagi?
                            String pilihanPengguna = scanner.nextLine();
                            if (!pilihanPengguna.equalsIgnoreCase("y")) {
                                bukuLooping = true; // Jika pilihan pengguna selain y, maka looping tambah buku baru akan dihentikan
                            }
                        }
                    }
                }
            }
        }
    }
    // Fungsinya untuk menambahkan anggota baru ke data member
    private static void tambahAnggotaBaru(Scanner scanner, List<Anggota> member) {
        boolean loopAnggota = false;
        while (!loopAnggota) { // Looping untuk menambahkan anggota baru
            System.out.print("Silahkan inputkan nama anggota : ");
            String namaBaru = scanner.nextLine();
            String idAnggota = generateIdAnggota(namaBaru); // Memanggil fungsi untuk mengenerate id sesuai dengan nama anggota yang diinputkan

            tambahAnggota(member, idAnggota, namaBaru, "aktif"); // Menambahkan anggota baru ke database member
            System.out.println("Selamat! Anda berhasil menambahkan anggota baru. Berikut update datanya : ");
            listAnggota(member); // Memanggil fungsi lihat anggota
            System.out.print("Apakah Anda ingin menambahkan anggota baru lagi (y/n) ? "); // Menanyakan pengguna apakah ingin menambahkan anggota lagi?
            String pilihanPengguna = scanner.nextLine();
            if (!pilihanPengguna.equalsIgnoreCase("y")) {
                loopAnggota = true; // Jika pilihan pengguna selain y, maka looping tambah anggota baru akan dihentikan
            }
        }
    }
    // Fungsinya untuk menyimpan variabel runningNumber agar bisa di loop-kan
    private static int runningNumber = 3;
    // Fungsinya untuk generate kode secara otomatis sesuai nama yang diinputkan pengguna
    private static String generateIdAnggota(String namaAnggota) {
        String kodeHuruf = namaAnggota.substring(0, Math.min(3, namaAnggota.length())).toUpperCase(); // Fungsinya untuk mengambil 3 huruf didepan kata nama(dan merubahnya menjadi uppercase)
        String generateIdAnggota = kodeHuruf + runningNumber; // Menambahkan kode huruf dan running number
        runningNumber++; // Menambah 1 running number setiap selesai generate kode

        return generateIdAnggota; // Mengembalikan hasil
    }
    // Fungsinya untuk menghapus buku di database dengan cara set status menjadi tidak aktif(soft delete)
    private static void hapusBuku(Scanner scanner, List<Book> books) {
        boolean hapusValid = false;
        boolean errorMessage = false;
        while (!hapusValid) { // Looping inputan bila terjadi hapus buku yang tidak ada
            listBuku(books); // Memanggil fungsi lihat buku
            System.out.print("Silahkan inputkan no buku yang ingin dihapus : ");
            String pilihanHapus = scanner.nextLine();

            if (pilihanHapus.equalsIgnoreCase("keluar")) {
                break;
            } else {
                for (int indeksBuku = 0; indeksBuku < books.size(); indeksBuku++) {
                    if (pilihanHapus.matches("^[0-9]+$")) { // Jika pengguna  menginputkan angka, maka data yang dihapus akan dicari berdasarkan index buku
                        int indexBuku = Integer.parseInt(pilihanHapus);
                        if (indexBuku - 1 == indeksBuku) { // Mengecek apakah inputan pengguna sama dengan data yang ada di database
                            books.get(indeksBuku).setStatusBuku("tidak aktif"); // Set status buku untuk metode soft delete
                            books.get(indeksBuku).setJumlah(0);
                            hapusValid = true; // Loop input akan berhenti karena data sudah sesuai
                            errorMessage = false; // Pesan kesalahan tidak akan ditampilkan karena data sudah sesuai
                            System.out.println("Selamat! Buku berhasil terhapus. Berikut update datanya : "); // Menampilkan pesan berhasil
                            listBuku(books); // Memanggil fungsi lihat buku
                            break;
                        } else { // Bila inputan pengguna tidak cocok dengan data yang di database, maka akan menampilkan pesan kesalahan
                            errorMessage = true;
                        }
                    } else {
                        if (books.get(indeksBuku).getJudul().equalsIgnoreCase(pilihanHapus)) { // Mengecek apakah inputan pengguna sama dengan data yang ada di database
                            books.get(indeksBuku).setStatusBuku("tidak aktif");
                            hapusValid = true;
                            errorMessage = false;
                            System.out.println("Selamat! Buku berhasil terhapus. Berikut update datanya : ");
                            listBuku(books);
                            break;
                        } else {
                            errorMessage = true;
                        }
                    }
                }

                if (errorMessage) { // Menampilkan pesan kesalahan bila error message = true
                    System.out.println("Maaf, buku belum tersedia. Silahkan pilih buku yang sudah ada. Untuk kembali ke menu, silahkan ketik `keluar`.");
                }

                System.out.print("Apakah Anda ingin menghapus buku lagi (y/n) ? "); // Menanyakan pengguna apakah ingin menghapus buku lagi?
                String pilihanPengguna = scanner.nextLine();
                if (pilihanPengguna.equalsIgnoreCase("y")) {
                    hapusValid = false; // Jika pilihan pengguna merupakan y, maka looping hapus buku akan dilanjutkan
                }
            }
        }
    }
    // Fungsinya untuk menghapus anggota di database dengan cara set status menjadi tidak aktif(soft delete)
    private static void hapusAnggota(Scanner scanner, List<Anggota> member) {
        boolean hapusValid = false;
        boolean errorMessage = false;
        while (!hapusValid) { // Looping inputan bila terjadi hapus anggota yang tidak ada
            listAnggota(member); // Memanggil fungsi lihat anggota
            System.out.print("Silahkan inputkan no anggota yang ingin dihapus : ");
            String pilihanHapus = scanner.nextLine();

            if (pilihanHapus.equalsIgnoreCase("keluar")) {
                break;
            } else {
                for (int indeksAnggota = 0; indeksAnggota < member.size(); indeksAnggota++) {
                    if (pilihanHapus.matches("^[0-9]+$")) { // Jika pengguna  menginputkan angka, maka data yang dihapus akan dicari berdasarkan index anggota
                        int indexAnggota = Integer.parseInt(pilihanHapus);
                        if (indexAnggota - 1 == indeksAnggota) { // Mengecek apakah inputan pengguna sama dengan data yang ada di database
                            member.get(indeksAnggota).setStatusAnggota("tidak aktif"); // Set status anggota untuk metode soft delete
                            hapusValid = true; // Loop input akan berhenti karena data sudah sesuai
                            errorMessage = false; // Pesan kesalahan tidak akan ditampilkan karena data sudah sesuai
                            System.out.println("Selamat! Anggota berhasil terhapus. Berikut update datanya : "); // Menampilkan pesan berhasil
                            listAnggota(member); // Memanggil fungsi lihat anggota
                            break;
                        } else { // Bila inputan pengguna tidak cocok dengan data yang di database, maka akan menampilkan pesan kesalahan
                            errorMessage = true;
                        }
                    } else {
                        // Mengecek apakah inputan pengguna sama dengan data yang ada di database
                        if (member.get(indeksAnggota).getNama().equalsIgnoreCase(pilihanHapus) || member.get(indeksAnggota).getIdAnggota().equalsIgnoreCase(pilihanHapus)) {
                            member.get(indeksAnggota).setStatusAnggota("tidak aktif");
                            hapusValid = true;
                            errorMessage = false;
                            System.out.println("Selamat! Anggota berhasil terhapus. Berikut update datanya : ");
                            listAnggota(member);
                            break;
                        } else {
                            errorMessage = true;
                        }
                    }
                }

                if (errorMessage) { // Menampilkan pesan kesalahan bila error message = true
                    System.out.println("Maaf, nama anggota tidak ada dalam data. Silahkan pilih nama anggota yang sudah ada. Untuk kembali ke menu, silahkan ketik `keluar`.");
                }

                System.out.print("Apakah Anda ingin menghapus anggota lagi (y/n) ? "); // Menanyakan pengguna apakah ingin menghapus anggota lagi?
                String pilihanPengguna = scanner.nextLine();
                if (pilihanPengguna.equalsIgnoreCase("y")) {
                    hapusValid = false; // Jika pilihan pengguna merupakan y, maka looping hapus anggota akan dilanjutkan
                }
            }
        }
    }
    // Fungsinya untuk mengecek apakah anggota ada atau tidak
    private static boolean cekAnggota(List<Anggota> member, String idAnggota) {
        boolean anggotaExist = false;
        for (int indeksAnggota = 0; indeksAnggota < member.size(); indeksAnggota++) {
            String idAnggotaCek = member.get(indeksAnggota).getIdAnggota();
            if (idAnggotaCek.equalsIgnoreCase(idAnggota)) {
                anggotaExist = true;
                break;
            }
        }
        return anggotaExist;
    }
    // Fungsinya untuk mengecek apakah buku ada atau tidak
    private static boolean cekBuku(List<Book> books, String isbnSpasi) {
        boolean bookExist = false;
        for (int indeksBuku = 0; indeksBuku < books.size(); indeksBuku++) {
            String isbnCek = books.get(indeksBuku).getIsbn();
            if (isbnCek.equalsIgnoreCase(isbnSpasi)) {
                bookExist = true;
                break;
            }
        }
        return bookExist;
    }
    // Fungsinya untuk membuat format waktu sekarang menyesuaikan dengan waktu Indonesia
    private static String formatWaktu() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Membuat objek SimpleDateFormat dengan timezone Asia/Jakarta dan locale Indonesia
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", new Locale("id", "ID"));
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        // Memformat Timestamp menjadi tanggal Indonesia
        String formattedDate = sdf.format(timestamp);
        // Menampilkan hasilnya
        return formattedDate;
    }
    // Fungsinya untuk meminjam buku di perpustakaan
    private static void pinjamBuku(Scanner scanner, List<Book> books, List<Anggota> member, List<String> history) {
        boolean loopPinjam = false;
        while (!loopPinjam) { // Looping untuk peminjaman buku lagi
            listBuku(books); // Memanggil fungsi lihat buku yang tersedia
            boolean idAnggotaValid = false;
            while (!idAnggotaValid) { // Looping untuk inputan id anggota
                System.out.print("Silahkan inputkan id anggota peminjam : ");
                String idAnggota = scanner.nextLine();
                if (idAnggota.equalsIgnoreCase("keluar")) {
                    loopPinjam = true;
                    break;
                } else {
                    boolean anggotaExist = cekAnggota(member, idAnggota); // Memanggil fungsi cek anggota
                    if (anggotaExist) { // Jika anggota ada dalam data, maka program akan dilanjutlan pada input isbn
                        boolean isbnValid = false;
                        while (!isbnValid) { // Looping untuk inputan isbn buku
                            System.out.print("Silahkan inputkan ISBN buku yang ingin dipinjam(hanya angka) : ");
                            String isbn = scanner.nextLine();

                            if (isbn.equalsIgnoreCase("keluar")) {
                                idAnggotaValid = true;
                                loopPinjam = true;
                                break;
                            } else {
                                String isbnSpasi = isbn.replaceAll("\\s", ""); // Bila inputan ada spasi, maka spasi akan dihilangkan
                                if (!isbnSpasi.matches("^[0-9]{13}$")) { // Jika inputan isbn bukan berupa 13 digit angka, maka akan diberikan pesan kesalahan
                                    System.out.println("Mohon maaf, input ISBN tidak boleh " + isbnSpasi.length() + " digit. Dan input ISBN harus berupa 13 digit angka. Silahkan coba kembali. Untuk kembali ke menu, silahkan ketik `keluar`.");
                                } else { // Jika inputan isbn sudah benar, maka isbn akan diformat dari seoerti ini 9786545636789 menjadi seperti ini 978-654-5636-78-9
                                    StringBuilder formatted = new StringBuilder();
                                    formatted.append(isbnSpasi.substring(0, 3)).append("-");
                                    formatted.append(isbnSpasi.substring(3, 6)).append("-");
                                    formatted.append(isbnSpasi.substring(6, 10)).append("-");
                                    formatted.append(isbnSpasi.substring(10, 12)).append("-");
                                    formatted.append(isbnSpasi.substring(12, 13));
                                    isbnSpasi = formatted.toString();
                                    isbnValid = true;

                                    boolean bookExist = cekBuku(books, isbnSpasi); // Memanggil fungsi cek buku
                                    if (bookExist) { // Jika buku ada, maka buku bisa dipinjam
                                        boolean validJumlah = false;
                                        for (int indeksBuku = 0; indeksBuku < books.size(); indeksBuku++) {
                                            String cekIsbn = books.get(indeksBuku).getIsbn();
                                            int cekJumlah = books.get(indeksBuku).getJumlah();
                                            if (cekIsbn.equalsIgnoreCase(isbnSpasi) && cekJumlah == 1) { // Cek apakah buku ada dan jumlah == 1(tidak dipinjamkan)
                                                String judul = books.get(indeksBuku).getJudul();
                                                int jumlah = books.get(indeksBuku).getJumlah();
                                                int kalkulasi = jumlah - 1; // Jumlah akan dikurangkan 1 agar jumlah menjadi 0(dipinjamkan)
                                                books.get(indeksBuku).setJumlah(kalkulasi); // Set jumlah menjadi 0
                                                for (int indeksMember = 0; indeksMember < member.size(); indeksMember++) {
                                                    if (idAnggota.equalsIgnoreCase(member.get(indeksMember).getIdAnggota())) {
                                                        String namaMember = member.get(indeksMember).getNama();
                                                        String members = member.get(indeksMember).getIdAnggota();
                                                        books.get(indeksBuku).setIdAnggota(members); // Set idAnggota peminjam pada database buku
                                                        // Menambahkan history bahwa buku dipinjamkan
                                                        history.add("Buku " + judul + " sedang dipinjam oleh " + namaMember + " di tanggal " + formatWaktu() + ".");
                                                        break;
                                                    }
                                                }
                                                System.out.println("Selamat! Anda berhasil meminjam buku " + judul + " di perpustakaan ini."); // Menampilkan pesan keberhasilan
                                                validJumlah = true;
                                                break;
                                            }
                                        }
                                        if (!validJumlah) { // Menampilkan pesan kesalahan bila jumlah nya 0(sedang dipinjam)
                                            System.out.println("Mohon maaf buku ini sedang dipinjam.");
                                        }
                                        System.out.print("Apakah Anda ingin meminjam buku lagi (y/n) ? "); // Menanyakan pengguna apakah ingin meminjam buku lagi?
                                        String pilihanPengguna = scanner.nextLine();
                                        if (!pilihanPengguna.equalsIgnoreCase("y")) {
                                            idAnggotaValid = true;
                                            loopPinjam = true;
                                            break;
                                        }
                                    } else {
                                        System.out.println("Mohon maaf buku dengan ISBN " + isbnSpasi + " tidak ada dalam data. Silahkan inputkan data yang sudah ada. Jika ingin kembali ke menu, ketik `keluar`.");
                                        isbnValid = false; // Melakukan looping input isbn, karena pengguna salah input isbn
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Mohon maaf anggota dengan id " + idAnggota + " tidak ada dalam data. Silahkan inputkan data yang sudah ada. Jika ingin kembali ke menu, ketik `keluar`.");
                    }
                }
            }
        }
    }
    // Fungsinya untuk mengembalikan buku di perpustakaan
    private static void pengembalianBuku(Scanner scanner, List<Book> books, List<String> history, List<Anggota> member) {
        boolean loopKembali = false;
        while (!loopKembali) { // Looping untuk pengembalian buku lagi
            listBukuPinjam(books, member); // Memanggil fungsi lihat buku yang dipinjam
            boolean isbnValid = false;
            while (!isbnValid) { // Looping untuk inputan isbn buku
                System.out.print("Silahkan inputkan ISBN buku yang ingin dipinjam(hanya angka) : ");
                String isbn = scanner.nextLine();

                if (isbn.equalsIgnoreCase("keluar")) {
                    loopKembali = true;
                    break;
                } else {
                    String isbnSpasi = isbn.replaceAll("\\s", ""); // Bila inputan ada spasi, maka spasi akan dihilangkan
                    if (!isbnSpasi.matches("^[0-9]{13}$")) { // Jika inputan isbn bukan berupa 13 digit angka, maka akan diberikan pesan kesalahan
                        System.out.println("Mohon maaf, input ISBN tidak boleh " + isbnSpasi.length() + " digit. Dan input ISBN harus berupa 13 digit angka. Silahkan coba kembali. Untuk kembali ke menu, silahkan ketik `keluar`.");
                    } else { // Jika inputan isbn sudah benar, maka isbn akan diformat dari seperti ini 9786545636789 menjadi seperti ini 978-654-5636-78-9
                        StringBuilder formatted = new StringBuilder();
                        formatted.append(isbnSpasi.substring(0, 3)).append("-");
                        formatted.append(isbnSpasi.substring(3, 6)).append("-");
                        formatted.append(isbnSpasi.substring(6, 10)).append("-");
                        formatted.append(isbnSpasi.substring(10, 12)).append("-");
                        formatted.append(isbnSpasi.substring(12, 13));
                        isbnSpasi = formatted.toString();
                        isbnValid = true;

                        boolean bookExist = cekBuku(books, isbnSpasi); // Memanggil fungsi cek buku
                        if (bookExist) { // Jika buku ada, maka buku bisa dipinjam
                            boolean cekPinjam = true;
                            for (int indeksBuku = 0; indeksBuku < books.size(); indeksBuku++) {
                                String cekIsbn = books.get(indeksBuku).getIsbn();
                                int jumlah = books.get(indeksBuku).getJumlah();
                                if (cekIsbn.equalsIgnoreCase(isbnSpasi)) { // Cek apakah isbn nya sama dengan data yang ada
                                    if (jumlah == 0) { // Cek apakah buku sedang dipinjamkan atau tidak
                                        String judul = books.get(indeksBuku).getJudul();;
                                        int kalkulasi = jumlah + 1; // Jika buku sedang dipinjamkan, maka jumlah ditambah 1 agar nilainya menjadi 1
                                        books.get(indeksBuku).setJumlah(kalkulasi); // Set jumlah buku menjadi 1(buku sudah tersedia lagi/buku sudah dikembalikan)
                                        books.get(indeksBuku).setIdAnggota(null); // Set idAnggota peminjam kembali menjadi null karena sudah tidak ada peminjam buku
                                        // Menambahkan history bahwa buku sudah dikembalikan
                                        history.add("Buku " + judul + " sudah dikembalikan" + " di tanggal " + formatWaktu() + ".");
                                        System.out.println("Selamat! Anda berhasil mengambalikan buku " + judul + " ke perpustakaan ini."); // Menampilkan pesan keberhasilan
                                        cekPinjam = true;
                                    } else { // Menampilkan bahwa buku dengan inputan isbn pengguna tidak sedang dipinjamkan, pengguna diminta untuk input ulang isbn
                                        cekPinjam = false;
                                        isbnValid = false;
                                    }
                                }
                            }
                            if (cekPinjam == false) { // Menampilkan pesan kesalahan bahwa buku tidak sedang dipinjamkan
                                System.out.println("Mohon maaf buku ini tidak dipinjamkan. Silahkan inputkan buku yang sedang dipinjam. Jika ingin kembali ke menu, ketik `keluar`.");
                            }
                            System.out.print("Apakah Anda ingin mengembalikan buku lagi (y/n) ? "); // Menanyakan pengguna apakah ingin meminjam buku lagi?
                            String pilihanPengguna = scanner.nextLine();
                            if (!pilihanPengguna.equalsIgnoreCase("y")) {
                                loopKembali = true;
                                break;
                            }
                        } else { // Menampilkan pesan kesalahan apabila isbn tidak sesuai dengan isbn yang ada di database, dan pengguna diminta untuk inputkan kembali isbn
                            System.out.println("Mohon maaf buku dengan ISBN " + isbnSpasi + " tidak ada dalam data. Silahkan inputkan data yang sudah ada. Jika ingin kembali ke menu, ketik `keluar`.");
                            isbnValid = false;
                        }
                    }
                }
            }
        }
    }
}
