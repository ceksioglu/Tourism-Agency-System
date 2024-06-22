package business;

import dao.SeasonDAO;
import entity.Season;

import java.util.List;

/**
 * Business logic class for managing seasons.
 */
public class SeasonManager {
    private final SeasonDAO seasonDAO;

    /**
     * Constructs a new SeasonManager instance and initializes the SeasonDAO.
     */
    public SeasonManager() {
        seasonDAO = new SeasonDAO();
    }

    /**
     * Retrieves all seasons.
     *
     * @return a list of all seasons
     */
    public List<Season> getAllSeasons() {
        return seasonDAO.getAllSeasons();
    }

    /**
     * Retrieves a season by its unique identifier.
     *
     * @param id the unique identifier of the season
     * @return the season with the specified id, or null if not found
     */
    public Season getSeasonById(int id) {
        return seasonDAO.getSeasonById(id);
    }

    /**
     * Adds a new season.
     *
     * @param season the season to add
     */
    public void addSeason(Season season) {
        seasonDAO.addSeason(season);
    }

    /**
     * Updates an existing season.
     *
     * @param season the season to update
     */
    public void updateSeason(Season season) {
        seasonDAO.updateSeason(season);
    }

    /**
     * Deletes a season by its unique identifier.
     *
     * @param id the unique identifier of the season to delete
     */
    public void deleteSeason(int id) {
        seasonDAO.deleteSeason(id);
    }
}
