package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;

    @Qualifier("restoranServiceImpl")
    @Autowired
    RestoranService restoranService;

    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.GET)
    private String addProductFormPage(@PathVariable(value = "idRestoran") Long idRestoran, Model model) {
        MenuModel menu = new MenuModel();
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        List<MenuModel> menuList = new ArrayList<>();
        menuList.add(menu);

        restoran.setListMenu(menuList);
        menu.setRestoran(restoran);

        model.addAttribute("idRestoran", idRestoran);
        model.addAttribute("resto", restoran);
        model.addAttribute("menu", menu);
        model.addAttribute("page_title", "Add Menu");

        return "form-add-menu";
    }

    @RequestMapping(value = "menu/add", method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute RestoranModel restoran, Model model) {
        RestoranModel restoranModel = restoranService.getRestoranByIdRestoran(restoran.getIdRestoran()).get();

        for (MenuModel menu: restoran.getListMenu()) {
            menu.setRestoran(restoranModel);
            menuService.addMenu(menu);
        }

        model.addAttribute("page_title", "Add Menu");

        return "add-menu";
    }

    @RequestMapping(value = "menu/add", method = RequestMethod.POST, params = {"addRow"})
    public String addRow(@ModelAttribute RestoranModel restoran, Model model) {
        if (restoran.getListMenu() == null) {
            restoran.setListMenu(new ArrayList<MenuModel>());
        }

        restoran.getListMenu().add(new MenuModel());
        model.addAttribute("resto", restoran);
        model.addAttribute("page_title", "Add Menu");

        return "form-add-menu";
    }

    @RequestMapping(value = "menu/add", method = RequestMethod.POST, params = {"removeRow"})
    public String removeRow(@ModelAttribute RestoranModel restoran, HttpServletRequest req, Model model) {
        Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        restoran.getListMenu().remove(rowId.intValue());

        model.addAttribute("resto", restoran);
        model.addAttribute("page_title", "Add Menu");
        
        return "form-add-menu";
    }

    @RequestMapping(value = "menu/change/{id}", method = RequestMethod.GET)
    public String changeMenuFormPage(@PathVariable Long id, Model model) {
        MenuModel existingMenu = menuService.getMenuById(id).get();
        model.addAttribute("menu", existingMenu);
        model.addAttribute("page_title", "Change Menu");

        return "form-change-menu";
    }

    @RequestMapping(value = "menu/change/{id}", method = RequestMethod.POST)
    public String changeMenuFormSubmit(@PathVariable Long id,
                                       @ModelAttribute MenuModel menu,
                                       Model model
        ) {
        MenuModel newMenuData = menuService.changeMenu(menu);
        model.addAttribute("menu", newMenuData);
        model.addAttribute("page_title", "Change Menu");

        return "change-menu";
    }

    @RequestMapping(value = "/menu/delete/{id}", method = RequestMethod.GET)
    public String deleteMenu(@PathVariable Long id, Model model) {
        MenuModel menu = menuService.getMenuById(id).get();

        model.addAttribute("page_title", "Delete Menu");
        model.addAttribute("menu", menu);
        menuService.deleteMenu(menu);

        return "delete-menu";
    }

    @RequestMapping(value = "/menu/delete", method = RequestMethod.POST)
    private String delete(@ModelAttribute RestoranModel restoran, Model model) {
        for (MenuModel menu : restoran.getListMenu()) {
            menuService.deleteMenu(menu);
        }
        model.addAttribute("page_title", "Delete Model");
        return "delete-menu";
    }
}
