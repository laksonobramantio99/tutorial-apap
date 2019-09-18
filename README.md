# Tutorial APAP
## Authors
* **laksono.bramantio** - *1706984650* - *C*

---
## Tutorial 1

### What I have learned today
#### Github
1. Apa itu Issue Tracker? Masalah apa yang dapat diselesaikan dengan Issue Tracker?
    > Issue tracker adalah salah satu fitur pada github yang memunginkan kita 
    membuat/melaporkan suatu isu pada repo github kita. Dengan membuat/melaporkan isu 
    yang ada, diri kita sendiri dan teman-teman kolaborator dapat mengetahui apa yang 
    harus dikerjakan pada repo tersebut.

2. Apa perbedaan dari `git merge` dan `git merge --squash`?
    > Perbedaanya adalah `git merge` akan melakukan merge (penggabungan code) dengan branch 
    yang dituju dan semua commit yang dilakukan dari branch sumber akan ditambahkan 
    semua ke log tanpa disatukan (di-squash) sedangkan `git merge --squash` akan melakukan 
    merge code juga, namun semua commit yang dilakukan akan disatukan/digabungkan 
    menjadi 1 commit.

#### Spring
3. Apa itu library & dependency?
    > Library adalah kumpulan code yang terkumpul dalam sebuah module atau package yang 
    nantinya dapat di-import atau di-reuse ke program lain. <br/>
    Dependency adalah library-library yang dibutuhkan dalam membuat sebuah project 
    program/aplikasi/software.

4. Apa itu Maven? Mengapa kita perlu menggunakan Maven?
    > Maven adalah build tool yang digunakan dalam membuat proyek program Java. Maven menggunakan konsep Project Object Model (POM), POM tersebut berisi informasi dan konfigurasi yang digunakan Maven untuk membuat project. Pada dasarnya POM adalah sebuath XML File yang terdapat di dalam project Maven dan di dalam File inilah konfigurasi dari project kita berada. <br/>
    sumber: [https://medium.com/@acep.abdurohman90/mengenal-maven-sebagai-java-build-tools-5ba752f75812](https://medium.com/@acep.abdurohman90/mengenal-maven-sebagai-java-build-tools-5ba752f75812)

5. Apa alternatif dari Maven?
    > Selain Maven, tools lain yang dapat digunakan yaitu gradle, jenkins, dll. 

### What I did not understand
Saya masih sedikit bingung. Saya membuat program `hitungumur` di branch `feat/tutorial-1-hitung-umur` tetapi saat saya pindah ke branch master, folder hitungumur sudah ada, tapi isi di dalam foldernya tidak selengkap yang ada pada branch `feat/tutorial-1-hitung-umur`. Apakah memang saya melakukan suatu kesalahan? hehe
- [ ] Kenapa saya harus belajar APAP?
- [x] Kenapa?
Karena sebagai mahasiswa IT, saya harus memiliki kemampuan programming yang baik dan bisa 
membuat aplikasi berskala perusahaan dengan baik dan benar. hehe

---
## Tutorial 2 - Gopud

### What I have learned today
1. Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut: 
http://localhost:8080/restoran/add?idRestoran=1&nama=PanyuFC&alamat=Kantin%20Fasilkom&nomorTelepo=14022 
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
    > Terjadi error, karena template html-nya belum diimplementasikan.

2. Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut: 
http://localhost:8080/restoran/add?idRestoran=2&nama=KentukuFC&alamat=Kantin%20FIK
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
    > Terjadi error, karena parameter nomor telepon tidak ada, sedangkan pada source code yang dibuat, parameter nomor telepon itu wajib ada (required), sehingga ketika dijalankan terjadi error.

3. Jika Papa APAP ingin melihat restoran PanyuFC, link apa yang harus diakses?
    > Papa APAP bisa mengakses link ini [http://localhost:8080/restoran/view?idRestoran=1](http://localhost:8080/restoran/view?idRestoran=1) atau [http://localhost:8080/restoran/view/id-restoran/1](http://localhost:8080/restoran/view/id-restoran/1)

4. Tambahkan 1 contoh restoran lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/restoran/viewall, apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.
    > Ia akan menampilkan semua restoran yang telah ditambahkan. Berikut gambarnya ... ![Screenshot dari /restoran/viewall](https://i.ibb.co/LZnL4Zs/image.png)