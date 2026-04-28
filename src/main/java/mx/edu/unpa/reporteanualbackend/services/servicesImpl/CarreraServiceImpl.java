package mx.edu.unpa.reporteanualbackend.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.carrera.CarreraRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.carrera.CarreraResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Carrera;
import mx.edu.unpa.reporteanualbackend.exceptions.ConflictException;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.CarreraMapper;
import mx.edu.unpa.reporteanualbackend.repositories.CarreraRepository;
import mx.edu.unpa.reporteanualbackend.services.CarreraService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarreraServiceImpl implements CarreraService {

    private final CarreraRepository carreraRepository;
    private final CarreraMapper carreraMapper;

    @Override
    @Transactional
    public CarreraResponseDTO crear(CarreraRequestDTO dto) {
        if (carreraRepository.existsByClave(dto.getClave()))
            throw new ConflictException("Ya existe una carrera con la clave: " + dto.getClave());
        Carrera carrera = carreraMapper.toEntity(dto);
        return carreraMapper.toResponse(carreraRepository.save(carrera));
    }

    @Override
    @Transactional(readOnly = true)
    public CarreraResponseDTO obtenerPorId(Integer id) {
        return carreraMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarreraResponseDTO> obtenerTodas() {
        return carreraRepository.findAll().stream()
                .map(carreraMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public CarreraResponseDTO actualizar(Integer id, CarreraRequestDTO dto) {
        Carrera carrera = findOrThrow(id);
        carreraMapper.updateFromDto(dto, carrera);
        return carreraMapper.toResponse(carreraRepository.save(carrera));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!carreraRepository.existsById(id))
            throw new ResourceNotFoundException("Carrera no encontrada con id: " + id);
        carreraRepository.deleteById(id);
    }

    private Carrera findOrThrow(Integer id) {
        return carreraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada con id: " + id));
    }
}