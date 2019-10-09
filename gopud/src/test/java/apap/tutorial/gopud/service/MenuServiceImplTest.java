package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.MenuDb;
import apap.tutorial.gopud.repository.RestoranDb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {

    @InjectMocks
    MenuService menuService = new MenuServiecImpl();

    @InjectMocks
    RestoranService restoranService = new RestoranServiceImpl();

    @Mock
    MenuDb menuDb;

    @Mock
    RestoranDb restoranDb;

    // To create dummy restoran with id
    public RestoranModel createDummyRestoranModel(Long id) {
        RestoranModel dummyRestoran = new RestoranModel();
        dummyRestoran.setIdRestoran(id);
        return dummyRestoran;
    }

    // To create dummy menu with id
    public MenuModel createDummyMenuModel(Long id) {
        MenuModel dummyMenu = new MenuModel();
        dummyMenu.setId(id);
        return dummyMenu;
    }

    @Test
    public void addMenuTest() {
        MenuModel newMenu = new MenuModel();
        newMenu.setNama("cumi rebus");

        menuService.addMenu(newMenu);
        verify(menuDb, times(1)).save(newMenu);
    }

    @Test
    public void findAllMenuByIdRestoranTest() {
        RestoranModel newRestoran = createDummyRestoranModel(1L);
        MenuModel newMenu = createDummyMenuModel(1L);
        newMenu.setRestoran(newRestoran);

        restoranService.addRestoran(newRestoran);
        menuService.addMenu(newMenu);

        menuService.findAllMenuByIdRestoran(1L);
        verify(menuDb, times(1)).findByRestoranIdRestoran(1L);
    }

    @Test
    public void getMenuByIdTest() {
        MenuModel newMenu = createDummyMenuModel(1L);
        menuService.addMenu(newMenu);

        menuService.getMenuById(1L);
        verify(menuDb, times(1)).findById(1L);
    }

    @Test
    public void changeMenuTest() {
        MenuModel newMenu = createDummyMenuModel(2L);
        newMenu.setNama("menu baru");
        newMenu.setHarga(BigInteger.valueOf(99));
        newMenu.setDeskripsi("desc");
        newMenu.setDurasiMasak(10);
        when (menuDb.findById(2L)).thenReturn(Optional.of(newMenu));

        menuService.changeMenu(newMenu);
        verify(menuDb, times(1)).findById(newMenu.getId());
        verify(menuDb, times(1)).save(newMenu);
    }

    @Test
    public void changeMenuTestWithNullPointerError() {
        MenuModel newMenu = createDummyMenuModel(2L);
        newMenu.setNama("menu baru");
        newMenu.setHarga(BigInteger.valueOf(99));
        newMenu.setDeskripsi("desc");
        newMenu.setDurasiMasak(10);
        when (menuDb.findById(2L)).thenReturn(Optional.of(newMenu));
        when (menuDb.save(newMenu)).thenThrow(NullPointerException.class);

        menuService.changeMenu(newMenu);
        verify(menuDb, times(1)).findById(newMenu.getId());
        verify(menuDb, times(1)).save(newMenu);
    }

    @Test
    public void deleteMenuTest() {
        MenuModel newMenu = createDummyMenuModel(1L);
        menuService.addMenu(newMenu);

        menuService.deleteMenu(newMenu);
        verify(menuDb, times(1)).delete(newMenu);
    }

    @Test
    public void getListMenuOrderByHargaAscTest() {
        RestoranModel newRestoran = createDummyRestoranModel(1L);
        MenuModel newMenu = createDummyMenuModel(1L);
        newMenu.setRestoran(newRestoran);

        restoranService.addRestoran(newRestoran);
        menuService.addMenu(newMenu);

        menuService.getListMenuOrderByHargaAsc(1L);
        verify(menuDb, times(1)).findByRestoranIdRestoranOrderByHargaAsc(1L);
    }


}
