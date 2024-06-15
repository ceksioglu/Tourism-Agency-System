package business;

import dao.PensionTypeDAO;
import entity.PensionType;

import java.util.List;

public class PensionTypeManager {
    private PensionTypeDAO pensionTypeDAO;

    public PensionTypeManager() {
        pensionTypeDAO = new PensionTypeDAO();
    }

    public List<PensionType> getAllPensionTypes() {
        return pensionTypeDAO.getAllPensionTypes();
    }

    public PensionType getPensionTypeById(int id) {
        return pensionTypeDAO.getPensionTypeById(id);
    }

    public void addPensionType(PensionType pensionType) {
        pensionTypeDAO.addPensionType(pensionType);
    }

    public void updatePensionType(PensionType pensionType) {
        pensionTypeDAO.updatePensionType(pensionType);
    }

    public void deletePensionType(int id) {
        pensionTypeDAO.deletePensionType(id);
    }
}
