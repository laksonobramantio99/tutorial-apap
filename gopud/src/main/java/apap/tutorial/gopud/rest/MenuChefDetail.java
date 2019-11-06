package apap.tutorial.gopud.rest;

import apap.tutorial.gopud.model.MenuModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuChefDetail {
    private String nama;

    @JsonProperty("spesialis")
    private String spesialis;

    @JsonProperty("experience_in_years")
    private String experienceInYears;

    @JsonProperty("menus")
    private List<MenuModel> menus;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(String experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public List<MenuModel> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuModel> menus) {
        this.menus = menus;
    }
}

