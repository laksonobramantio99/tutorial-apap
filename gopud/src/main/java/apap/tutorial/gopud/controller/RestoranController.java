package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class RestoranController {
    @Qualifier("restoranServiceImpl")
    @Autowired
    private RestoranService restoranService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/restoran/add", method = RequestMethod.GET)
     public String addRestoranFormPage(Model model) {
        RestoranModel newRestoran = new RestoranModel();
        model.addAttribute("restoran", newRestoran);
        return "form-add-restoran";
    }

    @RequestMapping(value = "/restoran/add", method = RequestMethod.POST)
    public String addRestoranSubmit(@ModelAttribute RestoranModel restoran, Model model) {
        restoranService.addRestoran(restoran);
        model.addAttribute("namaResto", restoran.getNama());
        return "add-restoran";
    }

    @RequestMapping(path = "/restoran/view", method = RequestMethod.GET)
    public String view(
            @RequestParam(value = "idRestoran") Long idRestoran, Model model
            ) {
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();

        List<MenuModel> menuList = menuService.getListMenuOrderByHargaAsc(restoran.getIdRestoran());
        restoran.setListMenu(menuList);

        model.addAttribute("resto", restoran);

        return "view-restoran";

//        RestoranModel restoran;
//
//        try {
//            restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
//        }
//        catch (NoSuchElementException e) {
//            model.addAttribute("idRestoranFromParam", idRestoran);
//            return "error-restoran-tidakditemukan";
//        }
//
//        model.addAttribute("resto", restoran);
//
//        List<MenuModel> menuList = menuService.findAllMenuByIdRestoran(restoran.getIdRestoran());
//        model.addAttribute("menuList", menuList);
//
//        return "view-restoran";
    }

    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.GET)
    public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model) {
        RestoranModel restoran;

        try {
            restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        }
        catch (NoSuchElementException e) {
            model.addAttribute("idRestoranFromParam", idRestoran);
            return "error-restoran-tidakditemukan";
        }

        model.addAttribute("restoran", restoran);
        return "form-change-restoran";
    }

    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.POST)
    public String changeRestoranFormSubmit(@PathVariable Long idRestoran,
                                           @ModelAttribute RestoranModel restoran,
                                           Model model
                                           ) {
        RestoranModel newRestoranData = restoranService.changeRestoran(restoran);
        model.addAttribute("restoran", newRestoranData);

        return "change-restoran";
    }

    // Latihan No.1
    // URL mapping view-all
    @RequestMapping("/restoran/view-all")
    public String viewall(Model model) {

        // Mengambil semua objek RestoranModel yang ada
        List<RestoranModel> listRestoran = restoranService.getRestoranListOrderByNama();

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("restoList", listRestoran);

        // Return view template
        return "viewall-restoran";
    }

    @RequestMapping(value = "/restoran/delete/{id}", method = RequestMethod.GET)
    public String deleteRestoran(@PathVariable Long id, Model model) {
        RestoranModel restoran;

        try {
            restoran = restoranService.getRestoranByIdRestoran(id).get();
        }
        catch (NoSuchElementException e) {
            model.addAttribute("idRestoranFromParam", id);
            return "error-restoran-tidakditemukan";
        }

        model.addAttribute("restoran", restoran);

        List<MenuModel> menuList = menuService.findAllMenuByIdRestoran(restoran.getIdRestoran());
        if (menuList.size() != 0) { // jika jumlah menunya tidak 0
            return "error-delete-restoran";
        }

        restoranService.deleteRestoran(restoran);
        return "delete-restoran";
    }

    //==================

//    // URL Mapping add
//    @RequestMapping("/restoran/add")
//    public String add(
//            // Request Parameter untuk diproses
//            @RequestParam(value = "idRestoran", required = true) String idRestoran,
//            @RequestParam(value = "nama", required = true) String nama,
//            @RequestParam(value = "alamat", required = true) String alamat,
//            @RequestParam(value = "nomorTelepon", required = true) Integer nomorTelepon,
//            Model model
//            ) {
//
//        // Membuat objek RestoranModel
//        RestoranModel restoran = new RestoranModel(idRestoran, nama, alamat, nomorTelepon);
//
//        // Memanggil service addRestoran
//        restoranService.addRestoran(restoran);
//
//        // Add variabel nama restoran ke "namaResto" untuk dirender
//        model.addAttribute("namaResto", nama);
//
//        // Return view template
//        return "add-restoran";
//    }
//
//    //URL Mapping view
//    @RequestMapping("/restoran/view")
//    public String viewWithRequestParam(
//            // Request Parameter untuk dipass
//            @RequestParam(value = "idRestoran") String idRestoran, Model model
//            ) {
//
//        // Mengambil objek RestoranModel yang dituju
//        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//
//        if (restoran == null) {
//            model.addAttribute("idRestoranFromParameter", idRestoran);
//            return "error-idTidakDitemukan";
//        }
//
//        // Add model restoran ke "resto" untuk dirender
//        model.addAttribute("resto", restoran);
//
//        // Return view template
//        return "view-restoran";
//    }
//
//    // URL mapping viewAll
//    @RequestMapping("/restoran/viewall")
//    public String viewall(Model model) {
//
//        // Mengambil semua objek RestoranModel yang ada
//        List<RestoranModel> listRestoran = restoranService.getRestoranList();
//
//        // Add model restoran ke "resto" untuk dirender
//        model.addAttribute("restoList", listRestoran);
//
//        // Return view template
//        return "viewall-restoran";
//    }
//
//    @RequestMapping("/restoran/view/id-restoran/{idRestoran}")
//    public String viewWithPathVariable(
//            // Path Variable untuk dipass
//            @PathVariable(value = "idRestoran") String idRestoran, Model model
//            ) {
//
//        // Mengambil objek RestoranModel yang dituju
//        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//
//        if (restoran == null) {
//            model.addAttribute("idRestoranFromParameter", idRestoran);
//            return "error-idTidakDitemukan";
//        }
//
//        // Add model restoran ke "resto" untuk dirender
//        model.addAttribute("resto", restoran);
//
//        // Return view template
//        return "view-restoran";
//    }
//
//    @RequestMapping("/restoran/update/id-restoran/{idRestoran}/nomor-telepon/{nomorTelepon}")
//    public String updateTelephoneNumber(
//            @PathVariable(value = "idRestoran") String idRestoran,
//            @PathVariable(value = "nomorTelepon") Integer nomorTelepon,
//            Model model
//            ) {
//
//        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//
//        if (restoran == null) {
//            model.addAttribute("idRestoranFromParameter", idRestoran);
//            return "error-idTidakDitemukan";
//        }
//
//        restoran.setNomorTelepon(nomorTelepon);
//
//        model.addAttribute("resto", restoran);
//
//        return "update-telephone-number";
//    }
//
//    @RequestMapping("/restoran/delete/id/{idRestoran}")
//    public String deleteRestoran(
//            @PathVariable(value = "idRestoran") String idRestoran,
//            Model model
//            ) {
//
//        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//
//        if (restoran == null) {
//            model.addAttribute("idRestoranFromParameter", idRestoran);
//            return "error-idTidakDitemukan";
//        }
//
//        restoranService.getRestoranList().remove(restoran);
//        model.addAttribute("idRestoran", idRestoran);
//
//        return "delete-restoran";
//    }
}
