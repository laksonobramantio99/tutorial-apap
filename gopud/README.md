## Tutorial 2

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

---

## Tutorial 3

### What I have learned today
1. Pada class MenuDb, terdapat method **findByRestoranIdRestoran**, apakah kegunaan dari method tersebut?
    > Method tersebut digunakan untuk mengambil/mendapatkan semua menu yang ada di sebuah restoran dengan ID restoran sebagai argumennya.

2. Pada class RestoranController, jelaskan perbedaan method **addRestoranFormPage** dan **addRestoranSubmit**?
    > Method **addRestoranFormPage** memiliki request method GET dan digunakan ketika kita mengakses path `/restoran/add` pada browser, sehingga method ini digunakan untuk menampilan form isian "Tambah Restoran". Sedangkan method **addRestoranSubmit** memiliki request method POST dan digunakan untuk melakukan penyimpanan calon RestoranModel ke database ketika kita telah mengklik tombol submit pada form isian "Tambah Restoran".

3. Jelaskan apa kegunaan dari JPA Repository?
    > JPA Repository digunakan untuk memudahkan proses CRUD (CREATE, READ, UPDATE, dan DELETE) pada database.

4. Sebutkan dan jelaskan di bagian kode mana sebuah relasi antara **RestoranModel** dan **MenuModel** dibuat?
    > `RestoranModel.java`
    ![](https://i.ibb.co/pnqKVGn/image.png)
    Pada RestoranModel, ia membentuk relasi one to many dengan objek MenuModel, karena setiap 1 Restoran bisa memiliki beberapa Menu. 
    <br><br>
    `MenuModel.java`
    ![](https://i.ibb.co/g6tQChW/image.png)
    Pada MenuModel, ia membentuk relasi many to one dengan objek RestoranModel. Setiap objek Menu akan me-reference ke column idRestoran pada tabel Restoran, yang menandakan bahwa Menu tersebut adalah milik Restoran dengan ID tersebut.

5. Jelaskan kegunaan **FetchType.LAZY**, **CascadeType.ALL**, dan **FetchType.EAGER**
    > Pada Java Persistence API (JPA), ketika kita membuat relasi antar table pada database, kita bisa membuat tipe fetch untuk object reference yang dikaitkan tersebut dengan 2 mode, yaitu LAZY atau EAGER.
    FetchType.LAZY berfungsi untuk melakukan fetching object hanya ketika ingin digunakan, sedangkan FetchType.EAGER digunakan jika kita ingin langsung me-load object yang reference tersebut.
    <br><br>
    Lalu untuk CascadeType.ALL, object yang diberikan CascadeType.ALL memiliki
	semua tipe Cascade yaitu PERSIST, REMOVE, REFRESH, MERGE, dan DETACH. Object
	tersebut juga memiliki semua tipe Hibernate yaitu REPLICATE, SAVE_UPDATE, 
	dan LOCK. Dengan menggunakan CascadeType.ALL memudahkan untuk melakukan
	CRUD (CREATE, READ, UPDATE, dan DELETE) pada suatu data.

## What I did not understand
Cara kerja JPA Repository dan penggunakan Serializable.

---

## Tutorial 4 - Thymeleaf

### What I have learned today
1. Jelaskan yang anda pelajari dari melakukan latihan nomor 2, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 2.
    > Pada latihan nomor 2 saya mempelajari tentang cara meng-injeksi suatu variable ke template html walau melalui fragment, jadi dengan hal tersebut saya dapat membuat halaman html yang dinamis. <br>
    Untuk pengerjaannya, pertama saya mengubah file fragment.html, saya tambahkan `th:text="${page_title}"` pada tempat dimana ia akan berada. Lalu saya tambahkan statement `model.addAttribute("page_title", "Add Menu");` pada setiap method di Controller namun valuenya disesuaikan dengan nama page-nya.

2. Jelaskan yang anda pelajari dari latihan nomor 3, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 3.
    > Pada latihan nomor 3 ini saya mengubah method add menu pada MenuController agar dapat melakukan multiple input, kemudian saya juga membuat method addRow dan deleteRow untuk melakukan penambahan atau pengurangan row input serta mengubah method POST addMenu untuk melakukan penyimpanan multiple menu ke database. Lalu pada file html form-add-menu saya membuat tombol untuk melakukan penambahan dan penghapusan row yang akan memanggil method pada MenuController yang sebelumnya telah saya buat dan terakhir untuk field-nya saya memanfaatkan iteration status.

3. Jelaskan perbedaan `th:include` dan `th:replace`.
    > `th:include` akan meng-insert/memasukkan fragment sebagai body pada host tag yang dituju tetapi tidak memasukkan fragment tag terluarnya. Sedangkan `th:replace` akan mengganti (substitute) host tag yang dituju dengan fragment yang akan digunakan.

4. Jelaskan bagaimana penggunaan `th:object` beserta tujuannya.
    > `th:objek` digunakan untuk menentukan objek yang terikat dengan data formulir yang dikirimkan.

---

## Tutorial 5 - Testing

### What I have learned today

#### Latihan
No. 1 - Before
![](https://i.ibb.co/q5jbn9C/before1.jpg)
After
![](https://i.ibb.co/z62T2BV/after1.jpg)
<br><br>
No. 2 - Before
![](https://i.ibb.co/DbFqsZp/before2.jpg)
After
![](https://i.ibb.co/wzssQT8/after2.jpg)
<br><br>
No. 3 - After
![](https://i.ibb.co/jrpqgQv/image.png)

#### Pertanyaan
1. Jelaskan bagian mana saja dari test yang dibuat pada latihan no 2 adalah given, when, dan and then. <br>
    Berikut bagian **given**. Bagian ini akan melakukan inisiasi apa-apa saja yang akan dibutuhkan.
    ```
    RestoranModel restoran = generateDummyRestoranModel(1);

    List<MenuModel> listMenu = new ArrayList<>();
    for (int i=1; i<=2; i++) {
        MenuModel menu = generateDummyMenuModel((long) i);
        menu.setRestoran(restoran);
        listMenu.add(menu);
    }
    restoran.setListMenu(listMenu);

    when (restoranService.getRestoranByIdRestoran(1L)).thenReturn(Optional.of(restoran));
    when (menuService.getListMenuOrderByHargaAsc(1L)).thenReturn(listMenu); 
    ```

    Berikut bagian **when**. Bagian ini akan melakukan interaksi dengan controller.
    ```
    mockMvc.perform(get("/restoran/view")
            .param("idRestoran", "1")
    )
    ```

    Berikut bagian **and then**. Bagian ini akan melakukan pengecekan terhadap interaksi yang diharapkan.
    ```
            .andExpect(status().isOk())
            .andExpect(content().string(Matchers.containsString("Informasi Restoran")))
            .andExpect(model().attribute("page_title", is("View Restoran")))
            .andExpect(model().attribute("resto", is(restoran)));
    verify(restoranService, times(1)).getRestoranByIdRestoran(1L);
    verify(menuService, times(1)).getListMenuOrderByHargaAsc(1L);
    ```

2. Jelaskan perbedaan line coverage dan logic coverage. <br>
    > Line coverage mengecek berapa banyak baris code yang tercakupi (dijalankan) sedangkan logic coverage akan mengecek atau code meng-handle logic code yang berupa branching (seperti if else), sehingga nantinya logic coverage dapat membantu penambahan line coverage secara keseluruhan. 

3. Pada keadaan ideal, apa yang seharusnya dibuat terlebih dahulu, code atau unit test? Mengapa seperti itu? Apa akibatnya jika urutannya dibalik, adakah risiko tak terlihat yang mungkin terjadi?<br>
    > Yang paling ideal adalah membuat unit test-nya terlebih dahulu, karena dengan dibuatnya unit test terlebih dahulu maka programmer tahu apa yang harus dibuat dan sampai mana batasan-batasan program yang perlu diimplementasi.

4. [Bonus] Jelaskan mengapa pada latihan no 3, main class spring tidak diikutsertakan ke dalam perhitungan coverage? Apa saja yang dapat menyebabkan suatu class dapat di-exclude dari perhitungan code coverage.<br>
    > Karena main class spring merupakan source code bawaan dari framework yang sama sekali tidak kita ubah, namun ada beberapa baris code pada main class tersebut yang tidak akan tercover ketika kita menjalankan aplikasi spring secara normal, sehingga lebih baik main class tersebut di-exclude dari perhitungan coverage.
