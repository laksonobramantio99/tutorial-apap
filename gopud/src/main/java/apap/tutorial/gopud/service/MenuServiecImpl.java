package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiecImpl implements MenuService {
    @Autowired
    private MenuDb menuDb;

    @Override
    public void addMenu(MenuModel menu) {
        menuDb.save(menu);
    }

    @Override
    public List<MenuModel> findAllMenuByIdRestoran(Long idRestoran) {
        return menuDb.findByRestoranIdRestoran(idRestoran);
    }

    @Override
    public Optional<MenuModel> getMenuById(Long id) {
        return menuDb.findById(id);
    }

    @Override
    public MenuModel changeMenu(MenuModel menuModel) {
        //mengamil obj menu yang ingin diubah
        MenuModel targetMenu = menuDb.findById(menuModel.getId()).get();

        try {
            targetMenu.setNama(menuModel.getNama());
            targetMenu.setHarga(menuModel.getHarga());
            targetMenu.setDeskripsi(menuModel.getDeskripsi());
            targetMenu.setDurasiMasak(menuModel.getDurasiMasak());
            menuDb.save(targetMenu);
            return targetMenu;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void deleteMenu(MenuModel menu) {
        menuDb.delete(menu);
    }
}
