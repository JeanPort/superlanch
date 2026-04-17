package com.jean.superlanch.addon;

import com.jean.superlanch.common.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddonService {

    private final AddonRepository repository;

    public AddonService(AddonRepository repository) {
        this.repository = repository;
    }

    public AddonResponse create(CreateAddonRequest request){
        validateNameUniqueness(request.name(), null);
        var addon = Addon.create(request.name(), request.price());
        addon = repository.save(addon);
        return AddonResponse.toAddonResponse(addon);
    }

    private void validateNameUniqueness(String name, Long id) {
        boolean taken = (id == null)
                ? repository.existsByName(name)
                : repository.existsByNameAndIdNot(name, id);

        if (taken) {
            throw new AddonNameAlreadyExistsException();
        }
    }
}
