package com.grostech.util.id;

import java.util.UUID;

public class RandomUUIDProvider implements IdProvider {

    public String provideId() {
        return UUID.randomUUID().toString();
    }
}
