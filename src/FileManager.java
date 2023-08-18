import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    // Fungsinya untuk import file csv ke dalam array(database)
    public static void loadBukuFile() {
        try {
            File file = new File("dataCsv/book/listBuku.csv"); // Mengakses file listBuku.csv pada path yang telah ditentukan
            Library library = new Library(); // Asumsi bahwa Library memiliki metode getBooks() yang mengembalikan daftar buku

            // Membuka dan membungkus FileReader dengan BufferedReader untuk membaca file
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine(); // Melewati baris header

            // Membaca setiap baris dari file, satu per satu
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Memisahkan baris menjadi bagian-bagian menggunakan koma sebagai pemisah
                String judul = parts[1].trim(); // Menghilangkan spasi ekstra
                String penulis = parts[2].trim();
                String isbn = parts[3].trim();
                int jumlah = Integer.parseInt(parts[4].trim());
                String statusBuku = parts[5].trim();

                boolean isbnExists = false;
                // Memeriksa apakah ISBN buku sudah ada dalam daftar buku di library
                for (Book book : library.getBook()) {
                    if (book.getIsbn().equals(isbn)) {
                        isbnExists = true;
                        break;
                    }
                }
                // Jika ISBN belum ada dalam daftar, tambahkan buku baru ke library buku(database)
                if (!isbnExists) {
                    Book book = new Book(judul, penulis, isbn, jumlah, statusBuku, null);
                    library.getBook().add(book);
                }

            }
            // Menutup BufferedReader dan FileReader untuk menghindari kebocoran sumber daya
            br.close();
            fr.close();
        } catch (FileNotFoundException error) { // Jika file listBUku.csv tidak ditemukan
            System.out.println("Mohon maaf, file belum ada. Silahkan save terlebih dahulu file nya.");
        } catch (IOException e){ // Jika terjadi error pada file
            e.printStackTrace();
        }
    }
    // Fungsinya untuk menyimpan data buku di array(database) ke file csv
    public static void saveBukuFile() {
        try {
            Library library = new Library(); // Asumsi bahwa Library memiliki metode getBooks() yang mengembalikan daftar buku
            File file = new File("dataCsv/book/listBuku.csv");

            // Membuka dan membungkus FileWriter dengan BufferedWriter untuk menulis teks kedalam file
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            // Tulis header csv
            bw.write("No,Judul,Penulis,ISBN,Jumlah,Status Buku,ID Anggota");
            bw.newLine();

            // Tulis data buku
            int no = 1;
            for (Book book : library.getBook()) { // Mengambil data dari array
                    bw.write( // Menulis data ke file dengan format sesuai header
                            no + "," +
                                   book.getJudul() + "," +
                                   book.getPenulis() + "," +
                                   book.getIsbn() + "," +
                                   book.getJumlah() + "," +
                                   book.getStatusBuku() + "," +
                                   book.getIdAnggota()
                    );
                    bw.newLine(); // Untuk membuat baris baru, jika data pertama sudah dimasukkan
                    no++;
            }

            bw.close();
            fw.close();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // Fungsinya untuk menyimpan data buku pinjam di array(database) ke file csv. Buku pinjam tidak ada fungsi load data dikarenakan data sudah ada dalam loadBukuFile
    public static void saveBukuPinjamFile() {
        try {
            Library library = new Library(); // Asumsi bahwa Library memiliki metode getBooks() yang mengembalikan daftar buku
            File file = new File("dataCsv/book/listBukuPinjam.csv");

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            // Tulis CSV header
            bw.write("No,Judul,Penulis,ISBN,Peminjam");
            bw.newLine();

            // Tulis data buku
            int no = 1;
            for (Book book : library.getBook()) { // Mengambil data buku
                for (Anggota member : library.getMember()) { // Mengambil data member agar nama peminjam buku bisa diambil
                    if (book.getIdAnggota() != null && book.getJumlah() == 0 && book.getIdAnggota().equalsIgnoreCase(member.getIdAnggota())) { // Pengecekan jika idAnggota dalam data buku tidak berupa null, dan jumlah buku = 0(sedang dipinjam)
                        bw.write( // Menulis data ke file dengan format sesuai header
                                no + "," +
                                        book.getJudul() + "," +
                                        book.getPenulis() + "," +
                                        book.getIsbn() + "," +
                                        member.getNama()
                        );
                        bw.newLine();
                        no++;
                    }
                }
            }

            bw.close();
            fw.close();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // Fungsinya untuk menyimpan data jumalh buku yang sama di array(database) ke file csv. Buku jumlah tidak ada fungsi load data dikarenakan data sudah ada dalam loadBukuFile
    public static void saveBukuJumlahFile() {
        try {
            Library library = new Library();
            File file = new File("dataCsv/book/listJumlahBuku.csv");

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("No,Judul,Penulis,Jumlah"); // Tulis CSV header
            bw.newLine();// Pindah ke baris baru setelah menulis header

            // List untuk menyimpan judul unik, penulis unil dan jumlah buku dalam satu judul
            List<String> uniqueJuduls = new ArrayList<>();
            List<String> uniquePenuliss = new ArrayList<>();
            List<Integer> judulJumlahs = new ArrayList<>();

            int no = 1; // Nomor urutan data dalam file

            for (Book book : library.getBook()) { // Loop melalui setiap buku dalam perpustakaan
                String judul = book.getJudul();
                String penulis = book.getPenulis();
                String statusBuku = book.getStatusBuku();
                int jumlah = book.getJumlah();

                if (statusBuku.equalsIgnoreCase("aktif")) { // Cek apakah status buku adalah "aktif" (tidak terhapus di database)
                    judul = judul.trim();// Menghilangkan spasi tambahan dari judul
                    if (uniqueJuduls.contains(judul)) { // Jika judul sudah ada dalam list unik, tambahkan jumlah ke judulJumlahs
                        int index = uniqueJuduls.indexOf(judul);
                        judulJumlahs.set(index, judulJumlahs.get(index) + jumlah);
                    } else { // Jika judul belum ada dalam list unik, tambahkan ke semua list unik
                        uniqueJuduls.add(judul);
                        uniquePenuliss.add(penulis);
                        judulJumlahs.add(jumlah);
                    }
                }
            }
            // Loop melalui list judul unik untuk menulis data ke file
            for (int i = 0; i < uniqueJuduls.size(); i++) {
                String judul = uniqueJuduls.get(i);
                String penulis = uniquePenuliss.get(i);
                int jumlah = judulJumlahs.get(i);
                // Menulis data ke file dengan format sesuai header
                bw.write(
                        no + "," +
                                judul + "," +
                                penulis + "," +
                                jumlah
                );
                bw.newLine();
                no++; // Menambah nomor urutan data
            }

            bw.close();
            fw.close();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Fungsinya untuk import file csv ke dalam array(database)
    public static void loadHistoryBukuFile() {
        try {
            File file = new File("dataCsv/book/listHistoryBuku.csv");
            Library library = new Library(); // Asumsi bahwa Library memiliki metode getBooks() yang mengembalikan daftar buku

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine(); // Melewati baris header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String historyBook = parts[1].trim();

                boolean historyExists = false;
                // Cek apakah history sudah ada atau belum dalam data
                for (String history : library.getHistory()) {
                    if (history.equalsIgnoreCase(historyBook)) {
                        historyExists = true;
                        break;
                    }
                }

                if (!historyExists) { // Jika history tidak ada dalam data, maka history akan ditambahkan pada database history
                    library.getHistory().add(historyBook);
                }

            }

            br.close();
            fr.close();
        } catch (FileNotFoundException error) {
            System.out.println("Mohon maaf, file belum ada. Silahkan save terlebih dahulu file nya.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // Fungsinya untuk menyimpan data history buku di array(database) ke file csv
    public static void saveHistoryBukuFile() {
        try {
            Library library = new Library(); // Asumsi bahwa Library memiliki metode getBooks() yang mengembalikan daftar buku
            File file = new File("dataCsv/book/listHistoryBuku.csv");

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            // Tulis header csv
            bw.write("No,History Buku");
            bw.newLine();

            // Tulis data buku
            int no = 1;
            for (String history : library.getHistory()) {
                bw.write( // Menulis data ke file dengan format sesuai header
                        no + "," +
                                history
                );
                bw.newLine();
                no++;
            }

            bw.close();
            fw.close();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // Fungsinya untuk import file csv ke dalam array(database)
    public static void loadAnggotaFile() {
        try {
            File file = new File("dataCsv/member/listAnggota.csv");
            Library library = new Library(); // Asumsi bahwa Library memiliki metode getBooks() yang mengembalikan daftar anggota

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine(); // Melewati baris header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String idAnggota = parts[1].trim();
                String namaAnggota = parts[2].trim();
                String statusAnggota = parts[3].trim();

                boolean idAnggotaExists = false;
                // Cek apakah anggota sudah ada atau belum dalam data
                for (Anggota member : library.getMember()) {
                    if (member.getIdAnggota().equals(idAnggota)) {
                        idAnggotaExists = true;
                        break;
                    }
                }

                if (!idAnggotaExists) { // Jika anggota tidak ada dalam data, maka anggota akan ditambahkan pada database anggota(member)
                    Anggota member = new Anggota(idAnggota, namaAnggota, statusAnggota);
                    library.getMember().add(member);
                }
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException error) {
            System.out.println("Mohon maaf, file belum ada. Silahkan save terlebih dahulu file nya.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // Fungsinya untuk menyimpan data anggota di array(database) ke file csv
    public static void saveAnggotaFile() {
        try {
            Library library = new Library(); // Asumsi bahwa Library memiliki metode getBooks() yang mengembalikan daftar anggota
            File file = new File("dataCsv/member/listAnggota.csv");

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            // Tulis CSV header
            bw.write("No,Id Anggota,Nama Anggota,Status Anggota");
            bw.newLine();

            // Tulis data member
            int no = 1;
            for (Anggota member : library.getMember()) {
                bw.write( // Menulis data ke file dengan format sesuai header
                        no + "," +
                                member.getIdAnggota() + "," +
                                member.getNama()  + "," +
                                member.getStatusAnggota()
                );
                bw.newLine();
                no++;
            }

            bw.close();
            fw.close();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}