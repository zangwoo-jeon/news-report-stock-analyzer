package com.newstoss.stock.application;

import com.newstoss.stock.application.port.in.GetIndiceUseCase;

public class IndiceQueryService implements GetIndiceUseCase {
    @Override
    public Float getIndicePrice(String market) {
        return 0f;
    }
}
