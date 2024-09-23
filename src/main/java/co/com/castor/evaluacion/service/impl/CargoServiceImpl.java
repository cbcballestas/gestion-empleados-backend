package co.com.castor.evaluacion.service.impl;

import co.com.castor.evaluacion.model.CargoEntity;
import co.com.castor.evaluacion.repository.CargoRepository;
import co.com.castor.evaluacion.repository.IGenericRepo;
import co.com.castor.evaluacion.service.ICargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl extends CrudImpl<CargoEntity, Integer> implements ICargoService {

    private final CargoRepository cargoRepository;
    @Override
    protected IGenericRepo<CargoEntity, Integer> getRepo() {
        return cargoRepository;
    }
}
