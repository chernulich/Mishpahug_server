package Application.models.holyday;

import Application.entities.HoliDayEntity;
import Application.repo.HolyDayRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HolyDayModel implements IHolyDayModel {

    @Autowired
    HolyDayRepository holyDayRepository;

    @Override
    public List<HoliDayEntity> getByReligion(Integer religionId) {
        return null;
    }

    @Override
    public List<HoliDayEntity> getByYear(Integer year) {
        return null;
    }

    @Override
    public void updateFromServer() {

    }
}
