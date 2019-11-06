package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.rest.MenuChefDetail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MenuRestService {
    MenuModel createMenu(MenuModel menu);
    List<MenuModel> retrieveListMenu();
    MenuModel getMenuByIdMenu(Long idMenu);
    MenuModel changeMenu(Long idMenu, MenuModel menuUpdate);
    void deleteMenu(Long idMenu);
    Mono<MenuChefDetail> getMenuByChef(String chef);
}
