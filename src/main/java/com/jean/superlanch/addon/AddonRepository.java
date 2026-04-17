package com.jean.superlanch.addon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddonRepository extends JpaRepository<Addon, Long> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
