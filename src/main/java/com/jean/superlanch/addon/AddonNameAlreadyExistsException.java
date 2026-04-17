package com.jean.superlanch.addon;

import com.jean.superlanch.common.exception.NameAlreadyExistsException;

public class AddonNameAlreadyExistsException extends NameAlreadyExistsException {

    private static final String MSG_ADDON_NAME_EXIST = "Já existe um addon com este nome";

    public AddonNameAlreadyExistsException(String message) {
        super(message);
    }

    public AddonNameAlreadyExistsException() {
        super(MSG_ADDON_NAME_EXIST);
    }
}
