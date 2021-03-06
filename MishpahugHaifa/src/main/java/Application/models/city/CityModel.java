package Application.models.city;

import Application.entities.CityEntity;
import Application.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CityModel implements ICityModel {
    @Autowired
    CityRepository cityRepository;

    @Override
    public CityEntity getById(Integer id) {
        return null;
    }

    @Override
    public CityEntity add(CityEntity data) {
        return null;
    }

    @Override
    public CityEntity remove(Integer id) {
        return null;
    }

    @Override
    public CityEntity updateName(Integer id, String name) {
        return null;
    }

    @Override
    public CityEntity getByName(String name) {
        return cityRepository.getByName(name);
    }

    @Override
    public List<CityEntity> getByCountry(Integer countryId) {
        return null;
    }
}
