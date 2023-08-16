// Model untuk anggota, dengan berisi id anggota, nama dan status anggota. Dan ini adalah contoh encapsulation
public class Anggota {
    // Deklarasi variabel
    private String idAnggota;
    private String nama;
    private String statusAnggota;

    // Fungsinya untuk menambahkan data ke database
    public Anggota(String idAnggota, String nama, String statusAnggota) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.statusAnggota = statusAnggota;
    }

    // Getter untuk id anggota, nama dan status anggota. Setter hanya untuk status anggota
    public String getIdAnggota() {
        return idAnggota;
    }

    public String getNama() {
        return nama;
    }

    public String getStatusAnggota() {
        return statusAnggota;
    }

    public void setStatusAnggota(String statusAnggota) {
        this.statusAnggota = statusAnggota;
    }
}
