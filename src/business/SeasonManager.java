package business;

import dao.SeasonDAO;
import entity.Season;

import java.util.List;

public class SeasonManager {
    private SeasonDAO seasonDAO;

    public SeasonManager() {
        seasonDAO = new SeasonDAO();
    }

    public List<Season> getAllSeasons() {
        return seasonDAO.getAllSeasons();
    }

    public Season getSeasonById(int id) {
        return seasonDAO.getSeasonById(id);
    }

    public void addSeason(Season season) {
        seasonDAO.addSeason(season);
    }

    public void updateSeason(Season season) {
        seasonDAO.updateSeason(season);
    }

    public void deleteSeason(int id) {
        seasonDAO.deleteSeason(id);
    }
}
