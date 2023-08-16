// Model untuk buku, dengan berisi judul, penulis, isbn, jumlah, dan status buku. Dan ini adalah contoh encapsulation
public class Book {
    // Deklarasi variabel
    private String judul;
    private String penulis;
    private String isbn;
    private int jumlah;
    private String statusBuku;

    private String idAnggota;

    // Fungsinya untuk menambahkan data ke database
    public Book(String judul, String penulis, String isbn, int jumlah, String statusBuku, String idAnggota) {
        this.judul = judul;
        this.penulis = penulis;
        this.isbn = isbn;
        this.jumlah = jumlah;
        this.statusBuku = statusBuku;
        this.idAnggota = idAnggota;
    }

    // Getter untuk judul, penulis, isbn, jumlah, dan status buku. Setter untuk jumlah dan status buku.
    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getStatusBuku() {
        return statusBuku;
    }

    public void setStatusBuku(String statusBuku) {
        this.statusBuku = statusBuku;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }
}