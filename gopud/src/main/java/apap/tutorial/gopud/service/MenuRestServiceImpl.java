package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDb;
import apap.tutorial.gopud.rest.Setting;
import apap.tutorial.gopud.rest.MenuChefDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class MenuRestServiceImpl implements MenuRestService {
    private final WebClient webClient;

    @Autowired
    private MenuDb menuDb;

    @Override
    public MenuModel createMenu(MenuModel menu) {
        return menuDb.save(menu);
    }

    @Override
    public List<MenuModel> retrieveListMenu() {
        return menuDb.findAll();
    }

    @Override
    public MenuModel getMenuByIdMenu(Long idMenu) {
        Optional<MenuModel> menu = menuDb.findById(idMenu);
        if (menu.isPresent()) {
            return menu.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public MenuModel changeMenu(Long idMenu, MenuModel menuUpdate) {
        MenuModel menu = getMenuByIdMenu(idMenu);
        menu.setNama(menuUpdate.getNama());
        menu.setHarga(menuUpdate.getHarga());
        menu.setDeskripsi(menuUpdate.getDeskripsi());
        menu.setDurasiMasak(menuUpdate.getDurasiMasak());
        return menuDb.save(menu);
    }

    @Override
    public void deleteMenu(Long idMenu) {
        menuDb.delete(getMenuByIdMenu(idMenu));
    }

    // Customer Service
    public MenuRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.menuUrl).build();
    }

    @Override
    public Mono<MenuChefDetail> getMenuByChef(String chef) {
        return this.webClient.get().uri("/api/v1/restoran/chef?nama=" + chef)
                .retrieve().bodyToMono(MenuChefDetail.class);
    }
}
