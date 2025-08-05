package com.app.campionsl.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchResultDTO {

    private List<Long> winners;
    private List<Long> losers;

}
